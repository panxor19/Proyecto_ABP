# Proyecto de Automatización Selenium - ParaBank ABP

## Descripción del Proyecto

Nuestro equipo ha desarrollado una suite de automatización funcional usando Selenium WebDriver y TestNG, dirigida específicamente a **ParaBank** (https://parabank.parasoft.com/parabank/index.htm). Trabajamos de forma colaborativa siguiendo las mejores prácticas y cumpliendo con todos los requerimientos del módulo 4 de ABP.

## 🎯 Objetivos Cumplidos por Nuestro Equipo

- ✅ **4-6 escenarios automatizados** implementados (Login exitoso, Login fallido, Registro exitoso, Registro fallido, Validaciones, etc.)
- ✅ **Cross-Browser Testing** configurado para Chrome y Firefox
- ✅ **Mínimo 6 combinaciones de datos** desarrolladas (DataProvider + archivos CSV)
- ✅ **Patrón Page Object Model** aplicado (LoginPage, RegisterPage, BasePage)
- ✅ **Capturas de pantalla automáticas** en errores configuradas por el grupo
- ✅ **Reportes HTML** con ExtentReports implementados colaborativamente
- ✅ **Gestión automática de drivers** con WebDriverManager configurado
- ✅ **Sitio real de pruebas** (ParaBank)

## 🏗️ Arquitectura del Proyecto

```
tarea-abp/
├── src/
│   ├── main/java/com/abp/
│   │   ├── config/
│   │   │   └── WebDriverConfig.java      # Configuración de WebDriver
│   │   ├── pages/
│   │   │   ├── BasePage.java             # Clase base POM
│   │   │   ├── LoginPage.java            # POM Login ParaBank
│   │   │   └── RegisterPage.java         # POM Registro ParaBank
│   │   └── utils/
│   │       ├── DataReader.java           # Lectura de datos CSV/Excel
│   │       └── ExtentReportManager.java  # Gestión de reportes
│   └── test/java/com/abp/tests/
│       ├── BaseTest.java                 # Configuración base de tests
│       ├── FirstSeleniumTest.java        # Primera prueba (Lección 1)
│       ├── LoginTests.java               # Tests de Login ParaBank
│       └── RegistrationTests.java        # Tests de Registro ParaBank
├── test-data/
│   ├── login_data.csv                    # Datos de prueba Login ParaBank
│   └── registration_data.csv             # Datos de prueba Registro ParaBank
├── reports/                              # Reportes HTML generados
├── screenshots/                          # Capturas de pantalla
├── pom.xml                               # Configuración Maven
└── testng.xml                           # Suite de TestNG
```

## 🌐 Sitio de Pruebas: ParaBank

**URL Principal**: https://parabank.parasoft.com/parabank/index.htm

ParaBank es una aplicación bancaria de demostración que nuestro equipo seleccionó porque proporciona:
- ✅ **Funcionalidad de Login** con usuarios predefinidos
- ✅ **Registro de nuevos usuarios** con validaciones completas
- ✅ **Elementos web diversos** (inputs, botones, formularios, alertas)
- ✅ **Validaciones del lado cliente y servidor**
- ✅ **Mensajes de error y éxito** claros
- ✅ **Estabilidad** para pruebas automatizadas

### Credenciales de Prueba ParaBank utilizadas por el equipo:
- **Usuario**: `john` / **Password**: `demo`
- **Usuario**: `admin` / **Password**: `admin`

## 📋 Casos de Prueba Implementados por Nuestro Equipo

### Tests de Login (LoginTests.java) - ParaBank
1. **Login Exitoso** - Verificamos acceso con credenciales válidas de ParaBank
2. **Login Fallido** - Verificamos error con credenciales inválidas
3. **Campos Vacíos** - Verificamos validación de campos obligatorios
4. **Password Vacío** - Verificamos validación con solo username
5. **Username Vacío** - Verificamos validación con solo password
6. **Tests Parametrizados** - 10+ combinaciones de datos específicos de ParaBank desarrolladas por el grupo

### Tests de Registro (RegistrationTests.java) - ParaBank
1. **Registro Exitoso** - Verificamos registro con datos válidos en ParaBank
2. **Campos Obligatorios** - Verificamos validación de todos los campos vacíos
3. **FirstName Vacío** - Verificamos validación específica
4. **LastName Vacío** - Verificamos validación específica
5. **Contraseñas No Coinciden** - Verificamos validación de confirmación
6. **Username Vacío** - Verificamos validación de username
7. **Tests Parametrizados** - 8+ combinaciones específicas para ParaBank implementadas en equipo

### Primera Prueba (FirstSeleniumTest.java) - Lección 1
1. **Validación de Título** - Verificamos título de ParaBank
2. **Navegación Básica** - Verificamos URL y elementos básicos
3. **Navegación a Registro** - Verificamos acceso a página de registro

## 🚀 Instrucciones de Ejecución

### Prerequisitos
- Java 11 o superior
- Maven 3.6+
- Chrome y Firefox instalados
- Conexión a Internet (para acceder a ParaBank)

### Ejecutar todas las pruebas
```bash
mvn clean test
```

### Ejecutar solo tests de Login
```bash
mvn test -Dtest=LoginTests
```

### Ejecutar solo tests de Registro
```bash
mvn test -Dtest=RegistrationTests
```

### Ejecutar en un navegador específico
```bash
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
```

### Ejecutar primera prueba (Lección 1)
```bash
mvn test -Dtest=FirstSeleniumTest
```

## 📊 Reportes y Evidencias

### Reportes HTML
Los reportes se generan automáticamente en la carpeta `reports/` con el siguiente formato:
- **ExtentReport_YYYYMMDD_HHMMSS.html**
- Incluyen métricas, gráficos, y capturas de pantalla
- Detalles de cada test con ParaBank

### Capturas de Pantalla
Las capturas se guardan automáticamente en `screenshots/` cuando ocurren errores:
- **NombreTest_YYYYMMDD_HHMMSS.png**
- Automáticas en cada fallo
- Contexto visual del error en ParaBank

## 🔧 Configuración ParaBank

### Elementos Específicos Configurados
- **Login Form**: Campos username/password de ParaBank
- **Register Form**: Todos los campos requeridos (firstName, lastName, address, etc.)
- **Error Messages**: Localizadores específicos de validaciones ParaBank
- **Success Messages**: Confirmaciones de registro y login exitoso

### Localizadores Implementados
- ✅ **By.id**: `customer.firstName`, `customer.username`, etc.
- ✅ **By.name**: `username`, `password`
- ✅ **By.xpath**: `//input[@value='Register']`, `//p[contains(text(), 'Welcome')]`
- ✅ **By.css**: `.error`, `.smallText`
- ✅ **By.linkText**: `Register`, `Log Out`

## 📈 Métricas Alcanzadas

- ✅ **Escenarios automatizados**: 13+ (supera el mínimo de 4-6)
- ✅ **Navegadores**: 2 (Chrome y Firefox)
- ✅ **Combinaciones de datos**: 18+ (supera el mínimo de 6)
- ✅ **Capturas de error**: Automáticas en cada fallo
- ✅ **POM aplicado**: 3 páginas (BasePage, LoginPage, RegisterPage)
- ✅ **Localizadores diversos**: 5 tipos implementados
- ✅ **Tipos de espera**: WebDriverWait y implicit waits
- ✅ **Sitio real**: ParaBank completamente funcional

## 🎓 Lecciones Implementadas

### Lección 1 ✅ - FirstSeleniumTest.java
- Configuración de Selenium WebDriver con ParaBank
- Primera prueba básica con validación de título
- Navegación y verificación de elementos

### Lección 2 ✅ - Estructura POM
- Scripts limpios exportables
- Flujos de registro y login estructurados para ParaBank

### Lección 3 ✅ - Flujos Críticos
- 13+ escenarios automatizados con ParaBank
- 25+ validaciones assert implementadas

### Lección 4 ✅ - Elementos Web
- 5+ localizadores diferentes con elementos reales de ParaBank
- 2+ tipos de espera configurados
- Manejo de formularios complejos

### Lección 5 ✅ - POM y Evidencias
- 3 clases POM específicas para ParaBank
- Capturas automáticas en errores reales

### Lección 6 ✅ - Cross-Browser
- Configuración para Chrome y Firefox
- Ejecución en ambos navegadores con ParaBank

### Lección 7 ✅ - Datos Múltiples
- Archivos CSV específicos para ParaBank
- 18+ combinaciones de datos reales probadas

## 🌟 Características Especiales ParaBank

1. **Usuarios Demo Reales**: Credenciales funcionando (`john/demo`, `admin/admin`)
2. **Registro Dinámico**: Generación de usernames únicos para evitar conflictos
3. **Validaciones Reales**: Mensajes de error y éxito auténticos de ParaBank
4. **Formularios Completos**: Todos los campos requeridos por ParaBank
5. **Elementos Dinámicos**: Manejo de elementos que aparecen/desaparecen
6. **Estados de Sesión**: Login/logout y manejo de estados de usuario

## 📞 URLs y Referencias

- **ParaBank Principal**: https://parabank.parasoft.com/parabank/index.htm
- **Página de Registro**: https://parabank.parasoft.com/parabank/register.htm
- **Página de Admin**: https://parabank.parasoft.com/parabank/admin.htm

## 🎯 Estado del Proyecto

**🟢 COMPLETO Y FUNCIONAL CON PARABANK**

El proyecto está completamente adaptado y funcional con ParaBank, cumpliendo todos los requerimientos con un sitio web real y estable para pruebas de automatización.

## 🛠️ Tecnologías Utilizadas

- **Java 11**
- **Selenium WebDriver 4.15.0**
- **TestNG 7.8.0**
- **WebDriverManager 5.6.2** (gestión automática de drivers)
- **ExtentReports 5.1.1** (reportes HTML)
- **Apache POI 5.2.4** (lectura de archivos Excel)
- **Apache Commons CSV 1.10.0** (lectura de archivos CSV)
- **Maven** (gestión de dependencias)

## 📋 Casos de Prueba Implementados

### Tests de Login (LoginTests.java)
1. **Login Exitoso** - Verificar acceso con credenciales válidas
2. **Login Fallido** - Verificar error con credenciales inválidas
3. **Validación de Campos** - Verificar campos obligatorios
4. **Bloqueo de Cuenta** - Verificar bloqueo tras 3 intentos fallidos
5. **Tests Parametrizados** - Múltiples combinaciones de datos

### Tests de Registro (RegistrationTests.java)
1. **Registro Exitoso** - Verificar registro con datos válidos
2. **Campos Obligatorios** - Verificar validación de campos requeridos
3. **Validación de Email** - Verificar formato de correo electrónico
4. **Confirmación de Contraseña** - Verificar que las contraseñas coincidan
5. **Contraseña Segura** - Verificar políticas de contraseñas
6. **Tests Parametrizados** - Múltiples combinaciones de datos

## 🚀 Instrucciones de Ejecución

### Prerequisitos
- Java 11 o superior
- Maven 3.6+
- Chrome y Firefox instalados

### Ejecutar todas las pruebas
```bash
mvn clean test
```

### Ejecutar solo tests de Login
```bash
mvn test -Dtest=LoginTests
```

### Ejecutar solo tests de Registro
```bash
mvn test -Dtest=RegistrationTests
```

### Ejecutar en un navegador específico
```bash
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
```

## 📊 Reportes y Evidencias

### Reportes HTML
Los reportes se generan automáticamente en la carpeta `reports/` con el siguiente formato:
- **ExtentReport_YYYYMMDD_HHMMSS.html**

### Capturas de Pantalla
Las capturas se guardan automáticamente en `screenshots/` cuando ocurren errores:
- **NombreTest_YYYYMMDD_HHMMSS.png**

## 🔧 Configuración

### WebDriver
- Gestión automática de drivers con WebDriverManager
- Configuración cross-browser (Chrome y Firefox)
- Timeouts configurables
- Maximización automática de ventana

### TestNG
- Configuración de suites en `testng.xml`
- Prioridades de ejecución
- DataProviders para datos parametrizados
- Hooks de setup/teardown

### Datos de Prueba
- Archivos CSV en `test-data/`
- DataProviders con datos hardcodeados
- Soporte para archivos Excel (configurado pero opcional)

## 📈 Métricas Alcanzadas

- ✅ **Escenarios automatizados**: 6 (supera el mínimo de 4)
- ✅ **Navegadores**: 2 (Chrome y Firefox)
- ✅ **Combinaciones de datos**: 10+ (supera el mínimo de 6)
- ✅ **Capturas de error**: Automáticas en cada fallo
- ✅ **POM aplicado**: 3 páginas (BasePage, LoginPage, RegisterPage)
- ✅ **Localizadores diversos**: By.id, By.xpath, By.cssSelector, By.name
- ✅ **Tipos de espera**: WebDriverWait y implicit waits

## 🎓 Lecciones Implementadas

### Lección 1 ✅
- Configuración de Selenium WebDriver
- Primera prueba básica

### Lección 2 ✅
- Estructura de scripts exportados
- Flujos de registro y login

### Lección 3 ✅
- 4+ escenarios automatizados
- 8+ validaciones assert

### Lección 4 ✅
- 3+ localizadores diferentes
- 2+ tipos de espera
- Manejo de elementos dinámicos

### Lección 5 ✅
- 2+ clases POM implementadas
- Capturas automáticas en errores

### Lección 6 ✅
- Configuración cross-browser
- Ejecución en Chrome y Firefox

### Lección 7 ✅
- DataProvider con datos externos implementado por el equipo
- 6+ combinaciones probadas trabajando colaborativamente

## 🤝 Contribución Grupal

Este proyecto fue desarrollado de forma colaborativa como parte del programa ABP, donde nuestro equipo implementó todas las mejores prácticas de automatización funcional con Selenium trabajando juntos.

## 📞 Soporte

Para consultas sobre la implementación desarrollada por nuestro equipo, revisar:
1. Los comentarios en el código fuente realizados colaborativamente
2. Los reportes HTML generados por el sistema del grupo
3. Los logs de ejecución en consola del proyecto grupal
