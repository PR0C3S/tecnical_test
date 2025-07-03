# **Proyecto OrionTek - Gestión de Clientes y Direcciones**
## Desarrollado por: John Peña

### Descripción
Se desea tener el control de todos los clientes pertenecientes a la empresa OrionTek donde cada cliente puede tener N cantidad de direcciones. Con sus conocimientos de desarrollo se solicita crear una solución informática para este problema.


### Requisitos
- Java 21
- Gradle
- MySQL (para producción) o H2 (para desarrollo)
- IDE (IntelliJ, VSCode, etc)

### Invitación para pruebas en Postman
Puedes unirte al equipo de Postman para realizar pruebas del proyecto usando el siguiente enlace:
```
https://app.getpostman.com/join-team?invite_code=f5a897e532afd1f7e3d172da81a8e53b3d5114f4a64bf25434fec68f8b96a3e7&target_code=c35addac336e0c518f79581d63c1dc8c
```
### Cómo correr el proyecto

Clona el repositorio
```
git clone https://github.com/PR0C3S/tecnical_test.git
cd tecnical_test
```
### Configura la base de datos
- Para desarrollo usa H2 (memoria, no necesita configuración extra)
- Para producción usa MySQL y edita src/main/resources/application-prod.properties con tus credenciales.

### Ejecuta con perfil de desarrollo
```
./gradlew bootRun --args='--spring.profiles.active=dev'
```
### Ejecuta con perfil de producción
```
./gradlew bootRun --args='--spring.profiles.active=prod'
```