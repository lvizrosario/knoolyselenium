package negocio;

import dsl.DSL;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


public class TesteLinhaDeNegocioPage {

    private final WebDriver browser;
    private final DSL dsl;

    public TesteLinhaDeNegocioPage(WebDriver browser) {
        this.browser = browser;
        dsl = new DSL(this.browser);
    }

    public void fecharBrowser() {
        this.browser.quit();
    }

    public void adicionarLinhaDeNegocio() {
        browser.findElement(By.id("btn-menu")).click();
        browser.findElement(By.id("btn-menu-0")).click();
        dsl.aguardarBy(By.id("btn-add-line-business"));
        browser.findElement(By.id("btn-add-line-business")).click();
        dsl.aguardarBy(By.id("mat-dialog-0"));
        dsl.escreverTexto("txt-LineOfBusinessName", "Linha Teste Luiz 2");
        browser.findElement(By.id("btn-save-add-line-business")).click();
    }

    public String getMensagemSucesso() {
        try {
            dsl.aguardarBy(By.xpath("//span[normalize-space()='Salvo com sucesso']"));
            return browser.findElement(By.xpath("//span[normalize-space()='Salvo com sucesso']")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

}
