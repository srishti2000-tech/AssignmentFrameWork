package frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.WebDriverUtils;

import java.util.concurrent.TimeUnit;

public class CromaTests {
    private WebDriver driver;
    private String productName;
    private String productPrice;
    private String productLink;

    @BeforeClass
    public void setUp() {
        driver = WebDriverUtils.getDriver();
    }

    @Test
    public void searchProduct() throws InterruptedException {
        driver.get("https://www.croma.com/");

        WebDriverWait wait = new WebDriverWait(driver, 20);

        // Close the popup if it appears
        try {
            WebElement closeButton = driver.findElement(By.xpath("//button[contains(@class, 'btn-close')]"));
            closeButton.click();
        } catch (Exception e) {
            System.out.println("Popup did not appear or could not be closed.");
        }

        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='searchV2']")));
        searchBox.sendKeys("nothing phone 2a black");

        // Press Enter key to submit the search
        searchBox.sendKeys(Keys.ENTER);

        // Wait for the products to be loaded
        WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='product-item' ][1]")));

        // Capture product details
        productName = firstProduct.getText();
        productLink = driver.getCurrentUrl();

        System.out.println("Croma - Product Name: " + productName);
        System.out.println("Croma - Product Link: " + productLink);

        // Navigate to product page
        firstProduct.click();


        WebDriverWait waitForProductPage = new WebDriverWait(driver, 20);


        // Scroll until the "Add to Cart" button appears
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll to the bottom of the page
        //js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        js.executeScript("window.scrollBy(0,200)");

        driver.getWindowHandles().forEach(handle -> {
            driver.switchTo().window(handle);
            WebElement addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'Add to Cart')]")));
            addToCartButton.click();
        });




        // Wait for the Cart page and proceed to Checkout
        WebElement buyNowButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Proceed to Checkout')]")));
        buyNowButton.click();

        // Proceed to payment gateway screen
        // Note: Stop before actual payment
        /*

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
         */
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
