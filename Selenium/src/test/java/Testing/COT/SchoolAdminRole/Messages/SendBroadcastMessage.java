package Testing.COT.SchoolAdminRole.Messages;

/**
 * Created by om on 11/12/2014.
 */
import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class SendBroadcastMessage {
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
        func.LoginRole("SchoolAdmin");
        driver.get(func.baseUrl + "/messages/broadcast");
    }

    @Test
    public void testSendBroadcastMessage() throws Exception {
        // Click Broadcast
        driver.findElement(By.linkText("BROADCAST")).click();

        // Send Broadcast
        // Select Grade
        driver.findElement(By.xpath("//div[@id='edit_get_grades_chzn']/a/div/b")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_get_grades_chzn_o_4\"]")).click();
        // Enter Subject
        driver.findElement(By.id("edit-subject")).click();
        driver.findElement(By.id("edit-subject")).clear();
        Tracking = func.RandomWords(2);
        driver.findElement(By.id("edit-subject")).sendKeys(Tracking[0] + " " + Tracking[1]);
        // Enter Message
        driver.findElement(By.id("edit-body-value")).clear();
        Tracking = func.RandomWords(8);
        for(int i= 0 ; i < Tracking.length ; i++) {
            TrackingValues = TrackingValues + " " + Tracking[i];
        }
        driver.findElement(By.id("edit-body-value")).sendKeys(TrackingValues);
        // Click Send Message
        driver.findElement(By.className("form-submit")).click();

        // Click Subject
        driver.findElement(By.cssSelector("a.use-ajax.ajax-processed")).click();
        Thread.sleep(200);
        // Click Close Button
        driver.findElement(By.cssSelector("span.close-button > img")).click();
    }

    private void clickAt(By by) throws InterruptedException {
        Thread.sleep(1500);
        Actions builder = new Actions(driver);
        WebElement tagElement = driver.findElement(by);
        builder.moveToElement(tagElement).click().perform();
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