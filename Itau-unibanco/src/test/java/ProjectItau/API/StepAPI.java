package ProjectItau.API;

import cucumber.api.java.pt.Dado;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class StepAPI {

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
      
      System.out.println(id);
     }finally {
    	 RestAssured.reset();
     }
    }
	
	@Dado("^Realizar response consulta$")
    public void realizar_response_consulta() throws Throwable {
     try {
    	 
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/employee/";
      
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
		RestAssured.baseURI = "http://dummy.restapiexample.com/public/api/v1/delete/";
      
      response = given().log().all()
    		  .contentType("application/json")
      .when().delete()
      .then().assertThat().statusCode(200).extract().response().asString();
      System.out.println(response);
    
     }finally {
    	 RestAssured.reset();
     }
    }
}
