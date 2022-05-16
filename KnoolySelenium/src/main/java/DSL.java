import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DSL {
	
	private WebDriver browser;
	
	public DSL(WebDriver browser) {
		this.browser = browser;
	}
	
	public void escreverTexto(String id_campo, String texto) {
		browser.findElement(By.id(id_campo)).sendKeys(texto);	
	}
	
	public String obterTexto(String id_campo) {
		return browser.findElement(By.id(id_campo)).getAttribute("value");
	}
	
	public void clicarNoBotao(String id_campo) {
		browser.findElement(By.id(id_campo)).submit();
	}
	
	public String obterMensagemErroLoginInvalido(String id_campo) {
		return browser.findElement(By.id(id_campo)).getText();
	}
	
	public String idElemento(String id_campo) {
		return id_campo;
	}
	
	public String urlAtual() {
		return browser.getCurrentUrl();
	}

}
