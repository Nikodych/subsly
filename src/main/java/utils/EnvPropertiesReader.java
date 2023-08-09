package utils;

import lombok.experimental.UtilityClass;

import java.util.ResourceBundle;

import static java.lang.Boolean.parseBoolean;
import static java.lang.System.getenv;
import static java.util.ResourceBundle.getBundle;

@UtilityClass
public class EnvPropertiesReader {

    private static final ResourceBundle bundle;

    static {
        // this reads from main/resources/
        bundle = getBundle("env");
    }

    public static String getBaseURL() {
        return get("BASE_URL");
    }

    public static String getBrowser() {
        return get("BROWSER_NAME");
    }

    public static String getBrowserSize() {
        return get("BROWSER_SIZE");
    }

    public static boolean isHeadless() {
        return parseBoolean(get("IS_HEADLESS"));
    }

    public static String getValidNickname() {
        return get("VALID_NICKNAME");
    }

    public static String getValidLink() {
        return get("VALID_LINK");
    }

    public static String getValidEmail() {
        return get("VALID_EMAIL");
    }

    public static String getInvalidNickname() {
        return get("INVALID_NICKNAME");
    }

    public static String getInvalidLink() {
        return get("INVALID_LINK");
    }

    public static String getInvalidEmail() {
        return get("INVALID_EMAIL");
    }

    private static String get(String property) {
        var propertyValue = !bundle.containsKey(property)
                ? getenv(property)
                : bundle.getString(property).isEmpty()
                ? getenv(property)
                : bundle.getString(property);

        if (propertyValue == null || property.isEmpty()) {
            throw new AssertionError("Either system property or configuration file should be set for " + property);
        }

        return propertyValue;
    }
}
