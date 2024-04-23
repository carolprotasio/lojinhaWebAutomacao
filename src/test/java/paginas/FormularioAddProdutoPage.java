package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class FormularioAddProdutoPage {
    private WebDriver navegador;

    public FormularioAddProdutoPage(WebDriver navegador){
        this.navegador = navegador;
    }

    public FormularioAddProdutoPage inserirProdutoNome(String produtoNome){
        navegador.findElement(By.id("produtonome")).sendKeys(produtoNome);
        return this;
    }
    public FormularioAddProdutoPage inserirProdutoValor(String produtoValor){
        navegador.findElement(By.id("produtovalor")).sendKeys(produtoValor);
        return this;
    }
    public FormularioAddProdutoPage inserirProdutoCores(String produtoCores){
        navegador.findElement(By.id("produtocores")).sendKeys(produtoCores);
        return this;
    }
    public ListaProdutoPage addProdutoErro(){
        navegador.findElement(By.cssSelector("button[type='submit']")).click();
        return new ListaProdutoPage(navegador);
    }
    public FormularioEdicaoProdutoPage addProdutoSucesso(){
        navegador.findElement(By.cssSelector("button[type='submit']")).click();
        return new FormularioEdicaoProdutoPage(navegador);
    }
    public LoginPage fazerLogOut(){
        navegador.findElement(By.linkText("Sair")).click();
        return new LoginPage(navegador);
    }



}
