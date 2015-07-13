/**
 * Teacher set the milestone for particular student. Means what student have to do in their lives and in what time. Time can be
 * set as month or Fall, Spring and Summer.
 */

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
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddMilestoneInItask {
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
        driver.get(func.baseUrl + "/student-planner");
    }

    @Test
    public void testAddMilestoneInItask() throws Exception {
        // Click ITasks
        driver.findElement(By.linkText("ITASKS")).click();

        // Click Career & Majors
        driver.findElement(By.cssSelector("div.careers-major-link")).click();
        // Click Cross Button
        driver.findElement(By.cssSelector("div.close-button > img")).click();

        // Click Add milestone
        driver.findElement(By.linkText("Add Milestone")).click();
        // Enter Milestone
        driver.findElement(By.id("edit-title")).clear();
        Tracking = func.RandomWords(2);
        driver.findElement(By.id("edit-title")).sendKeys(Tracking[0] + " " + Tracking[1]);
        // Enter Task
        driver.findElement(By.id("edit-field-ltp-milestone-task-und-0-value")).clear();
        Tracking = func.RandomWords(2);
        driver.findElement(By.id("edit-field-ltp-milestone-task-und-0-value")).sendKeys(Tracking[0] + " " + Tracking[1]);
        // Click "+" Symbol
        driver.findElement(By.xpath("//div[2]/input")).click();
        // Enter Another Task
        driver.findElement(By.id("edit-field-ltp-milestone-task-und-1-value")).clear();
        Tracking = func.RandomWords(2);
        driver.findElement(By.id("edit-field-ltp-milestone-task-und-1-value")).sendKeys(Tracking[0] + " " + Tracking[1]);
        // Select Grade
        driver.findElement(By.xpath("//div[5]/div/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_field_ltp_milestone_grade_dummy_chzn_o_2\"]")).click();
        // Select Team
        driver.findElement(By.xpath("//div[6]/div/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_field_ltp_milestone_month_dummy_chzn_o_2\"]")).click();
        // Enter Order
        driver.findElement(By.id("edit-field-ltp-milestone-order-und-0-value")).clear();
        driver.findElement(By.id("edit-field-ltp-milestone-order-und-0-value")).sendKeys("4");
        // Enter Notes
        driver.findElement(By.id("edit-field-ltp-milestone-notes-und-0-value")).clear();
        Tracking = func.RandomWords(2);
        driver.findElement(By.id("edit-field-ltp-milestone-notes-und-0-value")).sendKeys(Tracking[0] + " " + Tracking[1]);
        // Click Save Button
        driver.findElement(By.xpath("//div[11]/input")).click();

        // Click Selected Grade
        driver.findElement(By.xpath("//div[4]/div/div/div[2]/div/ul/li")).click();
        // Click Selected Team
        driver.findElement(By.xpath("//div[2]/div/ul[2]/li[2]")).click();
        Thread.sleep(300);
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