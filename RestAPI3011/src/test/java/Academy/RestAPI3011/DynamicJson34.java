package Academy.RestAPI3011;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*; //This import is for given()
import static org.hamcrest.Matchers.*; //This import is for equalTo()

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Academy.Files.Payload;

public class DynamicJson34 extends Payload {

	Payload pl = new Payload();
	
	@Test(dataProvider="getData")
	public void AddBook(String isbnDataProv, String aisleDataProv)
	{
		
	RestAssured.baseURI = "https://rahulshettyacademy.com";
	
	String response01 = given().log().all().header("Content-Type", "application/json")
	.body(pl.addBookJson(isbnDataProv, aisleDataProv)) //Sending these parameters thorough a TC via the DataProvider
	.when().post("/Library/Addbook.php")
	.then().log().all().assertThat().statusCode(200).extract().response().asString();
	
	JsonPath jp = new JsonPath(response01);
	String bookID = jp.get("ID");
	System.out.println(bookID);
	
	
	}
	
	

	
	
	@DataProvider
	public Object[][] getData()
	{
		return new Object [][]  { {"def","123"},{"ghi", "456"} }; //Multi-dimensional array
	}
	
}
