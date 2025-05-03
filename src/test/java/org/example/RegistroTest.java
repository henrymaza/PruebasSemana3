package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistroTest {
    static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeEach
    public void abrirFormulario() {
        File htmlFile = new File("src/test/resources/formulario_registro.html");
        driver.get("file://" + htmlFile.getAbsolutePath());
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    public void enviarFormulario(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.tagName("button")).click();
    }

    public String obtenerMensaje() {
        return driver.findElement(By.id("mensaje")).getText();
    }

    @Test
    @Order(1)
    public void CP01_camposVacios() {
        enviarFormulario("", "");
        assertEquals("Los campos no pueden estar vacíos", obtenerMensaje());
    }

    @Test
    @Order(2)
    public void CP02_usuarioYcontrasenaInvalidos() {
        enviarFormulario("juan", "clave1234");
        assertEquals("Usuario y contraseña inválidos", obtenerMensaje());
    }

    @Test
    @Order(3)
    public void CP03_usuarioInvalido() {
        enviarFormulario("pedro", "1234");
        assertEquals("Nombre de usuario inválido", obtenerMensaje());
    }

    @Test
    @Order(4)
    public void CP04_contrasenaInvalida() {
        enviarFormulario("ana", "claveincorrecta");
        assertEquals("Contraseña inválida", obtenerMensaje());
    }

    @Test
    @Order(5)
    public void CP05_datosValidos() {
        enviarFormulario("ana", "1234");
        assertEquals("Inicio de sesión exitoso", obtenerMensaje());
    }
}
