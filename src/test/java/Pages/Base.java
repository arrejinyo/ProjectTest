package Pages;

import com.paulhammant.ngwebdriver.NgWebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;



public class Base {

    private WebDriver driver;
    private WebDriverWait wait;
    private NgWebDriver ngDriver;

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public Base(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Base(WebDriver driver, NgWebDriver ngDriver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.ngDriver = ngDriver;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

   public NgWebDriver getNgDriver() {
        return this.ngDriver;
    }

    public void wait(int time) {
        this.getDriver().manage().timeouts().implicitlyWait((long)time, TimeUnit.SECONDS);
    }

    public boolean wait(WebElement element, int time) {
        this.wait = new WebDriverWait(this.getDriver(), (long)time);
        return ((WebElement)this.wait.until(ExpectedConditions.elementToBeClickable(element))).isEnabled();
    }

    public boolean wait(By element, int time) {
        this.wait = new WebDriverWait(this.getDriver(), (long)time);
        return ((WebElement)this.wait.until(ExpectedConditions.visibilityOfElementLocated(element))).isEnabled();
    }

    public WebDriver nuevaPestania(String url) {
        String a = "window.open('about:blank','_blank');";
        ((JavascriptExecutor)this.driver).executeScript(a, new Object[0]);
        this.ultimaPestania();
        this.driver.get(url);
        return this.getDriver();
    }

    public boolean elementExists(By locator) {
        try {
            this.driver.findElement(locator);
            return true;
        } catch (NoSuchElementException var3) {
            return false;
        }
    }

    public boolean elementExists(WebElement locator) {
        try {
            locator.getLocation();
            return true;
        } catch (NoSuchElementException var3) {
            return false;
        }
    }

    public void ultimaPestania() {
        Iterator var1 = this.getDriver().getWindowHandles().iterator();

        while(var1.hasNext()) {
            String subWindow = (String)var1.next();
            this.getDriver().switchTo().window(subWindow);
        }

    }

    public void aceptarAlerta(String windowHandle) {
        try {
            Alert alert = this.getDriver().switchTo().alert();
            System.out.println("---------------- Alert");
            System.out.println(alert.getText());
            System.out.println("-----------------");
            alert.accept();
            this.getDriver().switchTo().window(windowHandle);
        } catch (Exception var3) {
            System.out.println("No se visualiza alerta");
            this.getDriver().switchTo().window(windowHandle);
        }

    }

    public void takeScreenShot() {
        File scrFile = (File)((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);

        try {
            FileHandler.copy(scrFile, new File("screenShot\\" + (new Date()).toString().substring(0, 19).replace(":", ";") + ".jpg"));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public HttpURLConnection connectTo(String method, String url) {
        try {
            HttpURLConnection huc = (HttpURLConnection)((HttpURLConnection)(new URL(url)).openConnection());
            huc.setRequestMethod(method);
            huc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
            huc.addRequestProperty("Cookie", "intranet=" + this.getDriver().manage().getCookieNamed("intranet").getValue());
            huc.connect();
            return huc;
        } catch (Exception var4) {
            System.out.println(url + " - No se obtuvo respuesta");
            var4.printStackTrace();
            return null;
        }
    }

    public boolean testConnection(String url) {
        try {
            HttpURLConnection huc = this.connectTo("HEAD", url);
            int respCode = huc.getResponseCode();
            String respMessage = huc.getResponseMessage();
            if (respCode >= 400) {
                System.out.println(url + " es un enlace roto");
                System.out.println("Cod. Resp:" + respCode);
                System.out.println("Response Message:" + respMessage);
                return false;
            } else {
                System.out.println(url + " - Enlace OK!");
                return true;
            }
        } catch (Exception var5) {
            var5.printStackTrace();
            return false;
        }
    }
}
