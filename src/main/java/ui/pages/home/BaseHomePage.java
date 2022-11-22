package ui.pages.home;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public abstract class BaseHomePage<T> {

    public abstract T isLoaded();

    protected T verifyInputFieldHighlighted(By fieldLocator) {
        $(fieldLocator).shouldHave(Condition.attributeMatching("class", "(.|\\n)*(error)(.|\\n)*"));
        return (T) this;
    }

    protected By byTestId(String value) {
        return By.cssSelector(String.format("[data-testid='%s']", value));
    }

    protected By byParentTestId(String value) {
        return By.xpath(String.format("//*[@data-testid='%s']/parent::*", value));
    }

}
