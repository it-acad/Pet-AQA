package ui.home;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.BaseUITests;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.webdriver;
import static org.openqa.selenium.By.xpath;

@DisplayName("Home Page")
public class HomePageTests extends BaseUITests {

    @Test
    @DisplayName("Find some item by text")
    public void findByText() {
        openHomePage().isLoaded()
                .clickFormCurrency()
                .clickCurrencyList("USD")
                .clickSearch()
                .searchText("mac");

        Predicate<WebElement> predicate = webElement -> webElement.findElement(By.xpath(".//*[@class='caption']/h4/a")).getText().trim().equals("mac")
                && webElement.findElement(By.xpath(".//*[@class='caption']//*[@class='price']")).getText().split("\n")[0].trim().contains("$602.00");
        List<WebElement> list = webdriver().driver().getWebDriver().findElements(By.xpath("//*[@class='product-thumb']"))
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());

        Assertions.assertEquals(list.size(), 1);
        Assertions.assertTrue(webdriver().driver().getWebDriver().findElement(xpath("//a[text() = '" + "MacBook" + "']/ancestor::div[contains(@class, 'caption')]")).getText().contains("MacBook"));

        openHomePage().isLoaded()
                .clickFormCurrency()
                .clickCurrencyList("MacBook");

        String price = webdriver().driver().getWebDriver().findElement(xpath("//a[text() = '" + "MacBook" + "']/ancestor::div[contains(@class, 'caption')]//p[contains(@class,'price')]")).getText().split("\n")[0];
        Assertions.assertEquals(price, "$602.00");
    }

    @Test
    @DisplayName("Add few item to cart")
    public void addQuantityInCart() {
        openHomePage().isLoaded()
                .clickSearch()
                .searchText("mac")
                .findAndTakeSomeItem("mac", "3")
                .clickUpdateButtonByText("mac")
                .cardExpend();

        Assertions.assertEquals(webdriver().driver().getWebDriver().findElement(xpath("//span[@id='cart-total']/..")).getText().split("\\s")[0], "4");
    }
}
