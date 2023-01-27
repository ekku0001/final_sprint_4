import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import ru.praktikumservices.qascooter.MainPageScooter;
import ru.praktikumservices.qascooter.OrderPageScooter;


public class TestOrderPageFunc {
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
    public void testOrderFormClickUpButton() {
        // создать объект класса страницы авторизации
        MainPageScooter objScooterPage = new MainPageScooter(driver);
        objScooterPage.clickUpOrderButton();
        OrderPageScooter objOrderPageFirst = new OrderPageScooter(driver);
        objOrderPageFirst.fillFormFirstStep("Василий", "Иванов",
                "Невский проспект 106", "Красносельская", "89112345665");

        objOrderPageFirst.fillFormSecondStep("31.01.2023","пятеро суток","чёрный жемчуг","тестовый комментарий");

        Assert.assertTrue(objOrderPageFirst.confirmOrderAndCheck());
    }

    @Test
    public void testOrderFormClickMiddleButton() {
        // создать объект класса страницы авторизации
        MainPageScooter objScooterPage = new MainPageScooter(driver);
        objScooterPage.clickMiddleOrderButton();
        OrderPageScooter objOrderPageFirst = new OrderPageScooter(driver);
        objOrderPageFirst.fillFormFirstStep("Светлана", "Солнцева",
                "Московский проспект 6", "Пионерская", "89213456523");

        objOrderPageFirst.fillFormSecondStep("12.04.2023","сутки","серая безысходность","");

        Assert.assertTrue(objOrderPageFirst.confirmOrderAndCheck());
    }

    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}
