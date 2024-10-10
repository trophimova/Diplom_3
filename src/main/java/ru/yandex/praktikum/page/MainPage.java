package ru.yandex.praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Главная страница
 */
public class MainPage extends BasePage {

  private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";

  // Раздел Конструктор
  // Заголовок "Соберите бургер"
  private By header = By.xpath("//h1[text()='Соберите бургер']");

  // Кнопка "Булки"
  private By bunsButton = By.xpath("//span[text()='Булки']");
  // Кнопка "Соусы"
  private By saucesButton = By.xpath("//span[text()='Соусы']");
  // Кнопка "Начинки"
  private By fillingsButton = By.xpath("//span[text()='Начинки']");

  // Секция "Булки"
  public By bunsSection = By.xpath("//h2[text()='Булки']");
  // Секция "Соусы"
  public By saucesSection = By.xpath("//h2[text()='Соусы']");
  // Секция "Начинки"
  public By fillingsSection = By.xpath("//h2[text()='Начинки']");

  // Кнопка "Войти в аккаунт"
  private By loginButton = By.xpath("//button[text()='Войти в аккаунт']");

  public MainPage(WebDriver driver) {
    super(driver);
  }

  @Step("Открыть главную страницу")
  public void open() {
    driver.get(PAGE_URL);
  }

  @Step("Нажать на кнопку 'Войти в аккаунт'")
  public void clickLoginButton() {
    waitForLocator(loginButton);
    driver.findElement(loginButton).click();
  }

  public boolean isConstructorHeaderDisplayed() {
    return driver.findElement(header).isDisplayed();
  }

  @Step("Нажать на кнопку 'Булки'")
  public void clickBunsButton() {
    waitForLocator(bunsButton);
    driver.findElement(bunsButton).click();
    waitForElementInViewport(bunsSection);
  }

  @Step("Нажать на кнопку 'Соусы'")
  public void clickSaucesButton() {
    waitForLocator(saucesButton);
    driver.findElement(saucesButton).click();
    waitForElementInViewport(saucesSection);
  }

  @Step("Нажать на кнопку 'Начинки'")
  public void clickFillingsButton() {
    waitForLocator(fillingsButton);
    driver.findElement(fillingsButton).click();
    waitForElementInViewport(fillingsSection);
  }

  private void waitForElementInViewport(By locator) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(driver -> isElementInViewport(locator));
  }

  public boolean isElementInViewport(By locator) {
    WebElement element = driver.findElement(locator);
    return (Boolean) ((JavascriptExecutor) driver).executeScript(
        "var rect = arguments[0].getBoundingClientRect();" +
            "return (rect.top >= 0 && rect.left >= 0 && rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && " +
            "rect.right <= (window.innerWidth || document.documentElement.clientWidth));",
        element);
  }
}
