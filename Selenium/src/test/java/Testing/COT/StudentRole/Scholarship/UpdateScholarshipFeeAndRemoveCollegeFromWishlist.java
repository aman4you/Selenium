/**
 * Student can update how much scholarship he got, in front of respective scholarship through click applied checkbox.
 * Student can also remove scholarship from wishList.
 */

package Testing.COT.StudentRole.Scholarship;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpdateScholarshipFeeAndRemoveCollegeFromWishlist {
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
        func.LoginRole("Student");
        driver.get(func.baseUrl + "/scholarship-search");
    }

    @Test
    public void testUpdateSchoarshipFeeAndRemoveCollegeFromWishlist() throws Exception {
        // Click Scholarship Wish List
        driver.findElement(By.linkText("SCHOLARSHIP WISH LIST")).click();
        // Check Row Data Exist or Not
        List<WebElement> TableRows = driver.findElements(By.xpath("//table[contains(@class, ' scholarship-wishlist-table')]/tbody/tr"));
        int rowNumber = TableRows.size();
        int rowWithData = rowNumber - 1;
        if(rowWithData > 0) {
            // Access Random Row
            RandomRow = func.RandomIntegerNumber(rowWithData);
            // Check Applied Checkbox Clicked or Not
            if (!driver.findElement(By.xpath("//tr[" + RandomRow + "]//div[@class='checkbox-applied']//input")).isSelected()) {
                driver.findElement(By.xpath("//tr[" + RandomRow + "]//div[@class='checkbox-applied']//input")).click();
                // Wait for checkbox to be Clicked
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("throbber")));
            }
            // Click Awarded Link
            driver.findElement(By.xpath("//tr[" + RandomRow + "]//div[@class='received-amount-text']")).click();
            // Enter Received Amount
            driver.findElement(By.xpath("//tr[" + RandomRow + "]//input[@type='text']")).clear();
            driver.findElement(By.xpath("//tr[" + RandomRow + "]//input[@type='text']")).sendKeys(String.valueOf(200));
            // Click Save Button
            driver.findElement(By.xpath("//tr[" + RandomRow + "]//input[@type='submit']")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("throbber")));
            // Click Remove Link
            String ScholarshipID = driver.findElement(By.xpath("//tr[" + RandomRow  + "]//div[@class='scholarship-website-link']")).getAttribute("id");
            driver.findElement(By.xpath("//tr[" + RandomRow + "]//a[contains(@class, '" + ScholarshipID + "')]")).click();
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