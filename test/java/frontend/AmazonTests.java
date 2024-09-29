package frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.WebDriverUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AmazonTests {
    private WebDriver driver;
    private String productName;
    private String productPrice;
    private String productLink;

    @BeforeClass
    public void setUp() {
        driver = WebDriverUtils.getDriver();
    }

    @Test
    public void searchProduct() {
        driver.get("https://www.amazon.in/");
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("nothing phone 2a");
        searchBox.submit();

        // Capture product details with WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".s-main-slot .s-result-item")));


        productName = driver.findElement(By.cssSelector("h2 a span")).getText();
        productPrice = driver.findElement(By.cssSelector(".a-price-whole")).getText();
        productLink = driver.findElement(By.cssSelector("h2 a")).getAttribute("href");

        System.out.println("Amazon - Product Name: " + productName);
        System.out.println("Amazon - Product Price: " + productPrice);
        System.out.println("Amazon - Product Link: " + productLink);

        // Navigate to Add to Cart

        driver.manage().window().maximize();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div[1]/div[1]/div[1]/div/span[1]/div[1]/div[2]/div/div/span/div/div/div/div[2]/div/div/div[3]/div[1]/div/div[4]/div/div/div[1]/span/div/span/span/button")));
        WebElement addToCartButton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[1]/div/span[1]/div[1]/div[2]/div/div/span/div/div/div/div[2]/div/div/div[3]/div[1]/div/div[4]/div/div/div[1]/span/div/span/span/button"));
        addToCartButton.click();

        // Navigate to Buy Now
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body")));
        WebElement buyNowButton = driver.findElement(By.xpath("/html/body"));
        buyNowButton.click();

        // Proceed to payment gateway screen
        // Note: Stop before actual payment
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String getProductPrice() {
        return productPrice;
    }
}
