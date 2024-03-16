package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static config.PageObjectConfig.*;

public class BasePage {
    protected final WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Открытие страницы регистрации")
    public void openSignUpPage() {
        driver.get(BASE_URI + REGISTER);
    }
    @Step("Открытие основной страницы")
    public void openMainPage() {
        driver.get(BASE_URI);
    }
    @Step("Открытие страницы восстановления пароля")
    public void openRecoveryPasswordPage() {
        driver.get(BASE_URI + RECOVERY_PASSWORD);
    }
}
