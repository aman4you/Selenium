package Testing.COT.StudentRole.DigitalPortfolio;

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

public class AddJournalAndComment {
    COTFunctions func;
    private WebDriver driver;
    private WebDriverWait wait;
    private String[] Tracking = null;
    private String TrackingValues = "";
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
        driver.get(func.baseUrl + "/journals");
    }

    @Test
    public void testAddJournalAndComment() throws Exception {
        try {
            // Click Journal
            driver.findElement(By.linkText("JOURNAL")).click();
            // Click New Journal Entry
            driver.findElement(By.linkText("New Journal Entry")).click();

            // Add Journal Entry
            // Enter Title
            driver.findElement(By.xpath("//input[@name='title']")).clear();
            Tracking = func.RandomWords(2);
            driver.findElement(By.xpath("//input[@name='title']")).sendKeys(Tracking[0] + " " + Tracking[1]);
            // Click Source
            driver.findElement(By.xpath("//span[contains(@class, 'cke_button__source_label')]")).click();
            // Enter Description
            driver.findElement(By.cssSelector("textarea.cke_source.cke_enable_context_menu")).clear();
            Tracking = func.RandomWords(8);
            for(int i= 0 ; i < Tracking.length ; i++) {
                TrackingValues = TrackingValues + " " + Tracking[i];
            }
            driver.findElement(By.cssSelector("textarea.cke_source.cke_enable_context_menu")).sendKeys("<p>" + TrackingValues + " </p>");
            // Click Upload Video
            driver.findElement(By.xpath("//input[@id='botr-upload-button']")).click();
            driver.findElement(By.className("botr-upload-file")).sendKeys("C:\\Users\\om\\Downloads\\Andaman & Nicobar Islands(2 sec video)-BzosVry7nc4.mp4");
            // Click Upload
            driver.findElement(By.className("botr-upload-submit")).click();
            // Wait for upload
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.botr-close > img")));
            // Save journal entry
            driver.findElement(By.xpath("//input[@value='Save']")).click();

            // Click Add New Comment
            driver.findElement(By.id("comment-add")).click();
            // Enter Comment
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-comment-body')]//textarea")).clear();
            Tracking = func.RandomWords(2);
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-comment-body')]//textarea")).sendKeys(Tracking[0] + " " + Tracking[1]);
            // Click Save
            driver.findElement(By.xpath("//form[@id='comment-form']//input[@class='form-submit']")).click();
        } catch (Exception e) {
            String TestCase = Thread.currentThread().getStackTrace()[1].getClassName();
            func.StoreException(e, TestCase);
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