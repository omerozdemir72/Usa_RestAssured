import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.baseURI;

public class specification {

    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;


    @BeforeClass
    public void setup(){

        baseURI ="http://api.zippopotam.us";


        requestSpecification = new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .setAccept(ContentType.JSON)
                .build();



        responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .log(LogDetail.BODY)
                .build();

    }

    @Test
    public void test(){

        given()
                .spec(requestSpecification)
                .when()
                .get("/TR/01000")
                .then()

                .spec(responseSpecification)
        .body("places[0].'place name'",equalTo("Dervişler Köyü"))

        ;
    }
    @Test
    public void test2(){

        String extractedValue =given()
                .when()
                .get("/TR/01000")
                .then()
                .extract().path("places[0].'place name'")

                ;
        System.out.println(extractedValue);
        Assert.assertEquals(extractedValue,"Dervişler Köyü");
    }
    @Test
    public void test3(){

        String koy =given()
                .when()
                .get("/TR/01000")
                .then()
                .extract().response().jsonPath().getString("places[0].'place name'")
            //    .extract().response().jsonPath().
                //     .extract().path("places[0].'place name'")

                ;
        System.out.println("Köy:  " + koy);
        Assert.assertEquals(koy,"Dervişler Köyü");
    }




}
