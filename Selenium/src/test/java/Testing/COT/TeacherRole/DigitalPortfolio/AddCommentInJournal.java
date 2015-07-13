package Testing.COT.TeacherRole.DigitalPortfolio;

/**
 * Created by om on 11/12/2014.
 */
import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AddCommentInJournal {
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
        driver.get(func.baseUrl + "/journals/75486");
    }

    @Test
    public void testAddCommentInJournal() throws Exception {
        // Click Journal
        driver.findElement(By.linkText("JOURNAL")).click();
        // Click Comment
        driver.findElement(By.xpath("//div[5]/a")).click();

        // Click Add New Comment
        driver.findElement(By.id("comment-add")).click();
        // Enter Comment
        driver.findElement(By.id("edit-comment-body-und-0-value")).clear();
        Tracking = func.RandomWords(3);
        driver.findElement(By.id("edit-comment-body-und-0-value")).sendKeys(Tracking[0] + " " + Tracking[1] + " " + Tracking[2]);
        // Click Save Button
        driver.findElement(By.xpath("//div[@id='edit-actions']/input")).click();

        // Store Journal Name
        String Journal = driver.findElement(By.cssSelector("div.block-title")).getText();
        // Click Journal at Recent Post
        driver.findElement(By.linkText(Journal)).click();
        // Click Date at Archives
        driver.findElement(By.xpath("//section[2]/div/div/div/div/div/ul/li/a")).click();
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

