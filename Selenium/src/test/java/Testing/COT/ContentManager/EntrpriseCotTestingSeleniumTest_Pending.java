package Testing.COT.ContentManager;

/**
 * Created by om on 11/12/2014.
 */
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class EntrpriseCotTestingSeleniumTest_Pending {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\om\\Downloads\\Programs\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "http://collegeontrackdev.prod.acquia-sites.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testEntrpriseCotTestingSelenium() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.linkText("Log In")).click();
        driver.findElement(By.xpath("//div[@id='page-wrapper']/div/div[2]/div/div/div/div/div/div/ul/li[9]")).click();
        driver.findElement(By.id("edit-name")).clear();
        driver.findElement(By.id("edit-name")).sendKeys("amantest");
        driver.findElement(By.id("edit-pass")).clear();
        driver.findElement(By.id("edit-pass")).sendKeys("password");
        driver.findElement(By.id("edit-submit--7")).click();
        driver.findElement(By.linkText("Home")).click();
        driver.findElement(By.linkText("System")).click();
        driver.findElement(By.linkText("System")).click();
        driver.findElement(By.linkText("Add School")).click();
        driver.findElement(By.linkText("Add School")).click();
        driver.findElement(By.id("edit-title")).clear();
        driver.findElement(By.id("edit-title")).sendKeys("simpkins school");
        driver.findElement(By.id("edit-field-address-line-1-und-0-value")).clear();
        driver.findElement(By.id("edit-field-address-line-1-und-0-value")).sendKeys("2/333 delhi road, jaipur");
        driver.findElement(By.id("edit-field-address-line-2-und-0-value")).clear();
        driver.findElement(By.id("edit-field-address-line-2-und-0-value")).sendKeys("5/111 ajmeri road, kota");
        driver.findElement(By.id("edit-field-city-und-0-value")).clear();
        driver.findElement(By.id("edit-field-city-und-0-value")).sendKeys("jaipur");
        driver.findElement(By.id("edit-field-state-und-0-value")).clear();
        driver.findElement(By.id("edit-field-state-und-0-value")).sendKeys("raj");
        driver.findElement(By.id("edit-field-country-und-0-value")).clear();
        driver.findElement(By.id("edit-field-country-und-0-value")).sendKeys("india");
        new Select(driver.findElement(By.id("edit-field-district-und"))).selectByVisibleText("G District");
        driver.findElement(By.id("edit-field-zipcode-und-0-value")).clear();
        driver.findElement(By.id("edit-field-zipcode-und-0-value")).sendKeys("282010");
        driver.findElement(By.id("edit-field-user-limit-und-0-value")).clear();
        driver.findElement(By.id("edit-field-user-limit-und-0-value")).sendKeys("700");
        driver.findElement(By.id("edit-field-subscribed-apps-und-student-assessment")).click();
        driver.findElement(By.id("edit-field-subscribed-apps-und-lesson-plans")).click();
        driver.findElement(By.id("edit-field-subscribed-apps-und-colleges")).click();
        driver.findElement(By.id("edit-field-subscribed-apps-und-digital-portfolio")).click();
        driver.findElement(By.id("edit-field-subscribed-apps-und-student-planner")).click();
        driver.findElement(By.id("edit-field-subscribed-apps-und-grade-book")).click();
        driver.findElement(By.id("edit-field-subscribed-apps-und-dashboard")).click();
        driver.findElement(By.id("edit-field-subscribed-apps-und-administration")).click();
        driver.findElement(By.id("edit-field-subscribed-apps-und-calendar")).click();
        driver.findElement(By.id("edit-field-subscribed-apps-und-college-readiness")).click();
        driver.findElement(By.id("edit-field-subscribed-apps-und-attendance")).click();
        driver.findElement(By.id("edit-field-subscribed-apps-und-purpose-navigator")).click();
        driver.findElement(By.id("edit-field-subscribed-apps-und-benchprep")).click();
        driver.findElement(By.id("edit-field-subscribed-apps-und-lesson-library")).click();
        driver.findElement(By.id("edit-field-subscribed-apps-und-messages")).click();
        driver.findElement(By.id("edit-field-subscribed-apps-und-my-account")).click();
        driver.findElement(By.id("edit-field-subscribed-apps-und-document-locker")).click();
        driver.findElement(By.id("edit-field-receive-sms-und-1")).click();
        driver.findElement(By.id("edit-submit")).click();
        driver.findElement(By.linkText("Add User")).click();
        driver.findElement(By.linkText("Add User")).click();
        driver.findElement(By.id("edit-mail")).clear();
        driver.findElement(By.id("edit-mail")).sendKeys("akashjain132@gmail.com");
        driver.findElement(By.id("edit-pass-pass1")).clear();
        driver.findElement(By.id("edit-pass-pass1")).sendKeys("password");
        driver.findElement(By.id("edit-pass-pass2")).clear();
        driver.findElement(By.id("edit-pass-pass2")).sendKeys("password");
        driver.findElement(By.id("edit-roles-9")).click();
        driver.findElement(By.id("edit-field-user-first-name-und-0-value")).clear();
        driver.findElement(By.id("edit-field-user-first-name-und-0-value")).sendKeys("Akash");
        driver.findElement(By.id("edit-field-user-last-name-und-0-value")).clear();
        driver.findElement(By.id("edit-field-user-last-name-und-0-value")).sendKeys("Jain");
        driver.findElement(By.id("edit-field-user-telephone-und-0-value")).clear();
        driver.findElement(By.id("edit-field-user-telephone-und-0-value")).sendKeys("5254526554");
        driver.findElement(By.id("edit-field-user-address1-und-0-value")).clear();
        driver.findElement(By.id("edit-field-user-address1-und-0-value")).sendKeys("2/777 shahganj agra");
        driver.findElement(By.id("edit-field-user-state-und-0-value")).clear();
        driver.findElement(By.id("edit-field-user-state-und-0-value")).sendKeys("u.p");
        driver.findElement(By.id("edit-field-user-city-und-0-value")).clear();
        driver.findElement(By.id("edit-field-user-city-und-0-value")).sendKeys("Agra");
        driver.findElement(By.id("edit-field-user-state-und-0-value")).clear();
        driver.findElement(By.id("edit-field-user-state-und-0-value")).sendKeys("U.P");
        driver.findElement(By.id("edit-field-user-zipcode-und-0-value")).clear();
        driver.findElement(By.id("edit-field-user-zipcode-und-0-value")).sendKeys("254556");
        new Select(driver.findElement(By.id("edit-field-user-school-name-und"))).selectByVisibleText("simpkins school");
        driver.findElement(By.id("edit-field-user-school-phone-und-0-value")).clear();
        driver.findElement(By.id("edit-field-user-school-phone-und-0-value")).sendKeys("7889542132");
        // ERROR: Caught exception [ERROR: Unsupported command [addSelection | id=edit-field-user-activity-und | label=Art]]
        // ERROR: Caught exception [ERROR: Unsupported command [addSelection | id=edit-field-user-activity-und | label=Business]]
        // ERROR: Caught exception [ERROR: Unsupported command [addSelection | id=edit-field-user-activity-und | label=Theater]]
        // ERROR: Caught exception [ERROR: Unsupported command [addSelection | id=edit-field-user-activity-und | label=Videography]]
        // ERROR: Caught exception [ERROR: Unsupported command [addSelection | id=edit-field-user-activity-und | label=Video game creation]]
        // ERROR: Caught exception [ERROR: Unsupported command [addSelection | id=edit-field-user-activity-und | label=Volunteer work]]
        new Select(driver.findElement(By.id("edit-field-user-district-und"))).selectByVisibleText("G District");
        driver.findElement(By.id("edit-field-user-country-und-0-value")).clear();
        driver.findElement(By.id("edit-field-user-country-und-0-value")).sendKeys("india");
        // ERROR: Caught exception [ERROR: Unsupported command [addSelection | id=edit-field-user-area-of-study-und | label=Agricultural Sciences]]
        // ERROR: Caught exception [ERROR: Unsupported command [addSelection | id=edit-field-user-area-of-study-und | label=Agriculture, Agriculture Operations, and Related Sciences]]
        // ERROR: Caught exception [ERROR: Unsupported command [addSelection | id=edit-field-user-area-of-study-und | label=-Agribusiness/Agricultural Business Operations]]
        // ERROR: Caught exception [ERROR: Unsupported command [addSelection | id=edit-field-user-area-of-study-und | label=-Agricultural Business and Management]]
        driver.findElement(By.id("edit-field-race-und-white")).click();
        driver.findElement(By.id("edit-field-gender-und-male")).click();
        driver.findElement(By.id("edit-submit")).click();
        driver.findElement(By.cssSelector("span.username-page-link")).click();
        driver.findElement(By.linkText("Sign Out")).click();
        driver.findElement(By.linkText("Log In")).click();
        driver.findElement(By.id("edit-name")).clear();
        driver.findElement(By.id("edit-name")).sendKeys("akashjain132@gmail.com");
        driver.findElement(By.id("edit-pass")).clear();
        driver.findElement(By.id("edit-pass")).sendKeys("password");
        driver.findElement(By.id("edit-submit--7")).click();
        driver.findElement(By.id("edit-legal-accept")).click();
        driver.findElement(By.id("edit-save")).click();
        driver.findElement(By.xpath("(//a[contains(text(),'Administration')])[2]")).click();
        driver.findElement(By.xpath("(//a[contains(text(),'Administration')])[2]")).click();
        driver.findElement(By.cssSelector("div.download-template > a > img")).click();
        driver.findElement(By.id("edit-feeds-feedsfilefetcher-upload")).clear();
        driver.findElement(By.id("edit-feeds-feedsfilefetcher-upload")).sendKeys("/home/akash/Downloads/cot_testing/student_importer_template.csv");
        driver.findElement(By.id("edit-submit")).click();
        driver.findElement(By.linkText("Teachers")).click();
        driver.findElement(By.id("edit-feeds-feedsfilefetcher-upload")).clear();
        driver.findElement(By.id("edit-feeds-feedsfilefetcher-upload")).sendKeys("/home/akash/Downloads/cot_testing/teacher_importer_template.csv");
        driver.findElement(By.id("edit-submit")).click();
        driver.findElement(By.linkText("Course Catalog")).click();
        driver.findElement(By.id("edit-feeds-feedsfilefetcher-upload")).clear();
        driver.findElement(By.id("edit-feeds-feedsfilefetcher-upload")).sendKeys("/home/akash/Downloads/cot_testing/course_catalog_content_type_importer_template.csv");
        driver.findElement(By.id("edit-submit")).click();
        driver.findElement(By.linkText("Required Courses")).click();
        driver.findElement(By.id("edit-feeds-feedsfilefetcher-upload")).clear();
        driver.findElement(By.id("edit-feeds-feedsfilefetcher-upload")).sendKeys("/home/akash/Downloads/cot_testing/mandatory_planner_importer_template.csv");
        driver.findElement(By.id("edit-submit")).click();
        driver.findElement(By.linkText("Teacher Schedules")).click();
        driver.findElement(By.id("edit-feeds-feedsfilefetcher-upload")).clear();
        driver.findElement(By.id("edit-feeds-feedsfilefetcher-upload")).sendKeys("/home/akash/Downloads/cot_testing/teacher_assignment_importer_template.csv");
        driver.findElement(By.id("edit-submit")).click();
        driver.findElement(By.linkText("Student Schedules")).click();
        driver.findElement(By.id("edit-feeds-feedsfilefetcher-upload")).clear();
        driver.findElement(By.id("edit-feeds-feedsfilefetcher-upload")).sendKeys("/home/akash/Downloads/cot_testing/teacher_student_relation_template.csv");
        driver.findElement(By.id("edit-submit")).click();
        driver.findElement(By.linkText("Academic Setup")).click();
        driver.findElement(By.linkText("Academic Setup")).click();
        driver.findElement(By.linkText("Teacher Schedules")).click();
        driver.findElement(By.linkText("Add Teacher Assignment")).click();
        driver.findElement(By.id("edit-field-teach-assgn-period-und-0-value")).clear();
        driver.findElement(By.id("edit-field-teach-assgn-period-und-0-value")).sendKeys("7");
        new Select(driver.findElement(By.id("edit-field-teach-assgn-course-ref-und"))).selectByVisibleText("1126 : goegraphy");
        new Select(driver.findElement(By.id("edit-field-teach-assgn-teacher-ref-und"))).selectByVisibleText("Abhinay Agarwal");
        new Select(driver.findElement(By.id("edit-field-assignment-session-und"))).selectByVisibleText("Semester 1");
        driver.findElement(By.id("edit-submit")).click();
        driver.findElement(By.linkText("Class Scheduler")).click();
        driver.findElement(By.cssSelector("a.chzn-single.chzn-single-with-drop > span")).click();
        driver.findElement(By.cssSelector("#__sizzle__ > div.action > div.add")).click();
        driver.findElement(By.id("edit-student-75491")).click();
        driver.findElement(By.id("edit-student-75501")).click();
        driver.findElement(By.cssSelector("div.close-button > img")).click();
    }

    @After
    public void tearDown() throws Exception {
//        driver.quit();
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

