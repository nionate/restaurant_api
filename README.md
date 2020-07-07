# Prueba técnica Transbank

Este servicio disponibiliza una API REST para un restaurant 🍕

## Prerequisitos 📋

* Java 8

## Instalación

Utilizar [maven 3.6.3](https://maven.apache.org) como package manager.

```bash
$ mvn clean install
```

## Dependencias utilizadas
* Se utiliza JWT para la autenticación y autorización de rutas

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
</dependency>
```

* Para el uso de colas se utiliza activeMQ y JMS
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-activemq</artifactId>
</dependency>
```

* Para documentar la API se utiliza Swagger
```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
```

* Por último, para la persistencia de datos, se utiliza una base de datos en memoria con H2
```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

## Testing ⚙
Para realizar testing se utiliza los siguientes packages:

* JUnit 4
* MockMVC

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>4.3.2.RELEASE</version>
    <scope>test</scope>
</dependency>
```

* Se utiliza Hamcrest como librería de assert

```xml
<dependency>
    <groupId>org.hamcrest</groupId>
    <artifactId>hamcrest-library</artifactId>
    <version>1.3</version>
    <scope>test</scope>
</dependency>
```

* Para ejecutar los tests

```bash
$ mvn test
```
## Uso 🚀

1. Levantar el servicio

```bash
$ mvn spring-boot:run
```

Al momento de levantar el servicio, junto con la base de dataos en memoria se cargarán datos de prueba.

2. Para realizar login de usuario, mediante *POST* ejecutar el endpoint */login* con el siguiente body

```json
{
    "username":"tbk",
    "password":"superpassw0rd!"
}
```
El servicio responderá con el token que debe incluir en el *Header* para todas otras llamadas de la API

```json
{
    "usename": "tbk",
    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0YmsiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNTk0MTM2OTQwLCJleHAiOjE1OTQxMzc1NDB9.11GLtmf2GYfaMupC2mHXVRDw-xvM-Mi3pPPmTrlGjTfWaiQqG3A5JUFwU7hQf1-baksWawC5ecUtQUCGzaYxeA"
}
```

3. Para crear una venta, mediante *POST* ejecutar endpoint */create* con el siguiente body request

```json
{
    "invoice": "1234567",
    "totalPrice": "24990",
    "saleDate": "2020-07-10",
    "ciSeller": "11.111.111-1",
    "ciBuyer": "22.222.333-3"
}
```

Un ejemplo mediante *curl* es el siguiente

```bash
curl --location --request POST 'localhost:8080/create' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0YmsiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNTk0MTM2OTQwLCJleHAiOjE1OTQxMzc1NDB9.11GLtmf2GYfaMupC2mHXVRDw-xvM-Mi3pPPmTrlGjTfWaiQqG3A5JUFwU7hQf1-baksWawC5ecUtQUCGzaYxeA' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=0D6392381CCEFB347D94649F99E5AE2F' \
--data-raw '{
    "invoice": "01234567",
    "totalPrice": "24990",
    "saleDate": "2020-07-10",
    "ciSeller": "11.111.111-1",
    "ciBuyer": "22.222.333-3"
}'
```

4. Para obtener las ventas del día, mediante *GET* ejecutar endpoint */sales*.
Esta llamada retornará una lista con todas las ventas del día.

Ejemplo:

```json
[
    {
        "id": "3dbb0147-4dbb-4c25-9bad-57bd146424df",
        "invoice": 12345,
        "totalPrice": 10000,
        "saleDate": "2020-07-07",
        "ciSeller": "11.111.111-1",
        "ciBuyer": "11.111.222-2"
    },
    {
        "id": "97960de1-a88e-48f9-b81c-cf5c0de9babf",
        "invoice": 12346,
        "totalPrice": 13000,
        "saleDate": "2020-07-07",
        "ciSeller": "22.222.222-2",
        "ciBuyer": "22.222.333-3"
    }
]
```

Ejemplo de petición mediante *curl*

```bash
curl --location --request GET 'localhost:8080/sales' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0YmsiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNTk0MTM3NDkxLCJleHAiOjE1OTQxMzgwOTF9.9T6eD7pF6qgqmxaLRWaeFtwjWcNJIpD39KOUC3DiV8Vdpvxft3v3wUVwh7A_KnTDJJ263vXNH2F37Smoh9wX1w' \
--header 'Cookie: JSESSIONID=6E714A01ABD65D91F12F8DFCE09D0903' \
--data-raw ''
```

Si se desea consultar por un día en específico, en la url para la llamada a */sales* puede agregar el campo <b>date</b>

```html
http://localhost:8080/sales?date=2020-07-10
```

esto retornará las ventas para el día <b>2020-07-10</b>

Ejemplo de llamada a */sales* con una fecha específica

```bash
curl --location --request GET 'localhost:8080/sales?date=2020-07-10' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0YmsiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNTk0MTQzMjMzLCJleHAiOjE1OTQxNDM4MzN9.-XdSDuv179_4ARFZhqpd50v8joReAF2_-mIDNxygzUXGVVfww2frGtVh6UiA_yiHigzxRZ2lMwdXSOJtE9ruMw' \
--header 'Cookie: JSESSIONID=7B4337E5C0C2B5EB00D4F72420B38292' \
--data-raw ''
```

5. Se disponibilizan las siguientes rutas

|Método| Endpoint        | Http response status |
|------|-----------------|----------------------|
|POST   | /login         |200                   |
|POST  |  /create        |201                   |
|GET   |  /sales         |200                   |
|GET   |  /sales/{day}         |200                   |

Para ver la documentación de la API visitar [este enlace a Swagger](http://localhost:8080/swagger-ui.html) 

6. *Para autenticarse en Swagger con el token devuelto por el login se debe ingresar dicho token haciendo click en el boton <b>"Authorize"</b>*

![alt text](https://i.imgur.com/zrEQHfH.png)
![alt text](https://i.imgur.com/PIYUd1F.png)