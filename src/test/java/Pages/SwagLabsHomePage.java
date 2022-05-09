package Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SwagLabsHomePage {

    WebDriver driver;

    @FindBy(id="react-burger-menu-btn")
    WebElement btnBurguerMenu;

    public SwagLabsHomePage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }



    public boolean isLogged(){
        return    btnBurguerMenu.isDisplayed();

    }

}