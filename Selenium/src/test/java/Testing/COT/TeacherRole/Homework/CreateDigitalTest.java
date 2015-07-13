/**
 * Teacher can create online test form using elements that inherit by drag and drop feature. To create question, teacher have
 * to provide following information such as name of question, how much marks for it, name of options and correct option. After
 * create online test form, teacher will assign test to students. When student submit the test, it will automatically test by
 * the system and teacher can see the submitted test through "Grade Test".
 */

package Testing.COT.TeacherRole.Homework;

import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateDigitalTest {
    COTFunctions func;
    private WebDriver driver;
    private WebDriverWait wait;
    private int Max = 0;
    private int IntegerValue = 0;
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
        driver.get(func.baseUrl + "/lesson-library");
    }

    @Test
    public void testCreateDigital() throws Exception {
        // Click Online Tests
        driver.findElement(By.linkText("ONLINE TESTS")).click();

        // Create Test
        // Click Create Online Tests
        driver.findElement(By.linkText("Create Online Tests")).click();
        // Enter Test Title
        driver.findElement(By.id("edit-title")).clear();
        Tracking = func.RandomWords(2);
        driver.findElement(By.id("edit-title")).sendKeys(Tracking[0] + " " + Tracking[1]);
        // Click Save Button
        driver.findElement(By.xpath("//form[@id='onlinetest-title-form']/div/input")).click();
        // Drag And Drop "Multiple Answers" Element
        WebElement dragElement = driver.findElement(By.xpath("//div[@id='form-builder-field-palette']/div/ul/li"));
        WebElement dropElement = driver.findElement(By.xpath("//div[@id='form-builder']/div[4]"));
        Actions dragAndDrop = new Actions(driver);
        // Drag dragElement and drop at dropElement
        Action action = dragAndDrop.dragAndDrop(dragElement, dropElement).build();
        action.perform();
        // Click Edit Link of Element
        driver.findElement(By.xpath("//a[2]")).click();
        // Enter Title of Question
        driver.findElement(By.id("edit-title")).clear();
        Tracking = func.RandomWords(2);
        driver.findElement(By.id("edit-title")).sendKeys(Tracking[0] + " " + Tracking[1]);
        // Enter Maximum Marks
        driver.findElement(By.id("edit-max-marks")).clear();
        Max = 10;
        IntegerValue = func.RandomIntegerNumber(Max);
        driver.findElement(By.id("edit-max-marks")).sendKeys(String.valueOf(IntegerValue));
        // Wait For Title And Marks To Save
        Thread.sleep(3000);
        // Click Options
        driver.findElement(By.xpath("//form[@id='form-builder-field-configure']/div/ul/li[2]/span")).click();
        // Click Add Item
        driver.findElement(By.linkText("Add item")).click();
        // Enter First Option Name
        driver.findElement(By.xpath("//td[2]/input[2]")).clear();
        Tracking = func.RandomWords(1);
        driver.findElement(By.xpath("//td[2]/input[2]")).sendKeys(Tracking[0]);
        // Enter Second Option Name
        driver.findElement(By.xpath("//tr[2]/td[2]/input[2]")).clear();
        Tracking = func.RandomWords(1);
        driver.findElement(By.xpath("//tr[2]/td[2]/input[2]")).sendKeys(Tracking[0]);
        // Enter Third Option Name
        driver.findElement(By.xpath("//tr[3]/td[2]/input[2]")).clear();
        Tracking = func.RandomWords(1);
        driver.findElement(By.xpath("//tr[3]/td[2]/input[2]")).sendKeys(Tracking[0]);
        // Enter Fourth Option Name
        driver.findElement(By.xpath("//tr[4]/td[2]/input[2]")).clear();
        Tracking = func.RandomWords(1);
        driver.findElement(By.xpath("//tr[4]/td[2]/input[2]")).sendKeys(Tracking[0]);
        // Click Correct Option Checkbox
        driver.findElement(By.xpath("//tr[2]/td/input[3]")).click();
        // Wait For Options To Save
        Thread.sleep(5000);
        // Click Save Test
        driver.findElement(By.cssSelector("a.form-submit.test-save-link")).click();
        // Click Publish Test
        driver.findElement(By.cssSelector("a.form-submit.test-publish-link")).click();

        // Assign Test
        // Click Assign Link
        driver.findElement(By.xpath("//td[5]/a")).click();
        // Select Class
        new Select(driver.findElement(By.xpath("//select"))).selectByVisibleText("AP Biology : Period 1");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("throbber")));
        // Select Student
        new Select(driver.findElement(By.id("assign_students"))).selectByVisibleText("[11] Eric Lu");
        // Click Add Link
        driver.findElement(By.xpath("//div[5]/div/ul/li/a")).click();
        // Select Date
        driver.findElement(By.name("due_date[date]")).click();
        driver.findElement(By.linkText("18")).click();
        // Click Assign Button
        driver.findElement(By.xpath("//form/div/input")).click();
        // Click Online Tests
        driver.findElement(By.linkText("ONLINE TESTS")).click();

        // Look At Submitted Test
        // Click Grade Test Link
        driver.findElement(By.xpath("//td[6]/a")).click();
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