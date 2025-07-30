# Videos de Evidencia - Proyecto Selenium ABP

## 📹 Grabaciones de Video Automatizadas por Nuestro Equipo

Este directorio contiene las grabaciones de video automáticas de todos los tests ejecutados como evidencia de funcionamiento del proyecto desarrollado colaborativamente por nuestro equipo.

### 🎥 Características de las Grabaciones implementadas:

- **Formato:** AVI con compresión optimizada configurada por el grupo
- **Calidad:** 24-bit color, 15 FPS establecida en equipo
- **Resolución:** Pantalla completa capturada automáticamente
- **Audio:** Sin audio (solo video de pantalla) según diseño del equipo

### 📁 Nomenclatura de Archivos desarrollada por el grupo:

```
Test_[ClaseTest]_[MetodoTest]_[Timestamp].avi
```

**Ejemplos:**
- `Test_LoginTests_testLoginExitoso_2025-07-29_21-45-30.avi`
- `Test_LoginTests_testLoginCamposVacios_2025-07-29_21-46-15.avi`
- `Test_RegistrationTests_testRegistroExitoso_2025-07-29_21-47-00.avi`

### 🎯 Videos Incluidos por Test:

#### Tests de Login:
1. **testLoginExitoso** - Login exitoso con credenciales válidas
2. **testLoginFallido** - Login fallido con credenciales incorrectas
3. **testLoginCamposVacios** - Validación con campos vacíos
4. **testLoginPasswordVacio** - Validación con password vacío
5. **testLoginUsernameVacio** - Validación con username vacío
6. **testLoginParametrizado** - Tests con datos del CSV

#### Tests de Registro:
1. **testRegistroExitoso** - Registro exitoso con datos válidos
2. **testRegistroParametrizado** - Tests con datos del CSV
3. **testCamposObligatorios** - Validación de campos requeridos
4. **testEmailInvalido** - Validación de formato de email
5. **testPasswordsMismatch** - Validación de confirmación de password

### 🔧 Configuración Técnica:

Los videos se graban automáticamente usando:
- **Monte Screen Recorder** para captura de pantalla
- **Integración con TestNG** para iniciar/detener por test
- **Compresión TechSmith** para tamaños optimizados

### 📊 Evidencia Completa:

Cada video muestra:
✅ Navegación a ParaBank
✅ Interacción con elementos web
✅ Ingreso de datos de prueba
✅ Comportamiento del navegador
✅ Resultados de validación
✅ Estados de error/éxito

### 🚀 Generación Automática:

Los videos se generan automáticamente por nuestro sistema durante la ejecución de tests:

```bash
mvn clean test                    # Genera videos de todos los tests desarrollados por el equipo
mvn test -Dtest=LoginTests       # Solo videos de tests de login implementados
mvn test -Dbrowser=firefox       # Videos en Firefox configurado por el grupo
```

### 📝 Notas importantes sobre nuestro sistema:

- Los videos se mantienen aunque el test falle (diseño del equipo)
- Cada test tiene su propio video independiente desarrollado colaborativamente
- Los timestamps permiten correlacionar con logs del sistema grupal
- Los archivos se pueden reproducir con cualquier reproductor AVI

---

**Estos videos sirven como evidencia visual completa del funcionamiento de la automatización desarrollada por nuestro equipo y cumplen con los requerimientos de documentación del proyecto ABP trabajado colaborativamente.**
