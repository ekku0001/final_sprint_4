import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import ru.praktikumservices.qascooter.MainPageScooter;


public class TestMainPageFunc {
    private WebDriver driver;
    private final String TESTED_URL = "https://qa-scooter.praktikum-services.ru/";

    @Before
    public void setUp() {
        // Открыть браузер
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.get(TESTED_URL);
    }

    @Test
    public void testQuestions() {
        // создать объект класса страницы
        MainPageScooter objScooterPage = new MainPageScooter(driver);
        objScooterPage.loadQuestionPart();
       for (int i = 0; i < 8; i++) {
            objScooterPage.clickQuestion(i);
            Assert.assertTrue(objScooterPage.checkAnswer(i));
       }
    }

    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}