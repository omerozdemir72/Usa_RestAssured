import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;


public class zippoTest1 {


    @BeforeClass
    public void setup(){

        baseURI ="http://api.zippopotam.us";



    }
    @Test
    public void test(){

        given()
                .when()
                .get("/TR/01000")
                 .then()
                .statusCode(200)
                ;

    }

    @Test
    public void test2(){

        given()
                .when()
                .get("/TR/01000")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
        ;

    }
    @Test
    public void test3(){

        given()
            //    .log().all()
                .when()
                .get("/TR/01000")
                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
        ;

    }
    @Test
    public void test4(){

        given()
                //    .log().all()
                .when()
                .get("/TR/01000")
                .then()
                .body("country",equalTo("Turkey"))
        ;
    }
    @Test
    public void test5(){

        given()
                //    .log().all()
                .when()
                .get("/TR/01000")
                .then()
                .body("places[1].state",equalTo("Adana"))

        ;

    }
    @Test
    public void test6(){

        given()
                //    .log().all()
                .when()
                .get("/TR/01000")
                .then()
                .body("places[1].'place name'",equalTo("Camuzcu Köyü"))

        ;

    }
    @Test
    public void test7(){

        given()
                //    .log().all()
                .when()
                .get("/TR/01000")
                .then()
                .body("places[4].'place name'",equalTo("Sarihuğlar Köyü"))
        ;
    }
    @Test
    public void test8(){

        given()
                .when()
                .get("/TR/01000")
                .then()
                .body("places",hasSize(71))
        ;
    }

    @Test
    public void test9_pathParameters(){
        String tr ="TR";
        String zipCode="01000";
        given()

                //http://api.zippopotam.us/TR/01000
                .pathParam("country",tr)
                .pathParam("zipCode",zipCode)
                .log().uri()
                .when()
                .get("/{country}/{zipCode}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                ;
    }
//    @Test
//    public void test9_Parameters(){
//        String tr ="TR";
//        String zipCode="01000";
//        given()
//                .param("country",tr)
//                .param("zipCode",zipCode)
//                .log().uri()
//                .when()
//                .get()
//                .then()
//                .statusCode(200)
//                .contentType(ContentType.JSON)
//        ;
//    }




}
