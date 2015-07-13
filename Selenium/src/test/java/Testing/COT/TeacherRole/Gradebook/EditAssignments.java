package Testing.COT.TeacherRole.Gradebook;

import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EditAssignments {
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
        func.LoginRole("Teacher");
        driver.get(func.baseUrl + "/my-assignments");
    }

    @Test
    public void testEditAssignments() throws Exception {
        // Click Assignments
        driver.findElement(By.linkText("ASSIGNMENTS")).click();
        // Click Edit Symbol
        driver.findElement(By.linkText("Assignment/Test Edit")).click();

        // Assignment/Test Edit
        // Edit Assessment Name
        driver.findElement(By.id("edit-field-assessment-collection-und-0-field-grade-assessment-name-und-0-value")).clear();
        driver.findElement(By.id("edit-field-assessment-collection-und-0-field-grade-assessment-name-und-0-value")).sendKeys("do all example of chapter 1");
        // Click Save Button
        driver.findElement(By.xpath("//div[@id='edit-actions']/input")).click();
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
