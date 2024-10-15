package ru.yandex.praktikum.page.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.page.model.User;

import static io.restassured.RestAssured.given;

public class StellarBurgersClient {

  private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

  @Step("Удаление пользователя с токеном {accessToken}")
  public Response deleteUser(String accessToken) {
    return given().baseUri(BASE_URL)
        .header("Authorization", accessToken)
        .delete("/api/auth/user");
  }

  @Step("Авторизация пользователя {user.email}")
  public String loginUser(User user) {
    Response response = given().baseUri(BASE_URL)
        .header("Content-Type", "application/json")
        .body(user)
        .post("/api/auth/login");

    return response.then().extract().path("accessToken");
  }

  @Step("Регистрация пользователя {user.email}")
  public String createUser(User user) {
    Response response = given().baseUri(BASE_URL)
        .header("Content-Type", "application/json")
        .body(user)
        .post("/api/auth/register");

    return response.then().extract().path("accessToken");
  }
}
