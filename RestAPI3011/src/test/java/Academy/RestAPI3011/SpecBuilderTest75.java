package Academy.RestAPI3011;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*; //This import is for given()
import static org.hamcrest.Matchers.*; //This import is for equalTo()

import java.util.ArrayList;
import java.util.List;

import Academy.Pojo.AddPlace71;
import Academy.Pojo.Location;

public class SpecBuilderTest75 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		//This is the serialization process for creating a json file from a java object
		AddPlace71 ap = new AddPlace71();
		ap.setAccuracy(50);
		ap.setAddress("29, side layout, cohen 09");
		ap.setName("RSA");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("https://www.rahulshettyacademy.com");
		ap.setLanguage("French-IN");
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		
		ap.setTypes(myList);
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		ap.setLocation(l);
		
		
		//Request Spec Builder
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		// RequestSpecification req for RequestSpecBuilder()
		
		//Response Spec Builder
		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		
		//RestAssured.baseURI = "https://rahulshettyacademy.com"; //Not needed when we have RequestSpecification req
		
		RequestSpecification response01 = given().log().all().spec(req) //.spec(req) instead of baseURI, QueryParam, header ContentType
		.body(ap); //Place the java object as a body (json, payload)
		
		
		
		String response02 = response01.when().post("maps/api/place/add/json")
		.then().log().all().spec(res).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(response02);
		
		
		
	}

}
