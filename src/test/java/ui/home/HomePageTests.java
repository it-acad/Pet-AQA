package ui.home;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.BaseUITests;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Home Page")
public class HomePageTests extends BaseUITests {

    @Test
    @DisplayName("Verify exist 1 'mac' with proper price")
    public void findProperPriceInMac() {
        openHomePage().isLoaded()
                .clickFormCurrency()
                .clickCurrencyList("USD")
                .clickSearch()
                .searchText("mac")
                .scrollToItem();

        List<WebElement> prices = getWebDriver().findElements(By.xpath("//div[@class='product-thumb']//p[contains(@class, 'price')]"));
        long matchingPricesCount = prices.stream()
                .filter(price -> price.getText().split("\n")[0].trim().contains("$26.49"))
                .count();

        assertEquals(matchingPricesCount, 1);
        $(By.xpath("//a[text() = 'MacBook']/ancestor::div[contains(@class, 'caption')]"))
                .shouldHave(text("MacBook"));
    }

    @Test
    @DisplayName("Verify MacBook Pro has proper price")
    public void findPriceByText() {
        openHomePage().isLoaded()
                .clickFormCurrency()
                .clickCurrencyList("USD")
                .clickSearch()
                .searchText("MacBook Pro")
                .scrollToItem();

        SelenideElement priceElement = $(By.xpath("//a[text() = 'MacBook Pro']/ancestor::div[contains(@class, 'caption')]//p[contains(@class,'price')]"));
        String price = priceElement.getText().split("\n")[0];

        assertEquals("$2,099.21", price);
    }

    @Test
    @DisplayName("Add few item to cart")
    public void addQuantityInCart() {
        openHomePage().isLoaded()
                .clickSearch()
                .searchText("mac")
                .selectItem("MacBook Air")
                .takeSomeItem(3)
                .clickUpdateButton()
                .cardExpend();

        $(By.xpath("//span[@id='cart-total']/..")).shouldHave(text("3"));
    }
}
