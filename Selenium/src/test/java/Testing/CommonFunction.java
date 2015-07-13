package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by om on 1/23/2015.
 */
public class CommonFunction {
    private WebDriver driver;

    public CommonFunction(WebDriver driver) {
        this.driver = driver;
    }

    public String GenerateDate(String Time) throws IOException {
        String Date = null;
        Calendar Cal = Calendar.getInstance();
        DateFormat DateFormat = new SimpleDateFormat("MM/dd/yyyy");
        if (Time == "Today") {
            Date = DateFormat.format(Cal.getTime());
        } else if (Time == "Yesterday") {
            Cal.add(Cal.DATE, -1);
            Date = DateFormat.format(Cal.getTime());
        } else if (Time == "Tomorrow") {
            Cal.add(Cal.DATE, 1);
            Date = DateFormat.format(Cal.getTime());
        }
        return Date;
    }

    public boolean isAttributePresent(String Element, String Attribute) {
        Boolean result = false;
        String value = driver.findElement(By.xpath(Element)).getAttribute(Attribute);
        if (value != null){
            result = true;
        }
        return result;
    }

    public void ClickRandomCheckboxes(By Locator) throws IOException {
        List<WebElement> Checkboxes = driver.findElements(Locator);
        int NumberOfCheckboxes = Checkboxes.size();
        int NumberOfCheckboxesToBeClicked = NumberOfCheckboxes/2;
        // Get random integer numbers as NumberOfCheckboxesToBeClicked from NumberOfCheckboxes.
        int[] RandomNumber = ManyRandomIntegerNumber(NumberOfCheckboxes, NumberOfCheckboxesToBeClicked);
        for (int i = 0; i < NumberOfCheckboxesToBeClicked; i++) {
            int ExactCheckbox = RandomNumber[i] - 1;
            Checkboxes.get(ExactCheckbox).click();
        }
    }

    // Locator -> //div[contains(@class, field-name-field-job-posting-type)]//input
    public void ClickRandomRadioButton(By Locator) throws IOException {
        List<WebElement> RadioButtons = driver.findElements(Locator);
        int NumberOfRadioButtons = RadioButtons.size();
        if (NumberOfRadioButtons > 0) {
            // Access Random radio button
            int RandomElement = RandomIntegerNumber(NumberOfRadioButtons) - 1;
            // Click Random Element
            RadioButtons.get(RandomElement).click();
        }
    }

    public void SelectRandomElement(String MainLocator, String RestLocator) throws IOException {
        List<WebElement> Elements = driver.findElements(By.xpath(MainLocator));
        int NumberOfElements = Elements.size();
        if(NumberOfElements > 0) {
            // Access Random Element
            int RandomElement = RandomIntegerNumber(NumberOfElements);
            // Click Random Element
            driver.findElement(By.xpath(MainLocator + "[" + RandomElement + "]" + RestLocator)).click();
        }
    }

    // CountItem -> //div[contains(@class, 'field-name-field-site-section')]//ul[@class='chosen-results']/li
    // Click Chosen -> //div[contains(@class, 'field-name-field-site-section')]//ul
    // SelectItem -> [@data-option-array-index='
    public void SelectRandomChosenOption(String CountItem, String ClickChosen, String SelectItem) throws IOException {
        // Click chosen select list
        driver.findElement(By.xpath(ClickChosen)).click();
        // Check chosen select list option exist or not
        List<WebElement> SelectListOptions = driver.findElements(By.xpath(CountItem));
        int OptionsNumber = SelectListOptions.size();
        if(OptionsNumber > 0) {
            // Access Random Option
            int RandomOption = RandomIntegerNumber(OptionsNumber) - 1;
            // Select Random Option
            driver.findElement(By.xpath(CountItem + SelectItem + RandomOption + "']")).click();
        }
    }

    // Locator -> //div[contains(@class, 'form-item-job-posting-cid')]//select
    public void SelectRandomSelectListOption(String Locator) throws IOException {
        // Check Select List Options exist or not
        List<WebElement> SelectListOptions = driver.findElements(By.xpath(Locator + "/option"));
        int OptionsNumber = SelectListOptions.size();
        int TotalOptionsNumber = OptionsNumber - 1;
        if(TotalOptionsNumber > 0) {
            // Access Random Option
            int RandomOption = RandomIntegerNumber(TotalOptionsNumber);
            // Select Random Option
            new Select(driver.findElement(By.xpath(Locator))).selectByIndex(RandomOption);
        }
    }

    public void SelectRandomSelectListOptionWhichInGroup(String Locator) throws IOException {
        WebElement RoteElement = driver.findElement(By.xpath(Locator));
        List<WebElement> NodeElements = null;
        // Make list of elements that have tagName option in RoteElement.
        NodeElements = RoteElement.findElements(By.tagName("option"));
        // Count number of elements
        int NumberOfElement = NodeElements.size();
        if (NumberOfElement > 0) {
            // Access Random Element
            int RandomNumber = RandomIntegerNumber(NumberOfElement) - 1;
            // Select Random Element
            NodeElements.get(RandomNumber).click();
        }
    }

    public int RandomIntegerNumberBetweenRange(int Max, int Min) throws IOException {
        Random random = new Random();
        int number = random.nextInt(Max - Min) + 6;
        return number;
    }

    public int[] ManyRandomIntegerNumber(int Max, int NumberOfInteger) throws IOException {
        Random random = new Random();
        // HashSet class used to get unique values.
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < NumberOfInteger) {
            set.add(random.nextInt(Max) + 1);
        }
        // Assign Integer object value to primitive type int variable.
        int[] number = new int[set.size()];
        int i = 0;
        for (Integer val : set) {
            number[i++] = val;
        }
        return number;
    }

    public int RandomIntegerNumber(int Max) throws IOException {
        Random random = new Random();
        // If Max have 5 than random number return from 0 to 4, So add 1.
        int number = random.nextInt(Max) + 1;
        return number;
    }

    public Double RandomDecimalNumber(int Max) throws IOException {
        Double DecimalNumber = .5;
        Random random = new Random();
        Double number = Double.valueOf(random.nextInt(Max) + 1) * DecimalNumber;
        return number;
    }

    public String[] RandomWords(int NumberOfWords) throws IOException {
        String[] randomStrings = new String[NumberOfWords];
        Random random = new Random();
        for(int i = 0; i < NumberOfWords; i++) {
            // Length of array get through nextInt() method.
            char[] word = new char[random.nextInt(8)];
            for(int j = 0; j < word.length; j++) {
                // Character for word get through Typecast.
                word[j] = (char)('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }
}
