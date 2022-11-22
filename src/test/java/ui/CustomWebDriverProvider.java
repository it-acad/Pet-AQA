package ui;

import com.codeborne.selenide.WebDriverProvider;
import io.qameta.allure.Allure;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;

public class CustomWebDriverProvider implements WebDriverProvider {

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
            ChromeOptions options = new ChromeOptions();
            options.merge(capabilities);

            return new ChromeDriver(options);
    }
}
