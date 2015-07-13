package Testing.COT.TeacherRole.Gradebook;

/**
 * Created by om on 11/19/2014.
 */

import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EnterMarksSubmitGrades {
    COTFunctions func;
    private WebDriver driver;
    private WebDriverWait wait;
    private int Max = 0;
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
        func.LoginRole("Teacher");
        driver.get(func.baseUrl + "/my-assignments");
    }

    @Test
    public void testEnterMarksSubmitGrade() throws Exception {
        // Click Submit Grades
        driver.findElement(By.linkText("SUBMIT GRADES")).click();

        // Wait for Select list To Load
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ajax-waiting")));
        // Select My Classes
        driver.findElement(By.xpath("//form/div/div/div/div/div/div/ul/li[3]")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ajax-waiting")));
        // Select My Grading Attribute
        driver.findElement(By.xpath("//div[2]/div/div/div/div/ul/li[3]")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ajax-waiting")));
        // Select Assignment/Quiz
        driver.findElement(By.xpath("//div[3]/div/div/div/div/ul/li[2]")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ajax-waiting")));
        // Enter Accuracy
        driver.findElement(By.xpath("//td[3]/div/input")).clear();
        Max = 10;
        IntegerValue = func.RandomIntegerNumber(Max);
        driver.findElement(By.xpath("//td[3]/div/input")).sendKeys(String.valueOf(IntegerValue));
        // Enter Demonstration of Process
        driver.findElement(By.xpath("//td[4]/div/input")).clear();
        Max = 5;
        IntegerValue = func.RandomIntegerNumber(Max);
        driver.findElement(By.xpath("//td[4]/div/input")).sendKeys(String.valueOf(IntegerValue));
        // Enter Comment
        driver.findElement(By.xpath("//textarea")).clear();
        Tracking = func.RandomWords(1);
        driver.findElement(By.xpath("//textarea")).sendKeys(Tracking[0]);
        // Click Save Button
        driver.findElement(By.xpath("//div[4]/form/div/input[3]")).click();
        Thread.sleep(2000);
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