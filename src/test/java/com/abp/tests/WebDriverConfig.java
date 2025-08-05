package com.abp.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Clase para gestionar la configuración de WebDriver
 * Implementa ThreadLocal para soporte de ejecución paralela
 */
public class WebDriverConfig {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
    
    public static void initializeDriver(String browserName) {
        if (driver.get() == null) {
            WebDriver webDriver = null;
            
            switch (browserName.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.addArguments("--disable-popup-blocking");
                    chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    webDriver = new ChromeDriver(chromeOptions);
                    break;
                    
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addPreference("dom.webnotifications.enabled", false);
                    firefoxOptions.addPreference("dom.disable_beforeunload", true);
                    webDriver = new FirefoxDriver(firefoxOptions);
                    break;
                    
                default:
                    throw new IllegalArgumentException("Browser no soportado: " + browserName);
            }
            
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
            
            driver.set(webDriver);
            wait.set(new WebDriverWait(webDriver, Duration.ofSeconds(15)));
        }
    }
    
    public static WebDriver getDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver == null) {
            throw new IllegalStateException("Driver no ha sido inicializado. Llama a initializeDriver() primero.");
        }
        return webDriver;
    }
    
    public static WebDriverWait getWait() {
        WebDriverWait webDriverWait = wait.get();
        if (webDriverWait == null) {
            throw new IllegalStateException("WebDriverWait no ha sido inicializado. Llama a initializeDriver() primero.");
        }
        return webDriverWait;
    }
    
    public static void quitDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            try {
                webDriver.quit();
            } catch (Exception e) {
                System.err.println("Error cerrando driver: " + e.getMessage());
            }
            driver.remove();
            wait.remove();
        }
    }
}
