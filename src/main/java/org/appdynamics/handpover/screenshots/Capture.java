package org.appdynamics.handpover.screenshots;

import com.sun.jna.StringArray;
import net.anthavio.phanbedder.Phanbedder;
import org.apache.commons.io.FileUtils;
import org.appdynamics.handpover.config.Globals;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

/**
 * Created by michi on 04.09.16.
 */
public class Capture {
    public static void doScreenCapture (String user, String password) throws Exception {
        File phantomjs = Phanbedder.unpack();
        DesiredCapabilities dcaps = new DesiredCapabilities();
        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomjs.getAbsolutePath());
        String[] phantomArgs = new  String[] {
                "--webdriver-loglevel=NONE",
        };
        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs);
        PhantomJSDriver driver = new PhantomJSDriver(dcaps);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get(Globals.URL + Globals.CONTROLLER_ROOT);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("userNameInput")));
        driver.findElement(By.id("userNameInput")).click();
        driver.findElement(By.id("userNameInput")).clear();
        driver.findElement(By.id("userNameInput")).sendKeys(user);
        driver.findElement(By.id("passwordInput")).click();
        driver.findElement(By.id("passwordInput")).clear();
        driver.findElement(By.id("passwordInput")).sendKeys(password);
        driver.findElement(By.id("submitInput")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div > div:nth-of-type(2) > div > div.ads-home-screen-header-section > span.ads-link-toggle-item")));

        File scrFile = driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(Globals.OUTPUT_FOLDER + "screenshot.png"));
        driver.quit();
    }
}
