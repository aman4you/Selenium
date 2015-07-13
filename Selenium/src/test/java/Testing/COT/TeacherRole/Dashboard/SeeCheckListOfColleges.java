package Testing.COT.TeacherRole.Dashboard;

/**
 * Created by om on 11/12/2014.
 */
import java.util.List;
import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeeCheckListOfColleges {
    COTFunctions func;
    private WebDriver driver;
    private WebDriverWait wait;
    private String BookIconClass = null;
    private int RandomRow = 0;
    private String ApplicationItem = null;
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
        driver.get(func.baseUrl + "/students-dashboard");
    }

    @Test
    public void testSeeChecklistOfColleges() throws Exception {
        // Click Colleges
        driver.findElement(By.xpath("//div[6]/a")).click();

        // Select Student
        driver.findElement(By.xpath("//div[@id='edit_students_chzn']/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_students_chzn_o_1\"]")).click();

        // Check Row Data Exist or Not
        List<WebElement> TableRows = driver.findElements(By.xpath("//table[2]/tbody/tr"));
        int rowNumber = TableRows.size();
        if(rowNumber > 0) {
            // Access Random Row
            RandomRow = func.RandomIntegerNumber(rowNumber);

            // Create Application Item
            // Get Book Icon Class
            BookIconClass = driver.findElement(By.xpath("//tr[" + RandomRow  + "]/td/div[2]")).getAttribute("class");
            String ClassPart = BookIconClass.substring(0, BookIconClass.indexOf(" "));
            // Click Book Icon
            driver.findElement(By.xpath("//tr[" + RandomRow  + "]/td/div[2]")).click();
            // Click Checklist
            driver.findElement(By.xpath("//div[@class='" + ClassPart + " add-checklist-link']")).click();
            // Enter Application Item
            driver.findElement(By.xpath("//div[@class='" + ClassPart + " 10290 custom-checklist-header']//form//input")).clear();
            Tracking = func.RandomWords(2);
            ApplicationItem = Tracking[0] + " " + Tracking[1];
            driver.findElement(By.xpath("//div[@class='" + ClassPart + " 10290 custom-checklist-header']//form//input")).sendKeys(Tracking[0] + " " + Tracking[1]);
            // Click Save
            driver.findElement(By.xpath("//div[@class='" + ClassPart + " 10290 custom-checklist-header']//form//input[@class='form-submit ajax-processed']")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='" + ClassPart + " 10290 custom-checklist-header']//form//input[@class='form-submit ajax-processed']")));

            // Edit Created Application Item
            // Click Book Icon
            driver.findElement(By.xpath("//tr[" + RandomRow  + "]/td/div[2]")).click();
            // Find Application Item
            List<WebElement> TableRows1 = driver.findElements(By.xpath("//table[@class='custom-checklist-table checklist-table-" + ClassPart + "']/tbody/tr"));
            int rowNumber1 = TableRows1.size();
            driver.findElement(By.xpath("//table[@class='custom-checklist-table checklist-table-" + ClassPart + "']/tbody/tr[" + rowNumber1 + "]/td[3]/a")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("ajax-waiting")));
            // Enter Application Item
            driver.findElement(By.xpath("//div[@id='simple-dialog-container']//form//input")).clear();
            Tracking = func.RandomWords(2);
            ApplicationItem = Tracking[0] + " " + Tracking[1];
            driver.findElement(By.xpath("//div[@id='simple-dialog-container']//form//input")).sendKeys(Tracking[0] + " " + Tracking[1]);
            // Click Save
            driver.findElement(By.xpath("//div[@id='simple-dialog-container']//form//input[@class='form-submit']")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='simple-dialog-container']//form//input[@class='form-submit']")));

            // Delete Created Application Item
            // Click Book Icon
            driver.findElement(By.xpath("//tr[" + RandomRow  + "]/td/div[2]")).click();
            // Find Application Item
            List<WebElement> TableRows2 = driver.findElements(By.xpath("//table[@class='custom-checklist-table checklist-table-" + ClassPart + "']/tbody/tr"));
            int rowNumber2 = TableRows2.size();
            driver.findElement(By.xpath("//table[@class='custom-checklist-table checklist-table-" + ClassPart + "']/tbody/tr[" + rowNumber2 + "]/td[3]//span/img")).click();
            driver.findElement(By.xpath("//table[@class='custom-checklist-table checklist-table-" + ClassPart + "']/tbody/tr[" + rowNumber2 + "]/td[3]//form//input[@class='form-submit']")).click();
            Thread.sleep(5000);
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