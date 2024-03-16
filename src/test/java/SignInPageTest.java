import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SignInPageTest extends BaseTest {
    private final Faker faker = new Faker();
    private final String name = faker.name().name();
    private final String email = faker.internet().emailAddress();
    private final String password = faker.internet().password(6, 12);

    @Before
    public void setUp() {
        clientStep.addClientsDataToClear(clientStep.setAccessToken(clientStep.createClient(email, password, name)));
    }

    @Test
    @DisplayName("Логин пользователем через кнопку \"Войти в аккаунт\"")
    public void signViaByAccountButtonMainPageReturnOk() {
        basePage.openMainPage();
        mainPage.enterByAccountEnterButtonMainPage();
        signInPage.setEmail(email);
        signInPage.setPassword(password);
        signInPage.clickEnterButton();
        Assert.assertTrue(mainPage.checkOrderButtonMainPage());
    }

    @Test
    @DisplayName("Логин пользователем через кнопку \"Войти в личный кабинет\"")
    public void signInViaPersonalAccountButtonMainPageReturnOk() {
        basePage.openMainPage();
        mainPage.enterByPersonalAccountEnterButtonMainPage();
        signInPage.setEmail(email);
        signInPage.setPassword(password);
        signInPage.clickEnterButton();
        Assert.assertTrue(mainPage.checkOrderButtonMainPage());
    }

    @Test
    @DisplayName("Логин пользователем через кнопку \"Войти\" в форме регистрации")
    public void signInViaEnterButtonSignUpPageReturnOk() {
        basePage.openSignUpPage();
        signUpPage.enterButtonRestorePasswordOrSignUpPage();
        signInPage.setEmail(email);
        signInPage.setPassword(password);
        signInPage.clickEnterButton();
        Assert.assertTrue(mainPage.checkOrderButtonMainPage());
    }

    @Test
    @DisplayName("Логин пользователем через кнопку \"Войти\" в форме восстановления пароля")
    public void signInViaEnterButtonRestorePasswordPageReturnOk() {
        basePage.openRecoveryPasswordPage();
        signUpPage.enterButtonRestorePasswordOrSignUpPage();
        signInPage.setEmail(email);
        signInPage.setPassword(password);
        signInPage.clickEnterButton();
        Assert.assertTrue(mainPage.checkOrderButtonMainPage());
    }
}