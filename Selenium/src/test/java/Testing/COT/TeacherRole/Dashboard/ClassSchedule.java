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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClassSchedule {
    COTFunctions func;
    private WebDriver driver;
    private WebDriverWait wait;
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
    public void testClassSchedule() throws Exception {
        // Click Class Schedule
        driver.findElement(By.linkText("Class Schedule")).click();

        // Add Student
        // Click Add
        driver.findElement(By.xpath("//td[5]/div")).click();
        // Select Grade
        new Select(driver.findElement(By.xpath("//form[@id='get-school-students-reassign']/div/div/select"))).selectByVisibleText("Grade 10");
        // Check Student
        driver.findElement(By.xpath("//td/div/input")).click();
        // Click Add Button
        driver.findElement(By.xpath("//form[@id='relocate-teacher-add-student']/div/input")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='relocate-teacher-add-student']/div/input")));

        // Remove Student
        // Click Edit
        driver.findElement(By.xpath("//td[4]/div")).click();
        // Check Student
        driver.findElement(By.xpath("//td/div/input")).click();
        // Click Remove
        driver.findElement(By.xpath("//div[2]/div/div/div/div/div/div/form/div/input")).click();
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