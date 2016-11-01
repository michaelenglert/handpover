package org.appdynamics.handpover.screenshots;

import net.anthavio.phanbedder.Phanbedder;
import org.apache.commons.io.FileUtils;
import org.appdynamics.handpover.config.Globals;
import org.appdynamics.handpover.rest.Base;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static java.lang.Thread.sleep;

/**
 * Created by michi on 04.09.16.
 */
public class Capture implements Runnable{
    @Override
    public void run() {
        doScreenCapture();
    }

    private static void doScreenCapture() {
        try {
            File phantomjs = Phanbedder.unpack();
            DesiredCapabilities dcaps = new DesiredCapabilities();
            dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomjs.getAbsolutePath());
            String[] phantomArgs = new  String[] {
                "--webdriver-loglevel=NONE",
                "--ignore-ssl-errors=true",
                "--web-security=false",
            };
            dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs);
            PhantomJSDriver driver = new PhantomJSDriver(dcaps);
            
            driver.manage().window().setSize(new Dimension(1920, 1080));
            WebDriverWait wait = new WebDriverWait(driver, 20);
            driver.get(Globals.URL + Globals.CONTROLLER_ROOT + Globals.URL_HOME + Globals.URL_ENABLE_ACCOUNTS);
            wait.until(ExpectedConditions.elementToBeClickable(By.id("userNameInput")));
            File scrFile;

            String domain = driver.manage().getCookieNamed(Base.sessionCookie.getName()).getDomain();
            domain = domain.substring(domain.indexOf("."), domain.length());
            driver.manage().deleteCookieNamed(Base.sessionCookie.getName());
            driver.manage().addCookie(new Cookie(Base.sessionCookie.getName(), Base.sessionCookie.getValue(), domain,null, null));
            driver.manage().addCookie(new Cookie(Base.authCookie.getName(), Base.authCookie.getValue(), domain,null, null));
            driver.get(Globals.URL + Globals.CONTROLLER_ROOT + Globals.URL_HOME);

            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div > div:nth-of-type(2) > div > div.ads-home-screen-header-section > span.ads-link-toggle-item")));

            if (driver.findElements(By.cssSelector("div.closeButton:nth-child(2)")).size() != 0){
                driver.findElement(By.cssSelector("div.closeButton:nth-child(2)")).click();
                sleep(1000);
            }


            scrFile = driver.getScreenshotAs(OutputType.FILE);
            driver.quit();
            FileUtils.copyFile(scrFile, new File(Globals.OUTPUT_FOLDER + "screenshot.png"));
            Globals.PROGRESS = Globals.PROGRESS + 10;


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
