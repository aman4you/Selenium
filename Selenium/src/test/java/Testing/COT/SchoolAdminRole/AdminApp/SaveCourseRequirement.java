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

public class SaveCourseRequirement {
    COTFunctions func;
    private WebDriver driver;
    private int Max = 50;
    private int IntegerValue = 0;
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
        func.LoginRole("SchoolAdmin");
        driver.get(func.baseUrl + "/long-term-planner-config");
    }

    @Test
    public void testSaveCourseRequirement() throws Exception {
        // Click Course Requirement
        driver.findElement(By.linkText("Course Requirements")).click();

        // Check Graduation Requirement Tracking Present Or Not
        if(isElementPresent(By.linkText("Graduation Requirement Tracking"))) {
            // Click Graduation Requirement Tracking
            driver.findElement(By.linkText("Graduation Requirement Tracking")).click();
            // Enter Tracking Name
            driver.findElement(By.id("edit-traking-name")).clear();
            Tracking = func.RandomWords(1);
            driver.findElement(By.id("edit-traking-name")).sendKeys(Tracking[0]);
            // Enter AcaSpt
            driver.findElement(By.id("edit-acaspt")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-acaspt")).sendKeys(String.valueOf(IntegerValue));
            // Enter Arts, Visual & Performing
            driver.findElement(By.id("edit-arts-visual-performing")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-arts-visual-performing")).sendKeys(String.valueOf(IntegerValue));
            // Enter Business
            driver.findElement(By.id("edit-business")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-business")).sendKeys(String.valueOf(IntegerValue));
            // Enter English
            driver.findElement(By.id("edit-english")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-english")).sendKeys(String.valueOf(IntegerValue));
            // Enter Health
            driver.findElement(By.id("edit-health")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-health")).sendKeys(String.valueOf(IntegerValue));
            // Enter History - Social Science
            driver.findElement(By.id("edit-history-social-science")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-history-social-science")).sendKeys(String.valueOf(IntegerValue));
            // Enter Journalism
            driver.findElement(By.id("edit-journalism")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-journalism")).sendKeys(String.valueOf(IntegerValue));
            // Enter LOTE
            driver.findElement(By.id("edit-lote")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-lote")).sendKeys(String.valueOf(IntegerValue));
            // Enter Math
            driver.findElement(By.id("edit-math")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-math")).sendKeys(String.valueOf(IntegerValue));
            // Enter Mathematics
            driver.findElement(By.id("edit-mathematics")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-mathematics")).sendKeys(String.valueOf(IntegerValue));
            // Enter Music
            driver.findElement(By.id("edit-music")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-music")).sendKeys(String.valueOf(IntegerValue));
            // Enter Other
            driver.findElement(By.id("edit-other")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-other")).sendKeys(String.valueOf(IntegerValue));
            // Enter PE
            driver.findElement(By.id("edit-pe")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-pe")).sendKeys(String.valueOf(IntegerValue));
            // Enter Physical Education
            driver.findElement(By.id("edit-physical-education")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-physical-education")).sendKeys(String.valueOf(IntegerValue));
            // Enter Science
            driver.findElement(By.id("edit-science")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-science")).sendKeys(String.valueOf(IntegerValue));
            // Enter Soc Stud
            driver.findElement(By.id("edit-soc-stud")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-soc-stud")).sendKeys(String.valueOf(IntegerValue));
            // Enter Voc Ed
            driver.findElement(By.id("edit-voc-ed")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-voc-ed")).sendKeys(String.valueOf(IntegerValue));
            // Enter World Languages
            driver.findElement(By.id("edit-world-languages")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-world-languages")).sendKeys(String.valueOf(IntegerValue));
            // Click Save
            driver.findElement(By.id("edit-submit")).click();
        }
        // Check "+" Symbol Present Or Not
        else if (isElementPresent(By.linkText("+"))) {
            // Click "+" Symbol
            driver.findElement(By.linkText("+")).click();
            // Enter Tracking Name
            driver.findElement(By.id("edit-traking-name")).clear();
            Tracking = func.RandomWords(1);
            driver.findElement(By.id("edit-traking-name")).sendKeys(Tracking[0]);
            // Enter AcaSpt
            driver.findElement(By.id("edit-acaspt")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-acaspt")).sendKeys(String.valueOf(IntegerValue));
            // Enter Arts, Visual & Performing
            driver.findElement(By.id("edit-arts-visual-performing")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-arts-visual-performing")).sendKeys(String.valueOf(IntegerValue));
            // Enter Business
            driver.findElement(By.id("edit-business")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-business")).sendKeys(String.valueOf(IntegerValue));
            // Enter English
            driver.findElement(By.id("edit-english")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-english")).sendKeys(String.valueOf(IntegerValue));
            // Enter Health
            driver.findElement(By.id("edit-health")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-health")).sendKeys(String.valueOf(IntegerValue));
            // Enter History - Social Science
            driver.findElement(By.id("edit-history-social-science")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-history-social-science")).sendKeys(String.valueOf(IntegerValue));
            // Enter Journalism
            driver.findElement(By.id("edit-journalism")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-journalism")).sendKeys(String.valueOf(IntegerValue));
            // Enter LOTE
            driver.findElement(By.id("edit-lote")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-lote")).sendKeys(String.valueOf(IntegerValue));
            // Enter Math
            driver.findElement(By.id("edit-math")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-math")).sendKeys(String.valueOf(IntegerValue));
            // Enter Mathematics
            driver.findElement(By.id("edit-mathematics")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-mathematics")).sendKeys(String.valueOf(IntegerValue));
            // Enter Music
            driver.findElement(By.id("edit-music")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-music")).sendKeys(String.valueOf(IntegerValue));
            // Enter Other
            driver.findElement(By.id("edit-other")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-other")).sendKeys(String.valueOf(IntegerValue));
            // Enter PE
            driver.findElement(By.id("edit-pe")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-pe")).sendKeys(String.valueOf(IntegerValue));
            // Enter Physical Education
            driver.findElement(By.id("edit-physical-education")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-physical-education")).sendKeys(String.valueOf(IntegerValue));
            // Enter Science
            driver.findElement(By.id("edit-science")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-science")).sendKeys(String.valueOf(IntegerValue));
            // Enter Soc Stud
            driver.findElement(By.id("edit-soc-stud")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-soc-stud")).sendKeys(String.valueOf(IntegerValue));
            // Enter Voc Ed
            driver.findElement(By.id("edit-voc-ed")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-voc-ed")).sendKeys(String.valueOf(IntegerValue));
            // Enter World Languages
            driver.findElement(By.id("edit-world-languages")).clear();
            IntegerValue = func.RandomIntegerNumber(Max);
            driver.findElement(By.id("edit-world-languages")).sendKeys(String.valueOf(IntegerValue));
            // Click Save
            driver.findElement(By.id("edit-submit")).click();
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

