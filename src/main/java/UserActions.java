import com.google.common.collect.Comparators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UserActions {

    private WebDriver driver;

    public UserActions(WebDriver driver){
        this.driver = driver;
    }

    public void loginAs(String userName, String password) {

        driver.get("http://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public String getLoginResultMessage(){

        return driver.findElement(By.cssSelector("div.flash")).getText().trim().replace("\n" +
                "×", "");
    }

    public boolean isTableSortedByLastName(){

        WebElement table = driver.findElement(By.cssSelector("#table2"));
        String sortOrder =  table.findElement(By.xpath("//span[@class='last-name']/parent::th")).getAttribute("class");
        List<WebElement> lastNames = table.findElements(By.cssSelector("td.last-name"));

        switch (sortOrder) {
            case "header headerSortDown":
                return isSortAscending(lastNames);
            case "header headerSortUp":
                return isSortDescending(lastNames);
            default:
                return false;
        }
    }

    private static boolean isSortAscending (List<WebElement> names){
        return Comparators.isInStrictOrder(names, (a, b)->a.getText().compareTo(b.getText()));
    }

    private static boolean isSortDescending (List<WebElement> names){
        return Comparators.isInStrictOrder(names, (a, b)->b.getText().compareTo(a.getText()));
    }
}
