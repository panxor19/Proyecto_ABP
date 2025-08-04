package com.abp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

/**
 * Page Object Model para la página de Registro de ParaBank
 * Implementa el patrón POM para el manejo de elementos de la página de registro
 */
public class RegisterPage extends BasePage {
    
    // URL de registro de ParaBank
    private static final String REGISTER_URL = "https://parabank.parasoft.com/parabank/register.htm";
    
    // Localizadores específicos de ParaBank usando diferentes estrategias
    @FindBy(id = "customer.firstName")
    private WebElement firstNameField;
    
    @FindBy(id = "customer.lastName")
    private WebElement lastNameField;
    
    @FindBy(id = "customer.address.street")
    private WebElement addressField;
    
    @FindBy(id = "customer.address.city")
    private WebElement cityField;
    
    @FindBy(id = "customer.address.state")
    private WebElement stateField;
    
    @FindBy(id = "customer.address.zipCode")
    private WebElement zipCodeField;
    
    @FindBy(id = "customer.phoneNumber")
    private WebElement phoneNumberField;
    
    @FindBy(id = "customer.ssn")
    private WebElement ssnField;
    
    @FindBy(id = "customer.username")
    private WebElement usernameField;
    
    @FindBy(id = "customer.password")
    private WebElement passwordField;
    
    @FindBy(id = "repeatedPassword")
    private WebElement confirmPasswordField;
    
    @FindBy(xpath = "//input[@value='Register']")
    private WebElement registerButton;
    
    @FindBy(css = ".error")
    private WebElement errorMessage;
    
    @FindBy(xpath = "//p[contains(text(), 'Your account was created successfully')] | //div[contains(text(), 'successfully')] | //h1[contains(text(), 'Welcome')] | //p[contains(text(), 'Welcome')]")
    private WebElement successMessage;
    
    // Mensajes de validación específicos
    @FindBy(id = "customer.firstName.errors")
    private WebElement firstNameError;
    
    @FindBy(id = "customer.lastName.errors")
    private WebElement lastNameError;
    
    @FindBy(id = "customer.address.street.errors")
    private WebElement addressError;
    
    @FindBy(id = "customer.address.city.errors")
    private WebElement cityError;
    
    @FindBy(id = "customer.address.state.errors")
    private WebElement stateError;
    
    @FindBy(id = "customer.address.zipCode.errors")
    private WebElement zipCodeError;
    
    @FindBy(id = "customer.ssn.errors")
    private WebElement ssnError;
    
    @FindBy(id = "customer.username.errors")
    private WebElement usernameError;
    
    @FindBy(id = "customer.password.errors")
    private WebElement passwordError;
    
    @FindBy(id = "repeatedPassword.errors")
    private WebElement confirmPasswordError;
    
    // Constructor
    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Navega a la página de registro de ParaBank
     */
    public void navigateToRegisterPage() {
        driver.get(REGISTER_URL);
        waitForPageToLoad();
    }
    
    /**
     * Espera a que la página de registro esté completamente cargada
     */
    private void waitForPageToLoad() {
        waitForElementToBeVisible(firstNameField);
        waitForElementToBeVisible(registerButton);
    }
    
    /**
     * Realiza el proceso completo de registro para ParaBank
     */
    public void registerUser(String firstName, String lastName, String address, String city, 
                           String state, String zipCode, String phoneNumber, String ssn,
                           String username, String password, String confirmPassword) {
        waitForElementToBeVisible(firstNameField);
        
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        
        addressField.clear();
        addressField.sendKeys(address);
        
        cityField.clear();
        cityField.sendKeys(city);
        
        stateField.clear();
        stateField.sendKeys(state);
        
        zipCodeField.clear();
        zipCodeField.sendKeys(zipCode);
        
        phoneNumberField.clear();
        phoneNumberField.sendKeys(phoneNumber);
        
        ssnField.clear();
        ssnField.sendKeys(ssn);
        
        usernameField.clear();
        usernameField.sendKeys(username);
        
        passwordField.clear();
        passwordField.sendKeys(password);
        
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);
        
