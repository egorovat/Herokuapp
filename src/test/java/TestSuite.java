import org.assertj.core.api.Assertions;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.List;


public class TestSuite extends JUnitHTMLReporter {

    private WebDriver driver;
    private UserActions action;

    @Before
    public void beforeTest() throws Exception {
        System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        action = new UserActions(driver);
    }

    @After
    public void afterTest() throws Exception { driver.close(); }

    @Test
    public void loginSuccessTest() {

        action.loginAs("tomsmith", "SuperSecretPassword!");
        String loginMessage = action.getLoginResultMessage();
        Assertions.assertThat(loginMessage).isEqualTo("You logged into a secure area!");
    }

    @Test
    public void loginFailedUserNameTest() {

        action.loginAs("invalidUserName", "SuperSecretPassword!");
        String loginMessage = action.getLoginResultMessage();
        Assertions.assertThat(loginMessage).isEqualTo("Your username is invalid!");
    }

    @Test
    public void loginFailedPasswordTest() {

        action.loginAs("tomsmith", "invalidPassword");
        String loginMessage = action.getLoginResultMessage();
        Assertions.assertThat(loginMessage).isEqualTo("Your password is invalid!");
    }

    @Test
    public void userNameShownAtHoverTest(){

        driver.get("http://the-internet.herokuapp.com/hovers");
        List<WebElement> avatarPictures = driver.findElements(By.className("figure"));
        Actions action  =  new Actions(driver);

        for(int i=0; i<avatarPictures.size(); i++) {

            action.moveToElement(avatarPictures.get(i)).build().perform();
            WebElement userName = driver.findElement(By.xpath("//h5[text()='name: user" + (i+1) + "']"));

            Assertions.assertThat(userName.isDisplayed()).isTrue();
        }
    }

    @Test
    public void sortOrderVerificationTest(){

        driver.get("http://the-internet.herokuapp.com/tables");
        WebElement sortTrigger = driver.findElement(By.cssSelector("span.last-name"));

        //initiate sort order
        sortTrigger.click();
        Assertions.assertThat(action.isTableSortedByLastName()).isTrue();

        //change sort order
        sortTrigger.click();
        Assertions.assertThat(action.isTableSortedByLastName()).isTrue();
    }
}
