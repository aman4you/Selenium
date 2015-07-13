package Testing.COT.StudentRole.Homework;

import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KhanAcademy {
    COTFunctions func;
    private int RandomRow = 0;
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
        driver.get(func.baseUrl + "/assigned-lesson");
    }

    @Test
    public void testKhanAcademy() throws Exception {
        // Click Khan Academy
        driver.findElement(By.linkText("KHAN ACADEMY")).click();

        // Click Science
        driver.findElement(By.id("science")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ajax-waiting")));
        // Click Organic Chemistry
        driver.findElement(By.id("organic-chemistry")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ajax-waiting")));
        // Click Organic Structures
        driver.findElement(By.id("organic-structures")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ajax-waiting")));

        // Select Video
        func.SelectRandomElement("//table[@id='ka-videos']/tbody/tr", "//div[@class='ka-play']");
        // Close Video
        driver.findElement(By.cssSelector("a.close-button > img")).click();
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
