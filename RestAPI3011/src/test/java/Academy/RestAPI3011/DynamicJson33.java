package Academy.RestAPI3011;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*; //This import is for given()
import static org.hamcrest.Matchers.*; //This import is for equalTo()

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Academy.Files.Payload;

public class DynamicJson33 extends Payload {

	Payload pl = new Payload();
	
	@Test
	public void AddBook()
	{
		
	RestAssured.baseURI = "https://rahulshettyacademy.com";
	
	String response01 = given().log().all().header("Content-Type", "application/json")
	.body(pl.addBookJson("abc", "555")) //Sending these parameters thorough a TC, not hardcoding it into the Payload file
	.when().post("/Library/Addbook.php")
	.then().log().all().assertThat().statusCode(200).extract().response().asString();
	
	JsonPath jp = new JsonPath(response01);
	String bookID = jp.get("ID");
	System.out.println(bookID);
	
	
	}
	

	
}
