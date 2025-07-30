package com.abp.tests;

import com.abp.config.WebDriverConfig;
import com.abp.pages.LoginPage;
import com.abp.utils.DataReader;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Clase de pruebas para el módulo de Login de ParaBank
 * Implementa los escenarios requeridos en la Lección 3
 */
public class LoginTests extends BaseTest {
    
    private LoginPage loginPage;
    
    @BeforeMethod
    public void setupLoginTest() {
        loginPage = new LoginPage(WebDriverConfig.getDriver(), WebDriverConfig.getWait());
        loginPage.navigateToParaBank();
        extentTest.log(Status.INFO, "Navegando a ParaBank: https://parabank.parasoft.com/parabank/index.htm");
    }
    
    /**
     * Test 1: Login exitoso con credenciales válidas (usuario demo de ParaBank)
     */
    @Test(priority = 1, description = "Verificar login exitoso con credenciales válidas de ParaBank")
    public void testLoginExitoso() {
        extentTest.log(Status.INFO, "Iniciando test de login exitoso en ParaBank");
        
        // Verificar que la página de login está cargada
        Assert.assertTrue(loginPage.isLoginPageLoaded(), 
                         "La página de login de ParaBank no se cargó correctamente");
        extentTest.log(Status.PASS, "Página de login de ParaBank cargada correctamente");
        
        // Realizar login con credenciales válidas de ParaBank (demo user)
        String validUsername = "john";
        String validPassword = "demo";
        loginPage.login(validUsername, validPassword);
        extentTest.log(Status.INFO, "Credenciales ingresadas: " + validUsername);
        
        // Verificar login exitoso
        Assert.assertTrue(loginPage.isLoginSuccessful(), 
                         "No se pudo verificar login exitoso en ParaBank");
        extentTest.log(Status.PASS, "Login realizado exitosamente en ParaBank");
        
        // Verificar que no hay mensaje de error
        Assert.assertFalse(loginPage.isErrorMessageDisplayed(), 
                          "No debería haber mensaje de error en login exitoso");
        extentTest.log(Status.PASS, "Login exitoso verificado - sin errores");
        
        // Logout para limpiar estado
        loginPage.logout();
    }
    
    /**
     * Test 2: Login fallido con credenciales inválidas
     */
    @Test(priority = 2, description = "Verificar login fallido con credenciales inválidas")
    public void testLoginFallido() {
        extentTest.log(Status.INFO, "Iniciando test de login con credenciales inválidas");
        
        // Realizar login con credenciales inválidas
        String invalidUsername = "usuario_inexistente";
        String invalidPassword = "password_incorrecto";
        loginPage.login(invalidUsername, invalidPassword);
        extentTest.log(Status.INFO, "Credenciales inválidas ingresadas: " + invalidUsername);
        
        // Verificar que se muestra error
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "No se mostró mensaje de error para credenciales inválidas");
        extentTest.log(Status.PASS, "Mensaje de error mostrado correctamente");
        
        // Verificar que no se realizó login exitoso
        Assert.assertFalse(loginPage.isLoginSuccessful(), 
                          "No debería haberse realizado login con credenciales inválidas");
        extentTest.log(Status.PASS, "Login fallido verificado correctamente");
        
