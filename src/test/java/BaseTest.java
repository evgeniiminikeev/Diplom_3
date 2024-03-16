import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import client.UserClient;
import step.ClientStep;
import pageobject.*;

import static config.BrowserConfig.CURRENT_BROWSER;

public class BaseTest {
        protected WebDriver driver;
        protected ClientStep clientStep;
        protected SignUpPage signUpPage;
        protected SignInPage signInPage;
        protected MainPage mainPage;
        protected BasePage basePage;
        public BaseTest() {
        }

        @Before
        public void setUpAll() {
            switch (CURRENT_BROWSER) {
                case"yandex":
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
                    ChromeOptions options = new ChromeOptions();
                    driver = new ChromeDriver(options);
                    break;
                case"chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }
            basePage = new BasePage(driver);
            mainPage = new MainPage(driver);
            clientStep = new ClientStep(new UserClient());
            signUpPage = new SignUpPage(driver);
            signInPage = new SignInPage(driver);
        }
        @After
        public void tearDown() {
            driver.quit();
            clientStep.clearTestClientsData();
        }
}