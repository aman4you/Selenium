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

public class SaveGradePercentage_Pend {
    COTFunctions func;
    private WebDriver driver;
    private boolean acceptNextAlert = true;
    private int Max = 50;
    private Double DecimalValue = 0.0;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        func = new COTFunctions(driver);
        driver.manage().timeouts().implicitlyWait(func.timeoutOFAllElement, TimeUnit.SECONDS);

        driver.get(func.baseUrl + "/");
        func.CheckLogin();
        func.LoginRole("SchoolAdmin");
        driver.get(func.baseUrl + "/discipline-admin");
    }

    @Test
    public void testSaveGradePercentage() throws Exception {
        // Click Grade Percentage
        driver.findElement(By.linkText("Grade Percentages")).click();

        // Check "Letter Grading" Checked or not. If not, than Check it.
        //TODO edit system 1 is wrong as it can change
        // TODO edit letter grading with number grading
        if (!driver.findElement(By.id("edit-system-0")).isSelected()) {
            driver.findElement(By.id("edit-system-0")).click();
        }
        // Enter Minimum Passing Grade Equivalence
        driver.findElement(By.id("edit-passing-marks")).clear();
        DecimalValue = func.RandomDecimalNumber(Max);
        driver.findElement(By.id("edit-passing-marks")).sendKeys(String.valueOf(DecimalValue));
        // Click Save For Minimum Passing Grade Equivalence
        //TODO replace form [1] [2] etc by a proper class
        driver.findElement(By.xpath("//form[@id='grade-passing-equivalence-form']//input[@class='form-submit']")).click();
        // Enter Minimum Passing Grade Transfer
        driver.findElement(By.id("edit-min-pass-t")).clear();
        DecimalValue = func.RandomDecimalNumber(Max);
        driver.findElement(By.id("edit-min-pass-t")).sendKeys(String.valueOf(DecimalValue));
        // Click Save For Minimum Passing Grade Transfer
        driver.findElement(By.xpath("//form[@id='gradebook-min-passing-t-form']//input[@class='form-submit']")).click();
        // Check "Remove" button for "Signature" present or not. If present than click it.
        //TODO replace id name
        if(isElementPresent(By.xpath("//div[@id='edit-field-transcript-signature']//input[@value='Remove']"))) {
            driver.findElement(By.xpath("//div[@id='edit-field-transcript-signature']//input[@value='Remove']")).click();
        }
        // Upload File
        driver.findElement(By.xpath("//div[@id='edit-field-transcript-signature']//input[@type='file']")).sendKeys("D:\\software\\COTFiles\\abc.jpg");
        // Check "Remove" button for "School logo" present or not. If present than click it.
        if(isElementPresent(By.xpath("//div[@id='edit-field-transcript-school-logo']//input[@value='Remove']"))) {
            driver.findElement(By.xpath("//div[@id='edit-field-transcript-school-logo']//input[@value='Remove']")).click();
        }
        // Upload File
        driver.findElement(By.xpath("//div[@id='edit-field-transcript-school-logo']//input[@type='file']")).sendKeys("D:\\software\\COTFiles\\xyz.jpg");
        // Click Save
        driver.findElement(By.xpath("//form[@id='transcript-images-node-form']//input[@value='Save']")).click();
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

