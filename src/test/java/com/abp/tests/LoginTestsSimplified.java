package com.abp.tests;

import com.abp.config.WebDriverConfig;
import com.abp.pages.LoginPage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Tests de Login para ParaBank - Versión Simplificada ABP
 * Solo incluye los 3 tests básicos requeridos por la tarea
 */
public class LoginTestsSimplified extends BaseTest {
    private LoginPage loginPage;
    
    @BeforeMethod
    public void setupLoginTest() {
        loginPage = new LoginPage(WebDriverConfig.getDriver(), WebDriverConfig.getWait());
        loginPage.navigateToParaBank();
    }
    
    /**
     * Test 1: Login exitoso con credenciales válidas
     */
    @Test(priority = 1, description = "Verificar login exitoso con credenciales válidas")
    public void testLoginExitoso() {
        extentTest.log(Status.INFO, "Iniciando test de login exitoso");
        
        // Realizar login con credenciales válidas de ParaBank
        String validUsername = "john";
        String validPassword = "demo";
        loginPage.login(validUsername, validPassword);
        extentTest.log(Status.INFO, "Credenciales ingresadas: " + validUsername);
        
        // Verificar login exitoso
        Assert.assertTrue(loginPage.isLoginSuccessful(), 
                         "Login no fue exitoso");
        extentTest.log(Status.PASS, "Login realizado exitosamente");
        
        // Logout para limpiar estado
        try {
            loginPage.logout();
            extentTest.log(Status.INFO, "Logout realizado");
        } catch (Exception e) {
            extentTest.log(Status.WARNING, "No fue necesario logout");
        }
    }
    
    /**
     * Test 2: Login fallido con credenciales inválidas
     */
    @Test(priority = 2, description = "Verificar login fallido con credenciales inválidas")
    public void testLoginFallido() {
        extentTest.log(Status.INFO, "Iniciando test de login fallido");
        
        // Intentar login con credenciales inválidas
        String invalidUsername = "usuarioInvalido";
        String invalidPassword = "passwordIncorrecto";
        loginPage.login(invalidUsername, invalidPassword);
        extentTest.log(Status.INFO, "Credenciales inválidas ingresadas");
        
        // Verificar que el login falló
        Assert.assertFalse(loginPage.isLoginSuccessful(), 
                          "El login no debería ser exitoso");
        extentTest.log(Status.PASS, "Login falló correctamente");
        
        // Verificar mensaje de error
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "Debería mostrarse mensaje de error");
        extentTest.log(Status.PASS, "Mensaje de error mostrado");
    }
    
    /**
     * Test 3: Login con campos vacíos
     */
    @Test(priority = 3, description = "Verificar validación de campos vacíos")
    public void testLoginCamposVacios() {
        extentTest.log(Status.INFO, "Iniciando test de campos vacíos");
        
        // Intentar login sin ingresar datos
        loginPage.login("", "");
        extentTest.log(Status.INFO, "Intento de login con campos vacíos");
        
        // Verificar que no se permite login
        Assert.assertFalse(loginPage.isLoginSuccessful(), 
                          "No se debería permitir login con campos vacíos");
        extentTest.log(Status.PASS, "Login con campos vacíos rechazado");
    }
}