        // Verificar contenido del mensaje de error
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertFalse(errorMessage.isEmpty(), 
                          "El mensaje de error no debería estar vacío");
        extentTest.log(Status.PASS, "Mensaje de error verificado: " + errorMessage);
    }
    
    /**
     * Test 3: Login con campos vacíos
     */
    @Test(priority = 3, description = "Verificar validación de campos obligatorios")
    public void testLoginCamposVacios() {
        extentTest.log(Status.INFO, "Iniciando test de validación de campos vacíos");
        
        // Intentar login con campos vacíos
        loginPage.login("", "");
        extentTest.log(Status.INFO, "Intento de login con campos vacíos");
        
        // Verificar que se muestra error de validación
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "No se mostró error de validación para campos vacíos");
        extentTest.log(Status.PASS, "Validación de campos obligatorios funcionando");
        
        // Verificar que no se realizó login
        Assert.assertFalse(loginPage.isLoginSuccessful(), 
                          "No debería haberse realizado login con campos vacíos");
        extentTest.log(Status.PASS, "Login con campos vacíos rechazado correctamente");
    }
    
    /**
     * Test 4: Login con solo username (password vacío)
     */
    @Test(priority = 4, description = "Verificar validación con password vacío")
    public void testLoginPasswordVacio() {
        extentTest.log(Status.INFO, "Iniciando test con password vacío");
        
        // Intentar login solo con username
        loginPage.login("testuser", "");
        extentTest.log(Status.INFO, "Intento de login solo con username");
        
        // Verificar que se muestra error
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "No se mostró error para password vacío");
        extentTest.log(Status.PASS, "Validación de password obligatorio funcionando");
    }
    
    /**
     * Test 5: Login con solo password (username vacío)
     */
    @Test(priority = 5, description = "Verificar validación con username vacío")
    public void testLoginUsernameVacio() {
        extentTest.log(Status.INFO, "Iniciando test con username vacío");
        
        // Intentar login solo con password
        loginPage.login("", "testpassword");
        extentTest.log(Status.INFO, "Intento de login solo con password");
        
        // Verificar que se muestra error
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "No se mostró error para username vacío");
        extentTest.log(Status.PASS, "Validación de username obligatorio funcionando");
    }
    
    /**
     * Test parametrizado usando DataProvider - múltiples combinaciones de datos
     */
    @Test(dataProvider = "loginData", priority = 6, 
          description = "Pruebas parametrizadas con múltiples datos de login de ParaBank")
    public void testLoginParametrizado(String username, String password, String expectedResult) {
        extentTest.log(Status.INFO, 
                      String.format("Test parametrizado - Username: %s, Resultado esperado: %s", 
                                   username, expectedResult));
        
        // Limpiar campos antes de la prueba
        loginPage.clearFields();
        
        // Realizar login
        loginPage.login(username, password);
        extentTest.log(Status.INFO, "Login ejecutado con datos parametrizados");
        
        // Verificar resultado según lo esperado
        if (expectedResult.equals("success")) {
            Assert.assertTrue(loginPage.isLoginSuccessful(), 
                             "Login debería ser exitoso para: " + username);
            extentTest.log(Status.PASS, "Login exitoso como se esperaba");
            // Logout para limpiar estado
            loginPage.logout();
        } else {
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                             "Login debería fallar para: " + username);
            extentTest.log(Status.PASS, "Login falló como se esperaba");
        }
    }
    
    /**
     * DataProvider para pruebas parametrizadas con datos específicos de ParaBank
     */
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
            {"john", "demo", "success"},           // Usuario demo válido de ParaBank
            {"admin", "admin", "success"},         // Otro usuario válido
            {"usuario_invalido", "wrongpass", "error"},
            {"", "demo", "error"},                 // Username vacío
            {"john", "", "error"},                 // Password vacío
            {"", "", "error"},                     // Ambos vacíos
            {"test@invalid", "demo", "error"},     // Username con formato inválido
            {"john", "wrongpassword", "error"},    // Password incorrecto
            {"JOHN", "demo", "error"},             // Case sensitive
            {"john ", "demo", "error"}             // Con espacio
        };
    }
    
    /**
     * DataProvider alternativo leyendo desde CSV (datos actualizados para ParaBank)
     */
    @DataProvider(name = "loginDataFromCSV")
    public Object[][] getLoginDataFromCSV() {
        // Leer desde archivo CSV actualizado para ParaBank
        try {
            return DataReader.readUserDataFromCSV("test-data/login_data.csv");
        } catch (Exception e) {
            // Fallback a datos hardcodeados si el CSV no está disponible
            return getLoginData();
        }
    }
}
