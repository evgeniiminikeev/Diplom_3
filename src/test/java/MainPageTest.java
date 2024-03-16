import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainPageTest extends BaseTest {
    private final Faker faker = new Faker();
    private final String name = faker.name().name();
    private final String email = faker.internet().emailAddress();
    private final String password = faker.internet().password(6, 12);

    @Before
    public void setUp() {
        clientStep.addClientsDataToClear(clientStep.setAccessToken(clientStep.createClient(email, password, name)));
        basePage.openMainPage();
        mainPage.enterByAccountEnterButtonMainPage();
        signInPage.setEmail(email);
        signInPage.setPassword(password);
        signInPage.clickEnterButton();
    }

    @Test
    @DisplayName("Проверка перехода в личный кабинет")
    public void enterPersonalAccountSettingsReturnOk() {
        mainPage.enterByPersonalAccountEnterButtonMainPage();
        Assert.assertTrue(mainPage.checkExitButtonInAccountSettings());
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор кликом по кнопке конструктора")
    public void transitionFromPersonalAccountSettingsToConstructorViaClickConstructorReturnOk() {
        mainPage.enterByPersonalAccountEnterButtonMainPage();
        mainPage.clickConstructorButtonMainPage();
        Assert.assertTrue(mainPage.checkOrderButtonMainPage());
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор кликом по лого")
    public void transitionFromPersonalAccountSettingsToConstructorViaClickLogoReturnOk() {
        mainPage.enterByPersonalAccountEnterButtonMainPage();
        mainPage.clickLogoButtonMainPage();
        Assert.assertTrue(mainPage.checkOrderButtonMainPage());
    }

    @Test
    @DisplayName("Проверка выхода из аккаунта через личный кабинет")
    public void logOutFromPersonalAccountSettingsReturnOk() {
        mainPage.enterByPersonalAccountEnterButtonMainPage();
        mainPage.clickExitButtonInAccountSettings();
        Assert.assertTrue(signUpPage.checkSuccessfulRegistration());
    }

    @Test
    @DisplayName("Проверка перехода в раздел Соусы в конструкторе")
    public void transitionToSaucesChapterInConstructorReturnOk() {
        mainPage.clickSaucesChapter();
        Assert.assertTrue(mainPage.checkActiveSauceChapter());
    }

    @Test
    @DisplayName("Проверка перехода в раздел Булки в конструкторе")
    public void transitionToBunsChapterInConstructorReturnOk() {
        mainPage.clickSaucesChapter();
        mainPage.clickBunsChapter();
        Assert.assertTrue(mainPage.checkActiveBunsChapter());
    }

    @Test
    @DisplayName("Проверка перехода в раздел Начинки в конструкторе")
    public void transitionToFillingsChapterInConstructorReturnOk() {
        mainPage.clickFillingChapter();
        Assert.assertTrue(mainPage.checkActiveFillingsChapter());
    }
}