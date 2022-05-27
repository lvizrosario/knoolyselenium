package projeto;

import dsl.DSL;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class TesteProjetoPage {

    private final WebDriver browser;
    private final DSL dsl;

    public TesteProjetoPage(WebDriver browser) {
        this.browser = browser;
        dsl = new DSL(this.browser);
    }

    public void fecharBrowser() {
        this.browser.quit();
    }

    public void codigoDoProjeto(String codProjeto) {
        dsl.clicarNoBotaoClick("btn-menu");
        dsl.clicarNoBotaoClick("btn-menu-7");
        dsl.aguardarBy(By.id("btn-new-project"));
        dsl.clicarNoBotaoClick("btn-new-project");
        dsl.escreverTexto("input-project-code", codProjeto);
    }

    public void nomeDoProjeto(String nomeProjeto) {
        dsl.escreverTexto("input-project-name", nomeProjeto);
    }

    public void tamanhoProjeto(String tamanhoProjeto) {
        dsl.selecionarCombo("select-project-size-code", tamanhoProjeto);
    }

    public void tipoProjeto(String tipoProjeto) {
        dsl.selecionarCombo("select-project-type", tipoProjeto);
    }

    public void respProjetoEQualidade(String resProjeto, String respQualidade) throws InterruptedException {
        Thread.sleep(1500);
        browser.findElement(By.xpath("//label[@class='label']//mat-icon[@role='img'][normalize-space()='arrow_drop_down']")).click();
        dsl.aguardarBy(By.xpath("//ul[@class='options ng-star-inserted']"));
        dsl.selecionarByXpath("li", resProjeto);
        browser.findElement(By.xpath("//label[@class='label -dropdown']//mat-icon[@role='img'][normalize-space()='arrow_drop_down']")).click();
        dsl.aguardarBy(By.xpath("//ul[@class='options ng-star-inserted']"));
        dsl.selecionarByXpath("li", respQualidade);
    }

    public void nomeSquad(String nomeSquad) {
        dsl.escreverTexto("input-team-name", nomeSquad);
    }

    public void linhaDeNegocio(String linhaDeNegocio) {
        dsl.aguardarBy(By.id("select-add-project-line-of-business-id"));
        dsl.selecionarCombo("select-add-project-line-of-business-id", linhaDeNegocio);
    }

    public void ambienteTeste(String ambienteTeste) {
        dsl.escreverTexto("input-test-environment", ambienteTeste);
    }

    public void dataInicialProjeto(String dataInicial) {
        dsl.clicarNoBotaoClick("btn-input-start-date");
        dsl.aguardarBy(By.xpath("//table[@role='grid']"));
        dsl.selecionarByXpath("div", dataInicial);
    }

    public void dataFinalProjeto(String dataFinal) {
        dsl.clicarNoBotaoClick("btn-input-end-date");
        dsl.aguardarBy(By.xpath("//table[@role='grid']"));
        dsl.selecionarByXpath("div", dataFinal);
    }

    public void adicionarProjetoBotao() {
        dsl.clicarNoBotaoClick("btn-save-project");
    }

    public String getMensagemSucesso(String xPath) {
        try {
            dsl.aguardarBy(By.xpath(xPath));
            return browser.findElement(By.xpath(xPath)).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

}
