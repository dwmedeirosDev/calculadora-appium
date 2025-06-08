import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;

import operacoes.Somar;

public class Calculos {

    private AndroidDriver driver;
    private Somar somar;

    @BeforeEach
    public void setUp() {
        Capabilities options = new BaseOptions()
                .amend("platformName", "Android")
                .amend("appium:platformVersion", "13.0")
                .amend("appium:deviceName", "emulator5554")
                .amend("appium:deviceOrientation", "portrait")
                .amend("appium:appPackage", "com.google.android.calculator")
                .amend("appium:appActivity", "com.android.calculator2.Calculator")
                .amend("appium:automationName", "UiAutomator2")
                .amend("browserName", "")
                .amend("appium:ensureWebviewsHavePages", true)
                .amend("appium:nativeWebScreenshot", true)
                .amend("appium:newCommandTimeout", 3600)
                .amend("appium:connectHardwareKeyboard", true);

        driver = new AndroidDriver(this.getUrl(), options);
        somar = new Somar(driver);
    }

    @Test
    public void Somar() {
        // Cálculo de soma
        WebElement numero1 = driver.findElement(AppiumBy.accessibilityId("5"));
        numero1.click();
        somar.clicarBtnSomar();
        WebElement numero2 = driver.findElement(AppiumBy.accessibilityId("5"));
        numero2.click();
        WebElement sinalIgual = driver.findElement(AppiumBy.accessibilityId("equals"));
        sinalIgual.click();

        // Validação da soma
        assertEquals("10", driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final")).getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
