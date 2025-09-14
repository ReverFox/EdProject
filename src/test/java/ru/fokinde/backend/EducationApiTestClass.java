package ru.fokinde.backend;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fokinde.data.dto.UserAddAvailableResponseDto;

import static io.qameta.allure.Allure.step;
import static ru.fokinde.api.education.UserService.userAddAvailable;
import static ru.fokinde.mock.EducationApiMock.*;

@Epic("EducationApi")
@Feature("User functionality")
public class EducationApiTestClass {

    @BeforeClass
    public void init() {
        initWireMock();
    }

    @AfterMethod
    public void deleteMocks() {
        deleteAllTestMocks();
    }

    @Test(description = "Проверка доступности добавления пользователя")
    @TmsLink("TMSAPI-001")
    public void isUserAddAvailableSuccessTest() {
        buildUserAddAvailable();
        Response response = userAddAvailable();

        step("Получаем ответ на запрос: GET /user/add/available", () -> {
            SoftAssertions softly = new SoftAssertions();

            softly.assertThat(response.statusCode())
                    .as("status code: 200")
                    .isEqualTo(200);

            UserAddAvailableResponseDto responseDto = response.as(UserAddAvailableResponseDto.class);
            softly.assertThat(responseDto.isUserAddAvailable())
                    .as("isUserAddAvailable должен быть true")
                    .isEqualTo(true);

            softly.assertAll();
            System.out.println("isUserAddAvailable: %s".formatted(responseDto.isUserAddAvailable()));
        });
    }
}
