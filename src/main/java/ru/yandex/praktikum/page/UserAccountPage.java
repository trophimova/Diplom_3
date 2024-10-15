package ru.yandex.praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Страница Личный кабинет
 */
public class UserAccountPage extends BasePage {

  public static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";
  // Текст в разделе Личный кабинет
  private By accountText = By.xpath("//p[text()='В этом разделе вы можете изменить свои персональные данные']");
  // Инпут "Имя"
  private By nameInput = By.xpath("//label[text()='Имя']/following-sibling::input");

  // Инпут "Email"
  private By emailInput = By.xpath("//label[text()='Логин']/following-sibling::input");
  // Кнопка "Выход"
  private By logoutButton = By.xpath("//button[text()='Выход']");

  public UserAccountPage(WebDriver driver) {
    super(driver);
  }

  public Boolean isAccountTextDisplayed() {
    return driver.findElement(accountText).isDisplayed();
  }

  @Step("Получить значение инпута 'Имя'")
  public String getName() {
    return driver.findElement(nameInput).getAttribute("value");
  }

  @Step("Получить значение инпута 'Email'")
  public String getEmail() {
    return driver.findElement(emailInput).getAttribute("value");
  }

  @Step("Нажать на кнопку 'Выйти'")
  public void clickLogoutButton() {
    driver.findElement(logoutButton).click();
  }
}
