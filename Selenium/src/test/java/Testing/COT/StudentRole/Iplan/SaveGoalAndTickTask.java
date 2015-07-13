package Testing.COT.StudentRole.Iplan;

/**
 * Created by om on 11/12/2014.
 */
import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SaveGoalAndTickTask {
    COTFunctions func;
    private WebDriver driver;
    private WebDriverWait wait;
    private String[] Tracking = null;
    private String TrackingValues = "";
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        func = new COTFunctions(driver);
        wait = new WebDriverWait(driver, func.timeoutOfOneElement);
        driver.manage().timeouts().implicitlyWait(func.timeoutOFAllElement, TimeUnit.SECONDS);

        driver.get(func.baseUrl + "/");
        func.CheckLogin();
        func.LoginRole("Student");
        driver.get(func.baseUrl + "/long-term-planner");
    }

    @Test
    public void testSaveGoalAndTickTask() throws Exception {
        // Click ITasks
        driver.findElement(By.linkText("ITASKS")).click();

        // Click Careers & Majors
        driver.findElement(By.cssSelector("div.careers-major-link")).click();
        // Close Careers & Majors
        driver.findElement(By.cssSelector("div.close-button > img")).click();

        // Click My Goals
        driver.findElement(By.cssSelector("div.ltp-milestone-goal-link")).click();
        // Click Source
        driver.findElement(By.xpath("//span[contains(@class, 'cke_button__source_label')]")).click();
        // Enter Goal
        driver.findElement(By.cssSelector("textarea.cke_source.cke_enable_context_menu")).clear();
        Tracking = func.RandomWords(5);
        for(int i= 0 ; i < Tracking.length ; i++) {
            TrackingValues = TrackingValues + " " + Tracking[i];
        }
        driver.findElement(By.cssSelector("textarea.cke_source.cke_enable_context_menu")).sendKeys(TrackingValues);
        // Click Save Button
        driver.findElement(By.xpath("//form[@id='ltp-goal-form']//input[@class='form-submit']")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='ltp-goal-form']//input[@class='form-submit']")));

        // Click Title
        driver.findElement(By.cssSelector("div.title")).click();
        // Close Title
        driver.findElement(By.cssSelector("span.close-button > img")).click();
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

