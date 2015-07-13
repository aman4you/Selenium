/**
 * First student create the essay topic for particular university than student write the essay on that topic. After write the
 * essay student have two option, one to save the essay and another one is save and share the essay with teacher. Teacher
 * describe how much essay is complete in percentage. After every time student update and share the essay, version will be
 * created.
 */

package Testing.COT.StudentRole.CollegeApp;

/**
 * Created by om on 11/12/2014.
 */
import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateEssay {
    COTFunctions func;
    private WebDriver driver;
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
        driver.get(func.baseUrl + "/college-search");
    }

    @Test
    public void testCreateEssay() throws Exception {
        // Click Essay
        driver.findElement(By.linkText("ESSAYS")).click();

        if(isElementPresent(By.linkText("Create Your First Essay"))) {
            // Click "Create Your First Essay"
            driver.findElement(By.linkText("Create Your First Essay")).click();
        } else if (isElementPresent(By.linkText("Add Essay Topic"))) {
            // Click "Add Essay Topic"
            driver.findElement(By.linkText("Add Essay Topic")).click();
        }

        // Add Essay Topic
        // Select Type
        func.SelectRandomSelectListOption("//div[contains(@class, 'field-name-field-essay-type-form')]//select");
        // Enter University
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-essay-university-form')]//input")).clear();
        Tracking = func.RandomWords(1);
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-essay-university-form')]//input")).sendKeys(Tracking[0]);
        // Enter Topic
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea")).clear();
        Tracking = func.RandomWords(2);
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea")).sendKeys(Tracking[0] + " " + Tracking[1]);
        // Click "+" Symbol
        driver.findElement(By.xpath("//input[contains(@class, 'form-submit')]")).click();
        Thread.sleep(3000);

        // Create Essay
        // Click "+" Symbol
        driver.findElement(By.xpath("//tr[@class='odd views-row-first']//a[@class='add-essay']")).click();
        // Click Source
        driver.findElement(By.xpath("//span[contains(@class, 'cke_button__source_label')]")).click();
        // Enter Essay
        driver.findElement(By.cssSelector("textarea.cke_source.cke_enable_context_menu")).clear();
        Tracking = func.RandomWords(8);
        for(int i= 0 ; i < Tracking.length ; i++) {
            TrackingValues = TrackingValues + " " + Tracking[i];
        }
        driver.findElement(By.cssSelector("textarea.cke_source.cke_enable_context_menu")).sendKeys("<p>" + TrackingValues + " </p>");
        // Click Save
        driver.findElement(By.xpath("//input[@value='Save']")).click();

        // Click Essay
        driver.findElement(By.linkText("ESSAYS")).click();
        Thread.sleep(3000);
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

