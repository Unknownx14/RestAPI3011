package Academy.Files;

public class Payload {

	
	public String AddPlace()
	{
		String jsonBody= "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"RSA09\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"https://www.rahulshettyacademy.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}";
		
		return jsonBody;
	}
	
	
	public String MockResponse()
	{
		return  "{\r\n"
				+ "\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\r\n"
				+ "\"purchaseAmount\": 1460,\r\n"
				+ "\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "\"courses\": [\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\r\n"
				+ "\"price\": 50,\r\n"
				+ "\r\n"
				+ "\"copies\": 6\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\r\n"
				+ "\"price\": 40,\r\n"
				+ "\r\n"
				+ "\"copies\": 4\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\r\n"
				+ "\"price\": 45,\r\n"
				+ "\r\n"
				+ "\"copies\": 10\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "  {\r\n"
				+ "\r\n"
				+ "\"title\": \"Jmeter\",\r\n"
				+ "\r\n"
				+ "\"price\": 55,\r\n"
				+ "\r\n"
				+ "\"copies\": 10\r\n"
				+ "\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "]\r\n"
				+ "\r\n"
				+ "}";
	}
	
	public String addBookJson(String isbnJK, String aisleJK)
	{
		String body = "{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java01\",\r\n"
				+ "\"isbn\":\""+isbnJK+"\",\r\n"
				+ "\"aisle\":\""+aisleJK+"\",\r\n"
				+ "\"author\":\"Michael Williams\"\r\n"
				+ "}";
		return body;
	}
	
	
	public String deleteBookJson(String bookID)
	{
		String body = "{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+bookID+"\"\r\n"
				+ " \r\n"
				+ "} ";
		
		return body;
	}
	
	public String loginEcommerce(String Email, String password)
	{
		String body = "{\r\n"
				+ "\"userEmail\": \""+Email+"\", \r\n"
				+ "\"userPassword\": \""+password+"\"\r\n"
				+ "}";
		
		return body;
	}
	
}
