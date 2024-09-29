package backend;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestApiTest {
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
}
