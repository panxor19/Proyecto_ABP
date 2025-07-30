package com.abp.tests;

import com.abp.config.WebDriverConfig;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Lección 1: Primera prueba simple con Selenium en ParaBank
 * Validación de título y navegación básica
 */
public class FirstSeleniumTest extends BaseTest {
    
    /**
     * Primera prueba: Abrir ParaBank y validar título
     */
    @Test(priority = 1, description = "Primera prueba - Validar título de ParaBank")
    public void testPrimeraPrueba() {
        // Navegar a ParaBank
        WebDriverConfig.getDriver().get("https://parabank.parasoft.com/parabank/index.htm");
        
        // Obtener y validar el título
        String titulo = WebDriverConfig.getDriver().getTitle();
        
        // Verificar que el título no esté vacío
        Assert.assertFalse(titulo.isEmpty(), "El título de la página no debe estar vacío");
        
        // Verificar que contiene "ParaBank"
        Assert.assertTrue(titulo.contains("ParaBank"), 
                         "El título debe contener 'ParaBank', pero es: " + titulo);
        
        System.out.println("✅ Primera prueba exitosa - Título: " + titulo);
    }
    
    /**
     * Prueba de navegación básica en ParaBank
     */
    @Test(priority = 2, description = "Validar navegación básica en ParaBank")
    public void testNavegacionBasica() {
        // Navegar a ParaBank
        WebDriverConfig.getDriver().get("https://parabank.parasoft.com/parabank/index.htm");
        
        // Verificar URL actual
        String urlActual = WebDriverConfig.getDriver().getCurrentUrl();
        Assert.assertTrue(urlActual.contains("parabank.parasoft.com"), 
                         "La URL actual debe contener 'parabank.parasoft.com'");
        
        // Obtener título
        String titulo = WebDriverConfig.getDriver().getTitle();
        Assert.assertFalse(titulo.isEmpty(), "El título no debe estar vacío");
        
        System.out.println("✅ Navegación básica exitosa - URL: " + urlActual);
    }
    
    /**
     * Prueba de navegación a la página de registro
     */
    @Test(priority = 3, description = "Validar navegación a página de registro")
    public void testNavegacionRegistro() {
        // Navegar a la página de registro
        WebDriverConfig.getDriver().get("https://parabank.parasoft.com/parabank/register.htm");
        
        // Verificar URL de registro
        String urlActual = WebDriverConfig.getDriver().getCurrentUrl();
        Assert.assertTrue(urlActual.contains("register.htm"), 
                         "La URL debe contener 'register.htm'");
        
        // Verificar que la página contiene elementos de registro
        String pageSource = WebDriverConfig.getDriver().getPageSource();
        Assert.assertTrue(pageSource.contains("Signing up is easy"), 
                         "La página debe contener el texto de registro");
        
        System.out.println("✅ Navegación a registro exitosa - URL: " + urlActual);
    }
}
