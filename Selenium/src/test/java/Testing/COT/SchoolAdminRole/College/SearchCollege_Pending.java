package Testing.COT.SchoolAdminRole.College;

/**
 * Created by om on 11/12/2014.
 */
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import Testing.COT.COTFunctions;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SearchCollege_Pending {
    COTFunctions func;
    private WebDriver driver;
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
        driver.get(func.baseUrl + "/college-search");
    }

    @Test
    public void testCollege() throws Exception {
        // Functions For Find Colleges
        SearchButton();
        TypesOfSchool();
        AreaOfStudy();
        SpecialNeeds();
        ActivitiesOfInterest();
        Sports();
        Location();
    }

    private void SearchButton() throws IOException {
        // Enter Text To Be Search
        driver.findElement(By.id("edit-keys")).clear();
        driver.findElement(By.id("edit-keys")).sendKeys("Abilene");
        // Click Search
        driver.findElement(By.id("edit-submit")).click();
        // Check Result Received or Not
        try {
            assertTrue(func.isTextPresent(driver.findElement(By.className("search-total-results")).getText()));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        // Click All Selections
        driver.findElement(By.linkText("Clear All Selections")).click();
    }

    private void TypesOfSchool() throws IOException {
        // Click Type of School
        driver.findElement(By.cssSelector("strong")).click();
        // Click Private
        driver.findElement(By.id("edit-public-private-private")).click();
        // Move School Size Slider
        // TODO Slider not working properly
        WebElement Slider1 = driver.findElement(By.id("population_jq_slider"));
        Actions moveSlider1 = new Actions(driver);
        Action action1 = moveSlider1.dragAndDropBy(Slider1, 0, 10).build();
        action1.perform();
        // Select Religious Affiliation
        new Select(driver.findElement(By.id("edit-religious-affiliation"))).selectByVisibleText("Church of Christ");
        // Move Tuition Range Slider
        // TODO Slider not working properly
        WebElement Slider2 = driver.findElement(By.id("tuition_range_jq_slider"));
        Actions moveSlider2 = new Actions(driver);
        Action action2 = moveSlider2.dragAndDropBy(Slider2, 0, 10).build();
        action2.perform();
        // Click Apply
        driver.findElement(By.id("edit-type-of-school-apply-filter")).click();
        // Check Result Received or Not
        try {
            assertTrue(func.isTextPresent(driver.findElement(By.className("search-total-results")).getText()));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        // Click All Selections
        driver.findElement(By.linkText("Clear All Selections")).click();
    }

    private void AreaOfStudy() throws IOException, InterruptedException {
        // Click Area of School
        driver.findElement(By.xpath("//ul[@class='vertical-tabs-list']/li/a/strong[contains(text(), \"Area of Study\")]")).click();
        // Select One Area Study
        new Select(driver.findElement(By.id("edit-area-of-study-parent"))).selectByVisibleText("Area, Ethnic, Cultural, and Gender Studies");
        // Wait For Another Select List To Load
        Thread.sleep(5000);
        // Select Subareas of Study
        new Select(driver.findElement(By.xpath("//div[@id='child-area-of-study-wrapper']//select"))).selectByVisibleText("Area, Ethnic, Cultural, and Gender Studies, Other");
        // Click Apply
        driver.findElement(By.id("edit-area-of-study-apply-filter")).click();
        // Check Result Received or Not
        try {
            assertTrue(func.isTextPresent(driver.findElement(By.className("search-total-results")).getText()));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        // Click All Selections
        driver.findElement(By.linkText("Clear All Selections")).click();
    }

    private void SpecialNeeds() throws IOException {
        // Click Special Needs
        driver.findElement(By.xpath("//ul[@class='vertical-tabs-list']/li/a/strong[contains(text(), \"Special Needs\")]")).click();
        // Select Special Needs
        Select SpecialNeeds = new Select(driver.findElement(By.id("edit-special-needs")));
        // Select Learning Center
        SpecialNeeds.selectByVisibleText("Learning Center");
        // Select Early Syllabus
        SpecialNeeds.selectByVisibleText("Early syllabus");
        // Click Apply
        driver.findElement(By.id("edit-special-needs-apply-filter")).click();
        // Check Result Received or Not
        try {
            assertTrue(func.isTextPresent(driver.findElement(By.className("search-total-results")).getText()));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        // Click All Selections
        driver.findElement(By.linkText("Clear All Selections")).click();
    }

    private void ActivitiesOfInterest() throws IOException {
        // Click Activities of Interest
        driver.findElement(By.xpath("//ul[@class='vertical-tabs-list']/li/a/strong[contains(text(), \"Activities of Interest\")]")).click();
        // Click Student Newspaper Checkbox
        // TODO Do not use number in locator
        driver.findElement(By.xpath("//div[11]/input")).click();
        // Click TV Station Checkbox
        // TODO Do not use number in locator
        driver.findElement(By.xpath("//div[13]/input")).click();
        // Click Apply
        driver.findElement(By.id("edit-activities-interests-apply-filter")).click();
        // Check Result Received or Not
        try {
            assertTrue(func.isTextPresent(driver.findElement(By.className("search-total-results")).getText()));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        // Click All Selections
        driver.findElement(By.linkText("Clear All Selections")).click();
    }

    private void Sports() throws IOException {
        // Click Sports
        driver.findElement(By.xpath("//ul[@class='vertical-tabs-list']/li/a/strong[contains(text(), \"Sports\")]")).click();
        // Select Sports
        Select Sports = new Select(driver.findElement(By.id("edit-varsity-sports")));
        // Select Baseball
        Sports.selectByVisibleText("baseball");
        // Select Basketball
        Sports.selectByVisibleText("basketball");
        // Click Apply
        driver.findElement(By.id("edit-varsity-sports-apply-filter")).click();
        // Check Result Received or Not
        try {
            assertTrue(func.isTextPresent(driver.findElement(By.className("search-total-results")).getText()));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        // Click All Selections
        driver.findElement(By.linkText("Clear All Selections")).click();
    }

    private void Location() throws IOException {
        // Click Location
        driver.findElement(By.xpath("//ul[@class='vertical-tabs-list']/li/a/strong[contains(text(), \"Location\")]")).click();
        // Click West Checkbox
        // TODO Do not use number in Locator
        driver.findElement(By.xpath("//fieldset[6]/div/div[4]/div/div[3]/input")).click();
        // Click South Checkbox
        // TODO Do not use number in Locator
        driver.findElement(By.xpath("//fieldset[6]/div/div[4]/div/div[6]/input")).click();
        // Click Apply
        driver.findElement(By.id("edit-location-apply-filter")).click();
        // Check Result Received or Not
        try {
            assertTrue(func.isTextPresent(driver.findElement(By.className("search-total-results")).getText()));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        // Click All Selections
        driver.findElement(By.linkText("Clear All Selections")).click();
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

