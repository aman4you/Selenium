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

public class CreateResume {
    COTFunctions func;
    private WebDriver driver;
    private String[] Tracking = null;
    private String TrackingValues = "";
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
        driver.get(func.baseUrl + "/journals");
    }

    @Test
    public void testCreateResume() throws Exception {
        // Click Resume
        driver.findElement(By.linkText("RESUME")).click();
        // Click Add Resume
        driver.findElement(By.linkText("ADD RESUME")).click();

        // Create Resume
        // Enter Contact Info
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-resume-contact-info-form')]//textarea")).clear();
        Tracking = func.RandomWords(7);
        for(int i= 0 ; i < Tracking.length ; i++) {
            TrackingValues = TrackingValues + " " + Tracking[i];
        }
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-resume-contact-info-form')]//textarea")).sendKeys(TrackingValues);
        // Enter Objective
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-essay-objective-form')]//textarea")).clear();
        Tracking = func.RandomWords(10);
        for(int i= 0 ; i < Tracking.length ; i++) {
            TrackingValues = TrackingValues + " " + Tracking[i];
        }
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-essay-objective-form')]//textarea")).sendKeys(TrackingValues);
        // Education
        // Enter School/College/Course
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-education-school-college')]//input")).clear();
        Tracking = func.RandomWords(3);
        for(int i= 0 ; i < Tracking.length ; i++) {
            TrackingValues = TrackingValues + " " + Tracking[i];
        }
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-education-school-college')]//input")).sendKeys(TrackingValues);
        // Enter Location
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-education-location')]//input")).clear();
        Tracking = func.RandomWords(1);
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-education-location')]//input")).sendKeys(Tracking[0]);
        // Enter Year
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-education-year')]//input")).clear();
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-education-year')]//input")).sendKeys("June 2014 - April 2015");
        // Enter Brief Info
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-education-brief-info')]//textarea")).clear();
        Tracking = func.RandomWords(10);
        for(int i= 0 ; i < Tracking.length ; i++) {
            TrackingValues = TrackingValues + " " + Tracking[i];
        }
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-education-brief-info')]//textarea")).sendKeys(TrackingValues);
        Thread.sleep(5000);
        // Enter Awards & Honors
        driver.findElement(By.xpath("//table[@id='field-essay-award-honors-values']//input")).clear();
        Tracking = func.RandomWords(3);
        driver.findElement(By.xpath("//table[@id='field-essay-award-honors-values']//input")).sendKeys(Tracking[0] + " " + Tracking[1] + " " + Tracking[2]);
        // Enter Extracurricular & Leadership Experience
        driver.findElement(By.xpath("//table[@id='field-essay-extracurricular-values']//input")).clear();
        Tracking = func.RandomWords(1);
        driver.findElement(By.xpath("//table[@id='field-essay-extracurricular-values']//input")).sendKeys(Tracking[0]);
        // Enter Work Experience
        // Enter organisation
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-work-organisation')]//input")).clear();
        Tracking = func.RandomWords(1);
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-work-organisation')]//input")).sendKeys(Tracking[0]);
        // Enter Location
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-work-location')]//input")).clear();
        Tracking = func.RandomWords(1);
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-work-location')]//input")).sendKeys(Tracking[0]);
        // Enter Year
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-work-year')]//input")).clear();
        driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-work-year')]//input")).sendKeys("June 2013 - April 2014");
        // Save Resume
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

