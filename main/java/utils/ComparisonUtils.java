package utils;

import org.apache.commons.lang3.StringUtils;

public class ComparisonUtils {

    public static void comparePrices(String amazonPrice, String flipkartPrice) {
        double amazonPriceValue = convertPriceToDouble(amazonPrice);
        double flipkartPriceValue = convertPriceToDouble(flipkartPrice);

        System.out.println("Amazon Price: " + amazonPriceValue);
        System.out.println("Flipkart Price: " + flipkartPriceValue);

        if (amazonPriceValue < flipkartPriceValue) {
            System.out.println("Amazon offers the lowest price for the product.");
        } else if (flipkartPriceValue < amazonPriceValue) {
            System.out.println("Flipkart offers the lowest price for the product.");
        } else {
            System.out.println("Both platforms offer the same price for the product.");
        }
    }

    private static double convertPriceToDouble(String price) {
        // Remove any non-numeric characters (e.g., currency symbols, commas)
        String numericPrice = price.replaceAll("[^\\d.]", "");
        return StringUtils.isNotEmpty(numericPrice) ? Double.parseDouble(numericPrice) : 0.0;
    }
}
