package com.abp.tests;

import com.abp.config.WebDriverConfig;
import com.abp.pages.RegisterPage;
import com.abp.utils.DataReader;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Clase de pruebas para el módulo de Registro de Usuario en ParaBank
 * Implementa los escenarios requeridos en la Lección 3
 */
public class RegistrationTests extends BaseTest {
    
    private RegisterPage registerPage;
    private Random random = new Random();
    
    @BeforeMethod
    public void setupRegistrationTest() {
        registerPage = new RegisterPage(WebDriverConfig.getDriver(), WebDriverConfig.getWait());
        registerPage.navigateToRegisterPage();
        extentTest.log(Status.INFO, "Navegando a página de registro de ParaBank");
    }
    
    /**
     * Test 1: Registro exitoso con datos válidos en ParaBank
     */
    @Test(priority = 1, description = "Verificar registro exitoso con datos válidos en ParaBank")
    public void testRegistroExitoso() {
        extentTest.log(Status.INFO, "Iniciando test de registro exitoso en ParaBank");
        
        // Verificar que la página de registro está cargada
        Assert.assertTrue(registerPage.isRegisterPageLoaded(), 
                         "La página de registro de ParaBank no se cargó correctamente");
        extentTest.log(Status.PASS, "Página de registro de ParaBank cargada correctamente");
        
        // Generar username único para evitar conflictos
        String uniqueUsername = "testuser" + System.currentTimeMillis();
        
        // Realizar registro con datos válidos
        registerPage.registerUser(
            "Juan", 
            "Pérez", 
            "123 Main Street", 
            "Springfield", 
            "Illinois", 
            "62701", 
            "555-1234", 
            "123-45-6789", 
            uniqueUsername, 
            "Password123!", 
            "Password123!"
        );
        extentTest.log(Status.INFO, "Datos de registro válidos ingresados para usuario: " + uniqueUsername);
        
        // Verificar registro exitoso
        Assert.assertTrue(registerPage.isRegistrationSuccessful(), 
                         "El registro no fue exitoso en ParaBank");
        extentTest.log(Status.PASS, "Registro completado exitosamente en ParaBank");
        
        // Verificar mensaje de éxito
        String successMessage = registerPage.getSuccessMessage();
        Assert.assertFalse(successMessage.isEmpty(), 
                          "No se mostró mensaje de éxito");
        extentTest.log(Status.PASS, "Mensaje de éxito verificado: " + successMessage);
    }
    
    /**
     * Test 2: Registro fallido - todos los campos vacíos
     */
    @Test(priority = 2, description = "Verificar validación de campos obligatorios en ParaBank")
    public void testRegistroCamposObligatorios() {
        extentTest.log(Status.INFO, "Iniciando test de validación de campos obligatorios");
        
        // Intentar registro con todos los campos vacíos
        registerPage.submitEmptyRegistration();
        extentTest.log(Status.INFO, "Intento de registro con todos los campos vacíos");
        
        // Verificar que se muestran errores de validación
        Assert.assertTrue(registerPage.hasFieldValidationErrors(), 
                         "No se mostraron errores de validación para campos obligatorios");
        extentTest.log(Status.PASS, "Validación de campos obligatorios funcionando");
        
        // Verificar que no se realizó registro exitoso
        Assert.assertFalse(registerPage.isRegistrationSuccessful(), 
                          "No debería haberse registrado con campos vacíos");
        extentTest.log(Status.PASS, "Registro con campos vacíos rechazado correctamente");
    }
    
