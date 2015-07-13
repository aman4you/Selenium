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

public class AddCoursesInIplan {
    COTFunctions func;
    private WebDriver driver;
    private WebDriverWait wait;
    private int Max = 10;
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
        driver.get(func.baseUrl + "/student-planner");
    }

    @Test
    public void testAddCoursesInIplan() throws Exception {
        // Click Add To Planner
        driver.findElement(By.linkText("Add to Planner")).click();
        // Click Subject
        func.SelectRandomElement("//div[@class='planner-subject border']/div/div", "");
        // Click Course
        func.SelectRandomElement("//div[@class='subject-topic border']/div/div[@style='display: block;']", "");
        // Click Grade
        func.SelectRandomElement("//div[@class='planner-grade border']/div/div", "");
        // Click Duration
        func.SelectRandomElement("//form[@id='display-year']/div/div[@style='display: block;']", "/input");
        // Click Save
        driver.findElement(By.xpath("//div[contains(@class, 'form-actions')]/input")).click();
        // Click Subject
        func.SelectRandomElement("//div[@class='planner-subject border']/div/div", "");
        // Click Course
        func.SelectRandomElement("//div[@class='subject-topic border']/div/div[@style='display: block;']", "");
        // Click Grade
        func.SelectRandomElement("//div[@class='planner-grade border']/div/div", "");
        // Click Duration
        func.SelectRandomElement("//form[@id='display-year']/div/div[@style='display: block;']", "/input");
        // Click Save
        driver.findElement(By.xpath("//div[contains(@class, 'form-actions')]/input")).click();
        // Click Finish
        driver.findElement(By.linkText("Finish")).click();

        // Click Home
        driver.findElement(By.xpath("//div[@id='nav-group-home']")).click();
        // Click IPlan
        driver.findElement(By.linkText("iPlan")).click();

        // Click Plan Approvals
        driver.findElement(By.cssSelector("span.course-acceptance-link")).click();
        // Close Plan Approvals
        driver.findElement(By.cssSelector("div.close-button > img")).click();
        // Click Accept
        driver.findElement(By.linkText("ACCEPT")).click();
        // Click Plan Approvals
        driver.findElement(By.cssSelector("span.course-acceptance-link")).click();
        // Close Plan Approvals
        driver.findElement(By.cssSelector("div.close-button > img")).click();

        // Click Add External Courses
        driver.findElement(By.linkText("Add External Courses")).click();
        // Select Subject
        func.SelectRandomSelectListOption("//div[contains(@class, 'form-item-subjects-list')]//select");
        // Enter Course Name
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-course-name')]//input")).clear();
        Tracking = func.RandomWords(2);
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-course-name')]//input")).sendKeys(Tracking[0] + " " + Tracking[1]);
        // Select Grade
        func.SelectRandomSelectListOptionWhichInGroup("//div[contains(@class, 'form-item-grade')]//select");
        // Enter Course Credits
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-course-credits')]//input")).clear();
        IntegerValue = func.RandomIntegerNumber(Max);
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-course-credits')]//input")).sendKeys(String.valueOf(IntegerValue));
        // Select Duration
        func.SelectRandomSelectListOptionWhichInGroup("//div[contains(@class, 'form-item-duration')]/select");
        // Click Submit Button
        driver.findElement(By.xpath("//form[@id='transferred-courses-add-form']//input[contains(@class, 'form-submit')]")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='transferred-courses-add-form']//input[contains(@class, 'form-submit')]")));
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

