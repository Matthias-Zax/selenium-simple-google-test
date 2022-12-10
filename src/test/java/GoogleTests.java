import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleTests {
    public static String SUT ="https://www.google.at/";

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    public void eightComponents() throws InterruptedException {
        driver.get(SUT);

        driver.findElement(By.xpath("//button/div[text()[contains(., \"Alle akzeptieren\")]]")).click();
        WebElement searchBox = driver.findElement(By.name("q"));

        searchBox.sendKeys("BFI Wien");
        Thread.sleep(2000);
        searchBox.sendKeys(Keys.RETURN);


        Thread.sleep(2000);
        WebElement message = driver.findElement(By.xpath("//a[@href = \"http://www.bfi.wien/\"]"));
        String searchHits = message.getText();
        assertTrue(searchHits.contains("BFI Wien"), "Link beinhaltet den Text - BFI Wien");
        Thread.sleep(2000);

        driver.quit();
    }
}
