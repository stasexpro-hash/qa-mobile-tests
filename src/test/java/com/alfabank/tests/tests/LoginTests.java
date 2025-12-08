package com.alfabank.tests.tests;

import com.alfabank.tests.base.BaseTest;
import com.alfabank.tests.config.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTests extends BaseTest {

    // Успешный вход
    @Test
    @DisplayName("Тест 1: Успешная авторизация")
    public void testSuccessfulLogin() {
        loginPage.typeLogin(TestConfig.VALID_LOGIN);
        loginPage.typePassword(TestConfig.VALID_PASSWORD);
        loginPage.clickLoginButton();

        assertTrue(loginPage.isLoginSuccessful(), "Должен быть успешный вход");
    }

    // Максимальная длины логина 50 символов
    @Test
    @DisplayName("Тест 2: Ограничение длины логина")
    public void testLoginMaxLength() {
        String longLogin = "a".repeat(60);

        loginPage.typeLogin(longLogin);

        int actualLength = loginPage.getLoginLength();
        assertTrue(actualLength <= 50, "Логин должен быть максимум 50 символов");
    }

    // Недопустимые символы (параметризованный)
    @ParameterizedTest
    @DisplayName("Тест 3: Недопустимые символы в логине")
    @ValueSource(strings = {"Тест123", "user@mail.ru", "test#user", "логин", "user$123"})
    public void testInvalidCharacters(String invalidLogin) {
        loginPage.typeLogin(invalidLogin);
        loginPage.typePassword(TestConfig.VALID_PASSWORD);
        loginPage.clickLoginButton();

        assertTrue(loginPage.isErrorDisplayed(),
                "Должна быть ошибка при вводе: " + invalidLogin);
    }

    // Глазик
    @Test
    @DisplayName("Тест 4: Кнопка показа пароля")
    public void testShowPasswordButton() {
        loginPage.typePassword("TestPassword123");

        // Изначально пароль скрыт
        String before = loginPage.getPasswordAttribute();
        assertEquals("true", before, "Пароль должен быть скрыт");

        // Нажимаем кнопку - пароль показывается
        loginPage.clickShowPasswordButton();
        String after = loginPage.getPasswordAttribute();
        assertEquals("false", after, "Пароль должен быть виден");
    }

    // Прячу клавиатуру
    @Test
    @DisplayName("Тест 5: Кнопка доступна после скрытия клавиатуры")
    public void testLoginButtonClickable() {
        loginPage.typeLogin(TestConfig.VALID_LOGIN);
        loginPage.typePassword(TestConfig.VALID_PASSWORD);
        loginPage.clickLoginButton();

        boolean result = loginPage.isLoginSuccessful() || loginPage.isErrorDisplayed();
        assertTrue(result, "Кнопка должна быть доступна для клика");
    }
}