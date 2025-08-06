package com.abp.tests;

import java.lang.Thread;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    
    /**
     * Test 3: Login con campos vacíos
     */
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

    @DataProvider(name = "loginDataProvider")
    public Object[][] loginDataProvider() throws IOException {
        String csvFile = "src/test/resources/login.csv";
        List<Object[]> testData = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1); // Use -1 to include trailing empty strings
                String username = data[0];
                String password = data[1];
                boolean expectedSuccess = Boolean.parseBoolean(data[2]);
                testData.add(new Object[]{username, password, expectedSuccess});
            }
        }
        return testData.toArray(new Object[0][0]);
    }

    @Test(priority = 4, description = "Login con DataProvider", dataProvider = "loginDataProvider")
    public void testLoginConDataProvider(String username, String password, boolean expectedSuccess) {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        
        // Realizar login con datos del DataProvider
        loginPage.login(username, password);
        extentTest.log(Status.INFO, "Credenciales ingresadas: " + username);
        
        Assert.assertEquals(loginPage.isLoginSuccessful(), expectedSuccess,
                            "El resultado del login no coincide con lo esperado");

        loginPage.takeScreenshot("LoginConDataProvider_" + username);
        extentTest.log(Status.PASS, "Login realizado exitosamente con DataProvider");
    }
}
