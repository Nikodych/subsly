package utils;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static java.lang.Double.parseDouble;
import static org.apache.commons.lang3.RegExUtils.removeAll;

@UtilityClass
public class WebElementUtil {

    public static double parseDoubleValue(String textValue) {
        var fieldDigitsString = removeAll(textValue, "[^0-9?!\\\\.]");

        return fieldDigitsString.isEmpty() ? 0 : parseDouble(fieldDigitsString);
    }

    @Step("[x] Waited for {element} to be displayed")
    public static boolean isDisplayed(SelenideElement element, Duration timeout) {
        try {
            return element
                    .shouldBe(visible, timeout)
                    .isDisplayed();
        } catch (AssertionError error) {
            return false;
        }
    }
}
