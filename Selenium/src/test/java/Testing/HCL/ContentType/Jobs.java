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
public class Jobs extends HCLAbstract {
    private String JobTitle;
    private String Designation;
    private String JobDescription;
    private String Experience;
    private String KenexaJobID;
    private String Qualification;
    private String Location;
    private String NoOfPositions;
    private String SiteID;
    private String Skills;
    private String SRNumber;
    private String KenexaJobLinkTitle;
    private String KenexaJobLinkUrl;
    private String UpdatedDate;
    private String Country;
    private String SecondarySkills;
    private String ExperienceFrom;
    private String ExperienceTo;

    /**
     * Define a Collection method that will return the collection of parameters to the parallelTestThroughJUnit class
     * by using the @Parameters annotation.
     */
    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(
                new Object[][]{
                        {"Title", "Designation", "JobDesc", "Experience", "JobID", "Qualification", "Location", "Position", "siteID", "Skills", "SRNumber", "JobTitle", "http://google.com/", "Today", "Country", "SecondSkills", "2007", "2009", "All inputs are correct", "Admin"}
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
    public Jobs(String JobTitle, String Designation, String JobDescription, String Experience, String KenexaJobID, String Qualification, String Location, String NoOfPositions, String SiteID, String Skills, String SRNumber, String KenexaJobLinkTitle, String KenexaJobLinkUrl, String UpdatedDate, String Country, String SecondarySkills, String ExperienceFrom, String ExperienceTo, String TestCase, String Role) {
        this.JobTitle = JobTitle;
        this.Designation = Designation;
        this.JobDescription = JobDescription;
        this.Experience = Experience;
        this.KenexaJobID = KenexaJobID;
        this.Qualification = Qualification;
        this.Location = Location;
        this.NoOfPositions = NoOfPositions;
        this.SiteID = SiteID;
        this.Skills = Skills;
        this.SRNumber = SRNumber;
        this.KenexaJobLinkTitle = KenexaJobLinkTitle;
        this.KenexaJobLinkUrl = KenexaJobLinkUrl;
        this.UpdatedDate = UpdatedDate;
        this.Country = Country;
        this.SecondarySkills = SecondarySkills;
        this.ExperienceFrom = ExperienceFrom;
        this.ExperienceTo = ExperienceTo;
        this.TestCase = TestCase;
        this.Role = Role;
    }

    @Test
    public void CreateContent() throws Exception {
        try {
            // Move to Jobs page
            driver.get(baseUrl + "/node/add/content-type-kenexa-jobs");
            // Enter Job Title
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).sendKeys(JobTitle);
            // Select Language
            func2.SelectRandomSelectListOption("//div[contains(@class, 'form-item-language')]//select");
            // Enter Designation
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-designation')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-designation')]//input")).sendKeys(Designation);
            // Enter Job Description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-job-description')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-job-description')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-job-description')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(JobDescription);
            // Enter Experience
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-experience')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-experience')]//input")).sendKeys(Experience);
            // Enter Kenexa Job ID
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-job-id')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-job-id')]//input")).sendKeys(KenexaJobID);
            // Enter Qualification
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-qualification')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-qualification')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-qualification')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Qualification);
            // Enter Location
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-location')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-location')]//input")).sendKeys(Location);
            // Enter No. Of Positions
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-no-of-positions')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-no-of-positions')]//input")).sendKeys(NoOfPositions);
            // Enter Site ID
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-site-id')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-site-id')]//input")).sendKeys(SiteID);
            // Enter Qualification
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-job-skills')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-job-skills')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-job-skills')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Skills);
            // Enter SR Number
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-sr-number')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-sr-number')]//input")).sendKeys(SRNumber);
            // Enter Kenexa Job Link Title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-job-link')]//div[contains(@class, 'link-field-title')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-job-link')]//div[contains(@class, 'link-field-title')]//input")).sendKeys(KenexaJobLinkTitle);
            // Enter Kenexa Job Link Url
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-job-link')]//div[contains(@class, 'link-field-url')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-job-link')]//div[contains(@class, 'link-field-url')]//input")).sendKeys(KenexaJobLinkUrl);
            // Enter Updated Date
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenxa-jobs-updated-date')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenxa-jobs-updated-date')]//input")).sendKeys("2015-01-27");
            // Enter Country
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-country')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-country')]//input")).sendKeys(Country);
            // Enter Secondary Skills
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-secondary-skills')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-secondary-skills')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-secondary-skills')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(SecondarySkills);
            // Enter Experience From
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-experience-range')]//div[contains(@class, 'from')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-experience-range')]//div[contains(@class, 'from')]//input")).sendKeys(ExperienceFrom);
            // Enter Experience To
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-experience-range')]//div[contains(@class, 'to')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-experience-range')]//div[contains(@class, 'to')]//input")).sendKeys(ExperienceTo);
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
            // Click edit link of that content which type is Jobs
            driver.get(baseUrl + "/admin/content");
            // Click edit link of that content which type is Jobs
            List<WebElement> Types = driver.findElements(By.xpath("//table[contains(@class, 'sticky-enabled')]/tbody/tr/td[3]"));
            String ContentType = null;
            int RowNumber = 0;
            for (int i = 0; i < Types.size(); i++) {
                ContentType = Types.get(i).getText();
                if (ContentType.equals("Jobs")) {
                    RowNumber = i + 1;
                    driver.findElement(By.xpath("//table[contains(@class, 'sticky-enabled')]/tbody/tr[" + RowNumber + "]/td[8]/ul/li[1]/a")).click();
                    break;
                }
            }
            // Enter Job Title
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).sendKeys(JobTitle);
            // Select Language
            func2.SelectRandomSelectListOption("//div[contains(@class, 'form-item-language')]//select");
            // Enter Designation
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-designation')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-designation')]//input")).sendKeys(Designation);
            // Enter Job Description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-job-description')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-job-description')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-job-description')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(JobDescription);
            // Enter Experience
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-experience')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-experience')]//input")).sendKeys(Experience);
            // Enter Kenexa Job ID
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-job-id')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-job-id')]//input")).sendKeys(KenexaJobID);
            // Enter Qualification
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-qualification')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-qualification')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-qualification')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Qualification);
            // Enter Location
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-location')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-location')]//input")).sendKeys(Location);
            // Enter No. Of Positions
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-no-of-positions')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-no-of-positions')]//input")).sendKeys(NoOfPositions);
            // Enter Site ID
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-site-id')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-site-id')]//input")).sendKeys(SiteID);
            // Enter Qualification
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-job-skills')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-job-skills')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-job-skills')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Skills);
            // Enter SR Number
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-sr-number')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-sr-number')]//input")).sendKeys(SRNumber);
            // Enter Kenexa Job Link Title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-job-link')]//div[contains(@class, 'link-field-title')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-job-link')]//div[contains(@class, 'link-field-title')]//input")).sendKeys(KenexaJobLinkTitle);
            // Enter Kenexa Job Link Url
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-job-link')]//div[contains(@class, 'link-field-url')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-job-link')]//div[contains(@class, 'link-field-url')]//input")).sendKeys(KenexaJobLinkUrl);
            // Enter Updated Date
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenxa-jobs-updated-date')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenxa-jobs-updated-date')]//input")).sendKeys("2015-01-27");
            // Enter Country
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-country')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-jobs-country')]//input")).sendKeys(Country);
            // Enter Secondary Skills
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-secondary-skills')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-secondary-skills')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-secondary-skills')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(SecondarySkills);
            // Enter Experience From
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-experience-range')]//div[contains(@class, 'from')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-experience-range')]//div[contains(@class, 'from')]//input")).sendKeys(ExperienceFrom);
            // Enter Experience To
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-experience-range')]//div[contains(@class, 'to')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-kenexa-experience-range')]//div[contains(@class, 'to')]//input")).sendKeys(ExperienceTo);
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
