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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StudentLog {
    COTFunctions func;
    private WebDriver driver;
    private WebDriverWait wait;
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
        driver.get(func.baseUrl + "/students-dashboard/75486");
    }

    @Test
    public void testStudentLog() throws Exception {
        // Click Student Log
        driver.findElement(By.linkText("Student Log")).click();
        if(isElementPresent(By.linkText("Click here to create a log entry"))) {
            // Click "Click Here To Create a Log Entry"
            driver.findElement(By.linkText("Click here to create a log entry")).click();
            // Select Message Type
            driver.findElement(By.xpath("//div[@id='edit_field_stu_log_message_type_und_chzn']/a/span")).click();
            driver.findElement(By.xpath("//*[@id=\"edit_field_stu_log_message_type_und_chzn_o_1\"]")).click();
            // Enter Title
            driver.findElement(By.id("edit-title")).click();
            driver.findElement(By.id("edit-title")).clear();
            Tracking = func.RandomWords(2);
            driver.findElement(By.id("edit-title")).sendKeys(Tracking[0] + " " + Tracking[1]);
            // Select Name of Student
            driver.findElement(By.xpath("//div[@id='edit_more_students_chzn']/ul/li/input")).click();
            driver.findElement(By.xpath("//*[@id=\"edit_more_students_chzn_o_1\"]")).click();
            // Enter Description
            driver.findElement(By.id("edit-field-log-entry-description-und-0-value")).clear();
            Tracking = func.RandomWords(2);
            driver.findElement(By.id("edit-field-log-entry-description-und-0-value")).sendKeys(Tracking[0] + " " + Tracking[1]);
            // Click Save Button
            driver.findElement(By.xpath("//div[11]/input")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[11]/input")));
        } else if(isElementPresent(By.linkText("+"))) {
            // Click "+" Symbol
            driver.findElement(By.linkText("+")).click();
            // Select Message Type
            driver.findElement(By.xpath("//div[@id='edit_field_stu_log_message_type_und_chzn']/a/span")).click();
            driver.findElement(By.xpath("//*[@id=\"edit_field_stu_log_message_type_und_chzn_o_1\"]")).click();
            // Enter Title
            driver.findElement(By.id("edit-title")).click();
            driver.findElement(By.id("edit-title")).clear();
            Tracking = func.RandomWords(2);
            driver.findElement(By.id("edit-title")).sendKeys(Tracking[0] + " " + Tracking[1]);
            // Select Name of Student
            driver.findElement(By.xpath("//div[@id='edit_more_students_chzn']/ul/li/input")).click();
            driver.findElement(By.xpath("//*[@id=\"edit_more_students_chzn_o_1\"]")).click();
            // Enter Description
            driver.findElement(By.id("edit-field-log-entry-description-und-0-value")).clear();
            Tracking = func.RandomWords(2);
            driver.findElement(By.id("edit-field-log-entry-description-und-0-value")).sendKeys(Tracking[0] + " " + Tracking[1]);
            // Click Save Button
            driver.findElement(By.xpath("//div[11]/input")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[11]/input")));
        }

        driver.findElement(By.xpath("//td[2]/a")).click();
        Thread.sleep(1000);
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