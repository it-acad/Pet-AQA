package ui.pages.home;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class HomePage extends BaseHomePage<HomePage> {

    private final By logo = By.id("logo");
    private final By currencyButton = By.xpath("//*[@id='form-currency']//a");
    private final By searchInput = By.xpath("//*[@name='search']");
    private final By cartExpanded = By.id("//span[@id='cart-total']/..");

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

    @Step("Find and get some item")
    public HomePage findAndTakeSomeItem(String value, String count) {
        $("//*[@id='content']//a[text()='" + value + "']/../following-sibling::td/following-sibling::td//input").sendKeys(count);
        return this;
    }

    @Step("Click Update button")
    public HomePage clickUpdateButtonByText(String text) {
        $(By.xpath("//*[@id='content']//a[text()='" + text + "']/../following-sibling::td/following-sibling::td//button[@data-original-title='Update']")).click();
        return this;
    }

    @Step("Choose currency")
    public HomePage clickCurrencyList(String currency) {
        $(By.xpath("//*[@id='form-currency']//a[@href='" + currency + "']")).click();
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

    @Step("Update Cart")
    public HomePage cardExpend() {
        $(cartExpanded).click();
        return this;
    }
}
