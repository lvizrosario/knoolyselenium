package negocio;

import login.TesteLoginPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.WebDriver;

public class TesteLinhaDeNegocio {

    private TesteLinhaDeNegocioPage testeLinhaDeNegocioPage;
    private WebDriver browser;

    @Before
    public void before() {
        this.testeLinhaDeNegocioPage = new TesteLinhaDeNegocioPage(browser);
    }

    @After
    public void finaliza() {
        this.testeLinhaDeNegocioPage.fecharBrowser();
    }

    @Test
    public void criarLinhaDeNegocioValida() {
        TesteLoginPage testeLoginPage = new TesteLoginPage();

        // Efetuando o login e suas respectivas validações
        testeLoginPage.preencherformularioLogin("luiz.filho@keeggo.com", "Knooly123");
        this.testeLinhaDeNegocioPage = testeLoginPage.efetuarLogin();

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

        // Cadastrando uma nova linha de negócio
        testeLinhaDeNegocioPage.adicionarLinhaDeNegocio("Teste Luiz 4");

        // Validando a mensagem de cadastro realizado com sucesso
        Assert.assertEquals("Salvo com sucesso", testeLinhaDeNegocioPage.getMensagemSucesso("//span[normalize-space()='Salvo com sucesso']"));
    }

    @Test
    public void excluirLinhaDeNegocio() {
        TesteLoginPage testeLoginPage = new TesteLoginPage();

        // Efetuando o login e suas respectivas validações
        testeLoginPage.preencherformularioLogin("luiz.filho@keeggo.com", "Knooly123");
        this.testeLinhaDeNegocioPage = testeLoginPage.efetuarLogin();

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

        // Excluindo linha de negócio
        testeLinhaDeNegocioPage.excluirLinhaDeNegocio("line-of-business-1");

        // Validando a mensagem de exclusão realizada com sucesso
        Assert.assertEquals("Excluído com sucesso", testeLinhaDeNegocioPage.getMensagemSucesso("//span[normalize-space()='Excluído com sucesso']"));
    }

}
