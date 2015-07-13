package Testing.COT.TeacherRole;

import java.util.List;
import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateGroup {
    COTFunctions func;
    private WebDriver driver;
    private WebDriverWait wait;
    private String[] Tracking = null;
    private int RandomRow = 0;
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
    }

    @Test
    public void testCreateAGroup() throws Exception {
        // Click Account Holder Name
        driver.findElement(By.cssSelector("div.top-logout-list.top-menu")).click();

        // My Account
        driver.findElement(By.linkText("My Account")).click();
        // My Groups
        driver.findElement(By.linkText("MY GROUPS")).click();

        // Create Group
        // Create New Group
        driver.findElement(By.linkText("CREATE NEW GROUP")).click();
        // Enter Group Name
        driver.findElement(By.id("edit-gname")).clear();
        Tracking = func.RandomWords(1);
        driver.findElement(By.id("edit-gname")).sendKeys(Tracking[0]);
        // Select Group Members
        driver.findElement(By.xpath("//div[@id='edit_gmembers__2_chzn']/ul")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_gmembers__2_chzn_o_1\"]")).click();
        driver.findElement(By.xpath("//div[@id='edit_gmembers__2_chzn']/ul")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_gmembers__2_chzn_o_3\"]")).click();
        // Click Save
        driver.findElement(By.xpath("//form[@id='create-relation-group-form']/div/div[3]/input")).click();
        // Wait To See The Created Group
        Thread.sleep(2000);

        // Add Another Member To Group
        // Select Group
        driver.findElement(By.xpath("//div[@id='edit_all_relation_groups_chzn']/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_all_relation_groups_chzn_o_8\"]")).click();
        // Select Members
        driver.findElement(By.xpath("//div[@id='edit_gmembers_chzn']/ul")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_gmembers_chzn_o_0\"]")).click();
        // Click Add
        driver.findElement(By.xpath("//form[@id='add-to-relation-group-form']/div/input")).click();
        // Wait To See The Added Member
        Thread.sleep(2000);

        // Remove Member
        // Check Row Data Exist or Not
        List<WebElement> TableRows = driver.findElements(By.xpath("//table[2]/tbody/tr"));
        int rowNumber = TableRows.size();
        if(rowNumber > 0) {
            // Access Random Row
            RandomRow = func.RandomIntegerNumber(rowNumber);
            // Click Remove Link
            driver.findElement(By.xpath("//tr[" + RandomRow + "]/td[4]/a")).click();
            // Click Yes
            driver.findElement(By.xpath("//div[11]/div/button")).click();
        }
        // Wait To See The Member Removed
        Thread.sleep(2000);
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