package Academy.RestAPI3011;

import org.testng.Assert;
import org.testng.annotations.Test;

import Academy.Files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation29 extends Payload {

	
	@Test
	public void sumOfCourses()
	{
		Payload pl =  new Payload();
		
		JsonPath jp = new JsonPath(pl.MockResponse());
		
		//02. Print the purchase amount
		int purchaseAmount = jp.get("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
		
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
