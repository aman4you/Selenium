package Testing.COT.TeacherRole.Homework;

import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeeSubmittedHomework {
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
        driver.get(func.baseUrl + "/lesson-library");
    }

    @Test
    public void testSeeSubmittedHomework() throws Exception {
        // Clicked Homework
        driver.findElement(By.linkText("ASSIGNED HOMEWORK")).click();
        // Click View
        driver.findElement(By.linkText("View")).click();
        // Click View Assignment
        driver.findElement(By.linkText("View Assignment")).click();
        // Click Close
        driver.findElement(By.cssSelector("span.close-button > img")).click();

        // Select Chapter
        driver.findElement(By.xpath("//div[@id='edit_learning_plan_assignments_chzn']/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_learning_plan_assignments_chzn_o_0\"]")).click();
        // Select Student
        driver.findElement(By.xpath("//div[@id='edit_assigned_to_chzn']/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_assigned_to_chzn_o_0\"]")).click();
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