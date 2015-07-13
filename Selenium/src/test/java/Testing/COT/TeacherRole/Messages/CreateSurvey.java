package Testing.COT.TeacherRole.Messages;

/**
 * Created by om on 11/12/2014.
 */
import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class CreateSurvey {
    COTFunctions func;
    private WebDriver driver;
    private String[] Tracking = null;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        func = new COTFunctions(driver);
        driver.manage().timeouts().implicitlyWait(func.timeoutOFAllElement, TimeUnit.SECONDS);

        driver.get(func.baseUrl + "/");
        func.CheckLogin();
        func.LoginRole("Teacher");
        driver.get(func.baseUrl + "/messages");
    }

    @Test
    public void testCreateSurvey() throws Exception {
        // Click Survey
        driver.findElement(By.linkText("SURVEY")).click();

        // Create Survey
        // Click Create Survey
        driver.findElement(By.linkText("CREATE SURVEY")).click();
        // Enter Title
        driver.findElement(By.id("edit-survey-title")).clear();
        Tracking = func.RandomWords(2);
        driver.findElement(By.id("edit-survey-title")).sendKeys(Tracking[0] + " " + Tracking[1]);
        // Click Create New Survey
        driver.findElement(By.xpath("//form/div/input")).click();
        // Drag And Drop "Checkboxes" Element
        WebElement dragElement = driver.findElement(By.xpath("//div[2]/div/div/ul/li"));
        WebElement dropElement = driver.findElement(By.xpath("//div[2]/div[2]"));
        Actions dragAndDrop = new Actions(driver);
        // Drag dragElement and drop at dropElement
        Action action = dragAndDrop.dragAndDrop(dragElement, dropElement).build();
        action.perform();
        // Click Edit Link of Element
        driver.findElement(By.xpath("//a[2]")).click();
        // Enter Title of Question
        driver.findElement(By.xpath("//fieldset/div/div/input")).clear();
        Tracking = func.RandomWords(2);
        driver.findElement(By.xpath("//fieldset/div/div/input")).sendKeys(Tracking[0] + " " + Tracking[1]);
        // Wait For Title To Save
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
        Thread.sleep(3000);
        // Click Validation
        driver.findElement(By.xpath("//form[@id='form-builder-field-configure']/div/ul/li[3]/span")).click();
        // Click Required
        driver.findElement(By.id("edit-required")).click();
        // Wait For Validation  To Save
        Thread.sleep(3000);
        // Click Save Survey
        driver.findElement(By.linkText("SAVE SURVEY")).click();

        // Assign Survey
        // Click Assign Survey
        driver.findElement(By.linkText("ASSIGN SURVEY")).click();
        // Select Survey
        driver.findElement(By.xpath("//a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_field_admin_survey_und_chzn_o_4\"]")).click();
        // Select Group
        driver.findElement(By.xpath("//div[5]/div/div/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_group_id_chzn_o_6\"]")).click();
        // Click Save Button
        driver.findElement(By.xpath("//div[7]/input")).click();
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