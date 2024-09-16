package com.orangehrm.baseclass;

import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.orangehrm.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

    protected WebDriver driver;
    protected ConfigReader config;
    protected static final Logger log = LogManager.getLogger(BaseClass.class);
    private String url;

    @BeforeMethod
    public void setUp() throws Throwable {
        config = new ConfigReader();
        String browser = config.getProperty("browser");
        url = config.getProperty("url");

        try {
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                log.info("Launching Chrome Browser");
            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                log.info("Launching Firefox Browser");
            } else if (browser.equalsIgnoreCase("edge")) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                log.info("Launching Edge Browser");
            } else {
                throw new UnsupportedOperationException("Browser: " + browser + " is not supported");
            }
        } catch (Exception e) {
            log.error("Exception occurred during WebDriver setup: " + e.getMessage(), e);
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }

        if (driver == null) {
            log.error("WebDriver initialization failed.");
            throw new IllegalStateException("Driver is null, cannot continue test.");
        }

        log.info("Application URL is : " + url);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(url);  // Navigate to the application URL
        log.info("Browser launched successfully and navigated to the URL");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
                log.info("WebDriver closed.");
            } catch (Exception e) {
                log.error("Exception occurred while closing WebDriver: " + e.getMessage(), e);
            }
        }
    }

    // Method to get the WebDriver instance
    public WebDriver getDriver() {
        return this.driver;
    }
}
