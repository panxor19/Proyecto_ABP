package com.abp.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utilidad para generar reportes HTML con ExtentReports
 */
public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;
    
    /**
     * Inicializa el reporte
     */
    public static void initReport() {
        if (extent == null) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String reportPath = "reports/ExtentReport_" + timestamp + ".html";
            
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("Selenium Automation Report");
            sparkReporter.config().setReportName("Pruebas Funcionales - ABP");
            sparkReporter.config().setTimeStampFormat("dd/MM/yyyy HH:mm:ss");
            
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            
            // Información del sistema
            extent.setSystemInfo("Tester", "Estudiante ABP");
            extent.setSystemInfo("Proyecto", "Automatización Selenium");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
    }
    
    /**
     * Crea un nuevo test en el reporte
     */
    public static ExtentTest createTest(String testName, String description) {
        test = extent.createTest(testName, description);
        return test;
    }
    
    /**
     * Obtiene el test actual
     */
    public static ExtentTest getTest() {
        return test;
    }
    
    /**
     * Finaliza el reporte y lo guarda
     */
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
    
    /**
     * Cierra el reporte
     */
    public static void closeReport() {
        if (extent != null) {
            extent.flush();
            extent = null;
        }
    }
}
