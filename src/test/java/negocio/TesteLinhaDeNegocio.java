package negocio;

import login.TesteLogin;
import login.TesteLoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
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
        testeLoginPage.preencherformularioLogin("luiz.filho@keeggo.com", "Knooly123");
        this.testeLinhaDeNegocioPage = testeLoginPage.efetuarLogin();
        testeLoginPage.aguardar(testeLoginPage.botaoValidarCodigo());
        testeLoginPage.setToken("654321");
        testeLoginPage.validarToken();

    }

}
