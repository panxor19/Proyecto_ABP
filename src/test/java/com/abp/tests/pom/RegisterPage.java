package com.abp.tests.pom;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object Model para la página de Registro de ParaBank
 * Se implementa el patrón POM para el manejo de elementos de la página de registro
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

    By successMessage = By.xpath("//p[contains(text(), 'Your account was created successfully')]");
    
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
     * Registro con campos vacíos para probar validaciones
     */
    public void submitEmptyRegistration() {
        registerButton.click();
    }
    
    /**
     * Verifica si el registro fue exitoso
     */
    public boolean isRegistrationSuccessful() {
        List<WebElement> elements = driver.findElements(successMessage);
        return !elements.isEmpty();
    }
}
