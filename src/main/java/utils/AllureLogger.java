package utils;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;

import static com.codeborne.selenide.Selenide.screenshot;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;
import static org.openqa.selenium.OutputType.BYTES;

@UtilityClass
public class AllureLogger {

    // Allure logging purposes only
    @Step("{logMessage}")
    public static String log(String logMessage) {
        return logMessage;
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScreenshot() {
        if (hasWebDriverStarted()) {
            return screenshot(BYTES);
        }

        return null;
    }
}
