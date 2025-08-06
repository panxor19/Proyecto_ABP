# Proyecto Selenium ABP - ParaBank

## Guía de Instalación y Configuración del Proyecto

Esta guía detalla los pasos necesarios para configurar y ejecutar el proyecto de automatización Selenium para ParaBank. El proyecto incluye 6 escenarios automatizados (Login y Registro) con evidencias en screenshots y reportes HTML, cumpliendo completamente con los requisitos ABP.

### 1. Prerequisitos del Sistema

#### Java Development Kit (JDK)
```bash
# Verificar si Java está instalado
java -version
javac -version

# Si no está instalado, descargar JDK 11 o superior desde:
# https://adoptium.net/
```

#### Apache Maven
```bash
# Verificar si Maven está instalado
mvn -version

# Si no está instalado, descargar desde:
# https://maven.apache.org/download.cgi
```

#### Navegadores Web
- **Google Chrome** (última versión)
- **Mozilla Firefox** (última versión)

### 2. Configuración del Proyecto

#### Clonar/Descargar el Proyecto
```bash
# Clonar desde GitHub
git clone https://github.com/panxor19/Proyecto_ABP.git
cd Proyecto_ABP

# O descargar ZIP y extraer
```

#### Instalar Dependencias
```bash
# Instalar todas las dependencias de Maven
mvn clean install

# O solo descargar dependencias sin ejecutar tests
mvn dependency:resolve
```

### 3. Estructura de Archivos de Configuración

#### pom.xml
- Gestiona todas las dependencias del proyecto
- Selenium WebDriver 4.15.0
- TestNG 7.8.0  
- WebDriverManager 5.6.2
- ExtentReports 5.1.1

#### testng.xml
- Define las suites de prueba para Chrome y Firefox
- Configuración cross-browser
- Ejecuta LoginTest y RegisterTest en ambos navegadores

#### Estructura del Proyecto
```
src/test/java/com/abp/
├── tests/
│   ├── BaseTest.java           # Configuración base
│   ├── LoginTest.java          # 3 tests de login
│   ├── RegisterTest.java       # 3 tests de registro
│   └── pom/
│       ├── BasePage.java       # Funciones base POM
│       ├── LoginPage.java      # Page Object para login
│       └── RegisterPage.java   # Page Object para registro
└── utils/
    ├── WebDriverConfig.java    # Configuración de drivers
    └── ExtentReportManager.java # Gestión de reportes
```

### 4. Ejecución de Pruebas

#### Comandos Básicos de Maven

```bash
# Ejecutar todas las pruebas (6 escenarios en Chrome y Firefox)
mvn clean test

# Ejecutar solo tests de Chrome
mvn test -Dtest="*Test" -Dbrowser=chrome

# Ejecutar solo tests de Firefox  
mvn test -Dtest="*Test" -Dbrowser=firefox

# Ejecutar solo tests de Login
mvn test -Dtest=LoginTest

# Ejecutar solo tests de Registro
mvn test -Dtest=RegisterTest

# Ejecutar test específico
mvn test -Dtest=LoginTest#testLoginExitoso
mvn test -Dtest=RegisterTest#testRegistroExitoso
```

#### Tests Implementados (6 escenarios ABP)

**Tests de Login (3):**
1. `testLoginExitoso` - Login con credenciales válidas (john/demo)
2. `testLoginFallido` - Login con credenciales inválidas
3. `testLoginCamposVacios` - Validación de campos obligatorios

**Tests de Registro (3):**
1. `testRegistroExitoso` - Registro con datos válidos
2. `testRegistroCamposObligatorios` - Validación campos vacíos
3. `testRegistroPasswordsNoCoinciden` - Validación confirmación password

#### Comandos desde IDE (VS Code)

1. **Ejecutar Test Individual**
   - Clic derecho en método de test → "Run Test"
   - O usar comando: `Java: Run Tests in Current File`

2. **Ejecutar Clase de Test**
   - Clic derecho en clase → "Run All Tests"

3. **Ejecutar Suite Completa**
   - Clic derecho en `testng.xml` → "Run All Tests"

### 5. Configuración de WebDrivers

#### Automática (Recomendada)
El proyecto usa **WebDriverManager** que descarga automáticamente los drivers:

```java
// Chrome
WebDriverManager.chromedriver().setup();

// Firefox  
WebDriverManager.firefoxdriver().setup();
```

#### Manual (Si es necesario)
Si WebDriverManager falla, descargar manualmente:

1. **ChromeDriver**: https://chromedriver.chromium.org/
2. **GeckoDriver** (Firefox): https://github.com/mozilla/geckodriver/releases

Colocar en `src/main/resources/drivers/` y configurar PATH.

### 6. Sitio Web de Pruebas

#### ParaBank Demo Site
- **URL**: https://parabank.parasoft.com/parabank/index.htm
- **Usuario de prueba**: `john`
- **Password**: `demo`
- **Registro**: Usa datos únicos para cada ejecución

### 7. Configuración de Datos de Prueba

#### Datos Hardcodeados (Implementación Actual)
El proyecto utiliza datos de prueba directamente en el código:

**Login:**
- Usuario válido: `john` / `demo`
- Usuario inválido: `invalid_user` / `wrong_pass`

**Registro:**
- Datos únicos generados automáticamente
- Timestamp para evitar duplicados
- Validación de campos obligatorios

### 8. Configuración de Reportes y Evidencias

