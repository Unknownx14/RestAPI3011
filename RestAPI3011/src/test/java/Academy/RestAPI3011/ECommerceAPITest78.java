package Academy.RestAPI3011;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*; //This import is for given()
import static org.hamcrest.Matchers.*; //This import is for equalTo()

import Academy.Files.Payload;

public class ECommerceAPITest78 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Payload pl = new Payload();
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//01. Login to the application
		String response01 = given().log().all().header("Content-Type", "application/json")
		.body(pl.loginEcommerce("unknownxjk@gmail.com", "kojikurac123"))
		.when().post("/api/ecom/auth/login")
		.then().log().all().assertThat().statusCode(200).body("message", equalTo("Login Successfully")).extract().response().asString();
		
		JsonPath jp01 = new JsonPath(response01);
		String userID = jp01.get("userId");
		String token = jp01.get("token");
		System.out.println(userID);
		System.out.println(token);
		
		
		//02. Create a product
		String response02 = given().log().all().header("Authorization", token)
				.param("productName", "qwertyJK")
				.param("productAddedBy", userID)
				.param("productCategory", "fashion")
				.param("productSubCategory", "shirts")
				.param("productPrice", "11500")
				.param("productDescription", "Addias Originals")
				.param("productFor", "women")
				
				.when().post("/api/ecom/auth/login")
				.then().log().all().assertThat().statusCode(200).body("message", equalTo("Login Successfully")).extract().response().asString();
	
		
	}

}
