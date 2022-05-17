package test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import Pages.SwagLabsHomePage;


public class SwagLabsLoginPage extends BaseTest {

    Pages.SwagLabsLoginPage objLogin;
    SwagLabsHomePage objHomePage;





    @Test()
    public void Ingreso(){
        objLogin = new Pages.SwagLabsLoginPage(this.driver);
        driver.get("https://www.saucedemo.com/");
        Assert.assertTrue(objLogin.isConnected(), "No se pudo ingresar en el sitio");

    }


    @Test(dependsOnMethods = "Ingreso")
    public void LoginTest(){
        objLogin.login("standard_user", "secret_sauce");
        objHomePage = new SwagLabsHomePage(driver);
        Assert.assertTrue(objHomePage.isLogged(), "No fue posible loguearse");

    }


}
