package Academy.RestAPI3011;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*; //This import is for given()
import static org.hamcrest.Matchers.*; //This import is for equalTo()

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import Academy.Files.Payload;


public class StaticJosnFromExternalFile38 extends Payload {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Payload pl = new Payload();
		
		//01. Validate AddPlace API
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\joko2909\\Desktop\\Prntscr\\addBook.json")))) // Convert the Json content into bytes, then those bytes into the String
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
	/*	
		//02. AddPlace, then UpdatePlace with a new address, then GetPlace to verify whether the new address is in place
		
		JsonPath jp = new JsonPath(response); //For parsing a json file
		String placeID = jp.get("place_id");
		System.out.println(placeID);
		
		String newAddress = "Maple road 101, USA";
		
		given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID).header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		String responseGetPlace = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).body("address", equalTo(newAddress)).extract().response().asString();
		
		JsonPath jp02 = new JsonPath(responseGetPlace); //For parsing a json file
		 String extractedAddress =jp02.get("address");
		 Assert.assertEquals(extractedAddress, newAddress);
		 */
	}

}
