package com.abp.tests;

import com.abp.config.WebDriverConfig;
import com.abp.utils.ExtentReportManager;
import com.abp.utils.VideoRecorder;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestResult;
import org.testng.annotations.*;

/**
 * Clase base para todas las pruebas
 * Configura el setup y teardown común para todos los tests
 * Incluye grabación de video como evidencia
 */
public class BaseTest {
    protected ExtentTest extentTest;
    private static boolean videoRecordingEnabled = true; // Configurar según necesidad
    
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
        
        // Iniciar grabación de video para evidencia
        if (videoRecordingEnabled) {
            VideoRecorder.startRecording(className + "_" + testName);
            extentTest.log(Status.INFO, "🎥 Grabación de video iniciada para evidencia");
        }
        
        extentTest.log(Status.INFO, "Configurando test: " + testName);
    }
    
    @AfterMethod
    public void teardownMethod(ITestResult result) {
        // Detener grabación de video
        if (videoRecordingEnabled) {
            try {
                VideoRecorder.stopRecording();
                extentTest.log(Status.INFO, "🎬 Grabación de video completada y guardada en /videos/");
            } catch (Exception e) {
                extentTest.log(Status.WARNING, "Error deteniendo grabación de video: " + e.getMessage());
            }
        }
        
        if (result.getStatus() == ITestResult.FAILURE) {
            String errorMessage = result.getThrowable() != null ? result.getThrowable().getMessage() : "Error desconocido";
            extentTest.log(Status.FAIL, "Test fallido: " + errorMessage);
            
            // Tomar captura de pantalla en caso de fallo
            try {
                String screenshotPath = takeScreenshot(result.getMethod().getMethodName());
                if (screenshotPath != null) {
                    extentTest.addScreenCaptureFromPath(screenshotPath);
                }
            } catch (Exception e) {
                extentTest.log(Status.WARNING, "No se pudo tomar captura de pantalla: " + e.getMessage());
            }
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
    
    /**
     * Método helper para tomar capturas de pantalla
     */
    protected String takeScreenshot(String testName) {
        try {
            // Aquí implementarías la lógica de captura usando tu BasePage
            // Por ahora, retornamos un path de ejemplo
            return "screenshots/" + testName + "_error.png";
        } catch (Exception e) {
            System.err.println("Error tomando captura de pantalla: " + e.getMessage());
            return null;
        }
    }
}
