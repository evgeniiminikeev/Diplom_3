package step;

import client.UserClient;
import dto.CreateUserDTO;
import dto.LoginUserDTO;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;
import static config.APIConfig.DELETE_CONFIRM_MESSAGE;


public class ClientStep {
    private final UserClient userClient;
    private String token = null;
    static List<String> clientsData = new ArrayList<>();
    public ClientStep(UserClient userClient) {
        this.userClient = userClient;
    }
    @Step("Подготовка данных:создание клиента")
    public ValidatableResponse createClient(String email, String password, String name) {
        CreateUserDTO createUserDTO = new CreateUserDTO(email, password, name);
        return userClient.createClient(createUserDTO).then();
    }
    @Step("Подготовка к очистке: логин клиентом")
    public ValidatableResponse loginClient(String email, String password) {
        LoginUserDTO loginUserDTO = new LoginUserDTO(email, password);
        return userClient.loginClient(loginUserDTO).then();
    }
    @Step("Очистка данных: удаление клиента")
    public ValidatableResponse deleteClient(String token) {
        System.out.println("удаление токена " + token);
        return userClient.deleteClient(token).then();
    }
    @Step("Проверка статус кода в ответе")
    public void checkStatusCode(ValidatableResponse response, int code) {
        response.statusCode(code);
    }
    @Step("Проверка ответа при удалении клиента")
    public void checkResponseBodyAfterDeletion(ValidatableResponse response) {
        response.assertThat().body("success", is(true))
                .and().body("message", equalTo(DELETE_CONFIRM_MESSAGE));
    }
    public String setAccessToken(ValidatableResponse response) {
        token = response.assertThat().extract().path("accessToken");
        return token;
    }
    @Step("Очистка данных: удаление созданных клиентов")
    public void clearTestClientsData() {
        for (String token : clientsData) {
            ValidatableResponse response = deleteClient(token);
            checkStatusCode(response, SC_ACCEPTED);
            checkResponseBodyAfterDeletion(response);
        }
        clientsData.clear();
    }
    public void addClientsDataToClear(String token) {
        clientsData.add(token);
    }
}
