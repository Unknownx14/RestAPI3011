package Academy.RestAPI3011;

import org.testng.Assert;

import Academy.Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse24 extends Payload {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Payload pl =  new Payload();
		
		JsonPath jp = new JsonPath(pl.MockResponse());
		
		//01. Print the number of courses
		int numberOfCourses = jp.getInt("courses.size()");
		System.out.println(numberOfCourses);
		
		
		//02. Print the purchase amount
		int purchaseAmount = jp.get("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
		
		//03. Print the title of the first course
		String title01 = jp.get("courses[0].title");
		System.out.println(title01);
		
		
		//04. Print all courses titles and their respective prices
		for(int i=0; i<jp.getInt("courses.size()"); i++)
		{
			String titleForEveryCourse = jp.get("courses["+i+"].title");
			 int priceForEveryCourse = jp.getInt("courses["+i+"].price");
			 System.out.println(titleForEveryCourse+ " This is a title.");
			 System.out.println(priceForEveryCourse+ " This is a price.");
		}
		
		
		
		//05. Number of copies sold by the RPA course
		String wantedCourse = "RPA";
		for(int i=0; i<jp.getInt("courses.size()"); i++)
		{
			String titleForEveryCourse = jp.get("courses["+i+"].title");
			 	if(titleForEveryCourse.equalsIgnoreCase(wantedCourse))
			 	{
			 		int copiesForEveryCourse = jp.getInt("courses["+i+"].copies");
			 		System.out.println(copiesForEveryCourse+" This is the number of copies for the course "+wantedCourse);
			 		break;
			 	}
			 	
		}
		
		
		//06. Verify that sum of all course prices match the purchaseAmount
		int sumOfAll = 0;
		for(int i=0; i<jp.getInt("courses.size()"); i++)
		{
			
			 int priceForEveryCourse = jp.getInt("courses["+i+"].price");
			 int copiesForEveryCourse = jp.getInt("courses["+i+"].copies");
			 int purchasePerCourse = priceForEveryCourse*copiesForEveryCourse;
			 sumOfAll = sumOfAll + purchasePerCourse;
		}
		System.out.println(sumOfAll);
		
		Assert.assertEquals(sumOfAll, purchaseAmount);
		
		
	}

}
