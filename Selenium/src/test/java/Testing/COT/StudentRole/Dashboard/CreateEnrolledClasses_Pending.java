/**
 * Student can select which course of which subject he want to study from which teacher. That request will be send to selected
 * teacher.
 */

package Testing.COT.StudentRole.Dashboard;

/**
 * Created by om on 11/12/2014.
 */
import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateEnrolledClasses_Pending {
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
        func.LoginRole("Student");
        driver.get(func.baseUrl + "/students-dashboard");
    }

    @Test
    public void testCreateEnrolledClasses() throws Exception {
        // Click Dashboard
        driver.findElement(By.linkText("DASHBOARD")).click();
        // Click Current Enrolled Classes
        driver.findElement(By.linkText("Current Enrolled Classes")).click();

        // Click "+" Symbol
        driver.findElement(By.cssSelector("div.plus-symbol")).click();
        // Select Subject
        driver.findElement(By.xpath("//a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_subjects_filter__2_chzn_o_6\"]")).click();
        // Wait For Course Select List To Load
        driver.findElement(By.xpath("//div[2]/div/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_course_filter__3_chzn_o_2\"]")).click();
//        List<WebElement> options = driver.findElements(By.cssSelector("b"));
//        for (WebElement option : options) {
//            if(option.getText() == "Mathematics") {
//                option.click();
//                break;
//            }
//        }
        driver.findElement(By.cssSelector("a.chzn-single.chzn-single-with-drop > span")).click();
        driver.findElement(By.id("edit-teacher-assignment-891301")).click();
        driver.findElement(By.id("edit-submit-teacher-assignment--3")).click();
        driver.findElement(By.linkText("Sumeet Pareek")).click();
        driver.findElement(By.cssSelector("div.cot-overlay.75516-891276 > div.cot-overlay-content > div.close-button-wrapper > span.confirm-button")).click();
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

// How to select item from span type drop down list(Use xpath to reach at drop down list item).