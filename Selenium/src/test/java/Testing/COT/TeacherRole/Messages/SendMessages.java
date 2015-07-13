package Testing.COT.TeacherRole.Messages;

/**
 * Created by om on 11/12/2014.
 */
import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SendMessages {
    COTFunctions func;
    private WebDriver driver;
    private String[] Tracking = null;
    private String TrackingValues = null;
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
        driver.get(func.baseUrl + "/messages");
    }

    @Test
    public void testSendMessages() throws Exception {
        // Click Dialog
        driver.findElement(By.linkText("DIALOG")).click();

        // Send Message
        // Click Subject "Hello"
        driver.findElement(By.linkText("Hello")).click();
        // Enter Message
        driver.findElement(By.id("edit-body-value--2")).clear();
        Tracking = func.RandomWords(10);
        for(int i= 0 ; i < Tracking.length ; i++) {
            TrackingValues = TrackingValues + " " + Tracking[i];
        }
        driver.findElement(By.id("edit-body-value--2")).sendKeys(TrackingValues);
        // Click Send Message
        driver.findElement(By.xpath("//div[@id='edit-actions--3']/input")).click();

        // Send Message
        // Select Options
        driver.findElement(By.xpath("//div[@id='edit_recipient_individuals_chzn']/ul")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_recipient_individuals_chzn_o_0\"]")).click();
        // Enter Subject
        driver.findElement(By.id("edit-subject")).click();
        driver.findElement(By.id("edit-subject")).clear();
        Tracking = func.RandomWords(2);
        driver.findElement(By.id("edit-subject")).sendKeys(Tracking[0] + " " + Tracking[1]);
        // Enter Message
        driver.findElement(By.id("edit-body-value")).click();
        driver.findElement(By.id("edit-body-value")).clear();
        Tracking = func.RandomWords(6);
        for(int i= 0 ; i < Tracking.length ; i++) {
            TrackingValues = TrackingValues + " " + Tracking[i];
        }
        driver.findElement(By.id("edit-body-value")).sendKeys(TrackingValues);
        // Click Send Message
        driver.findElement(By.xpath("//div[@id='edit-actions--2']/input")).click();
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