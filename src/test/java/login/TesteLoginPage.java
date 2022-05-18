package login;

import dsl.DSL;

import negocio.TesteLinhaDeNegocioPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TesteLoginPage {

	private static final String URL_LOGIN = "https://knooly-qa.azurewebsites.net/#/login";
	private final WebDriver browser;
	private final DSL dsl;

	public TesteLoginPage() {
		browser = new ChromeDriver();
		this.browser.navigate().to(URL_LOGIN);
		browser.manage().window().setPosition(new Point(350, 50));
		browser.manage().window().setSize(new Dimension(1280, 900));
		dsl = new DSL(browser);
	}

	public void fecharBrowser() {
		this.browser.quit();
	}

	public void aguardar(String mensagem) {
		WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(mensagem)));
	}

	public void preencherformularioLogin(String email, String password) {
		dsl.escreverTexto("txt-email", email);
		dsl.escreverTexto("txt-password", password);
	}

	public TesteLinhaDeNegocioPage efetuarLogin() {
		dsl.clicarNoBotao("id-sec-login__btn");
		return new TesteLinhaDeNegocioPage(browser);
	}

	public void setToken(String token) {
		dsl.escreverTexto("id-sec-verification-code-verification-input", token);
	}

	public void validarToken() {
		dsl.clicarNoBotao("btn-send-password");

	}

	public String botaoValidarCodigo() {
		return dsl.idElemento("btn-send-password");
	}

	public String obterToken() {
		return dsl.obterTexto("id-sec-verification-code-verification-input");
	}

	public String obterIdUsuarioLogado() {
		return dsl.idElemento("inf-user-name");
	}

	public String obterNomeUsuarioLogado() {
		return browser.findElement(By.id("inf-user-name")).getText();
	}

	public String obterEmailLogin() {
		return dsl.obterTexto("txt-email");
	}

	public String obterPasswordLogin() {
		return dsl.obterTexto("txt-password");
	}

	public String usuarioInvalido() {
		return dsl.obterMensagemErro("id-sec-error-user-invalid");
	}

	public String mensagemErroLoginInvalido() {
		return dsl.idElemento("id-sec-error-user-invalid");
	}

	public String urlAtual() {
		return dsl.urlAtual();
	}
}
