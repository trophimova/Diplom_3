package praktikum;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.praktikum.page.LoginPage;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.UserAccountPage;
import ru.yandex.praktikum.page.model.User;

public class UserAccountTest extends BaseTest {

  private User user;
  private String accessToken;

  @Test
  public void shouldNavigateToUserAccountPage() {
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
    Assert.assertEquals("URL не совпадает с ожидаемым", UserAccountPage.PAGE_URL, driver.getCurrentUrl());
  }

  @Test
  public void shouldLogOut() {
    user = User.createValidUser();
    accessToken = client.createUser(user);

    MainPage mainPage = new MainPage(driver);
    mainPage.open();
    mainPage.clickLoginButton();

    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(user);

    mainPage.clickUserAccountButton();

    UserAccountPage userAccountPage = new UserAccountPage(driver);
    userAccountPage.clickLogoutButton();

    mainPage.clickUserAccountButton();

    Assert.assertTrue("Кнопка 'Войти' не отображается", loginPage.isLoginButtonDisplayed());
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
