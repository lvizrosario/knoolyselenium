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

    public String getMensagemSucesso(String xPath) {
        try {
            dsl.aguardarBy(By.xpath(xPath));
            return browser.findElement(By.xpath(xPath)).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void adicionarLinhaDeNegocio(String nomeLinhaDeNegocio) {
        browser.findElement(By.id("btn-menu")).click();
        browser.findElement(By.id("btn-menu-0")).click();
        dsl.aguardarBy(By.id("btn-add-line-business"));
        browser.findElement(By.id("btn-add-line-business")).click();
        dsl.aguardarBy(By.id("mat-dialog-0"));
        dsl.escreverTexto("txt-LineOfBusinessName", nomeLinhaDeNegocio);
        browser.findElement(By.id("btn-save-add-line-business")).click();
    }

    public void excluirLinhaDeNegocio(String idLinhaDeNegocio) {
        browser.findElement(By.id("btn-menu")).click();
        browser.findElement(By.id("btn-menu-0")).click();
        dsl.aguardarBy(By.id(idLinhaDeNegocio));
        browser.findElement(By.id(idLinhaDeNegocio)).click();
        dsl.aguardarBy(By.id("btn-edit-line-of-business"));
        browser.findElement(By.id("btn-edit-line-of-business")).click();
        dsl.aguardarBy(By.id("mat-dialog-0"));
        browser.findElement(By.id("btn-line-of-business-delete")).click();
        dsl.aguardarBy(By.id("mat-dialog-0"));
        browser.findElement(By.id("btn-line-of-business-yes")).click();
    }

}
