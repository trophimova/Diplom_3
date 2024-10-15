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

    mainPage.clickFillingsTab();

    Assert.assertTrue("Вкладка 'Начинки' не выбрана", mainPage.isSectionTabSelected(mainPage.fillingsTab));
    Assert.assertFalse("Вкладка 'Соусы' выбрана", mainPage.isSectionTabSelected(mainPage.saucesTab));
    Assert.assertFalse("Вкладка 'Булки' выбрана", mainPage.isSectionTabSelected(mainPage.bunsTab));

    Assert.assertTrue("Секция 'Начинки' не видна на экране", mainPage.isElementInViewport(mainPage.fillingsSection));
  }

  @Test
  public void shouldScrollToSaucesSectionWhenClickSaucesButton() {
    MainPage mainPage = new MainPage(driver);
    mainPage.open();

    mainPage.clickFillingsTab();
    mainPage.clickSaucesTab();

    Assert.assertTrue("Вкладка 'Соусы' не выбрана", mainPage.isSectionTabSelected(mainPage.saucesTab));
    Assert.assertFalse("Вкладка 'Булки' выбрана", mainPage.isSectionTabSelected(mainPage.bunsTab));
    Assert.assertFalse("Вкладка 'Начинки' выбрана", mainPage.isSectionTabSelected(mainPage.fillingsTab));

    Assert.assertTrue("Секция 'Соусы' не видна на экране", mainPage.isElementInViewport(mainPage.saucesSection));
  }

  @Test
  public void shouldScrollToBunsSectionWhenClickBunsButton() {
    MainPage mainPage = new MainPage(driver);
    mainPage.open();

    mainPage.clickFillingsTab();
    mainPage.clickBunsTab();

    Assert.assertTrue("Вкладка 'Булки' не выбрана", mainPage.isSectionTabSelected(mainPage.bunsTab));
    Assert.assertFalse("Вкладка 'Начинки' выбрана", mainPage.isSectionTabSelected(mainPage.fillingsTab));
    Assert.assertFalse("Вкладка 'Соусы' выбрана", mainPage.isSectionTabSelected(mainPage.saucesTab));

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
