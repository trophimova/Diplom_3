package praktikum;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.page.api.StellarBurgersClient;
import ru.yandex.praktikum.page.webdriver.WebDriverFactory;

import java.time.Duration;

public class BaseTest {

  protected StellarBurgersClient client = new StellarBurgersClient();
  protected WebDriver driver;

  @After
  public void after() {
    driver.quit();
  }

  @Before
  public void setUp() {
    driver = WebDriverFactory.getDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
  }
}
