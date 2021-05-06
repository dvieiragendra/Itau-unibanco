package ProjectItau.Web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class StepWeb 
{
	public WebDriver driver;
	public String nomeProduto;
	public String fornecedor;
	public String precoNormal;
	public String precoAssinante;
	
	@Before
	public void abrir_navegador(){
		System.setProperty("webdriver.chrome.driver", ".//src//chromedriver.exe"); //chomedriver para a verão 90 do chrome
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.petz.com.br/");
	}
	
	 @Dado("Produto {string}")
	    public void abrir_pagina(String produto) throws Throwable {
		 driver.findElement(By.xpath("//*[@class='inputPesquisa']")).sendKeys(produto);
		 driver.findElement(By.xpath("//*[@class='custom-icon icon-lupa']")).click();	        
	    }
	 
	 @Quando("clicar no terceiro item")
	 public void clicar_terceiro_item(){
		 driver.findElement(By.xpath("//*[@class='liProduct'][3]")).click();
	 }
	 
	 @E("Pegar informações do produto")
	 public void pegar_informacoes_produto(){
		 nomeProduto = driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();
		 fornecedor = driver.findElement(By.xpath("//*[@itemprop='brand'][1]/span")).getText();
		 precoNormal = driver.findElement(By.xpath("//*[@class='price-current']")).getText();
		 precoAssinante = driver.findElement(By.xpath("//*[@class='price-subscriber']")).getText();
		 
		 System.out.println(nomeProduto);
		 System.out.println(fornecedor);
		 System.out.println(precoNormal);
		 System.out.println(precoAssinante);
	 }

	 @E("clicar adicionar no carrinho")
	 public void adicionar_no_carrinho(){
		 driver.findElement(By.xpath("//*[@id='adicionarAoCarrinho']")).click();
	 }
	 
	 @Então("validar se os dados continuam identicos")
	 public void validar_dados() throws Exception{
		Thread.sleep(3500); 
		String nomeProd;
		String preco;
		nomeProd = driver.findElement(By.xpath("//*[@class='td-resumo']")).getText();
		preco = driver.findElement(By.xpath("//td[@class='preco']")).getText();
	
		if (nomeProd.contains(nomeProduto) && preco.equals(precoNormal)) {
			System.out.println("Validação do cenario correta.");
		}else{
			throw new Exception("Validação incorreta.");
		}
		
	 }
	 
	 @After
	 public void fecharNavegador(){
		 driver.quit();
	 }
	}