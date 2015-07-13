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
public class Event extends HCLAbstract {
    private String Title;
    private String Logo;
    private String LogoAlternateText;
    private String LogoTitle;
    private String Banner;
    private String BannerAlternateText;
    private String BannerTitle;
    private String TimeFrom;
    private String TimeTo;
    private String Description;
    private String LinkTitle;
    private String LinkUrl;
    private String Schedule;
    private String Image;
    private String ImageAlternateText;
    private String ImageTitle;
    private String ThankYourText;
    private String MapTo;
    private String AssociatedEmail;
    private String RegisterText;
    private String RegisterLink;
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
                        {"title", FilesPath + "abc.jpg", "LogoAlt", "LogoTitle", FilesPath + "abv.png", "BannerAlt", "BannerTitle", "02/27/2015 - 11:31am", "02/27/2015 - 04:00pm", "Description", "LinkTitle", "https://www.google.co.in", FilesPath + "abc.ics", FilesPath + "abc.jpg", "ImageAlt", "ImageTitle", "ThankYourText", "dummy@gmail.com", "dummy2@gmail.com", "RegisterText", "RegisterLink", "MetatagDesc", "MetatagKey", "All inputs are correct", "Admin"}
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
    public Event(String Title, String Logo, String LogoAlternateText, String LogoTitle, String Banner, String BannerAlternateText, String BannerTitle, String TimeFrom, String TimeTo, String Description, String LinkTitle, String LinkUrl, String Schedule, String Image, String ImageAlternateText, String ImageTitle, String ThankYourText, String MapTo, String AssociatedEmail, String RegisterText, String RegisterLink, String MetatagDescription, String MetatagKeyword, String TestCase, String Role) {
        this.Title = Title;
        this.Logo = Logo;
        this.LogoAlternateText = LogoAlternateText;
        this.LogoTitle = LogoTitle;
        this.Banner = Banner;
        this.BannerAlternateText = BannerAlternateText;
        this.BannerTitle = BannerTitle;
        this.TimeFrom = TimeFrom;
        this.TimeTo = TimeTo;
        this.Description = Description;
        this.LinkTitle = LinkTitle;
        this.LinkUrl = LinkUrl;
        this.Schedule = Schedule;
        this.Image = Image;
        this.ImageAlternateText = ImageAlternateText;
        this.ImageTitle = ImageTitle;
        this.ThankYourText = ThankYourText;
        this.MapTo = MapTo;
        this.AssociatedEmail = AssociatedEmail;
        this.RegisterText = RegisterText;
        this.RegisterLink = RegisterLink;
        this.MetatagDescription = MetatagDescription;
        this.MetatagKeyword = MetatagKeyword;
        this.TestCase = TestCase;
        this.Role = Role;
    }

