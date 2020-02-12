import com.google.common.collect.Comparators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import java.util.List;

public class TablePage {

    @FindBy(xpath="//table[@id='table2']//span[@class='last-name']/parent::th")
    WebElement sortOrder;

    @FindBy(xpath="//table[@id='table2']//td[@class='last-name']")
    List<WebElement> lastNamesList;

    @FindBy(css="span.last-name")
    WebElement lastNameColumnHeader;

    public TablePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void clickSortByLastName(){

        lastNameColumnHeader.click();
    }

    public boolean isTableSortedByLastName(){

        String order =  sortOrder.getAttribute("class");

        switch (order) {
            case "header headerSortDown":
                return isSortAscending(lastNamesList);
            case "header headerSortUp":
                return isSortDescending(lastNamesList);
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
