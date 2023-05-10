package Academy.RestAPI3011;

import static io.restassured.RestAssured.*; //This import is for given()
import static org.hamcrest.Matchers.*; //This import is for equalTo()

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import Academy.Pojo.GetCourse63;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;


public class OAuthAndPOJO65 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	/*	
		//Selenium for getCode API
		
		System.setProperty("webdriver.gecko.driver", "C:\\JK\\geckodriver-v0.31.0-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		
		String userEmail = "unknownxjk";
		String userPassword = "kojikurac123";
		
		WebElement emailBox = driver.findElement(By.cssSelector("#identifierId"));
		emailBox.sendKeys(userEmail);
		emailBox.sendKeys(Keys.ENTER);
		*/
		
		// Do manually getCode, log in and paste the url here
		String urlAfterLogin = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AfgeXvuUheUWDLefeh1beRqEoen1Tbxcl6OzKmEv-DrVg236TVEqBnOEUtteh2FB8AUhiA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		
		String [] splittedString = urlAfterLogin.split("=");
		System.out.println(splittedString[1]);
		String [] splittedString01 = splittedString[1].split("&");
		System.out.println(splittedString01[0]);
		

		
		
		//For getting a Code, just simply copy/paste
		String codeNeeded = "4%2F0AfgeXvtqFSHCWaWL5Sue01eyeMVbXcSqUA9CMOwZ0qFTNuNIr0EPZRJaPywGst40YVOl_A";
		
		
		
		//ExchangeCode
		String responseExchangeCode = given().log().all().urlEncodingEnabled(false).queryParams("code", splittedString01[0]) //This is to prevent REST assured to encode special characters
							.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
							.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
							.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
							.queryParams("grant_type", "authorization_code")
		.when().post("https://www.googleapis.com/oauth2/v4/token")
		.then().log().all().assertThat().statusCode(200).body("token_type", equalTo("Bearer")).extract().response().asString();
		
		JsonPath jp03 = new JsonPath(responseExchangeCode);
		String accessToken = jp03.get("access_token");
		
		
		
		
		//ActualRequestForRHA.com
		GetCourse63 responseActualRequest = given().log().all().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON) // to declare a format of the expected response .expect().defaultParser(Parser.JSON)
		.when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse63.class); //.asClass, use the parent class and change the type or "response" to the same class
		//There cannot be .log().all() in .when() if we do things like this .expect().defaultParser(Parser.JSON)
		//There cannot be .then() for some reason
	/*	.then().assertThat().statusCode(200)  // .body("url", equalTo("rahulshettycademy.com"))
		.header("Server", "Apache/2.4.41 (Ubuntu)");*/
		
		System.out.println(responseActualRequest.getLinkedIn());
		System.out.println(responseActualRequest.getUrl());
		
		
	}

}
