package praktikum;

import io.restassured.response.Response;
import org.junit.*;
import ru.yandex.praktikum.page.LoginPage;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.RegistrationPage;
import ru.yandex.praktikum.page.UserAccountPage;
import ru.yandex.praktikum.page.api.StellarBurgersClient;
import ru.yandex.praktikum.page.model.User;

public class RegistrationTest extends BaseTest {

  private User user;

  @Test
  public void shouldRegisterUser() {
    user = User.createValidUser();

    RegistrationPage registrationPage = new RegistrationPage(driver);
    registrationPage.open();
    registrationPage.register(user);

    LoginPage loginPage = new LoginPage(driver);
    Assert.assertTrue(loginPage.isLoginButtonDisplayed());

    loginPage.login(user);

    MainPage mainPage = new MainPage(driver);
    mainPage.clickUserAccountButton();

    UserAccountPage userAccountPage = new UserAccountPage(driver);
    Assert.assertTrue("Текст 'В этом разделе вы можете изменить свои персональные данные' в разделе Личный кабинет не отображается", userAccountPage.isAccountTextDisplayed());
    Assert.assertEquals("Имя пользователя в личном кабинете не соответсвует имени авторизованного пользователя", user.getName(), userAccountPage.getName());
    Assert.assertEquals("Email пользователя в личном кабинете не соответсвует Email указанному при авторизации", user.getEmail(), userAccountPage.getEmail());
  }

  @Test
  public void shouldReturnErrorIfInvalidPassword() {
    user = User.createInvalidUser();

    RegistrationPage registrationPage = new RegistrationPage(driver);
    registrationPage.open();
    registrationPage.register(user);

    Assert.assertTrue("Сообщение об ошибке для некорректного пароля не отображается", registrationPage.isErrorTextDisplayed());
  }

  @After
  public void tearDown() {
    StellarBurgersClient client = new StellarBurgersClient();
    String accessToken = client.loginUser(user);

    if (accessToken != null) {
      Response response = client.deleteUser(accessToken);

      response.then()
          .assertThat()
          .statusCode(202);
    }
  }
}
