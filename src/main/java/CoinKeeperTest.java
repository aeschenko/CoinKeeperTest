import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.io.File;
import java.net.URL;

/**
 * Created by Alex on 9/5/17.
 */

public class CoinKeeperTest {

    private AndroidDriver driver;

    @Before
    public void setup() throws Exception {
        File appDir = new File("src/test/resources/apk");
        File app = new File(appDir, "CoinKeeper.apk");
        DesiredCapabilities caps = new DesiredCapabilities();
        //my test device hardcoded. Run "adb devices" and put your device name there
        caps.setCapability("deviceName", "ce061606758a560504");
        caps.setCapability("platformName", "Android");
        caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void MainTest() throws InterruptedException {

        //TEST IS RUNNING ON ENGLISH LOCALE

        //need to wait until splash screen disappears
        Thread.sleep(3000);

        //let's check that all desired UI elements are on screen
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/snippet_image\")").isDisplayed());
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/snippet_title\")").isDisplayed());
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/snippet_text\")").isDisplayed());
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/snippet_indicator\")").isDisplayed());

        //let's click on Skip and wait until animation finishes
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/snippet_skip_btn\")").click();
        Thread.sleep(3000);
        //checking UI of a new screen
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/snippet_image\")").isDisplayed());
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/snippet_title\")").isDisplayed());
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/snippet_text\")").isDisplayed());

        //let's choose "CONTINUE FOR FREE"
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"CONTINUE FOR FREE\")").click();

        //checking UI of a Welcome screen
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/onboarding_header\")").isDisplayed());
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/onboarding_welcome_txt\")").isDisplayed());
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/onboarding_welcome_hint\")").isDisplayed());
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/onboarding_choose_currency_header\")").isDisplayed());
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/onboarding_currency_text_view\")").isDisplayed());
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/onboarding_pocket_txt\")").isDisplayed());
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/onboarding_pocket_edit\")").isDisplayed());
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/onboarding_bank_txt\")").isDisplayed());
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/onboarding_bank_edit\")").isDisplayed());

        //let's insert some digits and click "Next"
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/onboarding_pocket_edit\")").sendKeys("984");
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/onboarding_bank_edit\")").sendKeys("736");
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/onboarding_next_btn\")").click();

        //now we need to skip annoying promotion. And we're done!
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.disrapp.coinkeeper.material:id/premium_close_btn\")").click();

        driver.quit();
    }
}