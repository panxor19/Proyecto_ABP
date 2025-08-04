package com.abp.tests;

import com.abp.config.WebDriverConfig;
import com.abp.pages.RegisterPage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Tests de Registro para ParaBank - Versión Simplificada ABP
 * Solo incluye los 3 tests básicos requeridos por la tarea
 */
public class RegistrationTestsSimplified extends BaseTest {
    private RegisterPage registerPage;
    
    @BeforeMethod
    public void setupRegistrationTest() {
        registerPage = new RegisterPage(WebDriverConfig.getDriver(), WebDriverConfig.getWait());
        registerPage.navigateToRegisterPage();
    }
    
    /**
     * Test 1: Registro exitoso con datos válidos
     */
    @Test(priority = 1, description = "Verificar registro exitoso con datos válidos")
    public void testRegistroExitoso() {
        extentTest.log(Status.INFO, "Iniciando test de registro exitoso");
        
        // Generar username único para evitar conflictos
        String uniqueUsername = "testuser" + System.currentTimeMillis();
        
        // Ingresar datos de registro válidos usando el método correcto
        registerPage.registerUser(
            "Juan", 
            "Pérez", 
            "123 Main St", 
            "Springfield", 
            "IL", 
            "62701", 
            "555-1234", 
            "123-45-6789", 
            uniqueUsername, 
            "Password123!", 
            "Password123!"
        );
        extentTest.log(Status.INFO, "Datos de registro válidos ingresados");
        
        // Verificar registro exitoso
        Assert.assertTrue(registerPage.isRegistrationSuccessful(), 
                         "El registro no fue exitoso");
        extentTest.log(Status.PASS, "Registro completado exitosamente");
    }
    
    /**
     * Test 2: Registro con campos obligatorios vacíos
     */
    @Test(priority = 2, description = "Verificar validación de campos obligatorios")
    public void testRegistroCamposObligatorios() {
        extentTest.log(Status.INFO, "Iniciando test de campos obligatorios");
        
        // Intentar registro con todos los campos vacíos
        registerPage.submitEmptyRegistration();
        extentTest.log(Status.INFO, "Intento de registro con campos vacíos");
        
        // Verificar que el registro no fue exitoso
        Assert.assertFalse(registerPage.isRegistrationSuccessful(), 
                         "No debería permitirse registro con campos vacíos");
        extentTest.log(Status.PASS, "Validación de campos obligatorios funcionando");
    }
    
    /**
     * Test 3: Registro con contraseñas que no coinciden
     */
    @Test(priority = 3, description = "Verificar validación de confirmación de contraseña")
    public void testRegistroPasswordsNoCoinciden() {
        extentTest.log(Status.INFO, "Iniciando test de contraseñas que no coinciden");
        
        // Generar username único
        String uniqueUsername = "testuser" + System.currentTimeMillis();
        
        // Ingresar datos con contraseñas diferentes
        registerPage.registerUser(
            "Carlos", 
            "García", 
            "456 Oak Ave", 
            "Chicago", 
            "IL", 
            "60601", 
            "555-5678", 
            "987-65-4321", 
            uniqueUsername, 
            "Password123!", 
            "DifferentPass456!"
        );
        extentTest.log(Status.INFO, "Datos con contraseñas diferentes ingresados");
        
        // Verificar que no se permite el registro
        Assert.assertFalse(registerPage.isRegistrationSuccessful(), 
                          "No debería permitirse registro con contraseñas diferentes");
        extentTest.log(Status.PASS, "Validación de contraseñas funcionando");
    }
}
