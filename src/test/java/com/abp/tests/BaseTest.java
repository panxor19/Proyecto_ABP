package com.abp.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.abp.tests.pom.BasePage;
import com.abp.utils.ExtentReportManager;
import com.abp.utils.WebDriverConfig;

/**
 * Clase base para todas las pruebas
 * Configura el setup y teardown común para todos los tests
 */
public class BaseTest {
    protected ExtentTest extentTest;
    protected BasePage pom;
    
    @BeforeClass
    @Parameters("browser")
    public void setupClass(@Optional("chrome") String browser) {
        // Inicializar ExtentReports una vez por clase de test
        ExtentReportManager.initReport();
        
        // Inicializar WebDriver
        WebDriverConfig.initializeDriver(browser);
        
        System.out.println("=== Iniciando pruebas en navegador: " + browser + " ===");
    }
    
    @BeforeMethod
    public void setupMethod(ITestResult result) {
        // Crear un test para cada método
        String testName = result.getMethod().getMethodName();
        String className = this.getClass().getSimpleName();
        extentTest = ExtentReportManager.createTest(className + " - " + testName, 
                                                   "Prueba automatizada: " + testName);
                
        extentTest.log(Status.INFO, "Configurando test: " + testName);
    }
    
    @AfterMethod
    public void teardownMethod(ITestResult result) throws Exception {
        System.out.println("=== Finalizando test: " + result.getMethod().getMethodName() + " ===");
        
        if (result.getStatus() == ITestResult.FAILURE) {
            String errorMessage = result.getThrowable() != null ? result.getThrowable().getMessage() : "Error desconocido";
            extentTest.log(Status.FAIL, "Test fallido: " + errorMessage);            
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, "Test ejecutado exitosamente");
        } else if (result.getStatus() == ITestResult.SKIP) {
            String skipMessage = result.getThrowable() != null ? result.getThrowable().getMessage() : "Test omitido";
            extentTest.log(Status.SKIP, "Test omitido: " + skipMessage);
        }
    }
    
    @AfterClass
    public void teardownClass() {
        // Cerrar WebDriver
        WebDriverConfig.quitDriver();
        
        // Finalizar reporte
        ExtentReportManager.flushReport();
        
        System.out.println("=== Pruebas completadas ===");
    }
    
    @AfterSuite
    public void teardownSuite() {
        // Cerrar ExtentReports completamente
        ExtentReportManager.closeReport();
    }
}
