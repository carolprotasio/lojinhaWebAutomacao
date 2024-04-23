package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver navegador;

    public LoginPage(WebDriver navegador){
        this.navegador = navegador;
    }

    public LoginPage informarUsuario(String usuario){
        navegador.findElement(By.id("usuario")).sendKeys(usuario);
        return this;
    }
    public LoginPage informarSenha(String senha) {
        navegador.findElement(By.id("senha")).sendKeys(senha);
        return this;
    }
    public ListaProdutoPage submeterFormularioLogin() {
        navegador.findElement(By.name("action")).click();
        return new ListaProdutoPage(navegador);
    }
    public String submeterFormularioLoginComErro(){
        navegador.findElement(By.name("action")).click();
        return navegador.findElement(By.cssSelector(".toast.rounded")).getText();
    }

    public String paginaLogin(){
        return navegador.findElement(By.className("brand-logo")).getText();
    }




}
