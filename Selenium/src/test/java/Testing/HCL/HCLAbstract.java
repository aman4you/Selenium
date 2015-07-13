package Testing.HCL;

import Testing.CommonFunction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by om on 1/24/2015.
 */
public abstract class HCLAbstract {
    public WebDriver driver;
    public WebDriverWait wait;
    public HCLFunctions func;
    public CommonFunction func2;
    public String Role;
    public String TestCase;
    public int timeoutOfOneElement = 15;
    public int timeoutOFAllElement = 10;
    public String baseUrl = "http://demoaws.innoraft.com/";
    public String LoginUrl = "http://demoaws.innoraft.com?hcltest_switch=seleniumtest_hcl";
    public static String FilesPath = "C:\\Users\\om\\Downloads\\TestingFiles\\Files\\";

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, timeoutOfOneElement);
        func = new HCLFunctions(driver, wait);
        func2 = new CommonFunction(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(timeoutOFAllElement, TimeUnit.SECONDS);

        driver.get(baseUrl);
        func.CheckLogin();
        func.LoginRole(Role);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
