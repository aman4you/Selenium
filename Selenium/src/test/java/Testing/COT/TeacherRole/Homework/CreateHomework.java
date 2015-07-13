package Testing.COT.TeacherRole.Homework;

import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateHomework {
    COTFunctions func;
    private WebDriver driver;
    private WebDriverWait wait;
    private String[] Tracking = null;
    private int IntegerValue = 0;
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
        driver.get(func.baseUrl + "/lesson-library");
    }

    @Test
    public void testCreateHomework() throws Exception {
        // Click Homework Library
        driver.findElement(By.linkText("HOMEWORK LIBRARY")).click();
        // Create New Homework Assignment
        driver.findElement(By.linkText("Create New Homework Assignment")).click();

        //Create New Homework Assignment
        // Select Subject
        driver.findElement(By.id("mathematics")).click();

        // Homework Assignment Detail
        // Enter Assignment Title
        driver.findElement(By.id("edit-title")).click();
        driver.findElement(By.id("edit-title")).clear();
        Tracking = func.RandomWords(2);
        driver.findElement(By.id("edit-title")).sendKeys(Tracking[0] + " " + Tracking[1]);

        // Assessment Criteria
        // Enter Name
        driver.findElement(By.id("edit-field-assessment-collection-und-0-field-grade-assessment-name-und-0-value")).click();
        driver.findElement(By.id("edit-field-assessment-collection-und-0-field-grade-assessment-name-und-0-value")).clear();
        Tracking = func.RandomWords(1);
        driver.findElement(By.id("edit-field-assessment-collection-und-0-field-grade-assessment-name-und-0-value")).sendKeys(Tracking[0]);
        // Enter Points
        driver.findElement(By.id("edit-field-assessment-collection-und-0-field-grade-assessment-points-und-0-value")).click();
        driver.findElement(By.id("edit-field-assessment-collection-und-0-field-grade-assessment-points-und-0-value")).clear();
        IntegerValue = func.RandomIntegerNumber(10);
        driver.findElement(By.id("edit-field-assessment-collection-und-0-field-grade-assessment-points-und-0-value")).sendKeys(String.valueOf(IntegerValue));
        // Click "+" Symbol
        driver.findElement(By.xpath("//div[@id='field-assessment-collection-add-more-wrapper']/div/div[2]/input")).click();
        // Wait For Elements to be Display
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("edit-field-assessment-collection-und-add-more--2")));
        // Enter Name
        driver.findElement(By.id("edit-field-assessment-collection-und-1-field-grade-assessment-name-und-0-value")).click();
        driver.findElement(By.id("edit-field-assessment-collection-und-1-field-grade-assessment-name-und-0-value")).clear();
        Tracking = func.RandomWords(1);
        driver.findElement(By.id("edit-field-assessment-collection-und-1-field-grade-assessment-name-und-0-value")).sendKeys(Tracking[0]);
        // Enter Points
        driver.findElement(By.id("edit-field-assessment-collection-und-1-field-grade-assessment-points-und-0-value")).click();
        driver.findElement(By.id("edit-field-assessment-collection-und-1-field-grade-assessment-points-und-0-value")).clear();
        IntegerValue = func.RandomIntegerNumber(10);
        driver.findElement(By.id("edit-field-assessment-collection-und-1-field-grade-assessment-points-und-0-value")).sendKeys(String.valueOf(IntegerValue));

        // Milestones & Tasks
        // Enter Milestone
        driver.findElement(By.id("edit-field-learning-plan-mt-und-0-field-learning-plan-milestone-und-0-value")).clear();
        Tracking = func.RandomWords(1);
        driver.findElement(By.id("edit-field-learning-plan-mt-und-0-field-learning-plan-milestone-und-0-value")).sendKeys(Tracking[0]);
        // Enter Tasks
        driver.findElement(By.id("edit-field-learning-plan-mt-und-0-field-learning-plan-task-und-0-value")).clear();
        Tracking = func.RandomWords(1);
        driver.findElement(By.id("edit-field-learning-plan-mt-und-0-field-learning-plan-task-und-0-value")).sendKeys(Tracking[0]);
        // Click Save & Publish
        driver.findElement(By.cssSelector("span.dummy-submit-publish-link.form-submit")).click();
        // Click Yes Button
        driver.findElement(By.xpath("//div[@id='edit-actions']/div/div[2]/input")).click();

        // Click Assign
        driver.findElement(By.linkText("Assign")).click();

        // Assign Homework
        // Enter Due Date
        driver.findElement(By.xpath("//input")).click();
        driver.findElement(By.linkText("11")).click();
        // Select Grading Period
        new Select(driver.findElement(By.id("edit-field-gradebook-submit-semester-und"))).selectByVisibleText("First Semester");
        // Select Grading Standard
        new Select(driver.findElement(By.id("edit-field-gradebook-grading-standard-und"))).selectByVisibleText("algebra grading");
        // Select Student
        new Select(driver.findElement(By.name("assign_students_unsel"))).selectByVisibleText("[10] Basant Sharma");
        // Click "+" Symbol
        driver.findElement(By.linkText("Add")).click();
        // Click Assign Button
        driver.findElement(By.id("edit-submit")).click();
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