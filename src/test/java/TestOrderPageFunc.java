import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import ru.praktikumservices.qascooter.MainPageScooter;
import ru.praktikumservices.qascooter.OrderPageScooter;

@RunWith(Parameterized.class)
public class TestOrderPageFunc {
    private WebDriver driver;
    private final String TESTED_URL = "https://qa-scooter.praktikum-services.ru/";

    private final String button;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String period;
    private final String collor;
    private final String comment;

    public TestOrderPageFunc(String button,
                             String firstName, String lastName, String address, String metro, String phone,
                             String date, String period, String collor, String comment) {
        this.button = button;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.collor = collor;
        this.comment = comment;
    }

    @Before
    public void setUp() {
        // Открыть браузер
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.get(TESTED_URL);
    }

    @Parameterized.Parameters
    public static Object[] getInputData() {
        return new Object[][] {
                { "UP",
                        "Василий", "Иванов", "Невский проспект 106", "Красносельская", "89112345665",
                        "31.01.2023", "пятеро суток", "чёрный жемчуг", "тестовый комментарий"},
                { "MIDDLE",
                        "Светлана", "Солнцева", "Московский проспект 6", "Пионерская", "89213456523",
                        "12.04.2023","сутки","серая безысходность",""},
        };
    }
    @Test
    public void testOrderForm() {
        // создать объект класса страницы авторизации
        MainPageScooter objScooterPage = new MainPageScooter(driver);
        objScooterPage.clickOrderButton(button);
        OrderPageScooter objOrderPageFirst = new OrderPageScooter(driver);
        objOrderPageFirst.fillFormFirstStep(firstName, lastName, address, metro, phone);

        objOrderPageFirst.fillFormSecondStep(date, period, collor, comment);

        Assert.assertTrue(objOrderPageFirst.confirmOrderAndCheck());
    }

    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}
