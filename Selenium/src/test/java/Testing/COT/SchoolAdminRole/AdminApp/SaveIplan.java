package Testing.COT.SchoolAdminRole.AdminApp;

/**
 * Created by om on 11/12/2014.
 */
import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SaveIplan {
    COTFunctions func;
    private WebDriver driver;
    private String[] Tracking = null;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        func = new COTFunctions(driver);
        driver.manage().timeouts().implicitlyWait(func.timeoutOFAllElement, TimeUnit.SECONDS);

        driver.get(func.baseUrl + "/");
        func.CheckLogin();
        func.LoginRole("SchoolAdmin");
        driver.get(func.baseUrl + "/grad-req");
    }

    @Test
    public void testSaveIplan() throws Exception {
        // Click IPlan
        driver.findElement(By.cssSelector("a.long_term_planner_admin-link.admin-menu-tab-link")).click();
        // Click Add Milestone
        driver.findElement(By.linkText("Add Milestone")).click();

        // Add Milestone
        // Enter Milestone
        driver.findElement(By.id("edit-title")).clear();
        Tracking = func.RandomWords(4);
        driver.findElement(By.id("edit-title")).sendKeys(Tracking[0] + Tracking[1] + Tracking[2] + Tracking[3]);
        // Click Add To Calender
        driver.findElement(By.linkText("Add to Calendar")).click();
        driver.findElement(By.xpath("//div[@class='date-padding']//input")).click();
        driver.findElement(By.linkText("25")).click();
        // Click "+" Symbol And Enter Task
        driver.findElement(By.id("edit-field-ltp-milestone-task-und-add-more")).click();
        Tracking = func.RandomWords(4);
        driver.findElement(By.xpath("//tr[@class='draggable even']//input")).sendKeys(Tracking[0] + Tracking[1] + Tracking[2] + Tracking[3]);
        // Select Grade
        driver.findElement(By.xpath("//div[@id='edit_field_ltp_milestone_grade_dummy_chzn']//span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_field_ltp_milestone_grade_dummy_chzn_o_3\"]")).click();
        // Select Team
        driver.findElement(By.xpath("//div[@id='edit_field_ltp_milestone_month_dummy_chzn']//span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_field_ltp_milestone_month_dummy_chzn_o_2\"]")).click();
        // Enter Order
        driver.findElement(By.xpath("//div[@id='field-ltp-milestone-order-add-more-wrapper']//input")).clear();
        driver.findElement(By.xpath("//div[@id='field-ltp-milestone-order-add-more-wrapper']//input")).sendKeys("4");
        // Enter Notes
        driver.findElement(By.className("form-textarea")).clear();
        Tracking = func.RandomWords(4);
        driver.findElement(By.className("form-textarea")).sendKeys(Tracking[0] + Tracking[1] + Tracking[2] + Tracking[3]);
        // Click Save Button
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

