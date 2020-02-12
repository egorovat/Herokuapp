import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import java.util.List;

public class HoversPage {

    Actions action;

    @FindBy(className="figure")
    List<WebElement> avatarPictures;

    @FindBy(id="content")
    WebElement content;

    HoversPage(WebDriver driver){
        action  =  new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void mouseOverAvatarNumber(int index) {

        action.moveToElement(avatarPictures.get(index-1)).build().perform();
    }

    public boolean hoveredAvatarNameIs(String name){

        return content.findElement(By.xpath("//h5[text()='name: " + name + "']")).isDisplayed();
    }
}
