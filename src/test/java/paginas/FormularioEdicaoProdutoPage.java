package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioEdicaoProdutoPage {
    private WebDriver navegador;

    public FormularioEdicaoProdutoPage(WebDriver navegador){
        this.navegador = navegador;
    }

    public String capturarMsgAddProdutoSucesso(){
        return navegador.findElement(By.cssSelector(".toast.rounded")).getText();
    }

    public FormularioEdicaoProdutoPage addComponent(){
        navegador.findElement(By.linkText("ADICIONAR COMPONENTE")).click();
        return this;
    }
    public FormularioEdicaoProdutoPage componenteNome(String componenteNome){
        navegador.findElement(By.id("componentenomeadicionar")).sendKeys(componenteNome);
        return this;
    }
    public FormularioEdicaoProdutoPage componenteQtd(String componenteQtd){
        navegador.findElement(By.id("componentequantidadeadicionar")).sendKeys(componenteQtd);
        return this;
    }
    public FormularioEdicaoProdutoPage salvarComponente(){
        navegador.findElement(By.linkText("SALVAR COMPONENTE")).click();
        return this;
    }
    public String capturarComponenteAddSucesso(){
        return navegador.findElement(By.cssSelector(".toast.rounded")).getText();

    }
    public LoginPage fazerLogOut(){
        navegador.findElement(By.linkText("Sair")).click();
        return new LoginPage(navegador);
    }
}
