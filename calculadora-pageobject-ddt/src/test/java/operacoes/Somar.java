package operacoes;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class Somar {
    private AndroidDriver driver;
    
    public Somar(AndroidDriver driver){
        this.driver = driver;
    }

    // Clicar no botão somar
    public void clicarBtnSomar(){
        WebElement btnSomar = driver.findElement(AppiumBy.accessibilityId("plus"));
        btnSomar.click();
    }
}
