# INSTRUCCIONES DE EJECUCIÓN RÁPIDA
# Guía paso a paso para ejecutar el proyecto Selenium ABP

Write-Host "=== PROYECTO SELENIUM ABP - GUÍA DE EJECUCIÓN RÁPIDA ===" -ForegroundColor Green
Write-Host ""

Write-Host "📋 OPCIONES DE EJECUCIÓN:" -ForegroundColor Cyan
Write-Host "   1. Instalación automática completa (recomendado)" -ForegroundColor White
Write-Host "   2. Instalación manual paso a paso" -ForegroundColor White
Write-Host "   3. Solo ejecutar tests (si ya tienes Java/Maven)" -ForegroundColor White
Write-Host ""

$opcion = Read-Host "Selecciona una opción (1-3)"

switch ($opcion) {
    "1" {
        Write-Host ""
        Write-Host "🔧 INSTALACIÓN AUTOMÁTICA COMPLETA" -ForegroundColor Yellow
        Write-Host "   Esta opción instalará Java 11 y Maven automáticamente" -ForegroundColor White
        Write-Host "   Requiere permisos de administrador" -ForegroundColor White
        Write-Host ""
        
        $confirmar = Read-Host "¿Continuar con instalación automática? (s/n)"
        if ($confirmar -eq "s" -or $confirmar -eq "S") {
            Write-Host "▶️  Ejecutando instalación automática..." -ForegroundColor Cyan
            Start-Process PowerShell -ArgumentList "-ExecutionPolicy Bypass -File `"$PWD\instalar-herramientas.ps1`"" -Verb RunAs -Wait
            Write-Host "✅ Instalación completada" -ForegroundColor Green
            Write-Host "   Reinicia VS Code y ejecuta: mvn clean test" -ForegroundColor White
        }
    }
    
    "2" {
        Write-Host ""
        Write-Host "🛠️  INSTALACIÓN MANUAL PASO A PASO" -ForegroundColor Yellow
        Write-Host ""
        Write-Host "PASO 1: Instalar Chocolatey (si no lo tienes)" -ForegroundColor Cyan
        Write-Host "   Ejecuta en PowerShell como administrador:" -ForegroundColor White
        Write-Host "   Set-ExecutionPolicy Bypass -Scope Process -Force; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))" -ForegroundColor Gray
        Write-Host ""
        Write-Host "PASO 2: Instalar Java 11" -ForegroundColor Cyan
        Write-Host "   choco install openjdk11 -y" -ForegroundColor Gray
        Write-Host ""
        Write-Host "PASO 3: Instalar Maven" -ForegroundColor Cyan
        Write-Host "   choco install maven -y" -ForegroundColor Gray
        Write-Host ""
        Write-Host "PASO 4: Reiniciar PowerShell y ejecutar tests" -ForegroundColor Cyan
        Write-Host "   mvn clean test" -ForegroundColor Gray
        Write-Host ""
        Write-Host "ALTERNATIVAS SIN CHOCOLATEY:" -ForegroundColor Yellow
        Write-Host "   • winget install Microsoft.OpenJDK.11" -ForegroundColor White
        Write-Host "   • winget install Apache.Maven" -ForegroundColor White
    }
    
    "3" {
        Write-Host ""
        Write-Host "🚀 EJECUTAR SOLO TESTS" -ForegroundColor Yellow
        Write-Host "   Verificando prerrequisitos..." -ForegroundColor White
        
        # Verificar Java
        try {
            $javaVersion = java -version 2>&1
            Write-Host "   ✅ Java: $($javaVersion[0])" -ForegroundColor Green
        } catch {
            Write-Host "   ❌ Java no encontrado - instala Java 11 primero" -ForegroundColor Red
            exit 1
        }
        
        # Verificar Maven
        try {
            $mavenVersion = mvn -version 2>&1 | Select-Object -First 1
            Write-Host "   ✅ Maven: $mavenVersion" -ForegroundColor Green
        } catch {
            Write-Host "   ❌ Maven no encontrado - instala Maven primero" -ForegroundColor Red
            exit 1
        }
        
        Write-Host ""
        Write-Host "🎯 OPCIONES DE EJECUCIÓN:" -ForegroundColor Cyan
        Write-Host "   1. Todos los tests" -ForegroundColor White
        Write-Host "   2. Solo tests de login" -ForegroundColor White
        Write-Host "   3. Solo tests de registro" -ForegroundColor White
        Write-Host "   4. Test específico por navegador" -ForegroundColor White
        
        $testOpcion = Read-Host "Selecciona opción de test (1-4)"
        
        switch ($testOpcion) {
            "1" {
                Write-Host "▶️  Ejecutando todos los tests..." -ForegroundColor Cyan
                mvn clean test
            }
            "2" {
                Write-Host "▶️  Ejecutando tests de login..." -ForegroundColor Cyan
                mvn test -Dtest=LoginTests
            }
            "3" {
                Write-Host "▶️  Ejecutando tests de registro..." -ForegroundColor Cyan
                mvn test -Dtest=RegistrationTests
            }
            "4" {
                $navegador = Read-Host "Especifica navegador (chrome/firefox)"
                Write-Host "▶️  Ejecutando tests en $navegador..." -ForegroundColor Cyan
                mvn test -Dbrowser=$navegador
            }
        }
        
        Write-Host ""
        Write-Host "📊 REPORTES GENERADOS:" -ForegroundColor Yellow
        Write-Host "   • HTML Report: reports/extent-report.html" -ForegroundColor White
        Write-Host "   • TestNG Report: target/surefire-reports/index.html" -ForegroundColor White
        Write-Host "   • Screenshots: reports/screenshots/" -ForegroundColor White
    }
    
    default {
        Write-Host ""
        Write-Host "❌ Opción no válida" -ForegroundColor Red
        Write-Host "   Ejecuta el script de nuevo y selecciona 1, 2 o 3" -ForegroundColor White
    }
}

Write-Host ""
Write-Host "📚 DOCUMENTACIÓN ADICIONAL:" -ForegroundColor Cyan
Write-Host "   • README.md - Descripción general del proyecto" -ForegroundColor White
Write-Host "   • SETUP_GUIDE.md - Guía detallada de configuración" -ForegroundColor White
Write-Host "   • PROYECTO_COMPLETO.md - Documentación técnica completa" -ForegroundColor White
Write-Host ""
Write-Host "🌐 SITIO DE PRUEBAS:" -ForegroundColor Cyan
Write-Host "   https://parabank.parasoft.com/parabank/index.htm" -ForegroundColor White
Write-Host ""
Write-Host "👨‍💻 CREDENCIALES DE PRUEBA:" -ForegroundColor Cyan
Write-Host "   Usuario: john / Contraseña: demo" -ForegroundColor White
Write-Host "   Usuario: admin / Contraseña: admin" -ForegroundColor White
Write-Host ""
Write-Host "Presiona cualquier tecla para salir..." -ForegroundColor Yellow
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
