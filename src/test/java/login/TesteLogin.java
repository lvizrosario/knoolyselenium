package login;

import dsl.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TesteLogin {

	private TesteLoginPage testeLoginPage;
	private DSL dsl;

	@Before
	public void before() {
		this.testeLoginPage = new TesteLoginPage();
	}
	
	@After
	public void finaliza() {
		this.testeLoginPage.fecharBrowser();
	}
	
	@Test
	public void acessarTelaLoginValido() {
		// Enviando os dados de login
		testeLoginPage.preencherformularioLogin("luiz.filho@keeggo.com", "Knooly123");
		testeLoginPage.efetuarLogin();

		// Validando login valido
		Assert.assertEquals("luiz.filho@keeggo.com", testeLoginPage.obterEmailLogin());
		Assert.assertEquals("Knooly123", testeLoginPage.obterPasswordLogin());

		// Aguardando 3seg para que o botao da pagina de validação seja apresentado
		testeLoginPage.aguardar(testeLoginPage.botaoValidarCodigo());
		testeLoginPage.setToken("654321");
		testeLoginPage.validarToken();

		// Validando a URL atual e o token
		Assert.assertEquals("https://knooly-qa.azurewebsites.net/#/verification-code", testeLoginPage.urlAtual());
		Assert.assertEquals("654321", testeLoginPage.obterToken());

		// Validando a tela inicial pelo nome do usuário logado
		testeLoginPage.aguardar(testeLoginPage.obterIdUsuarioLogado());
		Assert.assertEquals("Luiz Filho", testeLoginPage.obterNomeUsuarioLogado());
	}
	
	@Test
	public void acessarTelaLoginInvalido() {
		// Enviando os dados de login
		testeLoginPage.preencherformularioLogin("luiz.filho@keeggo.com", "Knooly321");
		testeLoginPage.efetuarLogin();

		// Aguardando 3seg para que a mensagem de erro seja apresentada
		testeLoginPage.aguardar(testeLoginPage.mensagemErroLoginInvalido());

		// Validando login invalido
		Assert.assertEquals("luiz.filho@keeggo.com", testeLoginPage.obterEmailLogin());
		Assert.assertEquals("Não foi encontrado nenhum usuário ativo com as informações fornecidas", testeLoginPage.usuarioInvalido());
	}

	@Test
	public void alterarSenhaValido() {
		// Enviando os dados de login
		testeLoginPage.preencherformularioLogin("luiz.filho@keeggo.com", "Knooly123");
		testeLoginPage.efetuarLogin();

		// Validando login valido
		Assert.assertEquals("luiz.filho@keeggo.com", testeLoginPage.obterEmailLogin());
		Assert.assertEquals("Knooly123", testeLoginPage.obterPasswordLogin());

		// Aguardando 3seg para que o botao da pagina de validação seja apresentado
		testeLoginPage.aguardar(testeLoginPage.botaoValidarCodigo());
		testeLoginPage.setToken("654321");
		testeLoginPage.validarToken();

		// Validando a URL atual e o token
		Assert.assertEquals("https://knooly-qa.azurewebsites.net/#/verification-code", testeLoginPage.urlAtual());
		Assert.assertEquals("654321", testeLoginPage.obterToken());

		// Validando a tela inicial pelo nome do usuário logado
		testeLoginPage.aguardar(testeLoginPage.obterIdUsuarioLogado());
		Assert.assertEquals("Luiz Filho", testeLoginPage.obterNomeUsuarioLogado());

		// Alterando a senha do usuário logado
		testeLoginPage.alterarSenha("Knooly123", "Knooly321");

		// Validando se a senha foi alterada com sucesso
		Assert.assertEquals("Senha alterada com sucesso", testeLoginPage.getMensagemSucesso("Senha alterada com sucesso"));
	}
		
}