# Script de instalación rápida para el proyecto Selenium ABP
# Instala automáticamente Java y Maven si no están presentes

Write-Host "=== INSTALACIÓN RÁPIDA - PROYECTO SELENIUM ABP ===" -ForegroundColor Green
Write-Host ""

# Verificar si se ejecuta como administrador
$isAdmin = ([Security.Principal.WindowsPrincipal] [Security.Principal.WindowsIdentity]::GetCurrent()).IsInRole([Security.Principal.WindowsBuiltInRole] "Administrator")

if (-not $isAdmin) {
    Write-Host "⚠️  Este script requiere permisos de administrador." -ForegroundColor Yellow
    Write-Host "   Ejecuta PowerShell como administrador y vuelve a intentar." -ForegroundColor White
    pause
    exit 1
}

Write-Host "🔍 Verificando instalaciones existentes..." -ForegroundColor Cyan

# Verificar Java
$javaInstalled = $false
try {
    $javaVersion = java -version 2>&1
    Write-Host "✅ Java ya está instalado: $($javaVersion[0])" -ForegroundColor Green
    $javaInstalled = $true
} catch {
    Write-Host "❌ Java no encontrado" -ForegroundColor Red
}

# Verificar Maven
$mavenInstalled = $false
try {
    $mavenVersion = mvn -version 2>&1 | Select-Object -First 1
    Write-Host "✅ Maven ya está instalado: $mavenVersion" -ForegroundColor Green
    $mavenInstalled = $true
} catch {
    Write-Host "❌ Maven no encontrado" -ForegroundColor Red
}

# Verificar Chocolatey
$chocoInstalled = $false
try {
    choco --version | Out-Null
    Write-Host "✅ Chocolatey ya está instalado" -ForegroundColor Green
    $chocoInstalled = $true
} catch {
    Write-Host "❌ Chocolatey no encontrado" -ForegroundColor Red
}

Write-Host ""

# Instalar Chocolatey si no está presente
if (-not $chocoInstalled) {
    Write-Host "📦 Instalando Chocolatey..." -ForegroundColor Yellow
    Set-ExecutionPolicy Bypass -Scope Process -Force
    [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072
    iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "✅ Chocolatey instalado correctamente" -ForegroundColor Green
        $chocoInstalled = $true
    } else {
        Write-Host "❌ Error instalando Chocolatey" -ForegroundColor Red
        exit 1
    }
}

# Instalar Java si no está presente
if (-not $javaInstalled) {
    Write-Host "☕ Instalando Java 11..." -ForegroundColor Yellow
    choco install openjdk11 -y
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "✅ Java instalado correctamente" -ForegroundColor Green
        # Refrescar variables de entorno
        $env:Path = [System.Environment]::GetEnvironmentVariable("Path","Machine") + ";" + [System.Environment]::GetEnvironmentVariable("Path","User")
    } else {
        Write-Host "❌ Error instalando Java" -ForegroundColor Red
        exit 1
    }
}

# Instalar Maven si no está presente
if (-not $mavenInstalled) {
    Write-Host "🔨 Instalando Maven..." -ForegroundColor Yellow
    choco install maven -y
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "✅ Maven instalado correctamente" -ForegroundColor Green
        # Refrescar variables de entorno
        $env:Path = [System.Environment]::GetEnvironmentVariable("Path","Machine") + ";" + [System.Environment]::GetEnvironmentVariable("Path","User")
    } else {
        Write-Host "❌ Error instalando Maven" -ForegroundColor Red
        exit 1
    }
}

Write-Host ""
Write-Host "🎉 ¡Instalación completada!" -ForegroundColor Green
Write-Host ""
Write-Host "📋 Resumen de herramientas instaladas:" -ForegroundColor Cyan
Write-Host "   ✅ Java (OpenJDK 11)" -ForegroundColor White
Write-Host "   ✅ Maven (Build tool)" -ForegroundColor White
Write-Host "   ✅ Chocolatey (Package manager)" -ForegroundColor White
Write-Host ""
Write-Host "🚀 Próximos pasos:" -ForegroundColor Yellow
Write-Host "   1. Reinicia PowerShell o VS Code" -ForegroundColor White
Write-Host "   2. Ejecuta: .\ejecutar-proyecto.ps1" -ForegroundColor White
Write-Host "   3. O manualmente: mvn clean test" -ForegroundColor White
Write-Host ""
Write-Host "🌐 Sitio de pruebas: https://parabank.parasoft.com/parabank/index.htm" -ForegroundColor Cyan
Write-Host ""
Write-Host "Presiona cualquier tecla para continuar..." -ForegroundColor Yellow
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
