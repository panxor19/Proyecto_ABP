# Proyecto Selenium ABP - ParaBank

## Descripción
Proyecto de automatización funcional con Selenium WebDriver y TestNG para ParaBank, cumpliendo exactamente con los requerimientos ABP de 4-6 escenarios automatizados.

## Escenarios Implementados (6 total)

### Tests de Login (3)
1. **Login Exitoso** - Verificar acceso con credenciales válidas
2. **Login Fallido** - Verificar error con credenciales inválidas  
3. **Campos Vacíos** - Verificar validación de campos obligatorios

### Tests de Registro (3)
1. **Registro Exitoso** - Verificar registro con datos válidos
2. **Campos Obligatorios** - Verificar validación de campos vacíos
3. **Contraseñas No Coinciden** - Verificar validación de confirmación

## Tecnologías
- Java 11
- Selenium WebDriver 4.15.0
- TestNG 7.8.0
- WebDriverManager 5.6.2
- ExtentReports 5.1.1
- Monte Screen Recorder (videos .mov)

## Ejecución
```bash
mvn clean test
```

## Evidencias
- **Reportes HTML**: `reports/ExtentReport_*.html`
- **Videos**: `videos/Test_*.mov`
- **Screenshots**: Automáticas en errores

## Sitio de Pruebas
ParaBank: https://parabank.parasoft.com/parabank/index.htm
- Usuario: `john` / Password: `demo`

## Cumplimiento ABP
- ✅ 6 escenarios automatizados (cumple 4-6 requerido)
- ✅ Cross-browser (Chrome/Firefox)  
- ✅ POM implementado (3 clases)
- ✅ Videos de evidencia (.mov)
- ✅ Reportes HTML automáticos
