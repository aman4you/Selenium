package Testing.COT.TeacherRole.DigitalPortfolio;

/**
 * Created by om on 11/12/2014.
 */
import java.util.List;
import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeeStudentResume {
    COTFunctions func;
    private WebDriver driver;
    private int RandomOption = 0;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        func = new COTFunctions(driver);
        driver.manage().timeouts().implicitlyWait(func.timeoutOFAllElement, TimeUnit.SECONDS);

        driver.get(func.baseUrl + "/");
        func.CheckLogin();
        func.LoginRole("Teacher");
        driver.get(func.baseUrl + "/journals/75501");
    }

    @Test
    public void testSeeStudentResume() throws Exception {
        // Click Resume
        driver.findElement(By.linkText("RESUME")).click();
        // Check Select List Options exist or not
        List<WebElement> SelectListOptions = driver.findElements(By.xpath("//ul[@class='chzn-results']/li"));
        int OptionsNumber = SelectListOptions.size();
        if(OptionsNumber > 0) {
            // Access Random Option
            RandomOption = func.RandomIntegerNumber(OptionsNumber) - 1;
            // Select Random Option
            driver.findElement(By.xpath("//div[@id='edit_students_chzn']/a/div/b")).click();
            driver.findElement(By.xpath("//*[@id=\"edit_students_chzn_o_" + RandomOption + "\"]")).click();
        }
        Thread.sleep(2000);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}

