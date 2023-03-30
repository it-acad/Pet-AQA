package ui.pages.home;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class HomePage extends BaseHomePage<HomePage> {

    private final By logo = By.id("logo");
    private final By currencyButton = By.xpath("//*[@id='form-currency']//span");
    private final By searchInput = By.xpath("//*[@name='search']");
    private final By cartExpanded = By.xpath("//span[@id='cart-total']/..");
    private final By buyButton = By.cssSelector("button#button-cart");
    private final By quantity = By.cssSelector("input[name='quantity']");
    private final By productThumb = By.cssSelector(".product-thumb");

    @Override
    @Step("Verify Home page is loaded")
    public HomePage isLoaded() {
        $(logo).shouldBe(Condition.visible);
        return this;
    }

    @Step("Click form currency button")
    public HomePage clickFormCurrency() {
        $(currencyButton).click();
        return this;
    }

    @Step("Take {count} item")
    public HomePage takeSomeItem(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Count should be a positive integer.");
        }
        $(quantity).setValue(String.valueOf(count));
        return this;
    }

    @Step("Select item by {name}")
    public HomePage selectItem(String name) {
        $(By.xpath("//*[@id='content']//a[text()='" + name + "']")).click();
        return this;
    }

    @Step("Click Update button")
    public HomePage clickUpdateButton() {
        $(buyButton).click();
        return this;
    }

    @Step("Choose currency")
    public HomePage clickCurrencyList(String currency) {
        $(By.xpath("//*[@id='form-currency']//button[@name='" + currency + "']")).click();
        return this;
    }

    @Step("Click search button")
    public HomePage clickSearch() {
        $(searchInput).click();
        $(searchInput).clear();
        return this;
    }

    @Step("Search value")
    public HomePage searchText(String mac) {
        $(searchInput).sendKeys(mac + Keys.ENTER);
        return this;
    }

    @Step("Scroll browser to item")
    public HomePage scrollToItem() {
        WebElement firstProductThumb = getWebDriver().findElement(productThumb);
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView();", firstProductThumb);
        return this;
    }

    @Step("Update Cart")
    public HomePage cardExpend() {
        $(cartExpanded).click();
        return this;
    }
}
