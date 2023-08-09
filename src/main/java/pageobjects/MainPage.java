package pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Fail.fail;
import static utils.WebElementUtil.isDisplayed;

public abstract class MainPage<T extends MainPage<T>> {

    public String getPackageMainTitle() {
        return $(".main-title").getText();
    }

    @Step("[x] Waited till progress spinner disappears")
    public T waitTillProgressSpinnerDisappears() {
        var spinner = $x("//*[contains(@class,'loading')]");

        isDisplayed(spinner, ofSeconds(10));

        try {
            spinner.should(disappear, ofSeconds(15));
        } catch (AssertionError error) {
            fail(format("Failed to load page in %s seconds", 15), error);
        }

        return (T) this;
    }
}
