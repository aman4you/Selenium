package Testing.COT.TeacherRole.DigitalPortfolio;

/**
 * Created by om on 11/12/2014.
 */
import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeeStudentShowcase {
    COTFunctions func;
    private WebDriver driver;
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
        driver.get(func.baseUrl + "/journals/75486");
    }

    @Test
    public void testSeeStudentShowcase() throws Exception {
        // Click Showcases
        driver.findElement(By.linkText("SHOWCASES")).click();
        // Click Journey Description
        driver.findElement(By.linkText("journey description")).click();
        // Click Video
        driver.findElement(By.cssSelector("#cot-botr-thumbnail-JTbg1ZRa > img.botr-thumbnail")).click();
        // Close Video
        driver.findElement(By.cssSelector("#cot-botr-video-JTbg1ZRa > div.close-button-wrapper > a.close-button > img")).click();
        // Select Options
        driver.findElement(By.xpath("//div[@id='edit_students_chzn']/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_students_chzn_o_1\"]")).click();
        Thread.sleep(300);
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