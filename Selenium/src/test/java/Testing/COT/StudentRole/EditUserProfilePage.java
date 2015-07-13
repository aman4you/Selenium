package Testing.COT.StudentRole;

import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EditUserProfilePage {
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
        driver.get(func.baseUrl + "/scholarship-search");
    }

    @Test
    public void testEditUserProfilePage() throws Exception {
        // Click Account Holder Name
        driver.findElement(By.cssSelector("img[alt=\"my image\"]")).click();
        // Click My Account
        driver.findElement(By.xpath("//div[contains(@class, 'top-menu')]//a[@class='user-dropdown-account-link']")).click();

        // Click Edit Link
        driver.findElement(By.xpath("//a[@class='general-edit-user']")).click();
        // Enter Address Line 2
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-user-address2')]//input")).clear();
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-user-address2')]//input")).sendKeys("3/228 jaipur road");
        // Click Save
        driver.findElement(By.xpath("//form[@id='user-profile-form']//input[@class='form-submit']")).click();
        // Click Edit link
        driver.findElement(By.xpath("//a[@class='general-edit-user']")).click();
        // Click Disabled
        driver.findElement(By.id("edit-field-user-my-digital-presence-und-0")).click();
        // Click Enabled
        driver.findElement(By.id("edit-field-user-my-digital-presence-und-1")).click();
        // Click Save
        driver.findElement(By.xpath("//form[@id='user-profile-form']//input[@class='form-submit']")).click();
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
