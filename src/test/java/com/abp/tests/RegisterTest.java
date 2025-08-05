package com.abp.tests;

import com.abp.tests.pom.RegisterPage;
import com.abp.utils.WebDriverConfig;
import com.aventstack.extentreports.Status;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.*;

public class RegisterTest extends BaseTest {
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
        String uniqueUsername = generateUsername();

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
        registerPage.takeScreenshot("RegistroExitoso");
        extentTest.log(Status.PASS, "Registro completado exitosamente");
    }
    
    /**
     * Test 2: Registro con campos obligatorios vacíos
     */
    @Test(priority = 2, description = "Verificar validación de campos obligatorios")
    public void testRegistroCamposObligatorios() throws Exception {
        extentTest.log(Status.INFO, "Iniciando test de campos obligatorios");
        
        // Intentar registro con todos los campos vacíos
        registerPage.submitEmptyRegistration();
        extentTest.log(Status.INFO, "Intento de registro con campos vacíos");
        
        // Verificar que el registro no fue exitoso
        Assert.assertFalse(registerPage.isRegistrationSuccessful(), 
                         "No debería permitirse registro con campos vacíos");
        registerPage.takeScreenshot("RegistroCamposObligatorios");
        extentTest.log(Status.PASS, "Validación de campos obligatorios funcionando");
    }
    
    /**
     * Test 3: Registro con contraseñas que no coinciden
     */
    @Test(priority = 3, description = "Verificar validación de confirmación de contraseña")
    public void testRegistroPasswordsNoCoinciden() {
        extentTest.log(Status.INFO, "Iniciando test de contraseñas que no coinciden");
        
        // Generar username único
        String uniqueUsername = generateUsername();
        
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
        registerPage.takeScreenshot("RegistroPasswordsNoCoinciden");
        extentTest.log(Status.PASS, "Validación de contraseñas funcionando");
    }

    public static String generateUsername() {
        String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder codigo = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int indice = random.nextInt(caracteres.length());
            codigo.append(caracteres.charAt(indice));
        }

        return "user" + codigo.toString();
    }

}
