package Testing.COT.SchoolAdminRole.AdminApp;

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

public class SaveStudentLogSetup {
    COTFunctions func;
    private WebDriver driver;
    private WebDriverWait wait;
    private int Max = 50;
    private int IntegerValue = 0;
    private String[] Tracking = null;
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
        func.LoginRole("SchoolAdmin");
        driver.get(func.baseUrl + "/graduation_requirement_tracking");
    }

    @Test
    public void testSaveStudentLogSetup() throws Exception {
        // Click Student Log Setup
        driver.findElement(By.linkText("Student Log Setup")).click();

        if(isElementPresent(By.linkText("Click here to add a Message type entry"))) {
            // Click "Click here to add a Message type entry"
            driver.findElement(By.linkText("Click here to add a Message type entry")).click();
            // Enter Message Types
            driver.findElement(By.id("edit-incidents-type")).clear();
            Tracking = func.RandomWords(1);
            driver.findElement(By.id("edit-incidents-type")).sendKeys(Tracking[0]);
            // Enter Points
            driver.findElement(By.id("edit-incident-points")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-incident-points")).sendKeys(String.valueOf(IntegerValue));
            // Click Save Button
            driver.findElement(By.id("edit-attendance-submit")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("edit-attendance-submit")));
        } else if (isElementPresent(By.cssSelector("div.plus-symbol"))) {
            // Click "+" Symbol
            driver.findElement(By.cssSelector("div.plus-symbol")).click();
            // Enter Message Types
            driver.findElement(By.id("edit-incidents-type")).clear();
            Tracking = func.RandomWords(1);
            driver.findElement(By.id("edit-incidents-type")).sendKeys(Tracking[0]);
            // Enter Points
            driver.findElement(By.id("edit-incident-points")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-incident-points")).sendKeys(String.valueOf(IntegerValue));
            // Click Save Button
            driver.findElement(By.id("edit-attendance-submit")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("edit-attendance-submit")));
        }

        // Click Cross Button
        driver.findElement(By.cssSelector("span.confirm-button > img")).click();
        // Click Yes
        driver.findElement(By.id("edit-submit")).click();

        // Check Yes Checkbox
        driver.findElement(By.id("edit-parents-email-1")).click();
        // Click Save
        driver.findElement(By.id("edit-parent-submit")).click();
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

