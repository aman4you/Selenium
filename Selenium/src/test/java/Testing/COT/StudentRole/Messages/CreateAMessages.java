package Testing.COT.StudentRole.Messages;

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

public class CreateAMessages {
    COTFunctions func;
    private WebDriver driver;
    private int RandomOption = 0;
    private String[] Tracking = null;
    private String TrackingValues = "";
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
    public void testCreateAMessages() throws Exception {
        // Click Dialog
        driver.findElement(By.linkText("DIALOG")).click();

        // Send Message
        // Click Individual Checkbox
        driver.findElement(By.id("edit-recipient-type-0")).click();
        // Check Select List Options exist or not
        List<WebElement> SelectListOptions = driver.findElements(By.xpath("//div[@id='edit_recipient_individuals_chzn']//ul[@class='chzn-results']/li"));
        int OptionsNumber = SelectListOptions.size();
        if(OptionsNumber > 0) {
            // Access Random Option
            RandomOption = func.RandomIntegerNumber(OptionsNumber) - 1;
            // Select Random Option
            driver.findElement(By.xpath("//div[@id='edit_recipient_individuals_chzn']/ul")).click();
            driver.findElement(By.xpath("//*[@id=\"edit_recipient_individuals_chzn_o_" + RandomOption + "\"]")).click();
        }
        // Enter Subject
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-subject')]//input")).clear();
        Tracking = func.RandomWords(1);
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-subject')]//input")).sendKeys(Tracking[0]);
        // Enter Message
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-body-value')]//textarea")).clear();
        Tracking = func.RandomWords(5);
        for(int i= 0 ; i < Tracking.length ; i++) {
            TrackingValues = TrackingValues + " " + Tracking[i];
        }
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-body-value')]//textarea")).sendKeys(TrackingValues);
        // Click Send Message
        driver.findElement(By.xpath("//div[contains(@class, 'form-actions')]//input[@type='submit']")).click();

        // Send Message
        // Click Subject
        driver.findElement(By.cssSelector("a.use-ajax.ajax-processed")).click();
        // Enter Message
        driver.findElement(By.xpath("//div[@id='dialog-view-msg']//textarea")).clear();
        Tracking = func.RandomWords(10);
        for(int i= 0 ; i < Tracking.length ; i++) {
            TrackingValues = TrackingValues + " " + Tracking[i];
        }
        driver.findElement(By.xpath("//div[@id='dialog-view-msg']//textarea")).sendKeys("this is a dummy message");
        // Click Send Message
        driver.findElement(By.xpath("//div[@id='dialog-view-msg']//input[@type='submit']")).click();

        // To Remove Message
        driver.findElement(By.linkText("Remove")).click();
        // Click "Yes" Button
        driver.findElement(By.xpath("//span[contains(text(), \"Yes\")]/parent::button")).click();
        // To Undone Removed Message
        driver.findElement(By.linkText("undone")).click();
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