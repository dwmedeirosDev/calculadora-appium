import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Capabilities;

import commons.Common;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import operacoes.Somar;

public class Calculos {

    private AndroidDriver driver;
    private Common common;
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
        common = new Common(driver);
        somar = new Somar(driver);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "csv/numeros.csv", numLinesToSkip = 1, delimiter = ',')
    public void Somar(int numero1, int numero2) {
        // Cálculo de soma
        common.digito(numero1);
        somar.clicarBtnSomar();
        common.digito(numero2);
        common.clicarBtnIgual();

        // Validação da soma
        String provaRealSoma = String.valueOf(numero1 + numero2);
        assertEquals(provaRealSoma, common.resultadoDisplay());
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
