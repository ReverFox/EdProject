package ru.fokinde.api.education;

import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

import static io.restassured.RestAssured.given;


@UtilityClass
public class UserService {
    private final String URL = "http://localhost";

    public static Response userAddAvailable() {
        String path = "/user/add/available";

        return given().when().get(path);
    }
}
