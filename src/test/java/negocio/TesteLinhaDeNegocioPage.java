package negocio;

import dsl.DSL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


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

    public void aguardar(String mensagem) {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(mensagem)));
    }

    public void adicionarLinhaDeNegocio() {
        browser.findElement(By.id("btn-menu")).click();
        browser.findElement(By.id("btn-menu-0")).click();
        aguardar("btn-add-line-business");
        browser.findElement(By.id("btn-add-line-business")).click();
        aguardar("mat-dialog-0");
        dsl.escreverTexto("txt-LineOfBusinessName", "Linha Teste Luiz");
        browser.findElement(By.id("btn-save-add-line-business")).click();
    }

}
