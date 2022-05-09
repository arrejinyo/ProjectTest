package test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Pages.SwagLabsHomePage;
import Pages.SwagLabsLoginPage;



public class Test_Login{

    String driverPath = "C:/Users/sebastian.arrejin.so/ProjectTest/src/utils/chromedriver/chromedriver.exe";
    WebDriver driver;
    SwagLabsLoginPage objLogin;
    SwagLabsHomePage objHomePage;


    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("about:blank");
    }


    @Test()
    public void Ingreso(){
        objLogin = new SwagLabsLoginPage(driver);
        driver.get("https://www.saucedemo.com/");
        Assert.assertTrue(objLogin.isConnected(), "No se pudo ingresar en el sitio");

    }


    @Test(dependsOnMethods = "Ingreso")
    public void LoginTest(){
        objLogin.login("standard_user", "secret_sauce");
        objHomePage = new SwagLabsHomePage(driver);
        Assert.assertTrue(objHomePage.isLogged(), "No fue posible loguearse");

    }


    @AfterTest
    public void close(){
        driver.close();
    }
}
