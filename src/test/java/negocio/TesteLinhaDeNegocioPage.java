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

    public void adicionarLinhaDeNegocio(String nomeLinhaDeNegocio) {
        dsl.clicarNoBotaoClick("btn-menu");
        dsl.clicarNoBotaoClick("btn-menu-0");
        dsl.aguardarBy(By.id("btn-add-line-business"));
        dsl.clicarNoBotaoClick("btn-add-line-business");
        dsl.aguardarBy(By.id("mat-dialog-0"));
        dsl.escreverTexto("txt-LineOfBusinessName", nomeLinhaDeNegocio);
        dsl.clicarNoBotaoClick("btn-save-add-line-business");
    }

    public void excluirLinhaDeNegocio(String idLinhaDeNegocio) {
        dsl.clicarNoBotaoClick("btn-menu");
        dsl.clicarNoBotaoClick("btn-menu-0");
        dsl.aguardarBy(By.id(idLinhaDeNegocio));
        dsl.clicarNoBotaoClick(idLinhaDeNegocio);
        dsl.aguardarBy(By.id("btn-edit-line-of-business"));
        dsl.clicarNoBotaoClick("btn-edit-line-of-business");
        dsl.aguardarBy(By.id("mat-dialog-0"));
        dsl.clicarNoBotaoClick("btn-line-of-business-delete");
        dsl.aguardarBy(By.id("mat-dialog-0"));
        dsl.clicarNoBotaoClick("btn-line-of-business-yes");
    }

}
