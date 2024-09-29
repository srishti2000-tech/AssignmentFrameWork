package frontend;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ComparisonUtils;

public class ProductComparisonTests {
    private AmazonTests amazonTests;
    private CromaTests cromaTests;

    @BeforeClass
    public void setUp() {
        amazonTests = new AmazonTests();
        cromaTests = new CromaTests();
        amazonTests.setUp();
        cromaTests.setUp();
    }

    @Test
    public void compareProductPrices() throws InterruptedException {
        amazonTests.searchProduct();
        cromaTests.searchProduct();

        String amazonPrice = amazonTests.getProductPrice();
        String cromaPrice = cromaTests.getProductPrice();

        ComparisonUtils.comparePrices(amazonPrice, cromaPrice);
    }

    @AfterClass
    public void tearDown() {
        amazonTests.tearDown();
        cromaTests.tearDown();
    }
}
