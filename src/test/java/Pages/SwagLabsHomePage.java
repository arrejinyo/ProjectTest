package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SwagLabsHomePage extends Base{


    WebDriver driver;

    @FindBy(id="react-burger-menu-btn")
    WebElement btnBurguerMenu;

    public SwagLabsHomePage(WebDriver driver) { super(driver);}




    public boolean isLogged(){ return    btnBurguerMenu.isDisplayed();}

}