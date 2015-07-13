/**
 * Student search college in SearchCollege page and then add the college to wishList. The list of wishList colleges are shown at
 * wishList page. Student can also know how much he eligible for the wishList colleges according to his scores or target scores in
 * ACT or SAT. Student can set date in calender for college about his action.
 */

package Testing.COT.StudentRole.CollegeApp;

/**
 * Created by om on 11/12/2014.
 */
import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AddDeadlineForCollege_Pending {
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
        driver.get(func.baseUrl + "/college-search");
    }

    @Test
    public void testAddDealineForCollege() throws Exception {
        // Click WishList
        driver.findElement(By.linkText("WISHLIST")).click();
        // Click ACT
        driver.findElement(By.id("edit-act-sat-select-act")).click();
        // Click Target Scores
        driver.findElement(By.id("edit-sat-select-score-target-score")).click();
        //
        driver.findElement(By.xpath("//div[@id='act_math_slider_jq_slider']/a")).click();
        driver.findElement(By.id("edit-user-deadline-datepicker-popup-0")).click();
        driver.findElement(By.linkText("21")).click();
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

// Data not available at site.