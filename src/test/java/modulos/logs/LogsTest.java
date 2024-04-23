package modulos.logs;

import org.junit.jupiter.api.*;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.ListaProdutoPage;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testando Módulo de Log-in-out")
public class LogsTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach(){
        System.getProperty("webdriver.chome.drive", "C:\\drivers\\chromedriver\\chrome.exe");
        this.navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @Test
    @DisplayName("Fazer Login usando credenciais válidas")
    public void fazerLoginCredenciaisValida(){

        String msgSucesso = new LoginPage(navegador)
                .informarUsuario("carolp")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .loginComSucesso();

        Assertions.assertEquals("Lojinha", msgSucesso);
    }

    @Test
    @DisplayName("Fazer Login usando o nome do usuário inválido")
    public void fazerLoginUsuarioInvalido(){
        String msgErro = new LoginPage(navegador)
                .informarUsuario("nomeInvalido")
                .informarSenha("123456")
                .submeterFormularioLoginComErro();
        Assertions.assertEquals("Falha ao fazer o login", msgErro);
    }

    @Test
    @DisplayName("Tentar fazer login deixando usuario e senha em branco")
    public void fazerLoginSemDadosUsuarioESenha(){
        String msgErro = new LoginPage(navegador)
                .informarUsuario("")
                .informarSenha("")
                .submeterFormularioLoginComErro();
        Assertions.assertEquals("Falha ao fazer o login", msgErro);
    }
    @Test
    @DisplayName("Fazer o Logout estando na pagina de produtos")
    public void fazerLogOutPagProdutos(){
        String logOut = new LoginPage(navegador)
                .informarUsuario("carolp")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .fazerLogOut()
                .paginaLogin();
        Assertions.assertEquals("Lojinha", logOut);
    }
    @Test
    @DisplayName("Fazer Logout estando na pagina de adição de produto")
    public void fazerLoOutPgAddProduto(){
        String logOut = new LoginPage(navegador)
                .informarUsuario("carolp")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .acessarFormularioNovoProduto()
                .fazerLogOut()
                .paginaLogin();
        Assertions.assertEquals("Lojinha", logOut);
    }
    @Test
    @DisplayName("Fazer logout estando na pagina de edição de produto")
    public void fazerLogOutPaginaEdicaoProduto(){
        String logOut = new LoginPage(navegador)
                .informarUsuario("carolp")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .acessarFormularioNovoProduto()
                .inserirProdutoNome("Teste LogOut")
                .inserirProdutoValor("005")
                .inserirProdutoCores("blue")
                .addProdutoSucesso()
                .fazerLogOut()
                .paginaLogin();
        Assertions.assertEquals("Lojinha", logOut);
    }


    @AfterEach
    public void afterEach(){
        navegador.quit();
    }
}
