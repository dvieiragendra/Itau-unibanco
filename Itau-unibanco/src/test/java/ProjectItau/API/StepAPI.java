package ProjectItau.API;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;

import org.apache.commons.io.IOUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class StepAPI {

	public String pasta = "C:\\Users\\Diego\\Desktop\\Diego\\evidencia\\"; // Alterar caminho para criação do PDF
	public String response;

	
	@Dado("^Realizar response create$")
    public void realizar_response_create() throws Throwable {
     try {
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1/create";
      
      response = given().log().all()
    		  .contentType("application/json")
    		  .body("	{\r\n" + 
      		"        \"name\":\"test\",\r\n" + 
      		"        \"salary\":\"3200\",\r\n" + 
      		"        \"age\":\"23\"\r\n" + 
      		"    }")
      .when().post()
      .then().assertThat().statusCode(200).extract().response().asString();
      System.out.println(response);
      
      JsonPath js = new JsonPath(response);
      String id = js.getString("data.id");
      
      FileWriter  arquivo = new FileWriter(new File(pasta+"Arquivo.txt"));
		arquivo.write(id);
		arquivo.close();
      
      System.out.println(id);
     }finally {
    	 RestAssured.reset();
     }
    }
	
	@Dado("^Realizar response consulta$")
    public void realizar_response_consulta() throws Throwable {
     try {
    	 FileInputStream inputStream = new FileInputStream(pasta+"Arquivo.txt");
    	 @SuppressWarnings("deprecation")
		String texto = IOUtils.toString(inputStream);
    	 System.out.println(texto);
    	 inputStream.close();
    	 
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/employee/"+texto;
      
      response = given().log().all()
    		  .contentType("application/json")
      .when().get()
      .then().assertThat().statusCode(200).extract().response().asString();
      System.out.println(response);
    
     }finally {
    	 RestAssured.reset();
     }
    }
	
	@Dado("^Realizar response delete$")
    public void realizar_response_delete() throws Throwable {
     try {
    	 FileInputStream inputStream = new FileInputStream(pasta+"Arquivo.txt");
    	 @SuppressWarnings("deprecation")
		String texto = IOUtils.toString(inputStream);
    	 System.out.println(texto);
    	 inputStream.close();
    	 
		RestAssured.baseURI = "http://dummy.restapiexample.com/public/api/v1/delete/"+texto;
      
      response = given().log().all()
    		  .contentType("application/json")
      .when().delete()
      .then().assertThat().statusCode(200).extract().response().asString();
      System.out.println(response);
      
      File file = new File(pasta+"Arquivo.txt"); 
      file.delete();
    
     }finally {
    	 RestAssured.reset();
     }
    }
	
	@Então("^Gerar relatorio \"([^\"]*)\" \"([^\"]*)\"$")
	public void gerar_relatorio_something(String message,String name) throws DocumentException, FileNotFoundException {
		Document document = new Document(PageSize.A4);
		Font f=new Font(FontFamily.TIMES_ROMAN,30.0f,Font.NORMAL,BaseColor.RED);
		Font g=new Font(FontFamily.TIMES_ROMAN,15.0f,Font.NORMAL,BaseColor.BLACK);
		PdfWriter.getInstance(document, new FileOutputStream(pasta+name+".pdf"));
		document.open();
		document.add(new Paragraph(message,f));
		document.add(new Paragraph(response,g));
		 
        document.close();
        System.out.println("Done");
	}
}
