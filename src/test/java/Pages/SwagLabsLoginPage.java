package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SwagLabsLoginPage extends Base{

    @FindBy(id="user-name")
    WebElement userName;

    @FindBy(id="password")
    WebElement password;

    @FindBy(xpath = "//head[@title='Swag Labs']")
    WebElement titleText;

    @FindBy(id="login-button")
    WebElement btnLogin;


    public SwagLabsLoginPage(WebDriver driver) {
        super(driver);
    }



    public void setUserName(String strUserName){
        userName.sendKeys(strUserName);
    }

    public void setPassword(String strPassword){password.sendKeys(strPassword);}

    public void clickBtnLogin(){
        btnLogin.click();
    }

    public boolean isConnected() {
        System.out.println("sadfasdf" + getDriver().getTitle());
        return getDriver().getTitle().equalsIgnoreCase("Swag Labs");


    }

    public void login(String userName,String pasword){
        this.setUserName(userName);
        this.setPassword(pasword);
        this.clickBtnLogin();

    }

}
