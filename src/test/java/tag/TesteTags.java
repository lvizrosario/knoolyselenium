package tag;

import login.TesteLoginPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class TesteTags {

    private TesteTagsPage testeTagsPage;
    private WebDriver browser;

    @Before
    public void before() {
        this.testeTagsPage = new TesteTagsPage(browser);
    }

    @After
    public void finaliza() {
        this.testeTagsPage.fecharBrowser();
    }

    @Test
    public void cadastrarGrupoValido() {
        TesteLoginPage testeLoginPage = new TesteLoginPage();

        // Efetuando o login e suas respectivas validações
        testeLoginPage.preencherformularioLogin("luiz.filho@keeggo.com", "Knooly123");
        this.testeTagsPage = testeLoginPage.efetuarLoginTags();

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

        // Cadastrando um novo grupo
        testeTagsPage.cadastrarGrupo("Grupo teste");

        //Validando a mensagem de cadastro realizado com sucesso
        Assert.assertEquals("Salvo com sucesso", testeTagsPage.getMensagemSucesso("//span[normalize-space()='Salvo com sucesso']"));
    }

    @Test
    public void cadastrarTagValida() {
        TesteLoginPage testeLoginPage = new TesteLoginPage();

        // Efetuando o login e suas respectivas validações
        testeLoginPage.preencherformularioLogin("luiz.filho@keeggo.com", "Knooly123");
        this.testeTagsPage = testeLoginPage.efetuarLoginTags();

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

        // Cadastrando uma nova Tag
        testeTagsPage.cadastrarTag("id-page-register-tags-groups-btn-get-tags-1", "Tag - Grupo Teste");

        //Validando a mensagem de cadastro realizado com sucesso
        Assert.assertEquals("Salvo com sucesso", testeTagsPage.getMensagemSucesso("//span[normalize-space()='Salvo com sucesso']"));
    }

}
