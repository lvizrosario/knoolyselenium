package login;

import dsl.DSL;

import negocio.TesteLinhaDeNegocioPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import projeto.TesteProjetoPage;
import tag.TesteTagsPage;

import java.io.IOException;
import java.time.Duration;

public class TesteLoginPage {

	private static final String URL_LOGIN = "https://knooly-qa.azurewebsites.net/#/login";
	private final WebDriver browser;
	private final DSL dsl;

	public TesteLoginPage() {
		browser = new ChromeDriver();
		browser.manage().window().setPosition(new Point(350, 50));
		browser.manage().window().setSize(new Dimension(1280, 900));
		this.browser.navigate().to(URL_LOGIN);
		dsl = new DSL(browser);
	}

	public void fecharBrowser() throws IOException {
		dsl.screenShot();
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

	public void efetuarLogin() {
		dsl.clicarNoBotaoSubmit("id-sec-login__btn");
	}

	public TesteLinhaDeNegocioPage efetuarLoginLinhaDenegocio() {
		dsl.clicarNoBotaoSubmit("id-sec-login__btn");
		return new TesteLinhaDeNegocioPage(browser);
	}

	public TesteTagsPage efetuarLoginTags() {
		dsl.clicarNoBotaoSubmit("id-sec-login__btn");
		return new TesteTagsPage(browser);
	}

	public TesteProjetoPage efetuarLoginProjeto() {
		dsl.clicarNoBotaoSubmit("id-sec-login__btn");
		return new TesteProjetoPage(browser);
	}

	public void setToken(String token) {
		dsl.escreverTexto("id-sec-verification-code-verification-input", token);
	}

	public void validarToken() {
		dsl.clicarNoBotaoSubmit("btn-send-password");

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

	// Alterar Senha

	public void alterarSenha(String senhaAtual, String senhaNova) {
		dsl.clicarNoBotaoClick("btn-trigger-user-menu");
		dsl.aguardarBy(By.id("btn-change-password"));
		dsl.clicarNoBotaoClick("btn-change-password");
		dsl.aguardarBy(By.id("id-change-password-container"));
		dsl.escreverTexto("id-change-password-current-password-input", senhaAtual);
		dsl.escreverTexto("id-change-password-new-password-input", senhaNova);
		dsl.escreverTexto("id-change-password-new-pass-confirm-input", senhaNova);
		dsl.clicarNoBotaoClick("id-change-password-btn-update");
	}

	public String getMensagemSucesso(String mensagem) {
		try {
			dsl.aguardarBy(By.xpath("//span[normalize-space()='" + mensagem + "']"));
			return browser.findElement(By.xpath("//span[normalize-space()='" + mensagem + "']")).getText();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
}
