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

public class AddShowcase {
    COTFunctions func;
    private WebDriver driver;
    private WebDriverWait wait;
    private String[] Tracking = null;
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
    public void testAddShowcase() throws Exception {
        // Click Showcases
        driver.findElement(By.linkText("SHOWCASES")).click();
        // Click New Showcases
        driver.findElement(By.linkText("New Showcase")).click();

        // Create Showcase
        // Enter Title
        driver.findElement(By.xpath("//input[@name='title']")).clear();
        Tracking = func.RandomWords(2);
        driver.findElement(By.xpath("//input[@name='title']")).sendKeys(Tracking[0] + " " + Tracking[1]);
        // Enter Description
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-showcase-description-form')]//textarea")).clear();
        Tracking = func.RandomWords(4);
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-showcase-description-form')]//textarea")).sendKeys(Tracking[0] + " " + Tracking[1] + " " + Tracking[2] + " " + Tracking[3]);
        // Click Skills Required
        func.ClickRandomCheckboxes(By.xpath("//div[contains(@class, 'field-name-field-showcase-skills-acquired-form')]//input[@type='checkbox']"));
        // Upload Audio/Video
        driver.findElement(By.id("botr-upload-button")).click();
        driver.findElement(By.className("botr-upload-file")).sendKeys("C:\\Users\\om\\Downloads\\Andaman & Nicobar Islands(2 sec video)-BzosVry7nc4.mp4");
        driver.findElement(By.className("botr-upload-submit")).click();
        // Wait for upload
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.botr-close > img")));
        //Upload Image
        driver.findElement(By.id("showcase-upload-img")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-showcase-images-form')]//input[@type='file']")).sendKeys("C:\\Users\\om\\Downloads\\abc.jpg");
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-showcase-images-form')]//input[@type='submit']")).click();
        // Wait for upload
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-showcase-images-form')]//input[@type='submit']")));
        // Upload document
        driver.findElement(By.id("showcase-upload-doc")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-showcase-documents-form')]//input[@type='file']")).sendKeys("C:\\Users\\om\\Downloads\\bc.pdf");
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-showcase-documents-form')]//input[@type='submit']")).click();
        // Wait for upload
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-showcase-documents-form')]//input[@type='submit']")));
        // Enter Title For Document
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-showcase-documents-form')]//input[@type='text']")).sendKeys("business");
        // Wait for files to upload
        Thread.sleep(5000);
        // Save Showcase Detail
        driver.findElement(By.xpath("//div[contains(@class, 'form-actions')]//input[@type='submit']")).click();
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