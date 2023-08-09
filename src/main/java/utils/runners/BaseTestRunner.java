package utils.runners;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pageobjects.CheckoutPage;
import utils.listeners.UiTestListener;

import static com.codeborne.selenide.Selenide.open;
import static java.time.Duration.ofSeconds;
import static utils.EnvPropertiesReader.*;

@Listeners(UiTestListener.class)
public abstract class BaseTestRunner {

    protected CheckoutPage checkoutPage;

    @BeforeClass(alwaysRun = true, description = "Setup browser")
    public static void setupBrowser() {
        Configuration.browserSize = getBrowserSize();
        Configuration.timeout = ofSeconds(180).toMillis();
        Configuration.pageLoadTimeout = ofSeconds(300).toMillis();
        Configuration.browser = getBrowser();
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
        Configuration.headless = isHeadless();
        Configuration.browserCapabilities = getChromeOptions();

        open("about:blank");
    }

    @BeforeMethod(alwaysRun = true, description = "Opened checkout page")
    protected void openCheckoutPage() {
        open(getBaseURL());

        checkoutPage = new CheckoutPage();
    }

    @AfterMethod(description = "Closing web driver")
    protected void closeWebDriver() {
        WebDriverRunner.closeWebDriver();
    }

    private static ChromeOptions getChromeOptions() {
        return new ChromeOptions().addArguments("--no-sandbox",
                "--disable-site-isolation-trials",
                "--ignore-certificate-errors",
                "--disable-popup-blocking",
                "--disable-notifications",
                "--disable-browser-side-navigation",
                "--disable-gpu",
                "--dns-prefetch-disable",
                "--disable-impl-side-painting",
                "--disable-infobars",
                "--disable-dev-shm-usage");
    }
}
