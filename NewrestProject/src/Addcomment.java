import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

public class Addcomment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SessionFilter session=new SessionFilter();
		String expectedMessage="this is expected comment";
		
		
		RestAssured.baseURI="http://localhost:8081";
		given().log().all().header("Content-Type","application/json").body("{\r\n" +

"    \"username\": \"RuchitaBiradar\",\r\n" +

"    \"password\": \"Jira@123\"\r\n" +"}")
		.filter(session).when()
		.log().all().post("/rest/auth/1/session").then().extract().response().asString();
		
	String addCommentResponse=given().pathParam("key","10001").header("Content-Type","application/json").body("{\r\n"
				+ "    \"body\": \""+expectedMessage+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("/rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath js2=new JsonPath(addCommentResponse);
		String commentId= js2.getString("id");
		System.out.println(commentId);

		given().pathParam("key","10001").filter(session).multiPart("file",new File("abc.txt")).
		header("X-Atlassian-Token","no-check")
		.when().post("/rest/api/2/issue/{key}/attachments")
		.then().assertThat().statusCode(200);
		
		String responsejson=given().log().all().queryParam("fields", "comment").pathParam("key","10001").filter(session).when()
		.get("/rest/api/2/issue/{key}").then().log().all().extract().response().asString();
	    System.out.println(responsejson);
	   
		}

}
