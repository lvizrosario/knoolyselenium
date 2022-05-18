package negocio;

import dsl.DSL;
import org.openqa.selenium.By;
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

    public void cadastrarLinhaDeNegocio() {

    }

    public String itemNegocio() {
        return dsl.idElemento("(//mat-icon[normalize-space()='work'])[1]");
    }
}
