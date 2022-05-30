package projeto;

import dsl.DSL;
import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class TesteProjetoPage {

    private final WebDriver browser;
    private final DSL dsl;

    public TesteProjetoPage(WebDriver browser) {
        this.browser = browser;
        dsl = new DSL(this.browser);
    }

    public void fecharBrowser() throws IOException {
        dsl.screenShot();
        this.browser.quit();
    }

    /**
     *
     * FUNCIONALIDADES REFERENTES A PROJETO
     *
     */

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
        Thread.sleep(2000);
        browser.findElement(By.xpath("//label[@class='label']//mat-icon[@role='img'][normalize-space()='arrow_drop_down']")).click();
        dsl.aguardarBy(By.xpath("//ul[@class='options ng-star-inserted']"));
        dsl.clicarByXpath("li", resProjeto);
        browser.findElement(By.xpath("//label[@class='label -dropdown']//mat-icon[@role='img'][normalize-space()='arrow_drop_down']")).click();
        dsl.aguardarBy(By.xpath("//ul[@class='options ng-star-inserted']"));
        dsl.clicarByXpath("li", respQualidade);
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
        dsl.clicarByXpath("div", dataInicial);
    }

    public void dataFinalProjeto(String dataFinal) {
        dsl.clicarNoBotaoClick("btn-input-end-date");
        dsl.aguardarBy(By.xpath("//table[@role='grid']"));
        dsl.clicarByXpath("div", dataFinal);
    }

    public void adicionarProjetoBotao() {
        dsl.clicarNoBotaoClick("btn-save-project");
    }

    public String getMensagemSucesso(String mensagem) {
        try {
            dsl.aguardarBy(By.xpath("//span[normalize-space()='" + mensagem + "']"));
            return browser.findElement(By.xpath("//span[normalize-space()='" + mensagem + "']")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void consultarProjeto(String nomeProjeto) {
        dsl.clicarNoBotaoClick("btn-menu");
        dsl.clicarNoBotaoClick("btn-menu-7");
        dsl.aguardarBy(By.id("input-search-project"));
        dsl.escreverTexto("input-search-project", nomeProjeto);
        browser.findElement(By.xpath("//mat-icon[normalize-space()='search']")).click();
    }

    public String nomeProjeto(String nomeProjeto) throws InterruptedException {
        Thread.sleep(1000);
        return dsl.textoByXpath("span", nomeProjeto);
    }

    /**
     *
     * FUNCIONALIDADES REFERENTES A SPRINT
     *
     */

    public void adicionarSprint(String nomeProjeto, String nomeSprint) {
        dsl.clicarNoBotaoClick("btn-menu");
        dsl.clicarNoBotaoClick("btn-menu-7");
        dsl.aguardarBy(By.xpath("//tbody"));
        dsl.clicarByXpath("span", nomeProjeto);
        dsl.aguardarBy(By.id("project-type"));
        dsl.clicarNoBotaoClick("project-type");
        dsl.aguardarBy(By.id("id-cycles-new"));
        dsl.clicarNoBotaoClick("id-cycles-new");
        dsl.aguardarBy(By.id("input-cycle-name"));
        dsl.escreverTexto("input-cycle-name", nomeSprint);
    }

    public void dataInicialSprint(String dataInicial) {
        dsl.clicarNoBotaoClick("button-add-cycle-planned-start-date");
        dsl.aguardarBy(By.xpath("//table[@role='grid']"));
        dsl.clicarByXpath("div", dataInicial);
    }

    public void dataFinalSprint(String dataFinal) {
        dsl.clicarNoBotaoClick("button-add-cycle-planned-end-date");
        dsl.aguardarBy(By.xpath("//table[@role='grid']"));
        dsl.clicarByXpath("div", dataFinal);
    }

    public void adicionarSprintBotao() {
        dsl.clicarNoBotaoClick("btn-cycle-code");
    }

    public void adicionarCenarioSprint() {
        // As combos não carregam os dados cadastrados dos produtos e subprodutos
    }

}
