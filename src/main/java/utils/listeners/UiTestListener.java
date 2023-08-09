package utils.listeners;

import lombok.SneakyThrows;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import utils.runners.BaseTestRunner;

import static com.codeborne.selenide.Selenide.dismiss;
import static utils.AllureLogger.attachScreenshot;
import static utils.AllureLogger.log;

/**
 * Listener class for overriding TestNG events behavior
 */
public class UiTestListener extends TestListenerAdapter {

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        var currentClass = result.getInstance();

        try {
            // if is a web UI test, take a screenshot on fail
            if (currentClass instanceof BaseTestRunner) {
                attachScreenshot();
            }
        } catch (UnhandledAlertException e) {
            log("UnhandledAlertException when making screenshot: " + e);
            dismiss();
        }
    }
}
