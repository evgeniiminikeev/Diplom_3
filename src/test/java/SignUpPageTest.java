import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

public class SignUpPageTest extends BaseTest {
    private final Faker faker = new Faker();
    private final String name = faker.name().name();
    private final String email = faker.internet().emailAddress();
    private final String passwordLessSixSymbols = faker.internet().password(1, 5);
    private final String passwordMoreSixSymbols = faker.internet().password(6, 12);

    @Test
    @DisplayName("Проверка успешной регистрации пользователя")
    public void createUserPositiveShowsOk() {
        basePage.openSignUpPage();
        signUpPage.setName(name);
        signUpPage.setEmail(email);
        signUpPage.setPassword(passwordMoreSixSymbols);
        signUpPage.clickRegisterButton();
        Assert.assertTrue(signUpPage.checkSuccessfulRegistration());
        clientStep.addClientsDataToClear(clientStep.setAccessToken(clientStep.loginClient(email, passwordMoreSixSymbols)));
    }

    @Test
    @DisplayName("Проверка запрета регистрации пользователя при вводе пароля меньше 6 символов")
    public void createUserNegativePasswordLessSixSymbolsShowsError() {
        basePage.openSignUpPage();
        signUpPage.setName(name);
        signUpPage.setEmail(email);
        signUpPage.setPassword(passwordLessSixSymbols);
        signUpPage.clickRegisterButton();
        Assert.assertTrue(signUpPage.checkLengthPassword());
    }
}