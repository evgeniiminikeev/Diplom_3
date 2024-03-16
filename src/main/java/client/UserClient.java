package client;

import io.restassured.response.Response;
import dto.CreateUserDTO;
import dto.LoginUserDTO;

import static config.APIConfig.*;


public class UserClient extends RestClient {
    public Response createClient(CreateUserDTO createUserDTO) {
        return getDefaultRequestSpecificationWithoutToken()
                .body(createUserDTO)
                .when()
                .post(CREATE_USER);
    }
    public Response loginClient(LoginUserDTO loginUserDTO) {
        return getDefaultRequestSpecificationWithoutToken()
                .body(loginUserDTO)
                .when()
                .post(LOGIN_USER);
    }
    public Response deleteClient(String token) {
        return getRequestSpecificationWithToken(token)
                .when()
                .delete(USER);
    }
}