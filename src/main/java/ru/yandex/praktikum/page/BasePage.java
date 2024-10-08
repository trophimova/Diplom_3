package ru.yandex.praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

  // Логотип
  protected By logo = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']//a");
  // Кнопка "Конструктор"
  protected By constructorButton = By.xpath("//*[text()='Конструктор']");
  // Кнопка "Личный кабинет"
  protected By userAccountButton = By.xpath("//*[text()='Личный Кабинет']");

  protected WebDriver driver;

  public BasePage(WebDriver driver) {
    this.driver = driver;
  }

  @Step("Нажать на логотип")
  public void clickLogo() {
    driver.findElement(logo).click();
  }

  @Step("Нажать на кнопку 'Личный кабинет'")
  public void clickUserAccountButton() {
    driver.findElement(userAccountButton).click();
  }

  @Step("Нажать на кнопку 'Конструктор'")
  public void clickConstructorButton() {
    driver.findElement(constructorButton).click();
  }
}
