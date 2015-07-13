package Testing.COT.StudentRole.DigitalPortfolio;

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

public class AddActivity {
    COTFunctions func;
    private WebDriver driver;
    private WebDriverWait wait;
    private int Max = 12;
    private int Min = 6;
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
        func.LoginRole("Student");
        driver.get(func.baseUrl + "/journals");
    }

    @Test
    public void testAddActivity() throws Exception {
        try {
            // Click Activities
            driver.findElement(By.linkText("ACTIVITIES")).click();
            // Click Create Activity
            driver.findElement(By.linkText("CREATE ACTIVITY")).click();

            // Create Activity
            // Enter Activity
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-activity-activities-form')]//textarea")).clear();
            Tracking = func.RandomWords(2);
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-activity-activities-form')]//textarea")).sendKeys(Tracking[0] + " " + Tracking[1]);
            // Enter Grade
            driver.findElement(By.xpath("//table[@id='field-activity-grade-values']//input")).clear();
            IntegerValue = func.RandomIntegerNumberBetweenRange(Max, Min);
            driver.findElement(By.xpath("//table[@id='field-activity-grade-values']//input")).sendKeys(String.valueOf(IntegerValue));
            // Enter Hours Per Week
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-activity-hours-p-week-form')]//input")).clear();
            Max = 168;
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-activity-hours-p-week-form')]//input")).sendKeys(String.valueOf(IntegerValue));
            // Enter Weeks Per Year
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-activity-weeks-p-year-form')]//input")).clear();
            Max = 52;
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-activity-weeks-p-year-form')]//input")).sendKeys(String.valueOf(IntegerValue));
            // Enter Positions
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-activity-positions-form')]//textarea")).clear();
            Tracking = func.RandomWords(1);
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-activity-positions-form')]//textarea")).sendKeys(Tracking[0]);
            // Click Skills Required
            func.ClickRandomCheckboxes(By.xpath("//div[contains(@class, 'field-name-field-activity-skills-acquired-form ')]//input[@type='checkbox']"));
            // Click Save Button
            driver.findElement(By.xpath("//div[contains(@class, 'form-actions')]//input[@type='submit']")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'form-actions')]//input[@type='submit']")));
        } catch (Exception e) {
            String TestCase = Thread.currentThread().getStackTrace()[1].getClassName();
            func.StoreException(e, TestCase);
        }
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