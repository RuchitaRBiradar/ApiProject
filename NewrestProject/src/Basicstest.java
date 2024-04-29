import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import Files.Payload;
public class Basicstest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body(Payload.addplace()).when().post("/maps/api/place/add/json").then().statusCode(200).body("scope",equalTo("APP"))
		.header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
	    System.out.println(response);
	    JsonPath js=new JsonPath(response);
	    
	    String placeid=js.getString("place_id");
	    System.out.println(placeid);
	    String newAddress="Summer africa,UK";
	    
	    
	    given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body("{\r\n"
	    		+ "\"place_id\":\""+placeid+"\",\r\n"
	    		+ "\"address\":\""+newAddress+"\",\r\n"
	    		+ "\"key\":\"qaclick123\"\r\n"
	    		+ "}\r\n"
	    		+ "\r\n"
	    		+ "").when().put("/maps/api/place/update/json").then().assertThat().log().all().statusCode(200);
	    
	    String getnewaddress=given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeid).when().get("/maps/api/place/update/json").then().log().all()
	    .assertThat().statusCode(200).extract().response().asString();
	    
	    
	    JsonPath js1=new JsonPath(getnewaddress);
	    String newaddress1=js1.getString("address");
	    
	}
	

}
