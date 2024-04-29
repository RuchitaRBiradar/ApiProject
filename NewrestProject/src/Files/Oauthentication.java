package Files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import Pojo.Pojoclassexample;

public class Oauthentication {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token";
		
		String getResponse=given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials").formParam("scope", "trust").log()
		.all().when().post().then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js=new JsonPath(getResponse);
		String accessToken=js.get("access_token");
		
		System.out.println(accessToken);
		
		Pojoclassexample finalResponse=given().queryParam("access_token",accessToken).when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").then()
	.assertThat().statusCode(401).extract().response().as(Pojoclassexample.class);
		
		System.out.println(finalResponse.getLinkedin());
	}

}
