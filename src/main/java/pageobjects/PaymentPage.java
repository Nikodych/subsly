package pageobjects;

import io.qameta.allure.Step;
import utils.WebElementUtil;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;
import static utils.WebElementUtil.parseDoubleValue;

public class PaymentPage extends MainPage<PaymentPage> {

    private final String SPECIAL_OFFERS_ITEMS_TEMPLATE = "div[class*='special-offers'] div[class*='%s']>p";

    public Map<String, Double> getSpecialOffers() {
        var itemsDescriptionList = $$(format(SPECIAL_OFFERS_ITEMS_TEMPLATE, "item-description")).texts();
        var itemsPriceList = $$(format(SPECIAL_OFFERS_ITEMS_TEMPLATE, "item-price"))
                .texts()
                .stream()
                .map(WebElementUtil::parseDoubleValue)
                .collect(toList());

        if (itemsDescriptionList.size() == itemsPriceList.size()) {
            return range(0, itemsDescriptionList.size())
                    .boxed()
                    .collect(toMap(itemsDescriptionList::get, itemsPriceList::get));
        } else {
            throw new AssertionError("The number of descriptions and prices of the offers don't match");
        }
    }

    @Step("Payment page: Selected special offer #: [number]")
    public PaymentPage selectSpecialOffer(int number) {
        $$(format(SPECIAL_OFFERS_ITEMS_TEMPLATE, "item-description"))
                .get(number)
                .click();

        return this;
    }

    public Double getTotalPrice() {
        return parseDoubleValue($("div[class*='payment-price']>p").getText());
    }
}
