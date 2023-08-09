import io.qameta.allure.Description;
import org.testng.annotations.Test;
import utils.runners.BaseTestRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static repo.PackagesRepo.*;
import static utils.EnvPropertiesReader.*;

public class CheckoutTest extends BaseTestRunner {

    @Test
    @Description("Verification of adding special offers")
    public void verifyAddingSpecialOffers() {
        var amountOfFollowers = 30;
        var newPackageName = getPackageNameWithPrice(amountOfFollowers);

        var paymentPage = checkoutPage
                .startChangingPackage()
                .selectPackage(newPackageName)
                .submitPackageSelection()
                .setUsername(getValidNickname())
                .setEmail(getValidEmail())
                .clickContinue();

        assertThat(paymentPage.getPackageMainTitle())
                .withFailMessage("Package name should match with main title")
                .containsIgnoringCase(getPackageName(amountOfFollowers));

        var specialOffers = paymentPage.getSpecialOffers();
        var numberOfOffers = specialOffers
                .keySet()
                .size();

        for (var i = 0; i < numberOfOffers; i++) {
            paymentPage.selectSpecialOffer(i);
        }

        var packagePrice = getAmountOfFollowersWithPrices().get(amountOfFollowers);
        var offersTotalPrice = specialOffers
                .values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        var expectedTotalPrice = packagePrice + offersTotalPrice;

        assertThat(paymentPage.getTotalPrice())
                .withFailMessage("Total price should match with sum of prices")
                .isEqualTo(expectedTotalPrice);
    }

    @Test
    @Description("Verification of username input validation")
    public void verifyUsernameInputValidation() {
        checkoutPage
                .setUsername(getInvalidNickname())
                .setEmail(getValidEmail())
                .clickContinue();

        assertThat(checkoutPage.isUsernameInputValidationDisplayed())
                .withFailMessage("Username input validation should be triggered")
                .isTrue();
    }
}
