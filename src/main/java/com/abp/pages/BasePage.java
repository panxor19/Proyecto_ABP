package com.abp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase base para todas las páginas del patrón Page Object Model
 * Contiene métodos comunes y utilidades para el manejo de elementos
 */
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    
    /**
     * Espera a que un elemento sea visible
     */
    protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    /**
     * Espera a que un elemento sea clickeable
     */
    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    /**
     * Hace clic en un elemento de forma segura
     */
    protected void clickElement(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }
    
    /**
     * Obtiene el título de la página actual
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Obtiene la URL actual
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    /**
     * Toma una captura de pantalla y la guarda en la carpeta screenshots
     */
    public String takeScreenshot(String testName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
            
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = testName + "_" + timestamp + ".png";
            String filePath = "screenshots/" + fileName;
            
            File destFile = new File(filePath);
            destFile.getParentFile().mkdirs(); // Crear directorio si no existe
            
            FileUtils.copyFile(sourceFile, destFile);
            return filePath;
        } catch (IOException e) {
            System.err.println("Error al tomar captura de pantalla: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Navega a una URL específica
     */
    public void navigateTo(String url) {
        driver.get(url);
    }
    
    /**
     * Refresca la página actual
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }
    
    /**
     * Navega hacia atrás en el historial del navegador
     */
    public void goBack() {
        driver.navigate().back();
    }
}
