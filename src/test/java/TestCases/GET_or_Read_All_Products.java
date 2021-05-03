package TestCases;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GET_or_Read_All_Products {

	
@Test	
	public void read_All_Products() {
	
	Response response =
	given()
	    .baseUri("http://techfios.com/api-prod/api/product")
	    .header("Content-Type","application/json").
	when()
	    .get("/read.php").
	then()
	    .extract().response();
	
	int statusCode = response.getStatusCode();
	System.out.println("Status Code:"  + statusCode );
	
	Assert.assertEquals(statusCode, 200);
 
 //	System.out.println("Response :" + response.getBody().prettyPrint());
	 
 }
}
