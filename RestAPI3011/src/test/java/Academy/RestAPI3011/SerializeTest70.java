package Academy.RestAPI3011;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*; //This import is for given()
import static org.hamcrest.Matchers.*; //This import is for equalTo()

import java.util.ArrayList;
import java.util.List;

import Academy.Pojo.AddPlace71;
import Academy.Pojo.Location;

public class SerializeTest70 {

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
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response01 = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(ap) //Place the java object as a body (json, payload)
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(response01);
		
		
		
	}

}