    /**
     * Test 3: Registro fallido - solo firstName vacío
     */
    @Test(priority = 3, description = "Verificar validación de firstName obligatorio")
    public void testRegistroFirstNameVacio() {
        extentTest.log(Status.INFO, "Iniciando test de validación de firstName");
        
        String uniqueUsername = "testuser" + System.currentTimeMillis();
        
        // Registro con firstName vacío
        registerPage.registerUser(
            "",              // firstName vacío
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
        extentTest.log(Status.INFO, "Intento de registro con firstName vacío");
        
        // Verificar error específico
        String firstNameError = registerPage.getFirstNameError();
        Assert.assertFalse(firstNameError.isEmpty(), 
                          "No se mostró error para firstName vacío");
        extentTest.log(Status.PASS, "Error de firstName detectado: " + firstNameError);
    }
    
    /**
     * Test 4: Registro fallido - solo lastName vacío
     */
    @Test(priority = 4, description = "Verificar validación de lastName obligatorio")
    public void testRegistroLastNameVacio() {
        extentTest.log(Status.INFO, "Iniciando test de validación de lastName");
        
        String uniqueUsername = "testuser" + System.currentTimeMillis();
        
        // Registro con lastName vacío
        registerPage.registerUser(
            "Juan", 
            "",              // lastName vacío
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
        extentTest.log(Status.INFO, "Intento de registro con lastName vacío");
        
        // Verificar error específico
        String lastNameError = registerPage.getLastNameError();
        Assert.assertFalse(lastNameError.isEmpty(), 
                          "No se mostró error para lastName vacío");
        extentTest.log(Status.PASS, "Error de lastName detectado: " + lastNameError);
    }
    
    /**
     * Test 5: Registro fallido - contraseñas no coinciden
     */
    @Test(priority = 5, description = "Verificar validación de confirmación de contraseña")
    public void testRegistroPasswordsNoCoinciden() {
        extentTest.log(Status.INFO, "Iniciando test de validación de confirmación de contraseña");
        
        String uniqueUsername = "testuser" + System.currentTimeMillis();
        
        // Registro con contraseñas que no coinciden
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
            "DiferentePassword!"
        );
        extentTest.log(Status.INFO, "Registro con contraseñas que no coinciden");
        
        // Verificar error de confirmación de contraseña
        String passwordError = registerPage.getConfirmPasswordError();
        Assert.assertFalse(passwordError.isEmpty(), 
                          "No se mostró error de confirmación de contraseña");
        extentTest.log(Status.PASS, "Error de confirmación de contraseña detectado: " + passwordError);
    }
    
    /**
     * Test 6: Registro fallido - username vacío
     */
    @Test(priority = 6, description = "Verificar validación de username obligatorio")
    public void testRegistroUsernameVacio() {
        extentTest.log(Status.INFO, "Iniciando test de validación de username");
        
        // Registro con username vacío
        registerPage.registerUser(
            "Juan", 
            "Pérez", 
            "123 Main St", 
            "Springfield", 
            "IL", 
            "62701", 
            "555-1234", 
            "123-45-6789", 
            "",              // username vacío
            "Password123!", 
            "Password123!"
        );
        extentTest.log(Status.INFO, "Intento de registro con username vacío");
        
        // Verificar error específico
        String usernameError = registerPage.getUsernameError();
        Assert.assertFalse(usernameError.isEmpty(), 
                          "No se mostró error para username vacío");
        extentTest.log(Status.PASS, "Error de username detectado: " + usernameError);
    }
    
    /**
     * Test parametrizado usando DataProvider - múltiples combinaciones de datos
     */
    @Test(dataProvider = "registrationData", priority = 7, 
          description = "Pruebas parametrizadas con múltiples datos de registro para ParaBank")
    public void testRegistroParametrizado(String firstName, String lastName, String address, 
                                        String city, String state, String zipCode, String phone,
                                        String ssn, String username, String password, 
                                        String confirmPassword, String expectedResult) {
        extentTest.log(Status.INFO, 
                      String.format("Test parametrizado - Username: %s, Resultado esperado: %s", 
                                   username, expectedResult));
        
        // Limpiar campos antes de la prueba
        registerPage.clearAllFields();
        
        // Hacer username único si no está vacío
        String uniqueUsername = username.isEmpty() ? "" : username + System.currentTimeMillis();
        
        // Realizar registro
        registerPage.registerUser(firstName, lastName, address, city, state, zipCode, 
                                 phone, ssn, uniqueUsername, password, confirmPassword);
        extentTest.log(Status.INFO, "Registro ejecutado con datos parametrizados");
        
        // Verificar resultado según lo esperado
        if (expectedResult.equals("success")) {
            Assert.assertTrue(registerPage.isRegistrationSuccessful(), 
                             "Registro debería ser exitoso para: " + uniqueUsername);
            extentTest.log(Status.PASS, "Registro exitoso como se esperaba");
        } else {
            Assert.assertTrue(registerPage.hasFieldValidationErrors(), 
                             "Registro debería fallar para: " + uniqueUsername);
            extentTest.log(Status.PASS, "Registro falló como se esperaba");
        }
    }
    
    /**
     * DataProvider para pruebas parametrizadas específico para ParaBank
     */
    @DataProvider(name = "registrationData")
    public Object[][] getRegistrationData() {
        return new Object[][] {
            // firstName, lastName, address, city, state, zipCode, phone, ssn, username, password, confirmPassword, expectedResult
            {"Juan", "Pérez", "123 Main St", "Springfield", "IL", "62701", "555-1234", "123-45-6789", "validuser", "Pass123!", "Pass123!", "success"},
            {"", "García", "456 Oak Ave", "Chicago", "IL", "60601", "555-5678", "987-65-4321", "testuser", "Pass123!", "Pass123!", "error"},
            {"Pedro", "", "789 Pine St", "Rockford", "IL", "61101", "555-9012", "456-78-9012", "pedro", "Pass123!", "Pass123!", "error"},
            {"Ana", "Martín", "321 Elm St", "Peoria", "IL", "61602", "555-3456", "789-01-2345", "", "Pass123!", "Pass123!", "error"},
            {"Luis", "Rodríguez", "654 Maple Ave", "Aurora", "IL", "60505", "555-7890", "012-34-5678", "luis", "123", "456", "error"},
            {"Carmen", "Fernández", "987 Cedar Ln", "Joliet", "IL", "60431", "555-2468", "345-67-8901", "carmen", "Pass123!", "DifferentPass", "error"},
            {"María", "González", "147 Birch Dr", "Naperville", "IL", "60540", "555-1357", "678-90-1234", "maria", "SecurePass123!", "SecurePass123!", "success"},
            {"Carlos", "Ruiz", "258 Willow St", "Evanston", "IL", "60201", "555-2468", "901-23-4567", "carlos", "weak", "weak", "error"}
        };
    }
}
