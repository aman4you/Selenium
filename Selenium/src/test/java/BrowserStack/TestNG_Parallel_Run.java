/**
 * Annotation parameter with variable “browser”, "version" and "platform" will receive the value passed from xml file.
 * Check the values and then test will perform according to these values.
 */

package BrowserStack;

/**
 * Created by om on 10/2/14.
 */

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestNG_Parallel_Run {
    private WebDriver driver;
    private String browserName;

    @BeforeClass
    @org.testng.annotations.Parameters(value={"browser","version","platform"})
    public void setUp(String browser, String version, String platform) throws Exception {
        // Define Capabilities
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability("platform",platform);
        capability.setCapability("browserName", browser);
        capability.setCapability("browserVersion", version);
        capability.setCapability("project", "TestNG");
        capability.setCapability("build", "version1");
        capability.setCapability("browserstack.debug", "true");

        // Run Test on Browserstack
        driver = new RemoteWebDriver(new URL("http://amangoel9:244kSwskGEhtijmSnxHQ@hub.browserstack.com/wd/hub"), capability);
        driver.get("http://www.google.com");
        browserName = browser;
    }

    @Test
    public void testSimple() throws Exception {
        // Take Screenshot
        driver = new Augmenter().augment(driver);
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile,new File("C:\\Users\\om\\Downloads\\abc\\" + browserName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }
}
