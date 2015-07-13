package Testing.COT.StudentRole.Messages;

/**
 * Created by om on 11/12/2014.
 */
import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeeBroadcastMessages {
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
        func.LoginRole("Student");
        driver.get(func.baseUrl + "/messages");
    }

    @Test
    public void testSeeBroadcastMessages() throws Exception {
        // Click Broadcast
        driver.findElement(By.linkText("BROADCAST")).click();
        // Click Subject
        driver.findElement(By.cssSelector("a.use-ajax.ajax-processed")).click();
        // Close Subject
        driver.findElement(By.cssSelector("span.close-button > img")).click();

        // Remove Subject
        driver.findElement(By.linkText("Remove")).click();
        // Click No Button
        driver.findElement(By.xpath("//span[contains(text(), \"Yes\")]/parent::button")).click();
        // Remove Subject
        driver.findElement(By.linkText("Remove")).click();
        // Click Yes Button
        driver.findElement(By.xpath("//span[contains(text(), \"No\")]/parent::button")).click();
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