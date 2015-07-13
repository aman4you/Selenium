/**
 * Docushare App provided for both teacher and student and for both by default 3-3 folders created, at these folders they can
 * manage their files. Teacher can also see files of student that store at folder "current class".
 *                                                    At "current class" folder of student there will create default folders
 * of subject names that student studied and at "current class" folder of teacher there will create default folders of subject
 * names that teacher teaches.
 */

package Testing.COT.TeacherRole.Docushare;

/**
 * Created by om on 11/12/2014.
 */
import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateFilesAndFolder {
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
        func.LoginRole("Teacher");
        driver.get(func.baseUrl + "/document-locker");
    }

    @Test
    public void testCreateFilesAndFolder() throws Exception {
        // Click Docushare
        driver.findElement(By.linkText("DOCUSHARE")).click();

        // Find File
        // Current Class
        driver.findElement(By.xpath("//li[2]/div/span")).click();
        // Click 3139 - AP Biology
        driver.findElement(By.xpath("//li[3]/div/span")).click();
        // Click Student Files
        driver.findElement(By.id("edit-files-type-1")).click();
        // Select Period
        driver.findElement(By.xpath("//a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_period_chzn_o_1\"]")).click();
        // Wait For Student Select List To load
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ajax-waiting")));
        // Select Student
        driver.findElement(By.xpath("//div[2]/div/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_student_name__2_chzn_o_1\"]")).click();
        // Wait For File To Load
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ajax-waiting")));

        // Upload File
        // Click My Files
        driver.findElement(By.id("edit-files-type-0")).click();
        // Click Upload Document
        driver.findElement(By.cssSelector("span.show-file-add-form")).click();
        // Enter Document Name
        driver.findElement(By.id("edit-title")).clear();
        Tracking = func.RandomWords(2);
        driver.findElement(By.id("edit-title")).sendKeys(Tracking[0] + " " + Tracking[1]);
        // Upload File
        driver.findElement(By.id("edit-field-doc-loc-uploaded-file-und-0-upload")).sendKeys("C:\\Users\\om\\Downloads\\abc.txt");
        // Click Save Button
        driver.findElement(By.xpath("//form/div/div[2]/input")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form/div/div[2]/input")));
        // Wait For File To Load
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ajax-waiting")));

        // Download File
        // Check hidden checkbox
        WebElement Element1 = driver.findElement(By.xpath("//div/span/a"));
        WebElement HiddenElement1 = driver.findElement(By.xpath("//span/div/input"));
        Actions action1 = new Actions(driver);
        // Move To Element
        action1.moveToElement(Element1).perform();
        // Wait For Checkbox To Be Shown
        Thread.sleep(300);
        // Click Checkbox
        action1.click(HiddenElement1).perform();
        // Click Download Button
        driver.findElement(By.id("edit-files-submit")).click();

        // Share File
        // Click Share Button
        driver.findElement(By.id("edit-share-files")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("throbber")));
        // Click Student
        driver.findElement(By.id("edit-assigned-user-type-2")).click();
        // Select Options
        driver.findElement(By.xpath("//li/input")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_assigned_individual_chzn_o_1\"]")).click();
        // Click Save Button
        driver.findElement(By.xpath("//div[4]/input")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[4]/input")));

        // Rename File
        // Click Uploaded Document Link
        driver.findElement(By.xpath("//div/span/a")).click();
        // Enter Document Name
        driver.findElement(By.xpath("//div[2]/form/div/fieldset/div/div/input")).clear();
        Tracking = func.RandomWords(2);
        driver.findElement(By.xpath("//div[2]/form/div/fieldset/div/div/input")).sendKeys(Tracking[0] + " " + Tracking[1]);
        // Select Folder Name
        new Select(driver.findElement(By.xpath("//div[2]/form/div/fieldset/div/div[2]/div/select"))).selectByVisibleText("3139 - AP Biology");
        // Click Save Button
        driver.findElement(By.xpath("//div[2]/form/div/div[2]/input")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[2]/form/div/div[2]/input")));

        // Delete File
        // Click Uploaded Document Link
        driver.findElement(By.xpath("//div/span/a")).click();
        // Click Delete Button
        driver.findElement(By.xpath("//form/div/div[2]/input[2]")).click();
        driver.findElement(By.xpath("//div/div/input")).click();
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