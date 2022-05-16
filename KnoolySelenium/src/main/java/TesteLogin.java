import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteLogin {
	
	private WebDriver browser;
	private DSL dsl;
	private TesteLoginPage testeLoginPage;

	@Before
	public void inicializa() {
		browser = new ChromeDriver();
		browser.manage().window().setPosition(new Point(350, 50));
		browser.manage().window().setSize(new Dimension(1280, 900));
		browser.get("https://knooly-qa.azurewebsites.net/#/login");
		dsl = new DSL(browser);
		testeLoginPage = new TesteLoginPage(browser);
	}
	
	@After
	public void finaliza() {
		browser.quit();
	}
	
	@Test
	public void acessarTelaLoginValido() {
		// Verificando os dados de login
		testeLoginPage.setEmail("luiz.filho@keeggo.com");
		testeLoginPage.setPassword("Knooly123");
		testeLoginPage.logar();
		
		// Validando login
		Assert.assertEquals("luiz.filho@keeggo.com", testeLoginPage.obterEmailLogin());
		Assert.assertEquals("Knooly123", testeLoginPage.obterPasswordLogin());
	}
	
	@Test
	public void acessarTelaLoginInvalido() {
		testeLoginPage.setEmail("luiz.filho@keeggo.com");
		testeLoginPage.setPassword("Knooly321");
		testeLoginPage.logar();
		
		// Validando login
		Assert.assertEquals("luiz.filho@keeggo.com", testeLoginPage.obterEmailLogin());
		Assert.assertEquals("Knooly123", testeLoginPage.obterPasswordLogin());
	}
		
}