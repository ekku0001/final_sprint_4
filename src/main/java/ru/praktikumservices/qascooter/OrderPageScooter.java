package ru.praktikumservices.qascooter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPageScooter {
    private WebDriver driver;

    /*
    Поля для первого шага формы заказа
    */
    //заголовок формы заказа
    private final By FIRST_ORDER_HEADER = By.xpath("//*[text() = 'Для кого самокат']");
    //имя
    private final By FIRST_NAME = By.xpath(".//input[@placeholder ='* Имя']");
    //фамилия
    private final By LAST_NAME = By.xpath(".//input[@placeholder ='* Фамилия']");
    //адрес куда привезти
    private final By ADDRESS = By.xpath(".//input[@placeholder ='* Адрес: куда привезти заказ']");
    //станция метро
    private final By METROSTATION = By.xpath(".//input[@placeholder ='* Станция метро']");
    //телефон
    private final By PHONENUMBER = By.xpath(".//input[@placeholder ='* Телефон: на него позвонит курьер']");
    //кнопка далее
    private final By NEXT_BUTTON = By.xpath(".//div[@class ='Order_NextButton__1_rCA']/button");

    /*
    Поля для второго шага формы заказа
     */
    //заголовок формы заказа
    private final By SECOND_ORDER_HEADER = By.xpath("//*[text() = 'Про аренду']");

    //когда привезти самокат
    private final By DATE_START_RENT = By.xpath(".//input[@placeholder ='* Когда привезти самокат']");

    //срок аренды
    private final By RENT_PERIOD = By.xpath("//*[text() = '* Срок аренды']");

    //комментарий для курьера
    private final By COMMENTS = By.xpath("//input[@placeholder ='Комментарий для курьера']");

    //кнопка заказа
    private final By ORDER_BUTTON = By.xpath("//div[@class ='Order_Buttons__1xGrp']/button[text()='Заказать']");
    //кнопка подтверждения
    private final By CONFIRM_BUTTON = By.xpath("//div[@class ='Order_Buttons__1xGrp']/button[text()='Да']");

    private final By CONFIRMED = By.xpath("//*[text()='Заказ оформлен']");

    public OrderPageScooter(WebDriver driver) {
        this.driver = driver;
    }
    /*
    Методы первого шага формы заказа
     */
    public void clickNextButton(){
        driver.findElement(NEXT_BUTTON).click();
    }
    private void setFirstName(String firstName){
        driver.findElement(FIRST_NAME).clear();
        driver.findElement(FIRST_NAME).sendKeys(firstName);
    }

    private void setLastName(String lastName){
        driver.findElement(LAST_NAME).clear();
        driver.findElement(LAST_NAME).sendKeys(lastName);
    }
    private void setAddress(String address){
        driver.findElement(ADDRESS).clear();
        driver.findElement(ADDRESS).sendKeys(address);
    }

    private void setMetro(String metroStation){
        driver.findElement(METROSTATION).click();
        //wait until list is loaded
        By metroLocation = By.xpath("//*[text()='" + metroStation + "']");
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(metroLocation));
        driver.findElement(metroLocation).click();
    }
    private void setPhoneNumer(String phoneNumer){
        driver.findElement(PHONENUMBER).clear();
        driver.findElement(PHONENUMBER).sendKeys(phoneNumer);
    }
    public void fillFormFirstStep(String firstName, String lastName,
                                  String address, String metroStation, String phoneNumber){
        //wait until order form is loaded
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(FIRST_ORDER_HEADER));

        if (driver.findElement(FIRST_ORDER_HEADER).isDisplayed()){
            this.setFirstName(firstName);
            this.setLastName(lastName);
            this.setAddress(address);
            this.setMetro(metroStation);
            this.setPhoneNumer(phoneNumber);
        }
        this.clickNextButton();
    }

    /*
    Методы второго шага формы заказа
     */
    public void clickOrderButton(){
        driver.findElement(ORDER_BUTTON).click();
    }
    private void setDateStartRent(String date){
        driver.findElement(DATE_START_RENT).clear();
        driver.findElement(DATE_START_RENT).sendKeys(date);
        driver.findElement(DATE_START_RENT).sendKeys(Keys.ENTER);
    }
    private void setRentPeriod(String period){
        driver.findElement(RENT_PERIOD).click();
        By periodLocation = By.xpath("//*[text()='"+period+"']");
        driver.findElement(periodLocation).click();
    }

    private void setScooterCollor(String color){
        By colorLocation = By.xpath(".//label[text()='"+color+"']/input");
        driver.findElement(colorLocation).click();
    }

    private void setComments(String comments){
        driver.findElement(COMMENTS).clear();
        driver.findElement(COMMENTS).sendKeys(comments);
    }

    public void fillFormSecondStep(String date, String period, String collor, String comments){
        //check second step order form is loaded
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(SECOND_ORDER_HEADER));
        if (driver.findElement(SECOND_ORDER_HEADER).getText().equals("Про аренду")){
            this.setDateStartRent(date);
            this.setRentPeriod(period);
            this.setScooterCollor(collor);
            this.setComments(comments);
        }
        this.clickOrderButton();
    }

    public boolean confirmOrderAndCheck(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(CONFIRM_BUTTON));
        driver.findElement(CONFIRM_BUTTON).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(CONFIRMED));

        return driver.findElement(CONFIRMED).isDisplayed();
    }
}
