package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListaProdutoPage {
    private WebDriver navegador;
    public ListaProdutoPage(WebDriver navegador){
        this.navegador = navegador;
    }

    public FormularioAddProdutoPage acessarFormularioNovoProduto(){
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();
        return new FormularioAddProdutoPage(navegador);
    }

    public String capturarMsgErroValorForaDolimite(){
        return navegador.findElement(By.cssSelector(".toast.rounded")).getText();
    }

    public String loginComSucesso(){
        return navegador.findElement(By.cssSelector(".brand-logo")).getText();
    }

    public LoginPage fazerLogOut(){
        navegador.findElement(By.linkText("Sair")).click();
        return new LoginPage(navegador);
    }

}
