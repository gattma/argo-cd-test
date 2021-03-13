package at.gepaplexx;

import io.quarkus.test.junit.QuarkusTest;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ServiceAResourceTest {

    @Test
    @Disabled
    public void testHelloEndpoint() {
        given()
          .when().get("/serviceA")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

}