package com.alfabank.tests.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    // Локаторы элементов (XPath)
    private By loginField = By.xpath("//android.widget.EditText[@resource-id='com.alfabank.qapp:id/etUsername']");
    private By passwordField = By.xpath("//android.widget.EditText[@resource-id='com.alfabank.qapp:id/etPassword']");
    private By loginButton = By.xpath("//android.widget.Button[@resource-id='com.alfabank.qapp:id/btnConfirm']");


    // Кнопка показа/скрытия пароля
    private By showPasswordButton = By.xpath("//android.widget.ImageButton[@resource-id='com.alfabank.qapp:id/text_input_end_icon']");

    // Сообщения
    private By errorMessage = By.id("com.alfabank.qapp:id/tvError");
    private By successMessage = By.xpath("//*[contains(@text,'Вход в Alfa-Test выполнен')]");

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    // Ввести логин
    public void typeLogin(String login) {
        enterText(loginField, login);
    }

    // Ввести пароль
    public void typePassword(String password) {
        enterText(passwordField, password);
    }

    // Нажать кнопку "Вход"
    public void clickLoginButton() {
        hideKeyboard();
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        clickElement(loginButton);
    }

    // Нажать на "глазик"
    public void clickShowPasswordButton() {
        clickElement(showPasswordButton);
    }

    // Получить атрибут password поля
    public String getPasswordAttribute() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(passwordField));
            return findElement(passwordField).getAttribute("password");
        } catch (NoSuchElementException | TimeoutException e) {
            return "unknown";
        }
    }

    // Длина текста в поле логина
    public int getLoginLength() {
        String text = findElement(loginField).getText();
        return text != null ? text.length() : 0;
    }

    // Проверка успешного входа
    public boolean isLoginSuccessful() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(successMessage));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Проверка отображения ошибки
    public boolean isErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(errorMessage));
            String error = getText(errorMessage);
            return error != null && !error.isEmpty();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }
}