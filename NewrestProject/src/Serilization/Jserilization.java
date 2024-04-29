package Serilization;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class Jserilization {

	public static void main(String[] args) {
		
		
		AddPlace p=new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, Uk 09");
		p.setLanguage("French-IN");
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		
		List<String>mylist=new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		p.setTypes(mylist);
		
	Location l1=new Location();
	l1.setLag(33.427362);
	l1.setLat(-38.383494);
	p.setLocation(l1);
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		Response response1=given().log().all().queryParam("key", "qaclick123").body(p).when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
		.extract().response();
		String abc=response1.asString();
		
		System.out.println(abc);

	}

}
