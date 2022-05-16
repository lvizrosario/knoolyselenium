import org.openqa.selenium.WebDriver;


public class TesteLoginPage {
	
	private DSL dsl;
	public TesteLoginPage(WebDriver browser) {
		dsl = new DSL(browser);
	}
	
	public void setEmail(String email) {
		dsl.escreverTexto("txt-email", email);
	}
	
	public void setPassword(String password) {
		dsl.escreverTexto("txt-password", password);
	}

	public void logar() {
		dsl.clicarNoBotao("id-sec-login__btn");
	}
	
	public String obterEmailLogin() {
		return dsl.obterTexto("txt-email");
	}
	
	public String obterPasswordLogin() {
		return dsl.obterTexto("txt-password");
	}
	
	public String usuarioInvalido() {
		return dsl.obterMensagemErroLoginInvalido("id-sec-error-user-invalid");
	}
	
	public String elementoWeb() {
		return dsl.idElemento("id-sec-error-user-invalid");
	}
	
	public String urlAtual() {
		return dsl.urlAtual();
	}

}
