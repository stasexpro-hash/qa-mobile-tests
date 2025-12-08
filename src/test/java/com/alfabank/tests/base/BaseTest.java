package com.alfabank.tests.base;

import com.alfabank.tests.pages.LoginPage;
import com.alfabank.tests.utils.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected LoginPage loginPage;

    @BeforeEach
    public void setUp() throws Exception {
        DriverFactory.initDriver();
        loginPage = new LoginPage(DriverFactory.getDriver());
    }

    @AfterEach
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}