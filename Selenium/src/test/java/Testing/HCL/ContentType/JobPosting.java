package Testing.HCL.ContentType;

import Testing.CommonFunction;
import Testing.HCL.HCLFunctions;
import Testing.HCL.HCLAbstract;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.MethodSorter;
import org.junit.runner.RunWith;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by om on 1/24/2015.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class JobPosting extends HCLAbstract {
    private String JobTitle;
    private String EmailAddress;
    private String City;
    private String State;
    private String Roles;
    private String DesiredSkills;
    private String CTCAmount;
    private String NoOFVacancies;
    private String CCTo;
    private String DeadLineDate;
    private String VideoLink;
    private String VideoThumbnail;
    private String AlternateText;
    private String PictureTitle;
    private String MetatagDescription;
    private String MetatagKeyword;

    /**
     * Define a Collection method that will return the collection of parameters to the parallelTestThroughJUnit class
     * by using the @Parameters annotation.
     */
    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(
                new Object[][]{
                        {"Title", "dummy@gmail.com", "delhi", "delhi", "developer", "java", "30000", "5", "dummy2@gmail.com", "Today", "https://www.google.co.in", FilesPath + "abc.jpg", "AltText", "PicTitle", "MetaDesc", "MetaKey", "All inputs are correct", "Admin"}
//                        {"Today", "-1", "70", "FB Total is negative"},
//                        {"Today", "181", "-1", "Twitter Total is negative"},
//                        {"Today", "178", "abc", "Twitter Total is alphabetic"},
//                        {"Today", "abc", "15","FB Total is alphabetic"},
//                        {"Yesterday", "160", "45", "When date of past"},
//                        {"Tomorrow", "160", "45", "When date of future"}
                }
        );
    }

    // Constructor will be used by the test runner to pass the parameters to the Excel_drive class instance.
    public JobPosting(String JobTitle, String EmailAddress, String City, String State, String Roles, String DesiredSkills, String CTCAmount, String NoOfVacancies, String CCTo, String DeadLineDate, String VideoLink, String VideoThumbnail, String AlternateText, String PictureTitle, String MetatagDescription, String MetatagKeyword, String TestCase, String Role) {
        this.JobTitle = JobTitle;
        this.EmailAddress = EmailAddress;
        this.City = City;
        this.State = State;
        this.Roles = Roles;
        this.DesiredSkills= DesiredSkills;
        this.CTCAmount = CTCAmount;
        this.NoOFVacancies = NoOfVacancies;
        this.CCTo = CCTo;
        this.DeadLineDate = DeadLineDate;
        this.VideoLink = VideoLink;
        this.VideoThumbnail = VideoThumbnail;
        this.AlternateText = AlternateText;
        this.PictureTitle = PictureTitle;
        this.MetatagDescription = MetatagDescription;
        this.MetatagKeyword = MetatagKeyword;
        this.TestCase = TestCase;
        this.Role = Role;
    }

    @Test
    public void CreateContent() throws Exception {
        try {
            // Move to Job Posting page
            driver.get(baseUrl + "/node/add/job-posting");
            // Click Job Posting Type
            func2.ClickRandomRadioButton(By.xpath("//div[contains(@class, 'field-name-field-job-posting-type')]//input"));
            // Enter Job Title
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).sendKeys(JobTitle);
            // Enter Email Address
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-job-posting-email')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-job-posting-email')]//input")).sendKeys(EmailAddress);
            // Enter City
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-job-posting-city')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-job-posting-city')]//input")).sendKeys(City);
            // Enter State
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-job-posting-state')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-job-posting-state')]//input")).sendKeys(State);
            // Select Country
            func2.SelectRandomSelectListOption("//div[contains(@class, 'form-item-job-posting-cid')]//select");
            // Enter Roles
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'text-full')]/following-sibling::div[contains(@class, 'cke_browser_gecko')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Roles);
            // Select LOB
            func2.SelectRandomSelectListOption("//div[contains(@class, 'field-name-field-job-lob')]//select");
            // Enter Desired Skills
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-skills-required')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-skills-required')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-skills-required')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(DesiredSkills);
            // Select Qualification
            func2.SelectRandomChosenOption("//div[contains(@class, 'field-name-field-qualification')]//ul[@class='chosen-results']/li", "//div[contains(@class, 'field-name-field-qualification')]//ul", "[@data-option-array-index='");
            // Select Experience From
            new Select(driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-exp-from-years')]//select"))).selectByVisibleText("11");
            // Select Experience To
            new Select(driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-exp-to-years')]//select"))).selectByVisibleText("12");
            // Select Designation
            func2.SelectRandomChosenOption("//div[contains(@class, 'field-name-field-designation')]//ul[@class='chosen-results']/li", "//div[contains(@class, 'field-name-field-designation')]//ul", "[@data-option-array-index='");
            // Select Job Type
            func2.SelectRandomSelectListOption("//div[contains(@class, 'field-name-field-job-type')]//select");
            // Enter CTC Amount
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-salary')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-salary')]//input")).sendKeys(CTCAmount);
            // Select CTC Currency
            func2.SelectRandomSelectListOption("//div[contains(@class, 'field-name-field-salary')]//select");
            // Enter No Of Vacancies
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-num-of-vacancies')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-num-of-vacancies')]//input")).sendKeys(NoOFVacancies);
            // Enter CC To
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-assoc-email')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-assoc-email')]//input")).sendKeys(CCTo);
            // Enter Date
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-deadline-date')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-deadline-date')]//input")).sendKeys("15 Feb 2015");
            // Enter Video Link
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-job-video-link')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-job-video-link')]//input")).sendKeys(VideoLink);
            // Upload Video Thumbnail
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-job-video-thumbnail')]//input[@type='file']")).sendKeys(VideoThumbnail);
            // Click Upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-job-video-thumbnail')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-job-video-thumbnail')]//input[@value='Remove']")));
            // Enter alternate text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-job-video-thumbnail')]//input[contains(@id, 'alt')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-job-video-thumbnail')]//input[contains(@id, 'alt')]")).sendKeys(AlternateText);
            // Enter picture title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-job-video-thumbnail')]//input[contains(@id, 'title')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-job-video-thumbnail')]//input[contains(@id, 'title')]")).sendKeys(PictureTitle);
            // Enter Metatag Description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-description')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-description')]//textarea")).sendKeys(MetatagDescription);
            // Enter Metatag Keyword
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-keywords')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-keywords')]//textarea")).sendKeys(MetatagKeyword);
            // Select Site Selection
            func2.SelectRandomChosenOption("//div[contains(@class, 'field-name-field-site-section')]//ul[@class='chosen-results']/li", "//div[contains(@class, 'field-name-field-site-section')]//ul", "[@data-option-array-index='");
            // Click Save
            driver.findElement(By.id("edit-submit")).click();
            // Check content created or not
            driver.findElement(By.xpath("//div[@class='messages messages--status']"));
            // Set Result Pass
            func.SetResultPass(Role, TestCase);
        } catch (Exception e) {
            // Set Result Fail
            func.SetResultFail(Role, TestCase, e);
        }
    }

    @Test
    public void UpdateContent() throws Exception {
        try {
            // Click edit link of that content which type is Job Posting
            driver.get(baseUrl + "/admin/content");
            // Click edit link of that content which type is Job Posting
            List<WebElement> Types = driver.findElements(By.xpath("//table[contains(@class, 'sticky-enabled')]/tbody/tr/td[3]"));
            String ContentType = null;
            int RowNumber = 0;
            for (int i = 0; i < Types.size(); i++) {
                ContentType = Types.get(i).getText();
                if (ContentType.equals("Job Posting")) {
                    RowNumber = i + 1;
                    driver.findElement(By.xpath("//table[contains(@class, 'sticky-enabled')]/tbody/tr[" + RowNumber + "]/td[8]/ul/li[1]/a")).click();
                    break;
                }
            }
            // Click Job Posting Type
            func2.ClickRandomRadioButton(By.xpath("//div[contains(@class, 'field-name-field-job-posting-type')]//input"));
            // Enter Job Title
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).sendKeys(JobTitle);
            // Enter Email Address
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-job-posting-email')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-job-posting-email')]//input")).sendKeys(EmailAddress);
            // Enter City
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-job-posting-city')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-job-posting-city')]//input")).sendKeys(City);
            // Enter State
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-job-posting-state')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-job-posting-state')]//input")).sendKeys(State);
            // Select Country
            func2.SelectRandomSelectListOption("//div[contains(@class, 'form-item-job-posting-cid')]//select");
            // Enter Roles
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'text-full')]/following-sibling::div[contains(@class, 'cke_browser_gecko')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Roles);
            // Select LOB
            func2.SelectRandomSelectListOption("//div[contains(@class, 'field-name-field-job-lob')]//select");
            // Enter Desired Skills
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-skills-required')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-skills-required')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-skills-required')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(DesiredSkills);
            // Select Qualification
            func2.SelectRandomChosenOption("//div[contains(@class, 'field-name-field-qualification')]//ul[@class='chosen-results']/li", "//div[contains(@class, 'field-name-field-qualification')]//ul", "[@data-option-array-index='");
            // Select Experience From
            new Select(driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-exp-from-years')]//select"))).selectByVisibleText("11");
            // Select Experience To
            new Select(driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-exp-to-years')]//select"))).selectByVisibleText("12");
            // Select Designation
            func2.SelectRandomChosenOption("//div[contains(@class, 'field-name-field-designation')]//ul[@class='chosen-results']/li", "//div[contains(@class, 'field-name-field-designation')]//ul", "[@data-option-array-index='");
            // Select Job Type
            func2.SelectRandomSelectListOption("//div[contains(@class, 'field-name-field-job-type')]//select");
            // Enter CTC Amount
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-salary')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-salary')]//input")).sendKeys(CTCAmount);
            // Select CTC Currency
            func2.SelectRandomSelectListOption("//div[contains(@class, 'field-name-field-salary')]//select");
            // Enter No Of Vacancies
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-num-of-vacancies')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-num-of-vacancies')]//input")).sendKeys(NoOFVacancies);
            // Enter CC To
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-assoc-email')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-assoc-email')]//input")).sendKeys(CCTo);
            // Enter Date
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-deadline-date')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-deadline-date')]//input")).sendKeys("15 Feb 2015");
            // Enter Video Link
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-job-video-link')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-job-video-link')]//input")).sendKeys(VideoLink);
            // Remove Video Thumbnail
            if (func.isElementPresent(By.xpath("//div[contains(@class, 'field-name-field-job-video-thumbnail')]//input[@value='Remove']"))) {
                driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-job-video-thumbnail')]//input[@value='Remove']")).click();
                // Wait for remove
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-job-video-thumbnail')]//input[@type='file']")));
            }
            // Upload Video Thumbnail
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-job-video-thumbnail')]//input[@type='file']")).sendKeys(VideoThumbnail);
            // Click Upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-job-video-thumbnail')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-job-video-thumbnail')]//input[@value='Remove']")));
            // Enter alternate text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-job-video-thumbnail')]//input[contains(@id, 'alt')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-job-video-thumbnail')]//input[contains(@id, 'alt')]")).sendKeys(AlternateText);
            // Enter picture title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-job-video-thumbnail')]//input[contains(@id, 'title')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-job-video-thumbnail')]//input[contains(@id, 'title')]")).sendKeys(PictureTitle);
            // Enter Metatag Description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-description')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-description')]//textarea")).sendKeys(MetatagDescription);
            // Enter Metatag Keyword
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-keywords')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-keywords')]//textarea")).sendKeys(MetatagKeyword);
            // Select Site Selection
            func2.SelectRandomChosenOption("//div[contains(@class, 'field-name-field-site-section')]//ul[@class='chosen-results']/li", "//div[contains(@class, 'field-name-field-site-section')]//ul", "[@data-option-array-index='");
            // Click Save
            driver.findElement(By.id("edit-submit")).click();
            // Check content created or not
            driver.findElement(By.xpath("//div[@class='messages status']"));
            // Set Result Pass
            func.SetResultPass(Role, TestCase);
        } catch (Exception e) {
            // Set Result Fail
            func.SetResultFail(Role, TestCase, e);
        }
    }
}
