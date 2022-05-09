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

public class Test_Login {

    String driverPath = "C:/Users/sebastian.arrejin.so/ProjectTest/src/utils/chromedriver/chromedriver.exe";
    WebDriver driver;
    SwagLabsLoginPage objLogin;
    SwagLabsHomePage objHomePage;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");
    }


    @Test()
    public void Ingreso(){
        objLogin = new SwagLabsLoginPage(driver);
        Assert.assertTrue(objLogin.isConnected());

    }


    @Test(dependsOnMethods = "Ingreso")
    public void LoginTest(){
        objLogin.login("standard_user", "secret_sauce");
        objHomePage = new SwagLabsHomePage(driver);
        Assert.assertTrue(objHomePage.isLogged());

    }

    @AfterTest
    public void close(){
        driver.close();
    }

}
