import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class UploadFile {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "c:/temp/chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "http://localhost:8080/test_upfile/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);       
        
        driver.get("http://localhost:8080/test_upfile/");
        Thread.sleep(1000);
  }

  @Test
  public void testUploadFile() throws Exception {
    driver.findElement(By.linkText("login")).click();
    driver.findElement(By.name("j_username")).sendKeys("test");
    driver.findElement(By.name("j_password")).sendKeys("test");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    driver.findElement(By.linkText("Liste de fichiers")).click();
    driver.findElement(By.linkText("Create New File")).click();
    driver.findElement(By.id("j_idt33:name")).sendKeys("Test");
    driver.findElement(By.id("j_idt33:desciption")).sendKeys("Super description de fichier");
    driver.findElement(By.id("j_idt33:file")).sendKeys("C://Users/damien.gygi/Documents/Java Enterprise Edition/UpFile/CDC_Gygi_Schaffo.pdf");
    new Select(driver.findElement(By.id("j_idt33:idtype"))).selectByVisibleText("image");
    driver.findElement(By.linkText("Save")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}