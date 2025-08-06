# Guía de Uso de Selenium IDE

Este documento explica cómo se crearon los casos de prueba para el login en `https://the-internet.herokuapp.com/login` utilizando Selenium IDE.

Los scripts exportados se encuentran en la carpeta `/selenium-ide/`.

## Prerrequisitos

*   Tener instalada la extensión de [Selenium IDE](https://www.selenium.dev/selenium-ide/) en tu navegador (Chrome o Firefox).

## 1. Caso de Prueba: Login Exitoso

Este caso de prueba verifica que un usuario con credenciales correctas puede iniciar sesión.

**Pasos para grabar:**
1.  Abrir Selenium IDE y seleccionar "Record a new test in a new project".
2.  Establecer la URL base: `https://the-internet.herokuapp.com`.
3.  Iniciar la grabación. Se abrirá la página de login.
4.  **Comando `type`**: Ingresar el usuario `tomsmith` en el campo `username`.
5.  **Comando `type`**: Ingresar la contraseña `SuperSecretPassword!` en el campo `password`.
6.  **Comando `click`**: Hacer clic en el botón "Login".
7.  **Aserción**: Verificar que el texto `Welcome to the Secure Area` está presente en la página de bienvenida para confirmar el éxito.
8.  Detener la grabación y guardar el test como `Login Exitoso`.
9.  Exportar el test como un archivo `.java` con el nombre `LoginSuccess.java` y guardarlo en la carpeta `/selenium-ide`.

## 2. Caso de Prueba: Login Erróneo

Este caso de prueba verifica que un usuario con credenciales incorrectas no puede iniciar sesión.

**Pasos para grabar:**
1.  Crear un nuevo test en el mismo proyecto.
2.  Iniciar la grabación desde la página de login.
3.  **Comando `type`**: Ingresar el usuario `tomsmith` en el campo `username`.
4.  **Comando `type`**: Ingresar una contraseña incorrecta como `password-incorrecto` en el campo `password`.
5.  **Comando `click`**: Hacer clic en el botón "Login".
6.  **Aserción**: Verificar que el mensaje de error `Your username is invalid!` es visible.
7.  Detener la grabación y guardar el test como `Login Erróneo`.
8.  Exportar el test como un archivo `.java` con el nombre `LoginFailure.java` y guardarlo en la carpeta `/selenium-ide`.
