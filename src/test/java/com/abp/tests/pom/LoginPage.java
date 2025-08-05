package com.abp.tests.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    
    private static final String LOGIN_URL = "https://the-internet.herokuapp.com/login";
    
    @FindBy(name = "username")
    private WebElement usernameField;
    
    @FindBy(name = "password")
    private WebElement passwordField;
    
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;
    
    @FindBy(id = "flash")
    private WebElement flashMessage;
    
    // Constructor
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Navega al Login
     */
    public void navigateToLogin() {
        driver.get(LOGIN_URL);
        // waitForPageToLoad();
    }
    
    /**
     * Realiza el proceso de login con credenciales
     */
    public void login(String username, String password) {
        usernameField.sendKeys(username);        
        passwordField.sendKeys(password);
        loginButton.click();
    }
        
    /**
     * Verifica si el login fue exitoso (aparece mensaje de bienvenida o panel de usuario)
     */
    public boolean isLoginSuccessful() {
        waitForElementToBeVisible(flashMessage);
        String mensaje = flashMessage.getText();
        return mensaje.contains("You logged into a secure area!");
    }    
}
