package Testing.COT.TeacherRole.Iplan;

/**
 * Created by om on 11/12/2014.
 */
import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AddCourseInCoursePlanner {
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
        driver.get(func.baseUrl + "/student-planner");
    }

    @Test
    public void testAddCourseInCoursePlanner() throws Exception {
        // Select Student
        driver.findElement(By.xpath("//a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_students_chzn_o_2\"]")).click();
        // Click Add To Planner
        driver.findElement(By.linkText("Add to Planner")).click();

        // Add Course
        // Select Subjects
        driver.findElement(By.id("subject-mathematics")).click();
        // Select Course Title
        driver.findElement(By.xpath("//div[27]")).click();
        // Select Grade
        driver.findElement(By.id("grade-11")).click();
        // Select Duration
        driver.findElement(By.xpath("(//input[@name='semester'])[2]")).click();
        // Click Save Button
        driver.findElement(By.xpath("//div[2]/form/div/div[2]/input")).click();

        // Add Another Course
        // Select Subjects
        driver.findElement(By.id("subject-physical_education")).click();
        // Select Course Title
        driver.findElement(By.xpath("//div[163]")).click();
        // Select Grade
        driver.findElement(By.id("grade-12")).click();
        // Click Save Button
        driver.findElement(By.xpath("//div[2]/form/div/div[2]/input")).click();
        // Click Course Planner
        driver.findElement(By.linkText("COURSE PLANNER")).click();

        // Select Course Requirements
        driver.findElement(By.xpath("//div[5]/div/div/form/div/div/div/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_tracking_name_chzn_o_1\"]")).click();
        // Click Accept
        driver.findElement(By.linkText("ACCEPT")).click();

        // Remove Created Course
        // Click Cross Image
        driver.findElement(By.xpath("//td[5]/div/div[2]/span/img")).click();
        // Click Yes Button
        driver.findElement(By.xpath("//div[2]/input")).click();
        // Remove Another Created Course
        // Click Cross Image
        driver.findElement(By.xpath("//tr[3]/td[4]/div/div[2]/span/img")).click();
        // Click Yes Button
        driver.findElement(By.xpath("//tr[3]/td[4]/div/form/div/div[2]/input")).click();

        // Click Plan Approvals
        driver.findElement(By.cssSelector("span.course-acceptance-link")).click();
        // Click Close Button
        driver.findElement(By.cssSelector("div.close-button > img")).click();
        // Click Admissions Requirement
        driver.findElement(By.cssSelector("span.course-requirements-overlay-link")).click();
        // Click Close Button
        driver.findElement(By.cssSelector("span.confirm-button > img")).click();
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