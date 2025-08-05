package com.abp.tests.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    
    // URL de ParaBank
    private static final String PARABANK_URL = "https://parabank.parasoft.com/parabank/index.htm";
    
    // Localizadores específicos de ParaBank usando diferentes estrategias
    @FindBy(name = "username")
    private WebElement usernameField;
    
    @FindBy(name = "password")
    private WebElement passwordField;
    
    @FindBy(xpath = "//input[@value='Log In' or @value='LOG IN']")
    private WebElement loginButton;
    
    @FindBy(css = ".error")
    private WebElement errorMessage;
    
    // Localizadores adicionales para errores
    @FindBy(xpath = "//p[@class='error']")
    private WebElement errorMessageParagraph;
    
    @FindBy(xpath = "//*[contains(@class, 'error') or contains(text(), 'error') or contains(text(), 'invalid') or contains(text(), 'Please')]")
    private WebElement anyErrorMessage;
    
    @FindBy(xpath = "//p[contains(text(), 'Welcome')]")
    private WebElement welcomeMessage;
    
    @FindBy(linkText = "Forgot login info?")
    private WebElement forgotPasswordLink;
    
    @FindBy(linkText = "Register")
    private WebElement registerLink;
    
    // Elementos del panel de usuario logueado
    @FindBy(css = ".smallText")
    private WebElement loggedInUserInfo;
    
    @FindBy(linkText = "Log Out")
    private WebElement logoutLink;
    
    // Elementos adicionales para verificar login exitoso
    @FindBy(xpath = "//div[@id='rightPanel']//h1[contains(text(), 'Account Services')]")
    private WebElement accountServicesPanel;
    
    @FindBy(xpath = "//div[@class='ng-scope']//p[contains(text(), 'Welcome')]")
    private WebElement welcomeMessageAlternative;
    
    // Constructor
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Navega a la página de ParaBank
     */
    public void navigateToParaBank() {
        driver.get(PARABANK_URL);
        waitForPageToLoad();
    }
    
    /**
     * Espera a que la página de login esté completamente cargada
     */
    private void waitForPageToLoad() {
        waitForElementToBeVisible(usernameField);
        waitForElementToBeVisible(passwordField);
        waitForElementToBeVisible(loginButton);
    }
    
    /**
     * Realiza el proceso de login con credenciales
     */
    public void login(String username, String password) {
        waitForElementToBeVisible(usernameField);
        usernameField.clear();
        usernameField.sendKeys(username);
        
        passwordField.clear();
        passwordField.sendKeys(password);
        
        clickElement(loginButton);
        
        // Esperar a que aparezca resultado (success o error)
        try {
            Thread.sleep(2000); // Espera para que aparezca el resultado
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Obtiene el mensaje de error mostrado
     */
    public String getErrorMessage() {
        try {
            waitForElementToBeVisible(errorMessage);
            return errorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Obtiene el mensaje de bienvenida para login exitoso
     */
    public String getWelcomeMessage() {
        try {
            waitForElementToBeVisible(welcomeMessage);
            return welcomeMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Verifica si el mensaje de error está presente
     */
    public boolean isErrorMessageDisplayed() {
        try {
            // Esperar un poco para que la página se estabilice
            Thread.sleep(1000);
            
            // Si estamos en la página overview, no hay errores
            if (driver.getCurrentUrl().contains("overview")) {
                return false;
            }
            
            // Si vemos el link de Log Out, el login fue exitoso
            try {
                WebElement logoutLink = driver.findElement(By.linkText("Log Out"));
                if (logoutLink.isDisplayed()) {
                    return false; // No hay errores si podemos hacer logout
                }
            } catch (Exception e) {
                // Continuar con verificaciones de error
            }
            
            // Verificar mensajes de error específicos
            boolean hasErrorMessage = isElementPresent(By.xpath("//p[@class='error' and text()!='']"));
            boolean hasValidationError = isElementPresent(By.xpath("//*[contains(text(), 'Please enter a username and password') or contains(text(), 'invalid username')]"));
            
            return hasErrorMessage || hasValidationError;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Verifica si el login fue exitoso (aparece mensaje de bienvenida o panel de usuario)
     */
    public boolean isLoginSuccessful() {
        try {
            // Esperar un poco para que la página se cargue
            Thread.sleep(3000);
            
            String currentUrl = driver.getCurrentUrl();
            
            // Verificar si la URL cambió a una página de cuenta/overview
            boolean urlChangedToAccount = currentUrl.contains("overview") || 
                                        currentUrl.contains("accounts") ||
                                        currentUrl.contains("activity");
            
            // Verificar elementos específicos de login exitoso
            boolean hasWelcomeMessage = isElementPresent(By.xpath("//p[contains(text(), 'Welcome')]"));
            boolean hasAccountServices = isElementPresent(By.xpath("//div[@id='rightPanel']//h1[contains(text(), 'Account Services')]"));
            boolean hasLogoutLink = isElementPresent(By.linkText("Log Out"));
            boolean hasAccountsOverview = isElementPresent(By.xpath("//h1[contains(text(), 'Accounts Overview')]"));
            
            // Si seguimos en index.htm, definitivamente no es un login exitoso
            if (currentUrl.contains("index.htm")) {
                return false;
            }
            
            return urlChangedToAccount || hasWelcomeMessage || hasAccountServices || hasLogoutLink || hasAccountsOverview;
        } catch (Exception e) {
            System.out.println("Error verificando login exitoso: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Método auxiliar para verificar si un elemento está presente
     */
    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Hace clic en el enlace de "Olvidé mi contraseña"
     */
    public void clickForgotPassword() {
        clickElement(forgotPasswordLink);
    }
    
    /**
     * Hace clic en el enlace de "Register"
     */
    public void clickRegisterLink() {
        clickElement(registerLink);
    }
    
    /**
     * Verifica si la página de login está cargada correctamente
     */
    public boolean isLoginPageLoaded() {
        try {
            waitForElementToBeVisible(usernameField);
            waitForElementToBeVisible(passwordField);
            waitForElementToBeVisible(loginButton);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Limpia los campos de entrada
     */
    public void clearFields() {
        usernameField.clear();
        passwordField.clear();
    }
    
    /**
     * Realiza logout si el usuario está logueado
     */
    public void logout() {
        try {
            if (logoutLink.isDisplayed()) {
                clickElement(logoutLink);
            }
        } catch (Exception e) {
            // Usuario no está logueado
        }
    }
    
    /**
     * Verifica si el usuario está actualmente logueado
     */
    public boolean isUserLoggedIn() {
        try {
            return logoutLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
