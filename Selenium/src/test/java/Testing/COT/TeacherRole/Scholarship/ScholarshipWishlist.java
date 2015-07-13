package Testing.COT.TeacherRole.Scholarship;

/**
 * Created by om on 11/12/2014.
 */
import java.util.List;
import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScholarshipWishlist {
    COTFunctions func;
    private WebDriver driver;
    private WebDriverWait wait;
    private int RandomRow = 0;
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
        driver.get(func.baseUrl + "/scholarship-search/75501");
    }

    @Test
    public void testScholarshipWishlist() throws Exception {
        // Click Scholarship Wish List
        driver.findElement(By.linkText("SCHOLARSHIP WISH LIST")).click();

        // Select Student
        driver.findElement(By.xpath("//div[@id='edit_students_chzn']/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_students_chzn_o_4\"]")).click();
        // Check Row Data Exist or Not
        List<WebElement> TableRows = driver.findElements(By.xpath("//table/tbody/tr"));
        int rowNumber = TableRows.size();
        if(rowNumber > 0) {
            // Access Random Row
            RandomRow = func.RandomIntegerNumber(rowNumber);
            // Remove Random Scholarship
            driver.findElement(By.xpath("//tr[" + RandomRow + "]/td[6]/a")).click();
            // Wait To Check Scholarship Removed
            Thread.sleep(5000);
        }
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