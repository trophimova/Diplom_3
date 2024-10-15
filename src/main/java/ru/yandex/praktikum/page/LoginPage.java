package ru.yandex.praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.page.model.User;

/**
 * Страница авторизации
 */
public class LoginPage extends BasePage {

  // Поле "Email"
  private By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
  // Поле "Пароль"
  private By passwordInput = By.xpath("//input[@name='Пароль']");
  // Кнопка "Войти"
  private By loginButton = By.xpath("//button[text()='Войти']");

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  @Step("Ввести email: {email}")
  public void enterEmail(String email) {
    driver.findElement(emailInput).sendKeys(email);
  }

  @Step("Ввести пароль: {password}")
  public void enterPassword(String password) {
    driver.findElement(passwordInput).sendKeys(password);
  }

  @Step("Нажать на кнопку 'Войти'")
  public void clickLoginButton() {
    driver.findElement(loginButton).click();
  }

  @Step("Авторизоваться с email: {email} и паролем: {password}")
  public void login(User user) {
    enterEmail(user.getEmail());
    enterPassword(user.getPassword());
    clickLoginButton();
  }

  public Boolean isLoginButtonDisplayed() {
    return driver.findElement(loginButton).isDisplayed();
  }
}
