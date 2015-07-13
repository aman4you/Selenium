package Testing.COT.TeacherRole.Scholarship;

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

public class SeeScholarshipCollege {
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
        driver.get(func.baseUrl + "/scholarship-search");
    }

    @Test
    public void testSeeScholarshipCollege() throws Exception {
        // Click Scholarship Search
        driver.findElement(By.linkText("SCHOLARSHIP SEARCH")).click();

        // Select Student
        driver.findElement(By.xpath("//div[@id='edit_students_chzn']/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_students_chzn_o_4\"]")).click();

        // Click Search Scholarships
        driver.findElement(By.cssSelector("div.scholarship-search-button")).click();
        // Check Scholarship form Has Reset Form Option or Not
        if(isElementPresent(By.linkText("Reset Form"))) {
            // Click Reset Form
            driver.findElement(By.linkText("Reset Form")).click();
        }
        // Click Gender
        driver.findElement(By.id("edit-sex-1")).click();
        // Click Citizen of USA
        driver.findElement(By.id("edit-citizen-1")).click();
        // Select Date
        driver.findElement(By.id("edit-dob-datepicker-popup-0")).click();
        driver.findElement(By.linkText("8")).click();
        // Select State
        driver.findElement(By.xpath("//div[@id='edit_state_chzn']/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_state_chzn_o_2\"]")).click();
        // Enter ZipCode
        driver.findElement(By.id("edit-zipcode")).clear();
        driver.findElement(By.id("edit-zipcode")).sendKeys("83672");
        // Enter SAT Verbal
        driver.findElement(By.id("edit-satv")).clear();
        driver.findElement(By.id("edit-satv")).sendKeys("30");
        // Enter SAT Maths
        driver.findElement(By.id("edit-satm")).clear();
        driver.findElement(By.id("edit-satm")).sendKeys("33");
        // Enter SAT Writing
        driver.findElement(By.id("edit-satw")).clear();
        driver.findElement(By.id("edit-satw")).sendKeys("76");
        // Enter ACT
        driver.findElement(By.id("edit-act")).clear();
        driver.findElement(By.id("edit-act")).sendKeys("66");
        // Enter GPA
        driver.findElement(By.id("edit-gpa")).clear();
        driver.findElement(By.id("edit-gpa")).sendKeys("7.0");
        // Select Major
        driver.findElement(By.xpath("//div[@id='edit_major_chzn']/ul/li/input")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_major_chzn_o_2\"]")).click();
        // Select Career
        driver.findElement(By.xpath("//div[@id='edit_career_chzn']/ul/li/input")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_career_chzn_o_3\"]")).click();
        // Select Interest
        driver.findElement(By.xpath("//div[@id='edit_interest_chzn']/ul/li/input")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_interest_chzn_o_4\"]")).click();
        // Select Athletics
        driver.findElement(By.xpath("//div[@id='edit_athletics_chzn']/ul/li/input")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_athletics_chzn_o_2\"]")).click();
        // Select Religion
        driver.findElement(By.xpath("//div[@id='edit_religion_chzn']/ul/li/input")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_religion_chzn_o_8\"]")).click();
        // Select Race
        driver.findElement(By.xpath("//div[@id='edit_race_chzn']/ul")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_race_chzn_o_6\"]")).click();
        // Select Disability
        driver.findElement(By.xpath("//div[@id='edit_disability_chzn']/ul")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_disability_chzn_o_4\"]")).click();
        // Select Membership
        driver.findElement(By.xpath("//div[@id='edit_membership_chzn']/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_membership_chzn_o_2\"]")).click();
        // Select Military
        driver.findElement(By.xpath("//div[@id='edit_military_chzn']/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_military_chzn_o_3\"]")).click();
        // Select Circumstance
        driver.findElement(By.xpath("//div[@id='edit_circumstance_chzn']/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_circumstance_chzn_o_4\"]")).click();
        // Click Search
        driver.findElement(By.xpath("//input[@id='edit-search-button']")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='edit-search-button']")));

        // Check Result Received or Not
        try {
            assertTrue(func.isTextPresent(driver.findElement(By.className("result-count")).getText()));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }

        // Click Add to WishList
        driver.findElement(By.xpath("//tr[2]/td[4]/a")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("throbber")));
        Thread.sleep(4000);
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