package praktikum;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.praktikum.page.*;
import ru.yandex.praktikum.page.model.User;

public class LoginTest extends BaseTest {

  private User user;
  private String accessToken;

  @Test
  public void shouldLoginThroughMainPageLoginButton() {
    user = User.createValidUser();
    accessToken = client.createUser(user);

    MainPage mainPage = new MainPage(driver);
    mainPage.open();
    mainPage.clickLoginButton();

    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(user);

    mainPage.clickUserAccountButton();

    UserAccountPage userAccountPage = new UserAccountPage(driver);
    Assert.assertTrue("Текст 'В этом разделе вы можете изменить свои персональные данные' в разделе Личный кабинет не отображается", userAccountPage.isAccountTextDisplayed());
    Assert.assertEquals("Имя пользователя в личном кабинете не соответсвует имени авторизованного пользователя", user.getName(), userAccountPage.getName());
    Assert.assertEquals("Email пользователя в личном кабинете не соответсвует Email указанному при авторизации", user.getEmail(), userAccountPage.getEmail());
  }

  @Test
  public void shouldLoginThroughUserAccountButton() {
    user = User.createValidUser();
    accessToken = client.createUser(user);

    MainPage mainPage = new MainPage(driver);
    mainPage.open();
    mainPage.clickUserAccountButton();

    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(user);

    mainPage.clickUserAccountButton();

    UserAccountPage userAccountPage = new UserAccountPage(driver);
    Assert.assertTrue("Текст 'В этом разделе вы можете изменить свои персональные данные' в разделе Личный кабинет не отображается", userAccountPage.isAccountTextDisplayed());
    Assert.assertEquals("Имя пользователя в личном кабинете не соответсвует имени авторизованного пользователя", user.getName(), userAccountPage.getName());
    Assert.assertEquals("Email пользователя в личном кабинете не соответсвует Email указанному при авторизации", user.getEmail(), userAccountPage.getEmail());
  }

  @Test
  public void shouldLoginThroughRegistrationFormLoginButton() {
    user = User.createValidUser();
    accessToken = client.createUser(user);

    RegistrationPage registrationPage = new RegistrationPage(driver);
    registrationPage.open();
    registrationPage.clickLoginLink();

    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(user);

    MainPage mainPage = new MainPage(driver);
    mainPage.clickUserAccountButton();

    UserAccountPage userAccountPage = new UserAccountPage(driver);
    Assert.assertTrue("Текст 'В этом разделе вы можете изменить свои персональные данные' в разделе Личный кабинет не отображается", userAccountPage.isAccountTextDisplayed());
    Assert.assertEquals("Имя пользователя в личном кабинете не соответсвует имени авторизованного пользователя", user.getName(), userAccountPage.getName());
    Assert.assertEquals("Email пользователя в личном кабинете не соответсвует Email указанному при авторизации", user.getEmail(), userAccountPage.getEmail());
  }

  @Test
  public void shouldLoginThroughPasswordRecoveryFormLoginButton() {
    user = User.createValidUser();
    accessToken = client.createUser(user);

    PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
    passwordRecoveryPage.open();
    passwordRecoveryPage.clickLoginLink();

    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(user);

    MainPage mainPage = new MainPage(driver);
    mainPage.clickUserAccountButton();

    UserAccountPage userAccountPage = new UserAccountPage(driver);
    Assert.assertTrue("Текст 'В этом разделе вы можете изменить свои персональные данные' в разделе Личный кабинет не отображается", userAccountPage.isAccountTextDisplayed());
    Assert.assertEquals("Имя пользователя в личном кабинете не соответсвует имени авторизованного пользователя", user.getName(), userAccountPage.getName());
    Assert.assertEquals("Email пользователя в личном кабинете не соответсвует Email указанному при авторизации", user.getEmail(), userAccountPage.getEmail());
  }

  @After
  public void tearDown() {
    if (accessToken != null) {
      Response response = client.deleteUser(accessToken);

      response.then()
          .assertThat()
          .statusCode(202);
    }
  }
}
