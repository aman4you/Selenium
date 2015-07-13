package Testing.COT.TeacherRole.Gradebook;

import java.util.List;
import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateGradingStandard {
    COTFunctions func;
    private WebDriver driver;
    private WebDriverWait wait;
    private int RandomOption = 0;
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
        func.LoginRole("Teacher");
        driver.get(func.baseUrl + "/my-assignments");
    }

    @Test
    public void testCreateGradingStandard() throws Exception {
        // Click Grading Standards
        driver.findElement(By.linkText("GRADING STANDARDS")).click();
        if(isElementPresent(By.linkText("Click Here"))) {
            // Click "Click Here"
            driver.findElement(By.linkText("Click Here")).click();
            // Check Select List Options exist or not
            List<WebElement> SelectListOptions = driver.findElements(By.xpath("//form[@id='grading-standard-node-form']//select/option"));
            int OptionsNumber = SelectListOptions.size();
            int TotalOptionsNumber = OptionsNumber - 1;
            if(TotalOptionsNumber > 0) {
                // Access Random Option
                RandomOption = func.RandomIntegerNumber(TotalOptionsNumber);
                // Select Random Option
                new Select(driver.findElement(By.id("edit-field-gr-std-course-ref-und"))).selectByIndex(RandomOption);
            }
            // Enter Grading Attributes
            driver.findElement(By.xpath("//form[@id='grading-standard-node-form']/div/div[3]/input")).clear();
            Tracking = func.RandomWords(2);
            driver.findElement(By.xpath("//form[@id='grading-standard-node-form']/div/div[3]/input")).sendKeys(Tracking[0] + " " + Tracking[1]);
            // Enter Weight
            driver.findElement(By.id("edit-field-gr-std-weight-und-0-value")).clear();
            driver.findElement(By.id("edit-field-gr-std-weight-und-0-value")).sendKeys(String.valueOf(1));
            // Click Save Button
            driver.findElement(By.xpath("//div[6]/input")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[6]/input")));
        } else if (isElementPresent(By.linkText("+"))) {
            // Click "+" Symbol
            driver.findElement(By.linkText("+")).click();
            // Check Select List Options exist or not
            List<WebElement> SelectListOptions = driver.findElements(By.xpath("//form[@id='grading-standard-node-form']//select/option"));
            int OptionsNumber = SelectListOptions.size();
            int TotalOptionsNumber = OptionsNumber - 1;
            if(TotalOptionsNumber > 0) {
                // Access Random Option
                RandomOption = func.RandomIntegerNumber(TotalOptionsNumber);
                // Select Random Option
                new Select(driver.findElement(By.id("edit-field-gr-std-course-ref-und"))).selectByIndex(RandomOption);
            }
            // Enter Grading Attributes
            driver.findElement(By.xpath("//form[@id='grading-standard-node-form']/div/div[3]/input")).clear();
            Tracking = func.RandomWords(2);
            driver.findElement(By.xpath("//form[@id='grading-standard-node-form']/div/div[3]/input")).sendKeys(Tracking[0] + " " + Tracking[1]);
            // Enter Weight
            driver.findElement(By.id("edit-field-gr-std-weight-und-0-value")).clear();
            driver.findElement(By.id("edit-field-gr-std-weight-und-0-value")).sendKeys(String.valueOf(1));
            // Click Save Button
            driver.findElement(By.xpath("//div[6]/input")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[6]/input")));
        }

        // Click Edit
        driver.findElement(By.xpath("//td[3]/a")).click();
        // Enter Grading Attributes
        driver.findElement(By.xpath("//form[@id='grading-standard-node-form']/div/div[2]/input")).clear();
        Tracking = func.RandomWords(2);
        driver.findElement(By.xpath("//form[@id='grading-standard-node-form']/div/div[2]/input")).sendKeys(Tracking[0] + " " + Tracking[1]);
        // Click Save
        driver.findElement(By.xpath("//div[@id='edit-actions']/input")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='edit-actions']/input")));
        Thread.sleep(300);
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
