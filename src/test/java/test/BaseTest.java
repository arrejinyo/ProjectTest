package test;


//import com.paulhammant.ngwebdriver.NgWebDriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    String driverPath = "C:/Users/sebastian.arrejin.so/ProjectTest/src/utils/chromedriver/chromedriver.exe";
    protected WebDriver driver;
    //  protected NgWebDriver ngDriver;

    public BaseTest() {
    }



    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("about:blank");
    }


    @AfterTest
    public void close() {
        System.out.println("Finalizó ");
        this.driver.quit();
    }
}
