package Serilization;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.RequestBuilder;

public class Specclass {

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
		
	RequestSpecification req=new RequestSpecBuilder().addQueryParam("key", "qaclick123").build();
		
	ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).build();
		Response response1=given().log().all().spec(req).when().post("/maps/api/place/add/json").then().log().all().spec(res)
		.extract().response();
		String abc=response1.asString();
		
		System.out.println(abc);

	}

}