#### ExtentReports
- **Ubicación**: `reports/ExtentReport_YYYYMMDD_HHMMSS.html`
- Reportes HTML interactivos con gráficos
- Screenshots automáticas en errores
- Detalles de cada paso de test

#### Screenshots de Evidencia (.png)
- **Ubicación**: `screenshots/*.png`
- Un screenshot por cada test ejecutado

#### Configurar Reportes
Los reportes se configuran automáticamente en `ExtentReportManager.java`:
```java
// Configuración automática
sparkReporter.config().setReportName("Proyecto ABP - ParaBank Automation");
sparkReporter.config().setDocumentTitle("Reporte de Pruebas Selenium");
```

### 9. Solución de Problemas Comunes

#### Error: "Driver executable not found"
```bash
# Verificar WebDriverManager en pom.xml
mvn dependency:tree | findstr webdrivermanager

# WebDriverManager descarga automáticamente los drivers
# No necesitas descargar ChromeDriver o GeckoDriver manualmente
```

#### Error: "Tests not found"
```bash
# Verificar estructura de paquetes
src/test/java/com/abp/tests/LoginTest.java
src/test/java/com/abp/tests/RegisterTest.java

# Verificar que testng.xml apunta a las clases correctas
```

#### Error: "ParaBank site not accessible"
```bash
# Verificar conexión a internet
ping parabank.parasoft.com

# El sitio puede estar temporalmente inactivo
# Esperar unos minutos y reintentar
```

#### Error: "Port already in use" 
```powershell
# Windows - Matar procesos de drivers
taskkill /f /im chromedriver.exe
taskkill /f /im geckodriver.exe
```

#### Error: "Java version incompatible"
```bash
# Verificar versión Java (necesitas JDK 11+)
java -version
javac -version

# Configurar JAVA_HOME si es necesario
```

### 10. Configuración para Diferentes Entornos

#### Configuración Local de Desarrollo

1. **Variables de Entorno Windows**
```powershell
# Configurar JAVA_HOME
setx JAVA_HOME "C:\Program Files\Java\jdk-11"
setx PATH "%PATH%;%JAVA_HOME%\bin"

# Configurar MAVEN_HOME  
setx MAVEN_HOME "C:\Program Files\Apache\maven-3.9.0"
setx PATH "%PATH%;%MAVEN_HOME%\bin"
```

2. **Configuración VS Code**
```json
// settings.json
{
    "java.home": "C:\\Program Files\\Java\\jdk-11",
    "maven.executable.path": "C:\\Program Files\\Apache\\maven-3.9.0\\bin\\mvn.cmd",
    "java.test.defaultConfig": "testng",
    "java.test.config": {
        "workingDirectory": "${workspaceFolder}"
    }
}
```

#### Verificación de Instalación

```bash
# Verificar Java
java -version
# Debe mostrar: openjdk version "11.x.x" o superior

# Verificar Maven  
mvn -version
# Debe mostrar: Apache Maven 3.x.x

# Verificar navegadores
# Chrome: Debe estar actualizado a última versión
# Firefox: Debe estar actualizado a última versión

# Verificar dependencias del proyecto
mvn dependency:resolve
# Debe descargar todas las dependencias sin errores
```

### 11. Comandos Útiles para el Proyecto

```powershell
# Limpiar proyecto y compilar
mvn clean compile test-compile

# Ejecutar solo compilación (sin tests)
mvn compile

# Verificar dependencias y actualizaciones
mvn dependency:analyze
mvn versions:display-dependency-updates

# Información del proyecto
mvn help:describe -DgroupId=com.abp -DartifactId=selenium-automation

# Debug de tests (modo verbose)
mvn test -X

# Ejecutar tests en paralelo (más rápido)
mvn test -DsuiteXmlFile=testng.xml -Dparallel=classes

# Ver árbol de dependencias
mvn dependency:tree
```

### 12. Estructura de Cumplimiento ABP

#### ✅ Requisitos Cumplidos
- **6 Escenarios Automatizados** (cumple rango 4-6 requerido)
  - 3 tests de Login 
  - 3 tests de Registro
- **Cross-browser Testing** (Chrome y Firefox)
- **Page Object Model** implementado (3 clases POM)
- **Evidencias Completas**:
  - Reportes HTML con ExtentReports
  - Screenshots por cada prueba
- **Gestión de Drivers** automática con WebDriverManager

#### 📁 Archivos de Evidencia
```
Proyecto_ABP/
├── reports/           # Reportes HTML
├── screenshots/      # Capturas por cada prueba
└── testng.xml       # Configuración cross-browser
```

### 13. Ejecución Paso a Paso

1. **Preparación**
```bash
cd Proyecto_ABP
mvn clean install
```

2. **Ejecución Completa**
```bash
mvn clean test
```

3. **Verificar Resultados**
   - Abrir `reports/ExtentReport_*.html` en navegador
   - Revisar screenshots en carpeta `screenshots/`
   - Check console output para resumen de tests

4. **Tests Individuales** (opcional)
```bash
mvn test -Dtest=LoginTest
mvn test -Dtest=RegisterTest
```

---

## 🎯 Resumen Ejecutivo

Este proyecto de automatización Selenium cumple completamente con los requisitos ABP:
- **6 escenarios** automatizados con evidencias completas
- **Cross-browser** testing (Chrome/Firefox)  
- **POM** implementado correctamente
- **Screenshots** .png y reportes HTML automáticos
- **Sitio real** ParaBank para testing funcional

**Comando principal**: `mvn clean test`
**Evidencias**: `reports/` y `screenshots/`
