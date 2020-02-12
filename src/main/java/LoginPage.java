import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

public class LoginPage {

    @FindBy(xpath="//button[@type='submit']")
    WebElement loginButton;

    @FindBy(id="username")
    WebElement userName;

    @FindBy(id="password")
    WebElement password;

    @FindBy(css="div.flash")
    WebElement loginResultMessage;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void typeUserName(String name){

        userName.sendKeys(name);
    }

    public void typePassword(String pass){

        password.sendKeys(pass);
    }

    public void clickLoginButtun(){

        loginButton.click();
    }

    public void loginAs(String name, String pass) {

        typeUserName(name);
        typePassword(pass);
        clickLoginButtun();
    }

    public String getLoginResultMessage(){

        return loginResultMessage.getText().trim().replace("\n×", "");
    }
}
