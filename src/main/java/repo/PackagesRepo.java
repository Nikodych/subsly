package repo;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public class PackagesRepo {

    public static Map<Integer, Double> getAmountOfFollowersWithPrices() {
        return new HashMap<>() {{
            put(5, 4.99);
            put(10, 5.49);
            put(20, 8.19);
            put(25, 8.69);
            put(30, 10.99);
            put(50, 12.99);
            put(100, 19.49);
            put(150, 22.99);
            put(200, 25.99);
            put(250, 29.99);
            put(300, 30.99);
            put(400, 39.99);
            put(500, 41.99);
            put(600, 61.99);
            put(1000, 79.99);
            put(2000, 159.99);
            put(2500, 189.99);
            put(3000, 239.99);
            put(4000, 259.99);
            put(5000, 329.99);
            put(7500, 489.99);
            put(10000, 599.99);
            put(20000, 1299.59);
            put(30000, 1799.99);
            put(40000, 2398.99);
            put(50000, 2799.89);
        }};
    }

    public static String getPackageNameWithPrice(int amount) {
        var price = getAmountOfFollowersWithPrices().get(amount);

        if (price != null) {
            return format("%d Seguidores Instagram - R$%s", amount, price);
        } else {
            throw new IllegalArgumentException(amount + " amount is not available");
        }
    }

    public static String getPackageName(int amount) {
        var isValidAmount = getAmountOfFollowersWithPrices().get(amount);

        if (isValidAmount != null) {
            return format("%d Seguidores Instagram", amount);
        } else {
            throw new IllegalArgumentException(amount + " amount is not available");
        }
    }
}
