package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        // Crear el directorio de capturas de pantalla si no existe
        File screenshotsDir = new File("screenshots");
        if (!screenshotsDir.exists()) {
            screenshotsDir.mkdirs();
        }
    }

    @BeforeEach
    public void abrirFormulario() {
        File htmlFile = new File("src/test/resources/formulario_registro.html");
        driver.get("file://" + htmlFile.getAbsolutePath());
    }

    @AfterEach
    public void capturarPantalla(TestInfo testInfo) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String testName = testInfo.getTestMethod().orElse(null).getName();
        if (testName != null) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH_mm_ss");
            String timestamp = now.format(formatter);
            String screenshotName = "screenshots" + File.separator + testName.split("_")[0] + "_" + timestamp + ".png";
            File destination = new File(screenshotName);
            try {
                FileUtils.copyFile(source, destination);
                System.out.println("Captura de pantalla guardada en: " + screenshotName);
            } catch (IOException e) {
                System.out.println("Error al guardar la captura de pantalla: " + e.getMessage());
            }
        }
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
