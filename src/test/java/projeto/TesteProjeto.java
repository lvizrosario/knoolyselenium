package projeto;

import dsl.DSL;
import login.TesteLoginPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class TesteProjeto {

    private TesteProjetoPage testeProjetoPage;
    private WebDriver browser;
    private DSL dsl;

    @Before
    public void before() {
        this.testeProjetoPage = new TesteProjetoPage(browser);
    }

    @After
    public void finaliza() {
        this.testeProjetoPage.fecharBrowser();
    }

    @Test
    public void cadastrarProjetoValido() throws InterruptedException {
        TesteLoginPage testeLoginPage = new TesteLoginPage();

        // Efetuando o login e suas respectivas validações
        testeLoginPage.preencherformularioLogin("luiz.filho@keeggo.com", "Knooly123");
        this.testeProjetoPage = testeLoginPage.efetuarLoginProjeto();

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

        // Validando a criação de um novo projeto
        testeProjetoPage.codigoDoProjeto("111");
        testeProjetoPage.nomeDoProjeto("Projeto Luiz");
        testeProjetoPage.tamanhoProjeto("Pequeno");
        testeProjetoPage.tipoProjeto("Agile");
        testeProjetoPage.respProjetoEQualidade("Luiz Filho", "Alexandre Abreu");
        testeProjetoPage.nomeSquad("Squad Teste 1");
        testeProjetoPage.linhaDeNegocio("Teste Luiz 4");
        testeProjetoPage.ambienteTeste("Homologação");
        testeProjetoPage.dataInicialProjeto("27");
        testeProjetoPage.dataFinalProjeto("31");
        testeProjetoPage.adicionarProjetoBotao();

        // Validando mensagem de sucesso
        Assert.assertEquals("Projeto criado com sucesso!", testeProjetoPage.getMensagemSucesso("//span[normalize-space()='Projeto criado com sucesso!']"));
    }
}
