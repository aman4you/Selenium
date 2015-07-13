package Testing.COT.TeacherRole.Dashboard;

/**
 * Created by om on 11/12/2014.
 */
import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SendSMS {
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
        driver.get(func.baseUrl + "/students-dashboard/75486");
    }

    @Test
    public void testSendSms() throws Exception {
        // Click SMS
        driver.findElement(By.linkText("SMS")).click();

        // Send Message
        // Click Individuals
        driver.findElement(By.id("edit-recipient-type-0")).click();
        // Select Options
        driver.findElement(By.xpath("//div[@id='edit_recipient_individuals_chzn']/ul/li/input")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_recipient_individuals_chzn_o_0\"]")).click();
        // Enter Message
        driver.findElement(By.id("edit-body")).click();
        driver.findElement(By.id("edit-body")).clear();
        Tracking = func.RandomWords(7);
        for(int i= 0 ; i < Tracking.length ; i++) {
            TrackingValues = TrackingValues + " " + Tracking[i];
        }
        driver.findElement(By.id("edit-body")).sendKeys(TrackingValues);
        // Click Send Message
        driver.findElement(By.xpath("//form[@id='teacher-dashboard-message-form']/div/input")).click();
        Thread.sleep(2000);

        // Send message
        // Click Groups
        driver.findElement(By.id("edit-recipient-type-1")).click();
        // Select Options
        driver.findElement(By.xpath("//div[@id='edit_recipient_groups_chzn']/ul/li/input")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_recipient_groups_chzn_o_0\"]")).click();
        // Enter Message
        driver.findElement(By.id("edit-body")).click();
        driver.findElement(By.id("edit-body")).clear();
        Tracking = func.RandomWords(7);
        for(int i= 0 ; i < Tracking.length ; i++) {
            TrackingValues = TrackingValues + " " + Tracking[i];
        }
        driver.findElement(By.id("edit-body")).sendKeys(TrackingValues);
        // Click Send Message
        driver.findElement(By.xpath("//form[@id='teacher-dashboard-message-form']/div/input")).click();
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