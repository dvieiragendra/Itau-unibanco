package ProjectItau.API;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class StepAPI {

	public String response;
	public String idPet;
	public String idUser;

	
	@Dado("A criação de um usuario chamado {string}{string} com id {string}")
    public void criar_usuario(String firstname, String lastname, String id) throws Throwable {
     try {
		RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";
      
      response = given().log().all()
    		  .contentType("application/json")
    		  .body("{\n"
    		  		+ "    \"id\": "+id+",\n"
    		  		+ "    \"username\": \"<string>\",\n"
    		  		+ "    \"firstName\": \""+firstname+"\",\n"
    		  		+ "    \"lastName\": \""+lastname+"\",\n"
    		  		+ "    \"email\": \"teste@teste.com\",\n"
    		  		+ "    \"password\": \"teste\",\n"
    		  		+ "    \"phone\": \"<string>\",\n"
    		  		+ "    \"userStatus\": 0\n"
    		  		+ "    }")
      .when().post()
      .then().assertThat().statusCode(200).extract().response().asString();
      this.idUser = id;
      System.out.println(response);
      
     }finally {
    	 RestAssured.reset();
     }
    }
	
	@E("A criação de um pet chamado {string} do tipo {string} com id {string}")
	public void criar_pet(String nomePet, String tipoPet, String id) {
		 try {
				RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";
		      
		      response = given().log().all()
		    		  .contentType("application/json")
		    		  .body("{\n"
		    		  		+ "    \"name\": \""+nomePet+"\",\n"
		    		  		+ "    \"photoUrls\": [\n"
		    		  		+ "        \"<string>\",\n"
		    		  		+ "        \"<string>\"\n"
		    		  		+ "    ],\n"
		    		  		+ "    \"id\": "+id+",\n"
		    		  		+ "    \"category\": {\n"
		    		  		+ "        \"id\": 0,\n"
		    		  		+ "        \"name\": \""+tipoPet+"\"\n"
		    		  		+ "    },\n"
		    		  		+ "    \"tags\": [\n"
		    		  		+ "        {\n"
		    		  		+ "            \"id\": 0,\n"
		    		  		+ "            \"name\": \"<string>\"\n"
		    		  		+ "        },\n"
		    		  		+ "        {\n"
		    		  		+ "            \"id\": 0,\n"
		    		  		+ "            \"name\": \"<string>\"\n"
		    		  		+ "        }\n"
		    		  		+ "    ],\n"
		    		  		+ "    \"status\": \"available\"\n"
		    		  		+ "}")
		      .when().post()
		      .then().assertThat().statusCode(200).extract().response().asString();
		      this.idPet = id;
		      System.out.println(response);
		      
		     }finally {
		    	 RestAssured.reset();
		     }
	}

	@Quando("Realizar a venda do pet {string} para o usuario {string}")
	public void realizar_venda_do_pet(String user, String pet) {
		try {
			RestAssured.baseURI = "https://petstore.swagger.io/v2/store/order";
	      
	      response = given().log().all()
	    		  .contentType("application/json")
	    		  .body("{\n"
	    		  		+ "    \"id\": "+user+",\n"
	    		  		+ "    \"petId\": "+pet+",\n"
	    		  		+ "    \"quantity\": 0,\n"
	    		  		+ "    \"shipDate\": \"2021-05-03T20:17:29.569Z\",\n"
	    		  		+ "    \"status\": \"placed\",\n"
	    		  		+ "    \"complete\": true\n"
	    		  		+ "}")
	      .when().post()
	      .then().assertThat().statusCode(200).extract().response().asString();
	      System.out.println(response);
	      
	     }finally {
	    	 RestAssured.reset();
	     }
	}
	
	@E("Mudar o status da ordem de venda para {string}")
	public void alterar_status(String novoStatus) {
		try {
			RestAssured.baseURI = "https://petstore.swagger.io/v2/store/order";
	      
	      response = given().log().all()
	    		  .contentType("application/json")
	    		  .body("{\n"
	    		  		+ "    \"id\": "+this.idUser+",\n"
	    		  		+ "    \"petId\": "+this.idPet+",\n"
	    		  		+ "    \"quantity\": 0,\n"
	    		  		+ "    \"shipDate\": \"2021-05-03T20:17:29.569Z\",\n"
	    		  		+ "    \"status\": \""+novoStatus+"\",\n"
	    		  		+ "    \"complete\": true\n"
	    		  		+ "}")
	      .when().post()
	      .then().assertThat().statusCode(200).extract().response().asString();
	      System.out.println(response);
	      
	     }finally {
	    	 RestAssured.reset();
	     }
	}
	
	@Então("Consultar se a ordem gerada esta correta")
	public void consultar_ordem_gerada() throws Exception {
		try {
	      
	      response = given().pathParam("orderId", this.idUser).log().all()
	    		  .contentType("application/json")
	      .when().get("https://petstore.swagger.io/v2/store/order/{orderId}")
	      .then().assertThat().statusCode(200).extract().response().asString();
	      System.out.println(response);
	      
	      JsonPath js = new JsonPath(response);
	      String id = js.getString("status");
	      
	      if (id.equals("delivered") || id.equals("approved")) {
			System.out.println("Mensagem correta!!");
		}else {
			throw new Exception("status não correto");
		}
	      
	     }finally {
	    	 RestAssured.reset();
	     }
	}
	
	@E("A criação de um pet do tipo cat chamado {string} do tipo {string} com id {string}")
	public void criar_pet_cat(String nomePet, String tipoPet, String id) {
		 try {
				RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";
		      
		      response = given().log().all()
		    		  .contentType("application/json")
		    		  .body("{\n"
		    		  		+ "    \"name\": \""+nomePet+"\",\n"
		    		  		+ "    \"photoUrls\": [\n"
		    		  		+ "        \"<string>\",\n"
		    		  		+ "        \"<string>\"\n"
		    		  		+ "    ],\n"
		    		  		+ "    \"id\": "+id+",\n"
		    		  		+ "    \"category\": {\n"
		    		  		+ "        \"id\": 0,\n"
		    		  		+ "        \"name\": \""+tipoPet+"\"\n"
		    		  		+ "    },\n"
		    		  		+ "    \"tags\": [\n"
		    		  		+ "        {\n"
		    		  		+ "            \"id\": 0,\n"
		    		  		+ "            \"name\": \"<string>\"\n"
		    		  		+ "        },\n"
		    		  		+ "        {\n"
		    		  		+ "            \"id\": 0,\n"
		    		  		+ "            \"name\": \"<string>\"\n"
		    		  		+ "        }\n"
		    		  		+ "    ],\n"
		    		  		+ "    \"status\": \"available\"\n"
		    		  		+ "}")
		      .when().post()
		      .then().assertThat().statusCode(200).extract().response().asString();
		      this.idPet = id;
		      System.out.println(response);
		      
		     }finally {
		    	 RestAssured.reset();
		     }
	}
	
	@E("Mudar o status da ordem de venda do cat para {string}")
	public void alterar_status_cat(String novoStatus) {
		try {
			RestAssured.baseURI = "https://petstore.swagger.io/v2/store/order";
	      
	      response = given().log().all()
	    		  .contentType("application/json")
	    		  .body("{\n"
	    		  		+ "    \"id\": "+this.idUser+",\n"
	    		  		+ "    \"petId\": "+this.idPet+",\n"
	    		  		+ "    \"quantity\": 0,\n"
	    		  		+ "    \"shipDate\": \"2021-05-03T20:17:29.569Z\",\n"
	    		  		+ "    \"status\": \""+novoStatus+"\",\n"
	    		  		+ "    \"complete\": true\n"
	    		  		+ "}")
	      .when().post()
	      .then().assertThat().statusCode(200).extract().response().asString();
	      System.out.println(response);
	      
	     }finally {
	    	 RestAssured.reset();
	     }
	}
	
	
	
}
