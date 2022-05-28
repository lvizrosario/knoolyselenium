package dsl;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DSL {
	
	private final WebDriver browser;
	
	public DSL(WebDriver browser) {
		this.browser = browser;
	}

	public void escreverTexto(String id_campo, String texto) {
		browser.findElement(By.id(id_campo)).sendKeys(texto);
	}

	public String obterTexto(String id_campo) {
		return browser.findElement(By.id(id_campo)).getAttribute("value");
	}

	public void clicarNoBotaoSubmit(String id_campo) {
		browser.findElement(By.id(id_campo)).submit();
	}

	public void clicarNoBotaoClick(String id_campo) {
		browser.findElement(By.id(id_campo)).click();
	}

	public String obterMensagemErro(String id_campo) {
		return browser.findElement(By.id(id_campo)).getText();
	}

	public String idElemento(String id_campo) {
		return id_campo;
	}

	public String urlAtual() {
		return browser.getCurrentUrl();
	}

	public void aguardarBy(By by) {
		WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(8));
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public String getMensagemSucesso(String xPath) {
		try {
			aguardarBy(By.xpath(xPath));
			return browser.findElement(By.xpath(xPath)).getText();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public void selecionarCombo(String idElemento, String txtCombo) {
		WebElement element = browser.findElement(By.id(idElemento));
		Select combo = new Select(element);
		combo.selectByVisibleText(txtCombo);
	}

	public void clicarByXpath(String tag, String txtNome) {
		browser.findElement(By.xpath("//" + tag + "[normalize-space()='" + txtNome + "']")).click();
	}

	public String textoByXpath(String tag, String txtNome) {
		return browser.findElement(By.xpath("//" + tag + "[normalize-space()='" + txtNome + "']")).getText();
	}

}
