package Testing.COT.TeacherRole.Homework;

import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateDigitalLesson {
    COTFunctions func;
    private WebDriver driver;
    private WebDriverWait wait;
    private String TrackingValues = null;
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
    public void testCreateDigitalLesson() throws Exception {
        // Click Digital Lessons
        driver.findElement(By.linkText("DIGITAL LESSONS")).click();
        // Click Add a New Unit or Lesson
        driver.findElement(By.linkText("Add a New Unit or Lesson")).click();
        // Click Add New Lesson
        driver.findElement(By.linkText("Add New Lesson")).click();

        // Add a New Lesson
        // Select a Course
        driver.findElement(By.xpath("//div[@id='edit_field_ll_course_reference_und_chzn']/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_field_ll_course_reference_und_chzn_o_2\"]")).click();
        // Enter Lesson Title
        driver.findElement(By.id("edit-title--2")).clear();
        Tracking = func.RandomWords(2);
        driver.findElement(By.id("edit-title--2")).sendKeys(Tracking[0] + " " + Tracking[1]);
        // Click Save Button
        driver.findElement(By.xpath("//div[@id='edit-actions--2']/input")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText("//div[@id='edit-actions--2']/input")));

        // Create a Lesson Unit
        // Select a Lesson
        new Select(driver.findElement(By.id("edit-field-new-lesson-title-und"))).selectByVisibleText("Test");
        // Enter Title
        driver.findElement(By.id("edit-title")).clear();
        Tracking = func.RandomWords(3);
        driver.findElement(By.id("edit-title")).sendKeys(Tracking[0] + " " + Tracking[1] + " " + Tracking[2]);
        // Choose Published Option
        driver.findElement(By.id("edit-field-lesson-unit-published-und-yes")).click();
        // Click Save Button
        driver.findElement(By.xpath("//div[@id='edit-actions']/input")).click();

        // Click "+" Symbol
        driver.findElement(By.xpath("//form[@id='get-lesson-unit-widgets-form']/div/div[2]/div")).click();
        // Wait For "Add Text" Link
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.half-textarea-widget.lesson-unit-create-widget-link")));
        // Click Add Text
        driver.findElement(By.cssSelector("div.half-textarea-widget.lesson-unit-create-widget-link")).click();

        // Add Text Area Widget
        // Enter Title
        driver.findElement(By.id("edit-field-lesson-unit-title-und-0-value")).clear();
        Tracking = func.RandomWords(1);
        driver.findElement(By.id("edit-field-lesson-unit-title-und-0-value")).sendKeys(Tracking[0]);
        // Enter Body
        driver.findElement(By.id("cke_86_label")).click();
        driver.findElement(By.cssSelector("textarea.cke_source.cke_enable_context_menu")).clear();
        Tracking = func.RandomWords(8);
        for(int i= 0 ; i < Tracking.length ; i++) {
            TrackingValues = TrackingValues + " " + Tracking[i];
        }
        driver.findElement(By.cssSelector("textarea.cke_source.cke_enable_context_menu")).sendKeys("<p>" + TrackingValues + " </p>");
        // Click Save Button
        driver.findElement(By.xpath("//div[@id='edit-actions']/input")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText("//div[@id='edit-actions']/input")));

        // Click "+" Symbol
        driver.findElement(By.xpath("//form[@id='get-lesson-unit-widgets-form']/div/div[2]/div")).click();
        // Wait For "Add Image" Link
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.image-widget.lesson-unit-create-widget-link")));
        // Click Add Image
        driver.findElement(By.cssSelector("div.image-widget.lesson-unit-create-widget-link")).click();

        // Add Image Widget
        // Enter Title
        driver.findElement(By.id("edit-field-lesson-unit-title-und-0-value")).clear();
        Tracking = func.RandomWords(1);
        driver.findElement(By.id("edit-field-lesson-unit-title-und-0-value")).sendKeys(Tracking[0]);
        // Upload Image
        driver.findElement(By.id("edit-field-lesson-unit-image-und-0-upload")).sendKeys("C:\\Users\\om\\Downloads\\abc.jpg");
        // Click Save Button
        driver.findElement(By.xpath("//div[@id='edit-actions']/input")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText("//div[@id='edit-actions']/input")));

        // Click Submit Button
        driver.findElement(By.xpath("//form[@id='get-lesson-unit-widgets-form']/div/input[4]")).click();

        // Click Assign
        driver.findElement(By.linkText("Assign")).click();
        // Assign To Students
        // Select Group
        new Select(driver.findElement(By.id("edit-group-id"))).selectByVisibleText("S2: French : Period 4");
        // Click Assign Button
        driver.findElement(By.xpath("//form[@id='cot-lesson-unit-assign-form']/div/input")).click();
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