package Academy.RestAPI3011;

import static io.restassured.RestAssured.*; //This import is for given()
import static org.hamcrest.Matchers.*; //This import is for equalTo()

import io.restassured.path.json.JsonPath;


public class OAuthTest56 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		//For getting a Code
		String codeNeeded = "4%2F0AfgeXvtqFSHCWaWL5Sue01eyeMVbXcSqUA9CMOwZ0qFTNuNIr0EPZRJaPywGst40YVOl_A";
		
		
		
		//ExchangeCode
		String responseExchangeCode = given().log().all().urlEncodingEnabled(false).queryParams("code", codeNeeded)
							.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
							.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
							.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
							.queryParams("grant_type", "authorization_code")
		.when().post("https://www.googleapis.com/oauth2/v4/token")
		.then().log().all().assertThat().statusCode(200).body("token_type", equalTo("Bearer")).extract().response().asString();
		
		JsonPath jp03 = new JsonPath(responseExchangeCode);
		String accessToken = jp03.get("access_token");
		
		
		
		
		//ActualRequestForRHA.com
		String responseActualRequest = given().log().all().queryParam("access_token", accessToken)
		.when().get("https://rahulshettyacademy.com/getCourse.php")
		.then().log().all().assertThat().statusCode(200)  // .body("url", equalTo("rahulshettycademy.com"))
		.header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
	}

}
