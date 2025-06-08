package commons;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class Common {
    private AndroidDriver driver;

    public Common(AndroidDriver driver) {
        this.driver = driver;
    }

    // Digitar um número
    public void digito(int numero) {
        String numeroStr = String.valueOf(numero);
        for (char caracter : numeroStr.toCharArray()) {
            WebElement digito = driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_" + caracter));
            digito.click();
        }
    }

    // Clicar no botão igual
    public void clicarBtnIgual() {
        WebElement btnIgual = driver.findElement(AppiumBy.accessibilityId("equals"));
        btnIgual.click();
    }

    // Resultado Obtido
    public String resultadoDisplay() {
        WebElement resultado = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final"));
        return resultado.getText();
    }
}
