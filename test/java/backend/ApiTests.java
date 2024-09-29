package backend;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTests {
    @Test
    public void myTest(){
        Response response = RestAssured
                .given()
                .when()
                .get("https://www.amazon.in/")
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void testAmazonSearchApi() {
        // Example API endpoint, please replace with actual API if available
        Response response = RestAssured.get("https://www.amazon.in/");
        Assert.assertEquals(response.getStatusCode(), 200);

        String responseBody = response.getBody().asString();
        System.out.println("Amazon API Response: " + responseBody);
    }

    @Test
    public void testFlipkartSearchApi() {
        // Example API endpoint, please replace with actual API if available
        Response response = RestAssured.get("https://www.flipkart.com/");
        Assert.assertEquals(response.getStatusCode(), 200);


        String responseBody = response.getBody().asString();
        System.out.println("Flipkart API Response: " + responseBody);
    }
}
