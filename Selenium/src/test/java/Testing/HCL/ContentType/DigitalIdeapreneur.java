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
import java.util.concurrent.TimeUnit;

/**
 * Created by om on 1/24/2015.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class DigitalIdeapreneur extends HCLAbstract {
    private String Name;
    private String ProfilePic;
    private String AlternateText;
    private String Title;
    private String Bio;
    private String TwitterHandle;
    private String LastMaximumTwitterID;

    /**
     * Define a Collection method that will return the collection of parameters to the parallelTestThroughJUnit class
     * by using the @Parameters annotation.
     */
    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(
                new Object[][]{
                        {"dummy", FilesPath + "abc.jpg", "abc", "pic", "i am dummy", "Hello", "1234", "All inputs are correct", "Admin"}
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
    public DigitalIdeapreneur(String Name, String ProfilePic, String AlternateText, String Title, String Bio, String TwitterHandle, String LastMaximumTwitterID, String TestCase, String Role) {
        this.Name = Name;
        this.ProfilePic = ProfilePic;
        this.AlternateText = AlternateText;
        this.Title = Title;
        this.Bio = Bio;
        this.TwitterHandle = TwitterHandle;
        this.LastMaximumTwitterID = LastMaximumTwitterID;
        this.TestCase = TestCase;
        this.Role = Role;
    }

    @Test
    public void CreateContent() throws Exception {
        try {
            // Move to digital ideapreneur page
            driver.get(baseUrl + "/node/add/digital-ideapreneur");
            // Enter Name
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).sendKeys(Name);
            // Upload Profile Pic
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-profile-pic')]//input[@type='file']")).sendKeys(ProfilePic);
            // Click upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-profile-pic')]//input[@type='submit']")).click();
            // wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-profile-pic')]//input[contains(@id, 'remove')]")));
            // Enter alternate text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-profile-pic')]//input[contains(@id, 'alt')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-profile-pic')]//input[contains(@id, 'alt')]")).sendKeys(AlternateText);
            // Enter title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-profile-pic')]//input[contains(@id, 'title')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-profile-pic')]//input[contains(@id, 'title')]")).sendKeys(Title);
            // Enter description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'text-full')]/following-sibling::div[contains(@class, 'cke_browser_gecko')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Bio);
            // Enter Twitter Handle
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-twitter-handle')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-twitter-handle')]//input")).sendKeys(TwitterHandle);
            // Enter Last Maximum Twitter ID
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-last-max-tweet-id')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-last-max-tweet-id')]//input")).sendKeys(LastMaximumTwitterID);
            // Select DI Profile
            func2.SelectRandomSelectListOption("//div[contains(@class, 'field-name-field-di-profile')]//select");
            // Click save
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
            // Click edit link of that content which type is Digital Ideapreneur
            driver.get(baseUrl + "/admin/content");
            // Click edit link of that content which type is Digital Ideapreneur
            List<WebElement> Types = driver.findElements(By.xpath("//table[contains(@class, 'sticky-enabled')]/tbody/tr/td[3]"));
            String ContentType = null;
            int RowNumber = 0;
            for (int i = 0; i < Types.size(); i++) {
                ContentType = Types.get(i).getText();
                if (ContentType.equals("Digital Ideapreneur")) {
                    RowNumber = i + 1;
                    driver.findElement(By.xpath("//table[contains(@class, 'sticky-enabled')]/tbody/tr[" + RowNumber + "]/td[8]/ul/li[1]/a")).click();
                    break;
                }
            }
            // Enter Name
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).sendKeys(Name);
            // Remove Profile Pic
            if (func.isElementPresent(By.xpath("//div[contains(@class, 'field-name-field-profile-pic')]//input[contains(@id, 'remove')]"))) {
                driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-profile-pic')]//input[contains(@id, 'remove')]")).click();
                // wait for remove
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-profile-pic')]//input[@type='file']")));
            }
            // Upload Profile Pic
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-profile-pic')]//input[@type='file']")).sendKeys(ProfilePic);
            // Click upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-profile-pic')]//input[@type='submit']")).click();
            // wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-profile-pic')]//input[contains(@id, 'remove')]")));
            // Enter alternate text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-profile-pic')]//input[contains(@id, 'alt')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-profile-pic')]//input[contains(@id, 'alt')]")).sendKeys(AlternateText);
            // Enter title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-profile-pic')]//input[contains(@id, 'title')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-profile-pic')]//input[contains(@id, 'title')]")).sendKeys(Title);
            // Enter description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'text-full')]/following-sibling::div[contains(@class, 'cke_browser_gecko')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Bio);
            // Enter Twitter Handle
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-twitter-handle')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-twitter-handle')]//input")).sendKeys(TwitterHandle);
            // Enter Last Maximum Twitter ID
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-last-max-tweet-id')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-last-max-tweet-id')]//input")).sendKeys(LastMaximumTwitterID);
            // Select DI Profile
            func2.SelectRandomSelectListOption("//div[contains(@class, 'field-name-field-di-profile')]//select");
            // Click save
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
