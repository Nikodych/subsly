package pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.name;

public class CheckoutPage extends MainPage<CheckoutPage> {

    @Step("Checkout page: Set username: {username}")
    public CheckoutPage setUsername(String username) {
        $(name("page"))
                .shouldBe(visible.because("Username input should be visible"))
                .setValue(username);

        return this;
    }

    @Step("Checkout page: Set email: {email}")
    public CheckoutPage setEmail(String email) {
        $(name("email"))
                .shouldBe(visible.because("Username input should be visible"))
                .setValue(email);

        return this;
    }

    @Step("Checkout page: Started changing package")
    public CheckoutPage startChangingPackage() {
        $("div[class*='inner']:not([style*='display: none']) div[class*='change-package']>a")
                .shouldBe(visible.because("Change package button should be visible"))
                .click();

        return this;
    }

    @Step("Checkout page: Selected package: {packageName}")
    public CheckoutPage selectPackage(String packageName) {
        $("div[class*='change-package']>div[class*='tag-select'] select").selectOptionContainingText(packageName);

        return this;
    }

    @Step("Checkout page: Submitted package selection")
    public CheckoutPage submitPackageSelection() {
        $("div[class*='change-package']>div[class*='button'] a").click();

        return this.waitTillProgressSpinnerDisappears();
    }

    @Step("Checkout page: Clicked continue")
    public PaymentPage clickContinue() {
        $("div[class*='form']:not([class*='change']) div[class*='button']")
                .shouldBe(visible.because("Continue button should be visible"))
                .click();

        return new PaymentPage().waitTillProgressSpinnerDisappears();
    }

    public boolean isUsernameInputValidationDisplayed() {
        var isInputErrorDisplayed = $("div[class*='error']:has(input[name='page'])").isDisplayed();
        var isMessageWithImageDisplayed = $("div[class*='red'][class*='message']:has(img)").isDisplayed();

        return isInputErrorDisplayed && isMessageWithImageDisplayed;
    }
}
