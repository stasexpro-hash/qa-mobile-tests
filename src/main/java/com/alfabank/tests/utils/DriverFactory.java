package com.alfabank.tests.utils;

import com.alfabank.tests.config.TestConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

/**
 * Фабрика для создания и управления драйвером
 */
public class DriverFactory {
    private static AppiumDriver driver;

    // Инициализация драйвера
    public static void initDriver() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();

        // Основные настройки
        caps.setCapability("platformName", TestConfig.PLATFORM);
        caps.setCapability("automationName", TestConfig.AUTOMATION);
        caps.setCapability("deviceName", TestConfig.DEVICE_NAME);

        // Настройки приложения
        caps.setCapability("app", TestConfig.APP_PATH);

        // Дополнительные настройки
        caps.setCapability("autoGrantPermissions", true); 
        caps.setCapability("noReset", false);

        // Создаю драйвер
        driver = new AndroidDriver(new URL(TestConfig.APPIUM_SERVER), caps);

        // Неявное ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestConfig.IMPLICIT_WAIT));
    }

    // Получить драйвер
    public static AppiumDriver getDriver() {
        return driver;
    }

    // Завершить работу драйвера
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
