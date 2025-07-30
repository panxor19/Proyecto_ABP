# Selenium Automation Project - ABP

## Guía de Instalación y Configuración del Proyecto Grupal

Nuestro equipo ha desarrollado esta guía para facilitar la instalación y configuración del proyecto de automatización Selenium. Seguir estos pasos permitirá ejecutar exitosamente todas las pruebas implementadas colaborativamente.

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
# Si tienes el proyecto en GitHub
git clone <repository-url>
cd tarea-abp

# O simplemente descarga y extrae el ZIP
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
- Configuración de plugins de Maven
- Versiones de Selenium, TestNG, etc.

#### testng.xml
- Define las suites de prueba
- Configuración cross-browser
- Parámetros de ejecución

#### application.properties (opcional)
```properties
# URLs de las aplicaciones a probar
base.url=https://example.com
login.url=https://example.com/login
register.url=https://example.com/register

# Timeouts
implicit.wait=10
explicit.wait=15

# Configuración de reportes
reports.path=./reports
screenshots.path=./screenshots
```

### 4. Ejecución de Pruebas

#### Comandos Básicos de Maven

```bash
# Ejecutar todas las pruebas
mvn clean test

# Ejecutar con perfil específico
mvn test -Pbrowser-chrome
mvn test -Pbrowser-firefox

# Ejecutar clase específica
mvn test -Dtest=LoginTests
mvn test -Dtest=RegistrationTests

# Ejecutar método específico
mvn test -Dtest=LoginTests#testLoginExitoso

# Ejecutar con parámetros
mvn test -Dbrowser=firefox -Denv=staging
```

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

### 6. Configuración de Datos de Prueba

#### Archivos CSV
Ubicación: `test-data/`
- `login_data.csv` - Datos para tests de login
- `registration_data.csv` - Datos para tests de registro

#### Formato CSV
```csv
email,password,expectedResult
user@test.com,Password123!,success
invalid@test.com,wrongpass,error
```

#### Archivos Excel (Opcional)
```java
// Leer desde Excel
Object[][] data = DataReader.readDataFromExcel("test-data/users.xlsx", "LoginData");
```

### 7. Configuración de Reportes

#### ExtentReports
- Se generan automáticamente en `reports/`
- Formato HTML con gráficos y detalles
- Incluyen capturas de pantalla en errores

#### Configurar Reportes Personalizados
```java
// En ExtentReportManager.java
sparkReporter.config().setReportName("Mi Proyecto de Pruebas");
sparkReporter.config().setDocumentTitle("Reporte de Automatización");
```

### 8. Solución de Problemas Comunes

#### Error: "Driver executable not found"
```bash
# Solución 1: Verificar WebDriverManager
mvn dependency:tree | grep webdrivermanager

# Solución 2: Descargar driver manualmente y configurar PATH
export PATH=$PATH:/path/to/drivers
```

#### Error: "Tests not found"
```bash
# Verificar estructura de paquetes
src/test/java/com/abp/tests/

# Verificar annotations de TestNG
@Test, @BeforeMethod, @AfterMethod
```

#### Error: "Port already in use" (para Selenium Grid)
```bash
# Matar procesos de Selenium
taskkill /f /im chromedriver.exe
taskkill /f /im geckodriver.exe
```

### 9. Mejores Prácticas

#### Configuración Local de Desarrollo

1. **Variables de Entorno**
```bash
# Windows
set JAVA_HOME=C:\Program Files\Java\jdk-11
set MAVEN_HOME=C:\Program Files\Apache\maven-3.8.6

# Linux/Mac
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk
export MAVEN_HOME=/usr/local/maven
```

2. **IDE Configuration (VS Code)**
```json
// settings.json
{
    "java.home": "/path/to/java11",
    "maven.executable.path": "/path/to/mvn",
    "java.test.defaultConfig": "testng",
    "java.test.config": {
        "workingDirectory": "${workspaceFolder}"
    }
}
```

#### Configuración para CI/CD

```yaml
# GitHub Actions example
- name: Setup Java
  uses: actions/setup-java@v2
  with:
    java-version: '11'
    
- name: Install dependencies
  run: mvn clean install
  
- name: Run tests
  run: mvn test
  
- name: Upload reports
  uses: actions/upload-artifact@v2
  with:
    name: test-reports
    path: reports/
```

### 10. Comandos Útiles de Desarrollo

```bash
# Limpiar proyecto
mvn clean

# Compilar sin ejecutar tests
mvn compile test-compile

# Verificar dependencias
mvn dependency:analyze

# Generar documentación
mvn javadoc:javadoc

# Verificar actualizaciones
mvn versions:display-dependency-updates

# Ejecutar con debug
mvn test -Dmaven.surefire.debug

# Generar reporte de cobertura (si configurado)
mvn jacoco:report
```
