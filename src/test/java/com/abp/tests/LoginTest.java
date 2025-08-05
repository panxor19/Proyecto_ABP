package com.abp.tests;

import java.lang.Thread;
import com.abp.tests.pom.LoginPage;
import com.abp.utils.WebDriverConfig;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    private WebDriver driver;
    
    @BeforeMethod
    public void setupLoginTest() {
        System.out.println("=== Configurando página de login ===");

        driver = WebDriverConfig.getDriver();
        loginPage = new LoginPage(driver, WebDriverConfig.getWait());
        loginPage.navigateToLogin();
    }  

    /**
     * Test 1: Login exitoso con credenciales válidas
     */
    @Test(priority = 1, description = "Verificar login exitoso con credenciales válidas")
    public void testLoginExitoso() throws InterruptedException {
        extentTest.log(Status.INFO, "Iniciando test de login exitoso");
        
        // Realizar login con credenciales válidas de ParaBank
        String validUsername = "tomsmith";
        String validPassword = "SuperSecretPassword!";
        loginPage.login(validUsername, validPassword);
        extentTest.log(Status.INFO, "Credenciales ingresadas: " + validUsername);
        
        // Verificar login exitoso
        Assert.assertTrue(loginPage.isLoginSuccessful(), 
                         "Login no fue exitoso");
        loginPage.takeScreenshot("LoginExitoso");
        extentTest.log(Status.PASS, "Login realizado exitosamente");    
        
        Thread.sleep(2000); // Esperar 2 segundos para ver el resultado
    }
    
    /**
     * Test 2: Login fallido con credenciales inválidas
     */
    @Test(priority = 2, description = "Verificar login fallido con credenciales inválidas")
    public void testLoginFallido() throws InterruptedException {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        extentTest.log(Status.INFO, "Iniciando test de login fallido");
        
        // Intentar login con credenciales inválidas
        String invalidUsername = "usuarioInvalido";
        String invalidPassword = "passInvalida";
        loginPage.login(invalidUsername, invalidPassword);
        extentTest.log(Status.INFO, "Credenciales inválidas ingresadas");
        
        // Verificar que el login falló
        Assert.assertFalse(loginPage.isLoginSuccessful(), 
                          "El login no debería ser exitoso");
        extentTest.log(Status.PASS, "Login falló correctamente");
        loginPage.takeScreenshot("LoginFallido");
        Thread.sleep(2000); // Esperar 2 segundos para ver el resultado
    }
    
    // /**
    //  * Test 3: Login con campos vacíos
    //  */
    @Test(priority = 3, description = "Verificar validación de campos vacíos")
    public void testLoginCamposVacios() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        extentTest.log(Status.INFO, "Iniciando test de campos vacíos");
        
        // Intentar login sin ingresar datos
        loginPage.login("", "");
        extentTest.log(Status.INFO, "Intento de login con campos vacíos");
        
        // Verificar que no se permite login
        Assert.assertFalse(loginPage.isLoginSuccessful(), 
                          "No se debería permitir login con campos vacíos");
        loginPage.takeScreenshot("LoginCamposVacios");
        extentTest.log(Status.PASS, "Login con campos vacíos rechazado");
    }
}
