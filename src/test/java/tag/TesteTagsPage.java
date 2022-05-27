package tag;

import dsl.DSL;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class TesteTagsPage {

    private final WebDriver browser;
    private final DSL dsl;

    public TesteTagsPage(WebDriver browser) {
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

    public void cadastrarGrupo(String nomeGrupo) {
        dsl.clicarNoBotaoClick("btn-menu");
        dsl.clicarNoBotaoClick("btn-menu-4");
        dsl.aguardarBy(By.id("btn-toogle-input-new-group"));
        dsl.clicarNoBotaoClick("btn-toogle-input-new-group");
        dsl.aguardarBy(By.id("id-page-register-tags-groups-add-group-container"));
        dsl.escreverTexto("id-page-register-tags-groups-input-add-group", nomeGrupo);
        dsl.clicarNoBotaoClick("id-page-register-tags-groups-btn-add-group-check-icon");
    }

    public void cadastrarTag(String groupId, String nomeTag) {
        dsl.clicarNoBotaoClick("btn-menu");
        dsl.clicarNoBotaoClick("btn-menu-4");
        dsl.aguardarBy(By.id(groupId));
        dsl.clicarNoBotaoClick(groupId);
        dsl.aguardarBy(By.id("id-page-register-tags-column-tag-container"));
        dsl.clicarNoBotaoClick("id-page-register-tags-column-tag-btn-toogle-new-tag");
        dsl.aguardarBy(By.id("id-page-register-tags-column-tag-input-add-tag-name"));
        dsl.escreverTexto("id-page-register-tags-column-tag-input-add-tag-name", nomeTag);
        dsl.clicarNoBotaoClick("id-page-register-tags-column-tag-btn-new-tag-icon");
    }

}
