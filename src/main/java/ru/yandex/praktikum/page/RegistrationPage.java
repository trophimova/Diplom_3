package ru.yandex.praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.page.model.User;

/**
 * Страница регистрации
 */
public class RegistrationPage extends BasePage {

  private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

  // Поле "Имя"
  private By nameInput = By.xpath("//label[text()='Имя']/following-sibling::input");
  // Поле "Email"
  private By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
  // Поле "Пароль"
  private By passwordInput = By.xpath("//input[@name='Пароль']");
  // Кнопка "Зарегистрироваться"
  private By regButton = By.xpath("//button[text()='Зарегистрироваться']");
  // Ошибка для некорректного пароля
  private By errorText = By.xpath("//p[text()='Некорректный пароль']");
  // Ссылка "Войти"
  private By loginLink = By.linkText("Войти");

  public RegistrationPage(WebDriver driver) {
    super(driver);
  }

  @Step("Ввести имя: {name}")
  public void enterName(String name) {
    driver.findElement(nameInput).sendKeys(name);
  }

  @Step("Ввести email: {email}")
  public void enterEmail(String email) {
    driver.findElement(emailInput).sendKeys(email);
  }

  @Step("Ввести пароля: {password}")
  public void enterPassword(String password) {
    driver.findElement(passwordInput).sendKeys(password);
  }

  @Step("Нажать на кнопку 'Зарегистрироваться'")
  public void clickRegisterButton() {
    driver.findElement(regButton).click();
  }

  @Step("Нажать на ссылку 'Войти'")
  public void clickLoginLink() {
    driver.findElement(loginLink).click();
  }

  public Boolean isErrorTextDisplayed() {
    return driver.findElement(errorText).isDisplayed();
  }

  @Step("Зарегистрировать пользователя с именем {name}, email {email} и паролем {password}")
  public void register(User user) {
    enterName(user.getName());
    enterEmail(user.getEmail());
    enterPassword(user.getPassword());
    clickRegisterButton();
  }

  @Step("Перейти на страницу регистрации")
  public void open() {
    driver.get(PAGE_URL);
  }
}
