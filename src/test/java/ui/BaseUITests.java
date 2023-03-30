package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;
import ui.pages.home.HomePage;

import java.util.logging.Level;

@Execution(ExecutionMode.CONCURRENT)
public class BaseUITests{

    static {
        Configuration.driverManagerEnabled = true;
        Configuration.pollingInterval = 400;
        Configuration.timeout = 6000;
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("AllureListener", new AllureSelenide().enableLogs(LogType.BROWSER, Level.ALL));
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--disable-dev-shm-usage");
        Configuration.browser = "ui.CustomWebDriverProvider";
        Configuration.headless = false;
    }

    @BeforeEach
    public void before() {
        ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
        System.setProperty("selenide.browser", "ui.CustomWebDriverProvider");
    }

    @Step
    public HomePage openHomePage() {
        Selenide.open("https://dev.webmakers.com.ua/opencart/3.0.2.0/");
        return new HomePage();
    }

    @AfterEach
    public void tearDownBrowser() {
        WebDriverRunner.closeWebDriver();
        String testId = Allure.getLifecycle().getCurrentTestCase().get();
        BrowserWebDriverContainer container = ContainerHolder.getContainers(testId);
        if (container != null) {
            container.stop();
        }
    }
}
