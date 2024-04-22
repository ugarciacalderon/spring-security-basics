# spring-security.basics

Formas de asignar usuario y contraseña a nuestros endpoints
mediante el archivo application.properties
spring.security.user.name=ulisesgc
spring.security.user.password=Y3PK2}-D6)(*?7S


Configuración Personalizada de Autenticación
Se sobreescribe el método authenticate, para poner nuestra propia logica, validación de nacionalida, mayor de edad, si tiene autorización etc.
- /com.ugarciac/config/UserNamePasswordAuthentication.java

Flujo de Autenticación con nuestra Implementación
![img.png](img.png)
