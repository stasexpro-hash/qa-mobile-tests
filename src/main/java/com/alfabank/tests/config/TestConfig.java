package com.alfabank.tests.config;

/**
 * Конфигурация для тестов
 * Здесь хранятся все настройки и константы
 */
public class TestConfig {
    // Настройки Appium сервера
    public static final String APPIUM_SERVER = "http://127.0.0.1:4723";

    // Настройки платформы
    public static final String PLATFORM = "Android";
    public static final String AUTOMATION = "UiAutomator2";
    public static final String DEVICE_NAME = "Android Emulator";

    // Путь к APK файлу
    public static final String APP_PATH = "C:/Users/stase/IdeaProjects/qa-mobile/app/build/outputs/apk/debug/app-debug.apk";

    // Валидные данные для входа по требованиям
    public static final String VALID_LOGIN = "Login";
    public static final String VALID_PASSWORD = "Password";

    // Ожидание
    public static final int IMPLICIT_WAIT = 10;
}