    @Test
    public void CreateContent() throws Exception {
        try {
            // Move to Event page
            driver.get(baseUrl + "/node/add/event");
            // Enter Title
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).sendKeys(Title);
            // Upload Logo
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-logo')]//input[@type='file']")).sendKeys(Logo);
            // Click Upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-logo')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-upload-logo')]//input[@value='Remove']")));
            // Enter logo alternate text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-logo')]//input[contains(@id, 'alt')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-logo')]//input[contains(@id, 'alt')]")).sendKeys(LogoAlternateText);
            // Enter logo title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-logo')]//input[contains(@id, 'title')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-logo')]//input[contains(@id, 'title')]")).sendKeys(LogoTitle);
            // Upload Banner
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-banner')]//input[@type='file']")).sendKeys(Banner);
            // Click Upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-banner')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-banner')]//input[@value='Remove']")));
            // Enter banner alternate text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-banner')]//input[contains(@id, 'alt')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-banner')]//input[contains(@id, 'alt')]")).sendKeys(BannerAlternateText);
            // Enter banner title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-banner')]//input[contains(@id, 'title')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-banner')]//input[contains(@id, 'title')]")).sendKeys(BannerTitle);
            // Enter Time From
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-time-slot')]//div[contains(@class, 'start-date-wrapper')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-time-slot')]//div[contains(@class, 'start-date-wrapper')]//input")).sendKeys(TimeFrom);
            // Enter Time To
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-time-slot')]//div[contains(@class, 'end-date-wrapper')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-time-slot')]//div[contains(@class, 'end-date-wrapper')]//input")).sendKeys(TimeTo);
            // Select TimeZone
            func2.SelectRandomSelectListOption("//div[contains(@class, 'field-name-field-timezone')]//select");
            // Enter Description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'text-full')]/following-sibling::div[contains(@class, 'cke_browser_gecko')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Description);
            // Enter Link Title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-link')]//div[contains(@class, 'link-field-title')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-link')]//div[contains(@class, 'link-field-title')]//input")).sendKeys(LinkTitle);
            // Enter Link Url
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-link')]//div[contains(@class, 'link-field-url')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-link')]//div[contains(@class, 'link-field-url')]//input")).sendKeys(LinkUrl);
            // Upload Schedule
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-add-to-schedule')]//input[@type='file']")).sendKeys(Schedule);
            // Click Upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-add-to-schedule')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-add-to-schedule')]//input[@value='Remove']")));
            // Upload Image
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[@type='file']")).sendKeys(Image);
            // Click Upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[@value='Remove']")));
            // Enter image alternate text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[contains(@id, 'alt')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[contains(@id, 'alt')]")).sendKeys(ImageAlternateText);
            // Enter image title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[contains(@id, 'title')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[contains(@id, 'title')]")).sendKeys(ImageTitle);
            // Enter Thank Your Text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-thankyou-text')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-thankyou-text')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-thankyou-text')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(ThankYourText);
            // Enter Map To
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-mapto')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-mapto')]//input")).sendKeys(MapTo);
            // Enter Associated Email
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-assoc-email')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-assoc-email')]//input")).sendKeys(AssociatedEmail);
            // Enter Register Test
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-reg-text')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-reg-text')]//input")).sendKeys(RegisterText);
            // Enter Register Link
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-register-link')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-register-link')]//input")).sendKeys(RegisterLink);
            // Select Service Lines
            new Select(driver.findElement(By.id("edit-taxonomy-vocabulary-2-und-hierarchical-select-selects-0"))).selectByVisibleText("HCL AXON");
            // Click Add
            driver.findElement(By.id("edit-taxonomy-vocabulary-2-und-hierarchical-select-dropbox-add")).click();
            // Wait for add
            Thread.sleep(5000);
            // Select Industries
            new Select(driver.findElement(By.id("edit-taxonomy-vocabulary-3-und-hierarchical-select-selects-0"))).selectByVisibleText("Professional Services");
            // Click Add
            driver.findElement(By.id("edit-taxonomy-vocabulary-3-und-hierarchical-select-dropbox-add")).click();
            // Wait for add
            Thread.sleep(5000);
            // Select Category
            func2.SelectRandomSelectListOption("//div[contains(@class, 'field-name-taxonomy-vocabulary-4')]//select");
            // Enter Metatag Description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-description')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-description')]//textarea")).sendKeys(MetatagDescription);
            // Enter Metatag Keyword
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-keywords')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-keywords')]//textarea")).sendKeys(MetatagKeyword);
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
            // Click edit link of that content which type is Event
            driver.get(baseUrl + "/admin/content");
            // Click edit link of that content which type is Event
            List<WebElement> Types = driver.findElements(By.xpath("//table[contains(@class, 'sticky-enabled')]/tbody/tr/td[3]"));
            String ContentType = null;
            int RowNumber = 0;
            for (int i = 0; i < Types.size(); i++) {
                ContentType = Types.get(i).getText();
                if (ContentType.equals("Event")) {
                    RowNumber = i + 1;
                    driver.findElement(By.xpath("//table[contains(@class, 'sticky-enabled')]/tbody/tr[" + RowNumber + "]/td[8]/ul/li[1]/a")).click();
                    break;
                }
            }
            // Enter Title
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).sendKeys(Title);
            // Remove Logo
            if (func.isElementPresent(By.xpath("//div[contains(@class, 'field-name-field-upload-logo')]//input[@value='Remove']"))) {
                driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-logo')]//input[@value='Remove']")).click();
                // Wait for remove
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-upload-logo')]//input[@type='file']")));
            }
            // Upload Logo
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-logo')]//input[@type='file']")).sendKeys(Logo);
            // Click Upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-logo')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-upload-logo')]//input[@value='Remove']")));
            // Enter logo alternate text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-logo')]//input[contains(@id, 'alt')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-logo')]//input[contains(@id, 'alt')]")).sendKeys(LogoAlternateText);
            // Enter logo title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-logo')]//input[contains(@id, 'title')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-logo')]//input[contains(@id, 'title')]")).sendKeys(LogoTitle);
            // Remove Banner
            if (func.isElementPresent(By.xpath("//div[contains(@class, 'field-name-field-banner')]//input[@value='Remove']"))) {
                driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-banner')]//input[@value='Remove']")).click();
                // Wait for remove
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-banner')]//input[@type='file']")));
            }
            // Upload Banner
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-banner')]//input[@type='file']")).sendKeys(Banner);
            // Click Upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-banner')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-banner')]//input[@value='Remove']")));
            // Enter banner alternate text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-banner')]//input[contains(@id, 'alt')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-banner')]//input[contains(@id, 'alt')]")).sendKeys(BannerAlternateText);
            // Enter banner title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-banner')]//input[contains(@id, 'title')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-banner')]//input[contains(@id, 'title')]")).sendKeys(BannerTitle);
            // Enter Time From
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-time-slot')]//div[contains(@class, 'start-date-wrapper')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-time-slot')]//div[contains(@class, 'start-date-wrapper')]//input")).sendKeys(TimeFrom);
            // Enter Time To
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-time-slot')]//div[contains(@class, 'end-date-wrapper')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-time-slot')]//div[contains(@class, 'end-date-wrapper')]//input")).sendKeys(TimeTo);
            // Select TimeZone
            func2.SelectRandomSelectListOption("//div[contains(@class, 'field-name-field-timezone')]//select");
            // Enter Description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'text-full')]/following-sibling::div[contains(@class, 'cke_browser_gecko')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Description);
            // Enter Link Title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-link')]//div[contains(@class, 'link-field-title')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-link')]//div[contains(@class, 'link-field-title')]//input")).sendKeys(LinkTitle);
            // Enter Link Url
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-link')]//div[contains(@class, 'link-field-url')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-link')]//div[contains(@class, 'link-field-url')]//input")).sendKeys(LinkUrl);
            // Remove Schedule
            if (func.isElementPresent(By.xpath("//div[contains(@class, 'field-name-field-add-to-schedule')]//input[@value='Remove']"))) {
                driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-add-to-schedule')]//input[@value='Remove']")).click();
                // Wait for remove
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-add-to-schedule')]//input[@type='file']")));
            }
            // Upload Schedule
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-add-to-schedule')]//input[@type='file']")).sendKeys(Schedule);
            // Click Upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-add-to-schedule')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-add-to-schedule')]//input[@value='Remove']")));
            // Remove Image
            if (func.isElementPresent(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[@value='Remove']"))) {
                driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[@value='Remove']")).click();
                // Wait for remove (Not locating through file because there is another file of same image type)
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("throbber")));
            }
            // Upload Image
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[@type='file']")).sendKeys(Image);
            // Click Upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[@value='Remove']")));
            // Enter image alternate text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[contains(@id, 'alt')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[contains(@id, 'alt')]")).sendKeys(ImageAlternateText);
            // Enter image title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[contains(@id, 'title')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[contains(@id, 'title')]")).sendKeys(ImageTitle);
            // Enter Thank Your Text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-thankyou-text')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-thankyou-text')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-thankyou-text')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(ThankYourText);
            // Enter Map To
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-mapto')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-mapto')]//input")).sendKeys(MapTo);
            // Enter Associated Email
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-assoc-email')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-assoc-email')]//input")).sendKeys(AssociatedEmail);
            // Enter Register Test
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-reg-text')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-reg-text')]//input")).sendKeys(RegisterText);
            // Enter Register Link
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-register-link')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-register-link')]//input")).sendKeys(RegisterLink);
            // Select Service Lines
            new Select(driver.findElement(By.id("edit-taxonomy-vocabulary-2-und-hierarchical-select-selects-0"))).selectByVisibleText("HCL AXON");
            // Click Add
            driver.findElement(By.id("edit-taxonomy-vocabulary-2-und-hierarchical-select-dropbox-add")).click();
            // Wait for add
            Thread.sleep(5000);
            // Select Industries
            new Select(driver.findElement(By.id("edit-taxonomy-vocabulary-3-und-hierarchical-select-selects-0"))).selectByVisibleText("Professional Services");
            // Click Add
            driver.findElement(By.id("edit-taxonomy-vocabulary-3-und-hierarchical-select-dropbox-add")).click();
            // Wait for add
            Thread.sleep(5000);
            // Select Category
            func2.SelectRandomSelectListOption("//div[contains(@class, 'field-name-taxonomy-vocabulary-4')]//select");
            // Enter Metatag Description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-description')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-description')]//textarea")).sendKeys(MetatagDescription);
            // Enter Metatag Keyword
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-keywords')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-keywords')]//textarea")).sendKeys(MetatagKeyword);
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
