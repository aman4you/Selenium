/**
 * Planner can be turn on or off only by the school admin. If planner turn on than add to planner link and delete button visible
 * to student. Through add to planner link student can directly add the course to his plan and through delete button student can
 * directly remove the course from his plan. If planner turn off than add class link and drop class link visible to student.
 * Through add class link student can add the course to his plan and through drop class link student can remove the course from
 * his plan, only after the school admin approve the add course request and drop course request respectively.
 */

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

public class AddCourseRequest {
    COTFunctions func;
    private int RandomRow = 0;
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
        func.LoginRole("Student");
        driver.get(func.baseUrl + "/student-planner");
    }

    @Test
    public void testAddCourseRequest() throws Exception {
        // Click Course Planner
        driver.findElement(By.linkText("COURSE PLANNER")).click();
        // Click Add Class
        driver.findElement(By.className("add-class-request")).click();

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

