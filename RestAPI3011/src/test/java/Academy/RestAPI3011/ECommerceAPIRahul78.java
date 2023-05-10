package Academy.RestAPI3011;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*; //This import is for given()
import static org.hamcrest.Matchers.*; //This import is for equalTo()

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import Academy.Files.Payload;
import Academy.Pojo.CreateOrder80;
import Academy.Pojo.LoginRequest78;
import Academy.Pojo.LoginResponsePayload78;
import Academy.Pojo.OrderCreatedDetails81;
import Academy.Pojo.Orders80;

public class ECommerceAPIRahul78 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LoginRequest78 lr = new LoginRequest78();
		lr.setUserEmail("unknownxjk@gmail.com");
		lr.setUserPassword("kojikurac123");
		
		
		RequestSpecification reqSB = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		
		
		//01. Login to the application
		LoginResponsePayload78 response01 = given().log().all().spec(reqSB)
		.body(lr)
		.when().post("/api/ecom/auth/login")
		.then().log().all().assertThat().statusCode(200).body("message", equalTo("Login Successfully")).extract().response().as(LoginResponsePayload78.class);
		
		String token = response01.getToken();
		String userID = response01.getUserId();
		System.out.println(token);
		System.out.println(userID);
		
		
		//02. Create a product
		RequestSpecification reqSB02 = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token).build();
		
		String response02 = given().log().all().spec(reqSB02)
				.param("productName", "qwertyJK")
				.param("productAddedBy", userID)
				.param("productCategory", "fashion")
				.param("productSubCategory", "shirts")
				.param("productPrice", "11500")
				.param("productDescription", "Addias Originals")
				.param("productFor", "women")
				.multiPart("productImage", new File("C:\\Users\\joko2909\\Desktop\\Prntscr\\nba.png"))		
				.when().post("/api/ecom/product/add-product")
				.then().log().all().assertThat().statusCode(201).body("message", equalTo("Product Added Successfully")).extract().response().asString();
		
		JsonPath jp02 = new JsonPath(response02);
		String productID = jp02.get("productId");
		System.out.println(productID);
		
		
		
		//03. Create Order
		//So, set everything in the child POJO class, create a List<name of the child POJO class> and add every object from the child POJO, in the parent POJO set that List
		CreateOrder80 co = new CreateOrder80();
		co.setCountry("Yugoslavia");
		co.setProductOrderedId(productID);
		
		List<CreateOrder80> myList = new ArrayList<CreateOrder80>();
		myList.add(co);
		
		Orders80 orders = new Orders80();
		orders.setOrders(myList);
		
		
		RequestSpecification reqSB03 = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token).setContentType(ContentType.JSON).build();
		
	/*	String response03 = given().log().all().spec(reqSB03)
		.body(orders)
		.when().post("/api/ecom/order/create-order")
		.then().log().all().assertThat().statusCode(201).body("message", equalTo("Order Placed Successfully")).extract().response().asString();
		
		System.out.println(response03);*/
		
		OrderCreatedDetails81 response03 = given().log().all().spec(reqSB03)
				.body(orders)
				.when().post("/api/ecom/order/create-order")
				.then().log().all().assertThat().statusCode(201).body("message", equalTo("Order Placed Successfully")).extract().response().as(OrderCreatedDetails81.class);
		
		List<String> productOrderID =  response03.getProductOrderId();
		System.out.println(productOrderID.get(0));
		productOrderID.get(0);
		
		List<String> ordersID = response03.getOrders();
		ordersID.get(0);
		
		//04. Delete Product
		RequestSpecification reqSB04 = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token).build();
		
		String response04 = given().log().all().spec(reqSB04).pathParam("productId", productOrderID.get(0))
		.when().delete("/api/ecom/product/delete-product/{productId}")
		.then().log().all().assertThat().statusCode(200).body("message", equalTo("Product Deleted Successfully")).extract().response().asString();
		
		String wantedMessage = "Product Deleted Successfully";
		JsonPath jp04 = new JsonPath(response04);
		String messageFromResponse = jp04.get("message");
		
		Assert.assertEquals(messageFromResponse, wantedMessage);
		
		
		//05. Delete Order
		RequestSpecification reqSB05 = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token).build();
		
		String response05 = given().relaxedHTTPSValidation().log().all().spec(reqSB05).pathParam("ordersID", ordersID.get(0)) //This .relaxedHTTPSValidation() bypasses SSL sertification
		.when().delete("/api/ecom/order/delete-order/{ordersID}")
		.then().log().all().assertThat().statusCode(200).body("message", equalTo("Orders Deleted Successfully")).extract().response().asString();
		
		
	}

}
