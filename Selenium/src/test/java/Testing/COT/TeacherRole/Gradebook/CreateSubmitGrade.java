package Testing.COT.TeacherRole.Gradebook;

import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateSubmitGrade {
    COTFunctions func;
    private WebDriver driver;
    private WebDriverWait wait;
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
    public void testCreateSubmitGrade() throws Exception {
        // Click Submit Grades
        driver.findElement(By.linkText("SUBMIT GRADES")).click();
        // Click Add Assignment/Test
        driver.findElement(By.linkText("ADD ASSIGNMENT/TEST")).click();

        // Add Assignment/Test
        // Select Course
        new Select(driver.findElement(By.xpath("//select"))).selectByVisibleText("algebra");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[3]/div/select")));
        // Select Grading Standards
        new Select(driver.findElement(By.xpath("//div[3]/div/select"))).selectByVisibleText("eynwv yrtzy");
        // Select Semester
        new Select(driver.findElement(By.xpath("//div[4]/div/select"))).selectByVisibleText("First Semester");
        // Enter Assignment Title
        driver.findElement(By.xpath("//div[5]/input")).clear();
        Tracking = func.RandomWords(2);
        driver.findElement(By.xpath("//div[5]/input")).sendKeys(Tracking[0] + " " + Tracking[1]);
        // Assessment
        // Enter Name
        driver.findElement(By.xpath("//div/div/div/input")).clear();
        Tracking = func.RandomWords(1);
        driver.findElement(By.xpath("//div/div/div/input")).sendKeys(Tracking[0]);
        // Enter Points
        driver.findElement(By.xpath("//div[2]/div/div/input")).clear();
        IntegerValue = func.RandomIntegerNumber(10);
        driver.findElement(By.xpath("//div[2]/div/div/input")).sendKeys(String.valueOf(IntegerValue));
        // Click "+" Symbol
        driver.findElement(By.xpath("//div[@id='field-assessment-collection-add-more-wrapper--2']/div/div[2]/input")).click();
        // Enter Name
        driver.findElement(By.id("edit-field-assessment-collection-und-1-field-grade-assessment-name-und-0-value")).clear();
        Tracking = func.RandomWords(1);
        driver.findElement(By.id("edit-field-assessment-collection-und-1-field-grade-assessment-name-und-0-value")).sendKeys(Tracking[0]);
        // Enter Points
        driver.findElement(By.id("edit-field-assessment-collection-und-1-field-grade-assessment-points-und-0-value")).clear();
        IntegerValue = func.RandomIntegerNumber(10);
        driver.findElement(By.id("edit-field-assessment-collection-und-1-field-grade-assessment-points-und-0-value")).sendKeys(String.valueOf(IntegerValue));
        // Click Save Button
        driver.findElement(By.xpath("//div[8]/input")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[8]/input")));
        Thread.sleep(7000);
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