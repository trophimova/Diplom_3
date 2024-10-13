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

  // Вкладка "Булки"
  public By bunsTab = By.xpath("//span[text()='Булки']/parent::div");
  // Вкладка "Соусы"
  public By saucesTab = By.xpath("//span[text()='Соусы']/parent::div");
  // Вкладка "Начинки"
  public By fillingsTab = By.xpath("//span[text()='Начинки']/parent::div");

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

  @Step("Нажать на вкладку 'Булки'")
  public void clickBunsTab() {
    waitForLocator(bunsTab);
    driver.findElement(bunsTab).click();
    waitForElementInViewport(bunsSection);
  }

  @Step("Нажать на вкладку 'Соусы'")
  public void clickSaucesTab() {
    waitForLocator(saucesTab);
    driver.findElement(saucesTab).click();
    waitForElementInViewport(saucesSection);
  }

  @Step("Нажать на вкладку 'Начинки'")
  public void clickFillingsTab() {
    waitForLocator(fillingsTab);
    driver.findElement(fillingsTab).click();
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

  public boolean isSectionTabSelected(By locator) {
    WebElement element = driver.findElement(locator);
    return element.getAttribute("class").contains("tab_tab_type_current__2BEPc");
  }
}
