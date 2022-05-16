import java.time.Duration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


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
		// Enviando os dados de login
		testeLoginPage.setEmail("luiz.filho@keeggo.com");
		testeLoginPage.setPassword("Knooly123");
		testeLoginPage.logar();
		
		// Validando login valido
		Assert.assertEquals("luiz.filho@keeggo.com", testeLoginPage.obterEmailLogin());
		Assert.assertEquals("Knooly123", testeLoginPage.obterPasswordLogin());
	}
	
	@Test
	public void acessarTelaLoginInvalido() {
		// Enviando os dados de login
		testeLoginPage.setEmail("luiz.filho@keeggo.com");
		testeLoginPage.setPassword("Knooly321");
		testeLoginPage.logar();
		
		// Aguardando 3seg para que a mensagem de erro seja apresentada
		WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(3));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(testeLoginPage.elementoWeb())));
		
		// Validando login invalido
		Assert.assertEquals("luiz.filho@keeggo.com", testeLoginPage.obterEmailLogin());
		Assert.assertEquals("Não foi encontrado nenhum usuário ativo com as informações fornecidas", testeLoginPage.usuarioInvalido());
		
	}
		
}