/**
 * Student fill up the scholarship form & search for scholarship and scholarship that matches with the provided information
 * will displayed. Student can also add the scholarship to wishList that suitable to him.
 */

package Testing.COT.StudentRole.Scholarship;

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

public class AddCollegesInScholarship {
    COTFunctions func;
    private int Max =0;
    private int IntegerValue = 0;
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
        func.LoginRole("Student");
        driver.get(func.baseUrl + "/scholarship-search");
    }

    @Test
    public void testAddCollegesInScholarship() throws Exception {
        // Click Scholarship Search
        driver.findElement(By.linkText("SCHOLARSHIP SEARCH")).click();

        // Check Search Scholarship link present or not
        if(isElementPresent(By.cssSelector("div.scholarship-search-button"))) {
            // Click Search Scholarships
            driver.findElement(By.cssSelector("div.scholarship-search-button")).click();
        }
        // Check Scholarship form Has Reset Form Option or Not
        if(isElementPresent(By.className("reset-scholarship-search-link"))) {
            // Click Reset Form
            driver.findElement(By.className("reset-scholarship-search-link")).click();
        }
        // Click Gender
        driver.findElement(By.id("edit-sex-1")).click();
        // Click Citizen of USA
        driver.findElement(By.id("edit-citizen-1")).click();
        // Select Date
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-dob-date')]/input")).click();
        driver.findElement(By.linkText("8")).click();
        // Select State
        func.SelectRandomChosenOption("//div[@id='edit_state_chzn']//ul[@class='chzn-results']/li", "//div[@id='edit_state_chzn']/a/span", "//li[@id='edit_state_chzn_o_");
        // Enter ZipCode
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-zipcode')]/input")).clear();
        Max = 3000;
        IntegerValue = func.RandomIntegerNumber(Max);
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-zipcode')]/input")).sendKeys(String.valueOf(IntegerValue));
        // Enter SAT Verbal
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-satv')]/input")).clear();
        Max = 100;
        IntegerValue = func.RandomIntegerNumber(Max);
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-satv')]/input")).sendKeys(String.valueOf(IntegerValue));
        // Enter SAT Maths
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-satm')]/input")).clear();
        Max = 100;
        IntegerValue = func.RandomIntegerNumber(Max);
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-satm')]/input")).sendKeys(String.valueOf(IntegerValue));
        // Enter SAT Writing
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-satw')]/input")).clear();
        Max = 100;
        IntegerValue = func.RandomIntegerNumber(Max);
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-satw')]/input")).sendKeys(String.valueOf(IntegerValue));
        // Enter ACT
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-act')]/input")).clear();
        Max = 100;
        IntegerValue = func.RandomIntegerNumber(Max);
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-act')]/input")).sendKeys(String.valueOf(IntegerValue));
        // Enter GPA
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-gpa')]/input")).clear();
        Max = 100;
        IntegerValue = func.RandomIntegerNumber(Max);
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-gpa')]/input")).sendKeys(String.valueOf(IntegerValue));
        // Select Major
        func.SelectRandomChosenOption("//div[@id='edit_major_chzn']//ul[@class='chzn-results']/li", "//div[@id='edit_major_chzn']/ul/li/input", "//li[@id='edit_major_chzn_o_");
        // Select Career
        func.SelectRandomChosenOption("//div[@id='edit_career_chzn']//ul[@class='chzn-results']/li", "//div[@id='edit_career_chzn']/ul/li/input", "//li[@id='edit_career_chzn_o_");
        // Select Interest
        func.SelectRandomChosenOption("//div[@id='edit_interest_chzn']//ul[@class='chzn-results']/li", "//div[@id='edit_interest_chzn']/ul/li/input", "//li[@id='edit_interest_chzn_o_");
        // Select Athletics
        func.SelectRandomChosenOption("//div[@id='edit_athletics_chzn']//ul[@class='chzn-results']/li", "//div[@id='edit_athletics_chzn']/ul/li/input", "//li[@id='edit_athletics_chzn_o_");
        // Select Religion
        func.SelectRandomChosenOption("//div[@id='edit_religion_chzn']//ul[@class='chzn-results']/li", "//div[@id='edit_religion_chzn']/ul/li/input", "//li[@id='edit_religion_chzn_o_");
        // Select Race
        func.SelectRandomChosenOption("//div[@id='edit_race_chzn']//ul[@class='chzn-results']/li", "//div[@id='edit_race_chzn']/ul", "//li[@id='edit_race_chzn_o_");
        // Select Disability
        func.SelectRandomChosenOption("//div[@id='edit_disability_chzn']//ul[@class='chzn-results']/li", "//div[@id='edit_disability_chzn']/ul", "//li[@id='edit_disability_chzn_o_");
        // Select Membership
        func.SelectRandomChosenOption("//div[@id='edit_membership_chzn']//ul[@class='chzn-results']/li", "//div[@id='edit_membership_chzn']/a/span", "//li[@id='edit_membership_chzn_o_");
        // Select Military
        func.SelectRandomChosenOption("//div[@id='edit_military_chzn']//ul[@class='chzn-results']/li", "//div[@id='edit_military_chzn']/a/span", "//li[@id='edit_military_chzn_o_");
        // Select Circumstance
        func.SelectRandomChosenOption("//div[@id='edit_circumstance_chzn']//ul[@class='chzn-results']/li", "//div[@id='edit_circumstance_chzn']/a/span", "//li[@id='edit_circumstance_chzn_o_");
        // Click Search
        driver.findElement(By.xpath("//span[@class='scholarship-search-submit']/input")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='scholarship-search-submit']/input")));

        // Check Result Received or Not
        try {
            assertTrue(func.isTextPresent(driver.findElement(By.className("result-count")).getText()));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }

        // Click Add to WishList
        driver.findElement(By.xpath("//a[contains(text(), \"Add to Wish List\")]")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("throbber")));
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