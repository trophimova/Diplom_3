package praktikum;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.praktikum.page.LoginPage;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.UserAccountPage;
import ru.yandex.praktikum.page.model.User;

public class ConstructorTest extends BaseTest {

  private User user;
  private String accessToken;

  @Test
  public void shouldNavigateToConstructorFromAccountOnClickConstructor() {
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

    mainPage.clickConstructorButton();
    Assert.assertTrue("Заголовок 'Соберите бургер' не отображается", mainPage.isConstructorHeaderDisplayed());
  }

  @Test
  public void shouldNavigateToConstructorFromAccountOnClickLogo() {
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

    mainPage.clickLogo();
    Assert.assertTrue("Заголовок 'Соберите бургер' не отображается", mainPage.isConstructorHeaderDisplayed());
  }

  @Test
  public void shouldScrollToFillingsSectionWhenClickFillingsButton() {
    MainPage mainPage = new MainPage(driver);
    mainPage.open();

    mainPage.clickFillingsButton();

    Assert.assertTrue("Секция 'Начинки' не видна на экране", mainPage.isElementInViewport(mainPage.fillingsSection));
  }

  @Test
  public void shouldScrollToSaucesSectionWhenClickSaucesButton() {
    MainPage mainPage = new MainPage(driver);
    mainPage.open();

    mainPage.clickSaucesButton();

    Assert.assertTrue("Секция 'Соусы' не видна на экране", mainPage.isElementInViewport(mainPage.saucesSection));
  }

  @Test
  public void shouldScrollToBunsSectionWhenClickBunsButton() {
    MainPage mainPage = new MainPage(driver);
    mainPage.open();

    mainPage.clickFillingsButton();
    mainPage.clickBunsButton();

    Assert.assertTrue("Секция 'Булки' не видна на экране", mainPage.isElementInViewport(mainPage.bunsSection));
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
