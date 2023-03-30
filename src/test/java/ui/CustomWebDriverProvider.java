package ui;

import com.codeborne.selenide.WebDriverProvider;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CustomWebDriverProvider implements WebDriverProvider {

    @Override
    public WebDriver createDriver(@NotNull Capabilities capabilities) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.merge(capabilities);

            return new ChromeDriver(options);
    }
}
