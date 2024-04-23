package modulos.produtos;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testando Módulo de Produtos")
public class ProdutoTest {

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
    @DisplayName("Não pode cadastrar valor igual ou menor que zero")
    public void testeNaoCadastrarValorIgualAZero(){
        String msgErro = new LoginPage(navegador)
                .informarUsuario("carolp")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .acessarFormularioNovoProduto()
                .inserirProdutoNome("Teste Valor Zero")
                .inserirProdutoValor("000")
                .inserirProdutoCores("cinza, verde")
                .addProdutoErro()
                .capturarMsgErroValorForaDolimite();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", msgErro);
   }
    @Test
    @DisplayName("Não pode cadastrar valor maior que sete mil")
    public void testeNaoCadastrarValormaiorSetMil(){
        String msgErro = new LoginPage(navegador)
                .informarUsuario("carolp")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .acessarFormularioNovoProduto()
                .inserirProdutoNome("Teste Valor maior 7 mil")
                .inserirProdutoValor("700001")
                .inserirProdutoCores("cinza, verde")
                .addProdutoSucesso()
                .capturarMsgAddProdutoSucesso();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", msgErro);
    }

   @Test
   @DisplayName("Cadastro de produto dentro do valor limite, entre 0,01 a 7.000,00")
   public void testeCadastroProdutoDentroValorLimite(){
        String msgProdutoAddSucesso = new LoginPage(navegador)
                .informarUsuario("carol")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .acessarFormularioNovoProduto()
                .inserirProdutoNome("Teste valor limite")
                .inserirProdutoValor("10005")
                .inserirProdutoCores("azul")
                .addProdutoSucesso()
                .capturarMsgAddProdutoSucesso();
        Assertions.assertEquals("Produto adicionado com sucesso", msgProdutoAddSucesso);
   }
    @Test
    @DisplayName("Cadastro de produto no limite  0,01")
    public void testeCadastroProdutoLimiteZeroUmCentavo(){
        String msgProdutoAddSucesso = new LoginPage(navegador)
                .informarUsuario("carol")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .acessarFormularioNovoProduto()
                .inserirProdutoNome("Teste Limite 0,01")
                .inserirProdutoValor("1001")
                .inserirProdutoCores("azul")
                .addProdutoSucesso()
                .capturarMsgAddProdutoSucesso();
        Assertions.assertEquals("Produto adicionado com sucesso", msgProdutoAddSucesso);
    }
    @Test
    @DisplayName("Cadastro de produto no limite  7000")
    public void testeCadastroProdutoLimiteSeteMil(){
        String msgProdutoAddSucesso = new LoginPage(navegador)
                .informarUsuario("carol")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .acessarFormularioNovoProduto()
                .inserirProdutoNome("Teste Limite 7 mil")
                .inserirProdutoValor("700000")
                .inserirProdutoCores("azul")
                .addProdutoSucesso()
                .capturarMsgAddProdutoSucesso();
        Assertions.assertEquals("Produto adicionado com sucesso", msgProdutoAddSucesso);
    }

   @Test
   @DisplayName("Adicionar componentes")
   public void testeAdicionarComponentes(){
        String msgAddComponente = new LoginPage(navegador)
                .informarUsuario("carolp")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .acessarFormularioNovoProduto()
                .inserirProdutoNome("Add componente extra 2")
                .inserirProdutoValor("5000")
                .inserirProdutoCores("branco")
                .addProdutoSucesso()
                .addComponent()
                .componenteNome("componente 2 extra")
                .componenteQtd("1")
                .salvarComponente()
                .capturarComponenteAddSucesso();
        Assertions.assertEquals("Produto adicionado com sucesso", msgAddComponente);
   }



    @AfterEach
    public void afterEach(){
        navegador.quit();
    }


}
