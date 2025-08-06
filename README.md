# 🎯 Proyecto ABP - Automatización de Pruebas con Selenium

[![Java](https://img.shields.io/badge/Java-11-orange.svg)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.15.0-brightgreen.svg)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.8.0-red.svg)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9-blue.svg)](https://maven.apache.org/)

## 📋 Descripción del Proyecto

Proyecto académico de **Aprendizaje Basado en Problemas (ABP)** enfocado en la implementación de pruebas automatizadas funcionales utilizando **Selenium WebDriver** y **TestNG**. El proyecto automatiza escenarios críticos de la aplicación web **ParaBank** de Parasoft, cumpliendo con los estándares de calidad en testing de software y las mejores prácticas de automatización.

### 🎯 Objetivos ABP
- Implementar un framework de automatización robusto y escalable
- Aplicar patrones de diseño como **Page Object Model (POM)**
- Generar evidencias completas de ejecución (reportes y screenshots)
- Realizar pruebas **cross-browser** para garantizar compatibilidad
- Desarrollar habilidades en herramientas industriales de QA

## 🧪 Escenarios de Prueba Implementados

### 📝 **Módulo de Autenticación (3 escenarios)**
1. **✅ Login Exitoso** - Validación de acceso con credenciales válidas
2. **❌ Login Fallido** - Verificación de manejo de credenciales inválidas
3. **⚠️ Validación de Campos** - Control de campos obligatorios vacíos

### 👤 **Módulo de Registro (3 escenarios)**
1. **✅ Registro Exitoso** - Creación exitosa de nueva cuenta
2. **📋 Campos Obligatorios** - Validación de datos requeridos
3. **🔐 Validación de Contraseñas** - Verificación de coincidencia de passwords

**Total: 6 escenarios automatizados** *(cumple requisito ABP de 4-6 escenarios)*

## 🛠️ Stack Tecnológico

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| **Java** | 11 | Lenguaje de programación base |
| **Selenium WebDriver** | 4.15.0 | Automatización de navegadores web |
| **TestNG** | 7.8.0 | Framework de testing y assertions |
| **WebDriverManager** | 5.6.2 | Gestión automática de drivers |
| **ExtentReports** | 5.1.1 | Generación de reportes HTML interactivos |
| **Maven** | 3.9+ | Gestión de dependencias y ciclo de vida |

## 🏗️ Arquitectura del Proyecto

```
📁 Proyecto_ABP/
├── 📁 src/test/java/com/abp/
│   ├── 📁 tests/
│   │   ├── BaseTest.java           # Configuración base común
│   │   ├── LoginTest.java          # 3 casos de prueba de login
│   │   ├── RegisterTest.java       # 3 casos de prueba de registro
│   │   └── 📁 pom/
│   │       ├── BasePage.java       # Funcionalidades base POM
│   │       ├── LoginPage.java      # Page Object para autenticación
│   │       └── RegisterPage.java   # Page Object para registro
│   └── 📁 utils/
│       ├── WebDriverConfig.java    # Configuración de WebDrivers
│       └── ExtentReportManager.java # Gestión de reportes
├── 📁 reports/                     # Reportes HTML generados
├── 📁 screenshots/                 # Screenshots capturados en los tests
├── testng.xml                      # Configuración de suites de prueba
└── pom.xml                         # Configuración Maven
```

## 🚀 Guía de Ejecución

### ⚡ Ejecución Rápida
```bash
# Ejecutar todos los tests (6 escenarios en Chrome y Firefox)
mvn clean test
```

### 🎯 Ejecuciones Específicas
```bash
# Solo tests de Login
mvn test -Dtest=LoginTest

# Solo tests de Registro  
mvn test -Dtest=RegisterTest

# Test específico
mvn test -Dtest=LoginTest#testLoginExitoso

# Solo en Chrome
mvn test -Dtest="*Test" -Dbrowser=chrome

# Solo en Firefox
mvn test -Dtest="*Test" -Dbrowser=firefox
```

### 📊 Resultados de Ejecución
Después de ejecutar las pruebas, encontrarás:
- **📈 Reportes HTML**: `reports/ExtentReport_YYYYMMDD_HHMMSS.html`
- **📸 Screenshots**: Automáticos por cada test

## 🌐 Entorno de Pruebas

### 🏦 ParaBank Demo Application
- **🔗 URL**: [https://parabank.parasoft.com/parabank/index.htm](https://parabank.parasoft.com/parabank/index.htm)
- **👤 Usuario de prueba**: `john`
- **🔑 Contraseña**: `demo`
- **📝 Registro**: Utiliza datos únicos generados automáticamente

### 🌍 Navegadores Soportados
- **Google Chrome** (última versión estable)
- **Mozilla Firefox** (última versión estable)
- **Gestión automática** de WebDrivers (no requiere descarga manual)

## 📋 Cumplimiento de Requisitos ABP

| Requisito | Estado | Detalles |
|-----------|--------|----------|
| **4-6 Escenarios automatizados** | ✅ **6/6** | 3 Login + 3 Registro |
| **Cross-browser testing** | ✅ | Chrome y Firefox |
| **Page Object Model** | ✅ | 3 clases POM implementadas |
| **Evidencias de ejecución** | ✅ | Screenshots .png + Reportes HTML |
| **Gestión de datos** | ✅ | Datos hardcoded y generados |
| **Framework robusto** | ✅ | Selenium + TestNG + Maven |

## 📚 Patrones y Buenas Prácticas

### 🎨 **Page Object Model (POM)**
- **BasePage**: Funcionalidades comunes (waits, screenshots, etc.)
- **LoginPage**: Elementos y acciones específicas de autenticación
- **RegisterPage**: Elementos y acciones específicas de registro

### 🔧 **Configuración Modular**
- **WebDriverConfig**: Gestión centralizada de WebDrivers
- **ExtentReportManager**: Reportes unificados y configurables
- **BaseTest**: Setup/teardown común para todas las pruebas

### 📊 **Reporting Avanzado**
- Reportes HTML interactivos con gráficos
- Screenshots de evidencia por cada test
- Logs detallados paso a paso

## 🔧 Requisitos del Sistema

### 📋 **Prerequisitos**
- **Java JDK 11+** - [Descargar OpenJDK](https://adoptium.net/)
- **Apache Maven 3.9+** - [Descargar Maven](https://maven.apache.org/download.cgi)
- **Google Chrome** (última versión)
- **Mozilla Firefox** (última versión)

### ✅ **Verificación de Instalación**
```bash
# Verificar Java
java -version
javac -version

# Verificar Maven
mvn -version

# Verificar dependencias del proyecto
mvn dependency:resolve
```

## 📖 Documentación Adicional

- **[SETUP_GUIDE.md](./SETUP_GUIDE.md)** - Guía detallada de instalación y configuración
- **[Reportes de Ejemplo](./reports/)** - Ejemplos de reportes generados
- **[Screenshots de Evidencia](./screenshots/)** - Screenshots de Evidencia

## 🤝 Contribuciones

Este proyecto fue desarrollado como parte del programa ABP (Aprendizaje Basado en Problemas) enfocado en:
- Automatización de pruebas funcionales
- Implementación de frameworks de testing
- Aplicación de patrones de diseño en QA
- Generación de evidencias y reportes profesionales

## 📄 Licencia

Este proyecto es de uso académico y educativo, desarrollado para fines de aprendizaje en automatización de pruebas de software.

---

## 🚀 Quick Start

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/panxor19/Proyecto_ABP.git
   cd Proyecto_ABP
   ```

2. **Instalar dependencias**
   ```bash
   mvn clean install
   ```

3. **Ejecutar pruebas**
   ```bash
   mvn clean test
   ```

4. **Ver resultados**
   - Abrir `reports/ExtentReport_*.html` en tu navegador
   - Revisar screenshots en la carpeta `screenshots/`

**¡Proyecto listo para demostrar cumplimiento ABP! 🎉**
