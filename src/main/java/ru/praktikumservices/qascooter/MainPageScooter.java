package ru.praktikumservices.qascooter;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPageScooter {

    private WebDriver driver;

    //кнопка куки
    private final By APP_COOKIE = By.className("App_CookieButton__3cvqF");

    //четвертая часть главной странцы
    private final By HOME_FOUR_PART = By.className("Home_FourPart__1uthg");

    //кнопка заказать вверху страницы
    private final By ORDER_BUTTON_UP = By.className("Button_Button__ra12g");
    //кнопка заказать внизу страницы
    private final By ORDER_BUTTON_MIDDLE = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    // вопросы о важном
    private final By QUESTIONS = By.className("accordion__button");
    //ответы на вопросы
    private final By ANSWERS = By.xpath("//div[@class ='accordion__panel']/p");

    public MainPageScooter(WebDriver driver) {
        this.driver = driver;
        //accept all Cookie
        driver.findElement(APP_COOKIE).click();
    }

    public void loadQuestionPart(){
        WebElement element = driver.findElement(HOME_FOUR_PART);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    public void clickOrderButton(String button){
        if (button.equals("UP")){
            driver.findElement(ORDER_BUTTON_UP).click();
        }else if (button.equals("MIDDLE")){
            WebElement element = driver.findElement(ORDER_BUTTON_MIDDLE);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
            element.click();
        }

    }

    public void clickQuestion(int id){
        List<WebElement> questions = driver.findElements(QUESTIONS);
        questions.get(id).click();
    }
    public String getAnswer(int id){
        List<WebElement> answers = driver.findElements(ANSWERS);

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(answers.get(id)));

        //answer contains text
        return answers.get(id).getText();
    }
}

