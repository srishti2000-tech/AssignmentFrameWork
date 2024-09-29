package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverUtils {
    public static WebDriver getDriver() {
        // Automatically setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Create ChromeOptions instance to configure ChromeDriver
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*"); // Allow all origins
        options.setExperimentalOption("useAutomationExtension", false); // Disable automation extension
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); // Exclude automation switches



        return new ChromeDriver(options);
    }
}
