# Script de PowerShell para ejecutar el proyecto Selenium ABP
# Proyecto: Automatización ParaBank con Selenium WebDriver y TestNG

Write-Host "=== PROYECTO SELENIUM ABP - PARABANK ===" -ForegroundColor Green
Write-Host "Automatización funcional con Selenium WebDriver y TestNG" -ForegroundColor Cyan
Write-Host ""

# Verificar prerequisitos
Write-Host "🔍 Verificando prerequisitos..." -ForegroundColor Yellow

# Verificar Java
try {
    $javaVersion = java -version 2>&1
    Write-Host "✅ Java encontrado: $($javaVersion[0])" -ForegroundColor Green
} catch {
    Write-Host "❌ Java no encontrado. Instalar Java 11+:" -ForegroundColor Red
    Write-Host "   winget install Microsoft.OpenJDK.11" -ForegroundColor White
    exit 1
}

# Verificar Maven
try {
    $mavenVersion = mvn -version 2>&1 | Select-Object -First 1
    Write-Host "✅ Maven encontrado: $mavenVersion" -ForegroundColor Green
} catch {
    Write-Host "❌ Maven no encontrado. Instalar Maven:" -ForegroundColor Red
    Write-Host "   choco install maven" -ForegroundColor White
    Write-Host "   O: winget install Maven.Maven" -ForegroundColor White
    exit 1
}

Write-Host ""
Write-Host "🚀 Iniciando ejecución del proyecto..." -ForegroundColor Yellow

# Compilar proyecto
Write-Host ""
Write-Host "📦 Compilando proyecto..." -ForegroundColor Cyan
mvn clean compile

if ($LASTEXITCODE -eq 0) {
    Write-Host "✅ Compilación exitosa" -ForegroundColor Green
} else {
    Write-Host "❌ Error en compilación" -ForegroundColor Red
    exit 1
}

# Mostrar opciones de ejecución
Write-Host ""
Write-Host "🎯 Opciones de ejecución disponibles:" -ForegroundColor Yellow
Write-Host ""
Write-Host "1. Ejecutar TODAS las pruebas:" -ForegroundColor White
Write-Host "   mvn test" -ForegroundColor Gray
Write-Host ""
Write-Host "2. Ejecutar primera prueba (Lección 1):" -ForegroundColor White
Write-Host "   mvn test -Dtest=FirstSeleniumTest" -ForegroundColor Gray
Write-Host ""
Write-Host "3. Ejecutar tests de Login:" -ForegroundColor White
Write-Host "   mvn test -Dtest=LoginTests" -ForegroundColor Gray
Write-Host ""
Write-Host "4. Ejecutar tests de Registro:" -ForegroundColor White
Write-Host "   mvn test -Dtest=RegistrationTests" -ForegroundColor Gray
Write-Host ""
Write-Host "5. Ejecutar en Chrome:" -ForegroundColor White
Write-Host "   mvn test -Dbrowser=chrome" -ForegroundColor Gray
Write-Host ""
Write-Host "6. Ejecutar en Firefox:" -ForegroundColor White
Write-Host "   mvn test -Dbrowser=firefox" -ForegroundColor Gray
Write-Host ""

# Preguntar qué ejecutar
$choice = Read-Host "¿Qué deseas ejecutar? (1-6, o ENTER para todas las pruebas)"

switch ($choice) {
    "1" { mvn test }
    "2" { mvn test -Dtest=FirstSeleniumTest }
    "3" { mvn test -Dtest=LoginTests }
    "4" { mvn test -Dtest=RegistrationTests }
    "5" { mvn test -Dbrowser=chrome }
    "6" { mvn test -Dbrowser=firefox }
    default { mvn test }
}

Write-Host ""
if ($LASTEXITCODE -eq 0) {
    Write-Host "🎉 Pruebas ejecutadas exitosamente!" -ForegroundColor Green
    Write-Host ""
    Write-Host "📊 Revisa los resultados en:" -ForegroundColor Cyan
    Write-Host "   - Reportes HTML: ./reports/" -ForegroundColor White
    Write-Host "   - Capturas de pantalla: ./screenshots/" -ForegroundColor White
    Write-Host "   - Logs en consola" -ForegroundColor White
} else {
    Write-Host "❌ Algunas pruebas fallaron. Revisa los logs para más detalles." -ForegroundColor Red
}

Write-Host ""
Write-Host "🌐 Sitio de pruebas: https://parabank.parasoft.com/parabank/index.htm" -ForegroundColor Cyan
Write-Host "📧 Credenciales de prueba: john/demo, admin/admin" -ForegroundColor Cyan
Write-Host ""
Write-Host "Presiona cualquier tecla para continuar..." -ForegroundColor Yellow
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
