package TestCases;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GET_or_Read_A_Product {
	
	SoftAssert softAssert = new SoftAssert();
	

	@Test
	public void read_All_Products() {

		Response response = given().baseUri("http://techfios.com/api-prod/api/product")
				.header("Content-Type", "application/json").queryParams("id", "1519").when().get("/read_one.php").then()
				.extract().response();

		int statusCode = response.getStatusCode();
		System.out.println("Status Code:" + statusCode);
		//Assert.assertEquals(statusCode, 200);
		softAssert.assertEquals(statusCode, 200);

		long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("Response Time: " + responseTime);

		if (responseTime <= 2000) {
			System.out.println("Response time is within range!");

		} else {
			System.out.println("Response range is out of range!");
	}
      
		String responseBody = response.getBody().asString();
		System.out.println("Response Body: "  +responseBody);
		
		JsonPath js = new JsonPath(responseBody);
		String porductId = js.getString("id");
		System.out.println("Product Id:"  + porductId);
		Assert.assertEquals(porductId, "1519");
		
		String productPrice = js.getString("price");
		System.out.println("Product Price:"   + productPrice);
		Assert.assertEquals(productPrice, "699");
		
		String productName = js.getString("name");
		System.out.println("Product Name:"   + productName);
		Assert.assertEquals(productName,"Best Car Wash New Location");
		
		
		softAssert.assertAll();
		
		// System.out.println("Response :" + response.getBody().prettyPrint());

	}
}
