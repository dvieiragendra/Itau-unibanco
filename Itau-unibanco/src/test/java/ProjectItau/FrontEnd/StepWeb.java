package ProjectItau.FrontEnd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class StepWeb 
{
	public WebDriver driver;
	public String email;
	public String print = "\\Users\\dvieirag\\Downloads\\PDF\\"; // Alterar caminho para criação do PDF
	Document document = new Document(PageSize.A1);
	Font f=new Font(FontFamily.TIMES_ROMAN,50.0f,Font.NORMAL,BaseColor.BLACK);
	
	private By signIn = By.xpath("//*[contains(text(),'Sign in')]");
	private By message = By.xpath("//*[@class='page-heading']");
	private By messageError = By.xpath("//*[@class='alert alert-danger']");
	private By criarConta = By.xpath("//*[@id='email_create']");
	private By btnCreate = By.xpath("//*[@id='SubmitCreate']");
	private By messageError1 = By.xpath("//*[@id='create_account_error']");
	private By sexo = By.xpath("//*[@id='id_gender1']");
	private By firstName = By.xpath("//*[@id='customer_firstname']");
	private By lastName = By.xpath("//*[@id='customer_lastname']");
	private By password = By.xpath("//*[@id='passwd']");
	private By comboDay = By.xpath("//*[@id='days']");
	private By comboMonths = By.xpath("//*[@id='months']");
	private By comboYears = By.xpath("//*[@id='years']");
	private By comboState = By.xpath("//*[@id='id_state']");
	private By comboCountry = By.xpath("//*[@id='id_country']");
	private By address1 = By.xpath("//*[@id='address1']");
	private By city = By.xpath("//*[@id='city']");
	private By postCode = By.xpath("//*[@id='postcode']");
	private By phone = By.xpath("//*[@id='phone_mobile']");
	private By alias = By.xpath("//*[@id='alias']");
	private By btnAccount = By.xpath("//*[@id='submitAccount']");
	private By signOut = By.xpath("//*[contains(text(),'Sign out')]");
	private By emailLogin = By.xpath("//*[@id='email']");
	private By logar = By.xpath("//*[@id='SubmitLogin']");
	
	 @Dado("^Abrir pagina \\\"([^\\\"]*)\\\"$")
	    public void abrir_pagina(String name) throws Throwable {
	        System.setProperty("webdriver.chrome.driver", ".//src//chromedriver.exe"); //chomedriver para a verão 84 do chrome
	        
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("http://automationpractice.com/index.php");
	        screenshot();
	        PdfWriter.getInstance(document, new FileOutputStream(print+name+".pdf"));
	        document.open();
	        document.add(new Paragraph("Abrir pagina",f));
	        AddImagePdf();
	    }

	    @Quando("^Clicar em Sign In$")
	    public void clicar_em_sign_in() throws Throwable {
	    	driver.findElement(signIn).click();
	    }

	    @Então("^Validar se login foi realizado com sucesso$")
	    public void validar_se_login_foi_realizado_com_sucesso() throws Throwable {
	    	screenshot();
	        document.add(new Paragraph("validar se login foi realizado com sucesso",f));
	        AddImagePdf(); 
	    	String text = driver.findElement(message).getText();
	    	System.out.println(text);
	    	if (text.equals("My ACCOUNT")) {
				System.out.println("Correto");
			}
	    	driver.quit();
	    	closePdf();
	    }

	    @Então("^Validar se login foi realizado sem sucesso$")
	    public void validar_se_login_foi_realizado_sem_sucesso() throws Throwable {
	    	screenshot();
	        document.add(new Paragraph("validar se não foi possivel realizar o login",f));
	        AddImagePdf(); 
	    	String text = driver.findElement(messageError).getText();
	    	System.out.println(text);
	    	if (text != null) {
				System.out.println("Correto");
			}
	    	driver.quit();
	    	closePdf();
	    }
	    
	    @E("^Fazer o cadastro na pagina$")
	    public void fazer_o_cadastro_na_pagina() throws Throwable {
	        screenshot();
	        document.add(new Paragraph("Clicar em sign in",f));
	        AddImagePdf();
	        
	        driver.findElement(criarConta).sendKeys("teste_teste@teste.com");
	        driver.findElement(btnCreate).click();
	       String text = driver.findElement(messageError1).getText();
	       if(text != null) {
	    	   Random random = new Random();
	    	   int numero = random.nextInt(100);
	    	   driver.findElement(criarConta).clear(); 
	    	   email = "teste_teste"+numero+"@teste.com";
	    	   driver.findElement(criarConta).sendKeys(email); 
	    	   driver.findElement(btnCreate).click();
	       }
	        Thread.sleep(8000);
	        screenshot();
	        document.add(new Paragraph("Fazer o cadastro na pagina",f));
	        AddImagePdf();
	        driver.findElement(sexo).click();
	        driver.findElement(firstName).sendKeys("Diego");
	        driver.findElement(lastName).sendKeys("Gendra");
	        driver.findElement(password).sendKeys("teste");
	        combo(comboDay, "10");
	        combo(comboMonths, "6");
	        combo(comboYears, "1995");
	        screenshot();
	        document.add(new Paragraph("Cadastro de Usuario",f));
	        AddImagePdf();		
	        driver.findElement(address1).sendKeys("Rua Teste");
	        driver.findElement(city).sendKeys("Teste");
	        combo(comboState, "5");
	        driver.findElement(postCode).sendKeys("00000");
	        combo(comboCountry, "21");
	        driver.findElement(phone).sendKeys("11973780998");
	        driver.findElement(alias).sendKeys("Teste");
	        screenshot();
	        document.add(new Paragraph("Finalização do Cadastro",f));
	        AddImagePdf();
	        driver.findElement(btnAccount).click();
	        Thread.sleep(6000);
	        driver.findElement(signOut).click();
	    }

	    @E("^Logar no site \"([^\"]*)\" \"([^\"]*)\"$")
	    public void logar_no_site(String Email, String Senha) throws Throwable {
	    	Thread.sleep(2000);
	    	screenshot();
	        document.add(new Paragraph("Logar no site",f));
	        AddImagePdf();
	        if (Email.equals("OK")) {
				driver.findElement(emailLogin).sendKeys(email);
				driver.findElement(password).sendKeys(Senha);
				driver.findElement(logar).click();
			}else if (Email.equals("invalido")) {
				driver.findElement(emailLogin).sendKeys("teste_teste@shsjs..s");
				driver.findElement(password).sendKeys(Senha);
				driver.findElement(logar).click();
			}else {
				driver.findElement(emailLogin).sendKeys("");
				driver.findElement(password).sendKeys("");
				driver.findElement(logar).click();
			}
	        Thread.sleep(3000);
	    }
    
	    public void AddImagePdf() throws IOException, DocumentException {
	        Image img = Image.getInstance(print+"screenshot.png");
	        
	        document.add(img);
	        File file = new File( print+"screenshot.png" ); 
	        file.delete();
	    }
	    
	    public void closePdf() {
	    	document.close();
	        System.out.println("Done");
	    }
	    
	    public void screenshot() throws IOException {
	    	File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    	FileUtils.copyFile(screenshot, new File(print+"screenshot.png"));
	    	
	    }
	    
	    public void combo(By campo, String value) {
	    	WebElement comboElement = driver.findElement(campo);
       		Select combo = new Select(comboElement);
       		combo.selectByValue(value);
	    }
}
