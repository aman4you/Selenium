package Testing.HCL.WebForm;

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
public class CampaignCreator extends HCLAbstract {
    private String Title;
    private String Url;
    private String MainContent;
    private String ReadMore;
    private String UploadPDF;
    private String SitePDFPath;
    private String BannerImage;
    private String SiteImagePath;
    private String BannerAltText;
    private String BannerLink;
    private String SidebarVideoImage;
    private String SidebarVideoLink;
    private String SidebarVideoImageAltText;
    private String BlockHeader;
    private String Content;
    private String GAEventCategory;

    /**
     * Define a Collection method that will return the collection of parameters to the parallelTestThroughJUnit class
     * by using the @Parameters annotation.
     */
    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(
                new Object[][]{
                        {"title", "FirstCampaign", "Main", "Read", FilesPath + "bc.pdf", null, FilesPath + "abc.jpg", null, "BannerAlt", "https://www.google.co.in", FilesPath + "abv.png", "https://www.google.co.in", "SidebarAlt", "BlockHeader", "Content", "GAEvent", "Add New Files", "Admin"},
                        {"title", "SecondCampaign", "Main", "Read", FilesPath + "bc.pdf", null, FilesPath + "abc.jpg", null, "BannerAlt", "https://www.google.co.in", FilesPath + "abv.png", "https://www.google.co.in", "SidebarAlt", "BlockHeader", "Content", "GAEvent", "Add New Files", "Campaign-Admin"},
                        {"title", "ThirdCampaign", "Main", "Read", null, "sites/default/files/bc.pdf", null, "sites/default/files/abc.jpg", "BannerAlt", "https://www.google.co.in", null, null, null, null, null, "GAEvent", "Use Existing Files", "Admin"},
                        {"title", "FourthCampaign", "Main", "Read", null, "sites/default/files/bc.pdf", null, "sites/default/files/abc.jpg", "BannerAlt", "https://www.google.co.in", null, null, null, null, null, "GAEvent", "Use Existing Files", "Campaign-Admin"}
                }
        );
    }

    // Constructor will be used by the test runner to pass the parameters to the Excel_drive class instance.
    public CampaignCreator(String Title, String Url, String MainContent, String  ReadMore, String UploadPDF, String SitePDFPath, String BannerImage, String SiteImagePath, String BannerAltText, String BannerLink, String SidebarVideoImage, String SidebarVideoLink, String SidebarVideoImageAltText, String BlockHeader, String Content, String GAEventCategory, String TestCase, String Role) {
        this.Title = Title;
        this.Url = Url;
        this.MainContent = MainContent;
        this.ReadMore = ReadMore;
        this.UploadPDF = UploadPDF;
        this.SitePDFPath = SitePDFPath;
        this.BannerImage = BannerImage;
        this.SiteImagePath = SiteImagePath;
        this.BannerAltText = BannerAltText;
        this.BannerLink = BannerLink;
        this.SidebarVideoImage = SidebarVideoImage;
        this.SidebarVideoLink = SidebarVideoLink;
        this.SidebarVideoImageAltText = SidebarVideoImageAltText;
        this.BlockHeader = BlockHeader;
        this.Content = Content;
        this.GAEventCategory = GAEventCategory;
        this.TestCase = TestCase;
        this.Role = Role;
    }

    @Test
    public void CreateContent() throws Exception {
        try {
            // Move to Campaign Creator page
            driver.get(baseUrl + "/campaign-creator");
            // Enter Title
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).sendKeys(Title);
            // Enter URL
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-url')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-url')]//input")).sendKeys(Url);
            // Select Language
            func2.SelectRandomSelectListOption("//div[contains(@class, 'form-item-language')]//select");
            // Enter Main Content
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-main-content-value')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-main-content-value')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-main-content-value')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(MainContent);
            // Enter Read More
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-read-more-value')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-read-more-value')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-read-more-value')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(ReadMore);
            // Click Upload PDF
            driver.findElement(By.xpath("//fieldset[@id='edit-pdf-file']//a")).click();
            if (UploadPDF != null) {
                // Click Upload New PDF File
                driver.findElement(By.id("edit-pdf-file-type-new")).click();
                // Upload PDF
                driver.findElement(By.id("edit-pdf-file-new-file-upload")).sendKeys(UploadPDF);
                // Click Upload
                driver.findElement(By.id("edit-pdf-file-new-file-upload-button")).click();
                // Wait for upload
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-pdf-file-new-file-remove-button")));
            } else {
                // Click Use Existing PDF File
                driver.findElement(By.id("edit-pdf-file-type-existing")).click();
                // Enter URL of the existing PDF file
                driver.findElement(By.id("edit-pdf-file-existing-path")).clear();
                driver.findElement(By.id("edit-pdf-file-existing-path")).sendKeys(SitePDFPath);
            }
            // Click Banner
            driver.findElement(By.xpath("//fieldset[@id='edit-banner']//a")).click();
            if (BannerImage != null) {
                // Click Upload New Image
                driver.findElement(By.id("edit-banner-image-type-new")).click();
                // Upload Image
                driver.findElement(By.id("edit-banner-new-file-upload")).sendKeys(BannerImage);
                // Click Upload
                driver.findElement(By.id("edit-banner-new-file-upload-button")).click();
                // Wait for upload
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-banner-new-file-remove-button")));
            } else {
                // Click Use Existing Image
                driver.findElement(By.id("edit-banner-image-type-existing")).click();
                // URL of the existing image
                driver.findElement(By.id("edit-banner-existing-path")).clear();
                driver.findElement(By.id("edit-banner-existing-path")).sendKeys(SiteImagePath);
            }
            // Enter Banner Alt Text
            driver.findElement(By.id("edit-banner-text")).clear();
            driver.findElement(By.id("edit-banner-text")).sendKeys(BannerAltText);
            // Enter Banner Link
            driver.findElement(By.id("edit-banner-link")).clear();
            driver.findElement(By.id("edit-banner-link")).sendKeys(BannerLink);
            // Click Sidebar Video
            driver.findElement(By.xpath("//fieldset[@id='edit-sidebar-video']//a")).click();
            if (SidebarVideoImage != null) {
                // Click Add New
                driver.findElement(By.id("edit-sidebar-video-type-new")).click();
                // Upload Image
                driver.findElement(By.id("edit-sidebar-image-upload")).sendKeys(SidebarVideoImage);
                // Click Upload
                driver.findElement(By.id("edit-sidebar-image-upload-button")).click();
                // Wait for upload
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-sidebar-image-remove-button")));
                // Enter Youtube Video Link
                driver.findElement(By.id("edit-sidebar-video-link")).clear();
                driver.findElement(By.id("edit-sidebar-video-link")).sendKeys(SidebarVideoLink);
                // Enter Image Alt Text
                driver.findElement(By.id("edit-sidebar-video-text")).clear();
                driver.findElement(By.id("edit-sidebar-video-text")).sendKeys(SidebarVideoImageAltText);
            } else {
                // Click Use Existing
                driver.findElement(By.id("edit-sidebar-video-type-existing")).click();
                // Click Existing Sidebar Content
                func2.ClickRandomRadioButton(By.xpath("//fieldset[@id='edit-sidebar-existing']//input"));
            }
            // Click Sidebar Custom Content
            driver.findElement(By.xpath("//fieldset[@id='edit-sidebar-content']//a")).click();
            if (BlockHeader != null) {
                // Click Add New
                driver.findElement(By.id("edit-sidebar-content-type-new")).click();
                // Enter Block Header
                driver.findElement(By.id("edit-sidebar-content-header")).clear();
                driver.findElement(By.id("edit-sidebar-content-header")).sendKeys(BlockHeader);
                // Enter Content
                driver.findElement(By.xpath("//div[contains(@class, 'form-item-sidebar-content-content-value')]//span[contains(@class, 'cke_button__source_label')]")).click();
                driver.findElement(By.xpath("//div[contains(@class, 'form-item-sidebar-content-content-value')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
                driver.findElement(By.xpath("//div[contains(@class, 'form-item-sidebar-content-content-value')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Content);
            } else {
                // Click Use Existing
                driver.findElement(By.id("edit-sidebar-content-type-existing")).click();
                // Click Existing Sidebar Content
                func2.ClickRandomRadioButton(By.xpath("//div[@id='edit-sidebar-content-existing']//input"));
            }
            // Enter GA Event Category
            driver.findElement(By.id("edit-ga-event-category")).clear();
            driver.findElement(By.id("edit-ga-event-category")).sendKeys(GAEventCategory);
            // Click Save
            driver.findElement(By.id("edit-submit")).click();
            // Check content created or not
            driver.findElement(By.xpath("//div[contains(@class, 'campaign-edit-link')]"));
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
            // Move to Campaign Creator content page
            driver.get(baseUrl + "/de/" + Url);
            // Click Edit Campaign
            driver.findElement(By.xpath("//div[contains(@class, 'campaign-edit-link')]//a[contains(@href, 'edit')]")).click();
            // Enter Title
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).sendKeys(Title);
            // Enter URL
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-url')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-url')]//input")).sendKeys(Url);
            // Select Language
            func2.SelectRandomSelectListOption("//div[contains(@class, 'form-item-language')]//select");
            // Enter Main Content
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-main-content-value')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-main-content-value')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-main-content-value')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(MainContent);
            // Enter Read More
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-read-more-value')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-read-more-value')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-read-more-value')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(ReadMore);
            // Click Upload PDF
            driver.findElement(By.xpath("//fieldset[@id='edit-pdf-file']//a")).click();
            if (UploadPDF != null) {
                // Click Upload New PDF File
                driver.findElement(By.id("edit-pdf-file-type-new")).click();
                // Upload PDF
                driver.findElement(By.id("edit-pdf-file-new-file-upload")).sendKeys(UploadPDF);
                // Click Upload
                driver.findElement(By.id("edit-pdf-file-new-file-upload-button")).click();
                // Wait for upload
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-pdf-file-new-file-remove-button")));
            } else {
                // Click Use Existing PDF File
                driver.findElement(By.id("edit-pdf-file-type-existing")).click();
                // Enter URL of the existing PDF file
                driver.findElement(By.id("edit-pdf-file-existing-path")).clear();
                driver.findElement(By.id("edit-pdf-file-existing-path")).sendKeys(SitePDFPath);
            }
            // Click Banner
            driver.findElement(By.xpath("//fieldset[@id='edit-banner']//a")).click();
            if (BannerImage != null) {
                // Click Upload New Image
                driver.findElement(By.id("edit-banner-image-type-new")).click();
                // Upload Image
                driver.findElement(By.id("edit-banner-new-file-upload")).sendKeys(BannerImage);
                // Click Upload
                driver.findElement(By.id("edit-banner-new-file-upload-button")).click();
                // Wait for upload
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-banner-new-file-remove-button")));
            } else {
                // Click Use Existing Image
                driver.findElement(By.id("edit-banner-image-type-existing")).click();
                // URL of the existing image
                driver.findElement(By.id("edit-banner-existing-path")).clear();
                driver.findElement(By.id("edit-banner-existing-path")).sendKeys(SiteImagePath);
            }
            // Enter Banner Alt Text
            driver.findElement(By.id("edit-banner-text")).clear();
            driver.findElement(By.id("edit-banner-text")).sendKeys(BannerAltText);
            // Enter Banner Link
            driver.findElement(By.id("edit-banner-link")).clear();
            driver.findElement(By.id("edit-banner-link")).sendKeys(BannerLink);
            // Click Sidebar Video
            driver.findElement(By.xpath("//fieldset[@id='edit-sidebar-video']//a")).click();
            if (SidebarVideoImage != null) {
                // Click Add New
                driver.findElement(By.id("edit-sidebar-video-type-new")).click();
                // Upload Image
                driver.findElement(By.id("edit-sidebar-image-upload")).sendKeys(SidebarVideoImage);
                // Click Upload
                driver.findElement(By.id("edit-sidebar-image-upload-button")).click();
                // Wait for upload
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-sidebar-image-remove-button")));
                // Enter Youtube Video Link
                driver.findElement(By.id("edit-sidebar-video-link")).clear();
                driver.findElement(By.id("edit-sidebar-video-link")).sendKeys(SidebarVideoLink);
                // Enter Image Alt Text
                driver.findElement(By.id("edit-sidebar-video-text")).clear();
                driver.findElement(By.id("edit-sidebar-video-text")).sendKeys(SidebarVideoImageAltText);
            } else {
                // Click Use Existing
                driver.findElement(By.id("edit-sidebar-video-type-existing")).click();
                // Click Existing Sidebar Content
                func2.ClickRandomRadioButton(By.xpath("//fieldset[@id='edit-sidebar-existing']//input"));
            }
            // Click Sidebar Custom Content
            driver.findElement(By.xpath("//fieldset[@id='edit-sidebar-content']//a")).click();
            if (BlockHeader != null) {
                // Click Add New
                driver.findElement(By.id("edit-sidebar-content-type-new")).click();
                // Enter Block Header
                driver.findElement(By.id("edit-sidebar-content-header")).clear();
                driver.findElement(By.id("edit-sidebar-content-header")).sendKeys(BlockHeader);
                // Enter Content
                driver.findElement(By.xpath("//div[contains(@class, 'form-item-sidebar-content-content-value')]//span[contains(@class, 'cke_button__source_label')]")).click();
                driver.findElement(By.xpath("//div[contains(@class, 'form-item-sidebar-content-content-value')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
                driver.findElement(By.xpath("//div[contains(@class, 'form-item-sidebar-content-content-value')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Content);
            } else {
                // Click Use Existing
                driver.findElement(By.id("edit-sidebar-content-type-existing")).click();
                // Click Existing Sidebar Content
                func2.ClickRandomRadioButton(By.xpath("//div[@id='edit-sidebar-content-existing']//input"));
            }
            // Enter GA Event Category
            driver.findElement(By.id("edit-ga-event-category")).clear();
            driver.findElement(By.id("edit-ga-event-category")).sendKeys(GAEventCategory);
            // Click Save
            driver.findElement(By.id("edit-submit")).click();
            // Check content created or not
            driver.findElement(By.xpath("//div[contains(@class, 'campaign-edit-link')]"));
            // Set Result Pass
            func.SetResultPass(Role, TestCase);
        } catch (Exception e) {
            // Set Result Fail
            func.SetResultFail(Role, TestCase, e);
        }
    }
}
