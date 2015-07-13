package BrowserStack;

/**
 * Created by om on 10/2/14.
 */

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@RunWith(value = Parameterized.class)
public class JUnit_Parallel_Run {
    private WebDriver driver;
    private String platform;
    private String browserName;
    private String browserVersion;

    @Parameterized.Parameters
    public static LinkedList<String[]> getEnvironments() throws Exception {
        LinkedList<String[]> env = new LinkedList<String[]>();
        env.add(new String[]{Platform.WINDOWS.toString(), "chrome", "27"});
        env.add(new String[]{Platform.WINDOWS.toString(),"firefox","20"});
        env.add(new String[]{Platform.WINDOWS.toString(),"ie","9"});
        env.add(new String[]{Platform.WINDOWS.toString(),"opera","12.14"});
        return env;
    }

    public JUnit_Parallel_Run(String platform, String browserName, String browserVersion) {
        this.platform = platform;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
    }

    @Before
    public void setUp() throws Exception {
        // Define Capabilities
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability("platform", platform);
        capability.setCapability("browser", browserName);
        capability.setCapability("browserVersion", browserVersion);
        capability.setCapability("build", "JUnit-Parallel");

        // Run Test on Browserstack
        driver = new RemoteWebDriver(new URL("http://amangoel9:244kSwskGEhtijmSnxHQ@hub.browserstack.com/wd/hub"), capability);
        driver.get("http://www.google.com");
    }

    @Test
    public void testSimple() throws Exception {
        // Take Screenshot
        driver = new Augmenter().augment(driver);
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("C:\\Users\\om\\Downloads\\abc\\" + browserName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
