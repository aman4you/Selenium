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
public class IdeapreneurshipCaseStudies extends HCLAbstract {
    private String Title;
    private String EmployeeName;
    private String EmployeePicture;
    private String AlternateText;
    private String PictureTitle;
    private String EmployeeDesignation;
    private String Situation;
    private String Problem;
    private String Solution;
    private String Impact;
    private String Caption;

    /**
     * Define a Collection method that will return the collection of parameters to the parallelTestThroughJUnit class
     * by using the @Parameters annotation.
     */
    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(
                new Object[][]{
                        {"data", "dummy", FilesPath + "abc.jpg", "abc", "pic", "designation", "situation", "problem", "solution", "impact", "caption", "all inputs are correct", "Admin"}
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
    public IdeapreneurshipCaseStudies(String Title, String EmployeeName, String EmployeePicture, String AlternateText, String PictureTitle, String EmployeeDesignation, String Situation, String Problem, String Solution, String Impact, String Caption, String TestCase, String Role) {
        this.Title = Title;
        this.EmployeeName = EmployeeName;
        this.EmployeePicture = EmployeePicture;
        this.AlternateText = AlternateText;
        this.PictureTitle = PictureTitle;
        this.EmployeeDesignation = EmployeeDesignation;
        this.Situation = Situation;
        this.Problem = Problem;
        this.Solution = Solution;
        this.Impact = Impact;
        this.Caption = Caption;
        this.TestCase = TestCase;
        this.Role = Role;
    }

    @Test
    public void CreateContent() throws Exception {
        try {
            // Move to ideapreneurship case studies page
            driver.get(baseUrl + "/node/add/ideapreneurship-case-studies");
            // Enter Title
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).sendKeys(Title);
            // Enter Employee Name
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employe-name')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employe-name')]//input")).sendKeys(EmployeeName);
            // Upload Employee Picture
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[@type='file']")).sendKeys(EmployeePicture);
            // Click upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'remove')]")));
            // Enter alternate text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'alt')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'alt')]")).sendKeys(AlternateText);
            // Enter title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'title')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'title')]")).sendKeys(PictureTitle);
            // Enter Employee Designation
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-des')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-des')]//textarea")).sendKeys(EmployeeDesignation);
            // Enter Situation
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-situation')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-situation')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-situation')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Situation);
            // Enter Problem
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-problem')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-problem')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-problem')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Problem);
            // Enter Solution
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-solution')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-solution')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-solution')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Solution);
            // Enter Impact
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-impact')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-impact')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-impact')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Impact);
            // Enter Caption
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-caption')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-caption')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-caption')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Caption);
            // Select Line of Business
            func2.SelectRandomSelectListOption("//div[contains(@class, 'field-name-field-ideaprene-line-of-busniess')]//select");
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
            // Click edit link of that content which type is Ideapreneurship Case Studies
            driver.get(baseUrl + "/admin/content");
            // Click edit link of that content which type is Ideapreneurship Case Studies
            List<WebElement> Types = driver.findElements(By.xpath("//table[contains(@class, 'sticky-enabled')]/tbody/tr/td[3]"));
            String ContentType = null;
            int RowNumber = 0;
            for (int i = 0; i < Types.size(); i++) {
                ContentType = Types.get(i).getText();
                if (ContentType.equals("Ideapreneurship Case Studies")) {
                    RowNumber = i + 1;
                    driver.findElement(By.xpath("//table[contains(@class, 'sticky-enabled')]/tbody/tr[" + RowNumber + "]/td[8]/ul/li[1]/a")).click();
                    break;
                }
            }
            // Enter Title
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).sendKeys(Title);
            // Enter Employee Name
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employe-name')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employe-name')]//input")).sendKeys(EmployeeName);
            // Remove Employee Picture
            if (func.isElementPresent(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'remove')]"))) {
                driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'remove')]")).click();
                // Wait for remove
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[@type='file']")));
            }
            // Upload Employee Picture
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[@type='file']")).sendKeys(EmployeePicture);
            // Click upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'remove')]")));
            // Enter alternate text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'alt')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'alt')]")).sendKeys(AlternateText);
            // Enter title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'title')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'title')]")).sendKeys(PictureTitle);
            // Enter Employee Designation
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-des')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-des')]//textarea")).sendKeys(EmployeeDesignation);
            // Enter Situation
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-situation')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-situation')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-situation')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Situation);
            // Enter Problem
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-problem')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-problem')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-problem')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Problem);
            // Enter Solution
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-solution')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-solution')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-solution')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Solution);
            // Enter Impact
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-impact')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-impact')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-impact')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Impact);
            // Enter Caption
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-caption')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-caption')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneurship-caption')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Caption);
            // Select Line of Business
            func2.SelectRandomSelectListOption("//div[contains(@class, 'field-name-field-ideaprene-line-of-busniess')]//select");
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
