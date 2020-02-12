import org.assertj.core.api.Assertions;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSuite extends JUnitHTMLReporter {

    private WebDriver driver;
    private LoginPage loginPage;
    private TablePage tablePage;
    private HoversPage hoversPage;

    @Before
    public void beforeTest() throws Exception {
        System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
    }

    @After
    public void afterTest() throws Exception { driver.close(); }

    @Test
    public void loginSuccessTest() {

        loginPage = new LoginPage(driver);
        driver.get("http://the-internet.herokuapp.com/login");

        loginPage.loginAs("tomsmith", "SuperSecretPassword!");
        Assertions.assertThat(loginPage.getLoginResultMessage()).isEqualTo("You logged into a secure area!");
    }

    @Test
    public void loginFailedUserNameTest() {

        loginPage = new LoginPage(driver);
        driver.get("http://the-internet.herokuapp.com/login");

        loginPage.loginAs("invalidUserName", "SuperSecretPassword!");
        Assertions.assertThat(loginPage.getLoginResultMessage()).isEqualTo("Your username is invalid!");
    }

    @Test
    public void loginFailedPasswordTest() {

        loginPage = new LoginPage(driver);
        driver.get("http://the-internet.herokuapp.com/login");

        loginPage.loginAs("tomsmith", "invalidPassword");
        Assertions.assertThat(loginPage.getLoginResultMessage()).isEqualTo("Your password is invalid!");
    }

    @Test
    public void userNameShownAtHoverTest(){

        hoversPage = new HoversPage(driver);
        driver.get("http://the-internet.herokuapp.com/hovers");

        hoversPage.mouseOverAvatarNumber(1);
        Assertions.assertThat(hoversPage.hoveredAvatarNameIs("user1")).isTrue();

        hoversPage.mouseOverAvatarNumber(2);
        Assertions.assertThat(hoversPage.hoveredAvatarNameIs("user2")).isTrue();

        hoversPage.mouseOverAvatarNumber(3);
        Assertions.assertThat(hoversPage.hoveredAvatarNameIs("user3")).isTrue();
    }

    @Test
    public void sortOrderVerificationTest(){

        tablePage = new TablePage(driver);
        driver.get("http://the-internet.herokuapp.com/tables");

        //initiate sort order
        tablePage.clickSortByLastName();
        Assertions.assertThat(tablePage.isTableSortedByLastName()).isTrue();

        //change sort order
        tablePage.clickSortByLastName();
        Assertions.assertThat(tablePage.isTableSortedByLastName()).isTrue();
    }
}