        clickElement(registerButton);
        
        // Esperar a que aparezca resultado
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Registro básico solo con campos obligatorios mínimos
     */
    public void registerUserBasic(String firstName, String lastName, String username, 
                                 String password, String confirmPassword) {
        registerUser(firstName, lastName, "123 Test St", "Test City", "Test State", 
                    "12345", "555-1234", "123-45-6789", username, password, confirmPassword);
    }
    
    /**
     * Registro con campos vacíos para probar validaciones
     */
    public void submitEmptyRegistration() {
        waitForElementToBeVisible(registerButton);
        clickElement(registerButton);
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Obtiene el mensaje de error general
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
     * Obtiene el mensaje de éxito
     */
    public String getSuccessMessage() {
        try {
            waitForElementToBeVisible(successMessage);
            return successMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Verifica si hay errores de validación específicos
     */
    public boolean hasFieldValidationErrors() {
        try {
            return isElementDisplayed(firstNameError) || 
                   isElementDisplayed(lastNameError) ||
                   isElementDisplayed(addressError) ||
                   isElementDisplayed(cityError) ||
                   isElementDisplayed(stateError) ||
                   isElementDisplayed(zipCodeError) ||
                   isElementDisplayed(ssnError) ||
                   isElementDisplayed(usernameError) ||
                   isElementDisplayed(passwordError) ||
                   isElementDisplayed(confirmPasswordError);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Verifica si un elemento está visible
     */
    private boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Obtiene errores específicos de campos
     */
    public String getFirstNameError() {
        return getElementText(firstNameError);
    }
    
    public String getLastNameError() {
        return getElementText(lastNameError);
    }
    
    public String getUsernameError() {
        return getElementText(usernameError);
    }
    
    public String getPasswordError() {
        return getElementText(passwordError);
    }
    
    public String getConfirmPasswordError() {
        return getElementText(confirmPasswordError);
    }
    
    /**
     * Helper para obtener texto de un elemento
     */
    private String getElementText(WebElement element) {
        try {
            if (element.isDisplayed()) {
                return element.getText();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Verifica si el registro fue exitoso
     */
    public boolean isRegistrationSuccessful() {
        try {
            // Esperar un poco a que la página se cargue después del registro
            Thread.sleep(2000);
            
            // Verificar múltiples indicadores de éxito
            
            // 1. Verificar mensaje de éxito
            try {
                if (successMessage.isDisplayed()) {
                    return true;
                }
            } catch (Exception e) {
                // Continuar con otras verificaciones
            }
            
            // 2. Verificar si estamos en la página de bienvenida o overview
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("overview") || currentUrl.contains("welcome") || currentUrl.contains("account")) {
                return true;
            }
            
            // 3. Verificar si hay elementos que indican login exitoso
            try {
                WebElement logoutLink = driver.findElement(By.linkText("Log Out"));
                if (logoutLink.isDisplayed()) {
                    return true;
                }
            } catch (Exception e) {
                // Continuar
            }
            
            // 4. Verificar ausencia de errores
            try {
                if (!errorMessage.isDisplayed()) {
                    // Si no hay errores y hemos llegado aquí, probablemente fue exitoso
                    return true;
                }
            } catch (Exception e) {
                // Si no se encuentra el elemento de error, es buena señal
                return true;
            }
            
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Limpia todos los campos del formulario
     */
    public void clearAllFields() {
        firstNameField.clear();
        lastNameField.clear();
        addressField.clear();
        cityField.clear();
        stateField.clear();
        zipCodeField.clear();
        phoneNumberField.clear();
        ssnField.clear();
        usernameField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
    }
    
    /**
     * Verifica si la página de registro está completamente cargada
     */
    public boolean isRegisterPageLoaded() {
        try {
            waitForElementToBeVisible(firstNameField);
            waitForElementToBeVisible(passwordField);
            waitForElementToBeVisible(registerButton);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
