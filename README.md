
## Pruebas - Formulario de Inicio de Sesión

Este repositorio contiene las pruebas automatizadas realizadas con **Selenium** 
y **JUnit** de los casos de prueba priorizados de un formulario de inicio de sesión.



## 🛠️ Tecnologías Utilizadas
- **IntelliJ** - Entorno de Desarrollo.
- **Java JDK** - Framework de desarrollo JDK 24.
- **JUnit Jupiter** - Framework de testing .
- **Maven** - Compilador
- **Selenium WebDriver** - Automatización de pruebas en UI.


## 📂 Estructura del Proyecto
```plaintext
prueba-tecnica/
├── src/
│   ├── main/
│   │     ├── java/
│   ├── test/
│   │     ├── java/
│   │     ├── resources/
├── .gitignore
└── pom.xml
```

## Requisitos 
- **Java JDK**: v24 o superior
- **Eclipse o IntelliJ**

## Casos de Prueba Priorizados 

| ID   | Escenario                        | Datos de Entrada                        | Resultado Esperado                        |
| :----| :------------------------------: | :-------------------------------------: |  ---------------------------------------: |
| CP01 |	Campos vacíos	                  | Usuario: "" Contraseña: ""	            | "Los campos no pueden estar vacíos"       |
| CP02 |	Usuario y contraseña inválidos	| Usuario: "juan" Contraseña: "clave1234"	| "Usuario y contraseña inválidos"          |
| CP03 |	Usuario inválido	              | Usuario: "pedro" Contraseña:            | "1234"	"Nombre de usuario inválido"      |
| CP04 |	Contraseña inválida	            | Usuario: "ana" Contraseña:              | "claveincorrecta"	"Contraseña inválida"   |
| CP05 |	Datos válidos	                  | Usuario: "ana" Contraseña: "1234"	      | "Inicio de sesión exitoso"                |


## Configuración y Ejecución

### 1. Clona el repositorio:
		```bash
		git clone https://github.com/henrymaza/PruebasSemana3.git
		```
### 2. Navega al directorio de las pruebas:
		```bash
		cd test
		```
### 3. Instalar JDK:
		https://openjdk.org/install/

## Comandos Disponibles

- **Iniciar el Jar**:
    ```bash
    java -jar src/ejecutable/registro-test-1.0-SNAPSHOT.jar
    ```
- **Compilar el proyecto**:
    ```bash
    mvn clean package
    ```
- **Ejecutar pruebas**:
    ```bash
    mvn test
