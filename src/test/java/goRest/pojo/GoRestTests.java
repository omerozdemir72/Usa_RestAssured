package goRest.pojo;

import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import pojo.ToDo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.List;

public class GoRestTests {

    private int id;
    @Test(enabled = false)
    public void getUserList(){
List<User> userList=
        given()
                .when()
                .get("https://gorest.co.in/public-api/users")
                .then()
               .log().body()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .body("data",not(empty()))
        .extract().jsonPath().getList("data",User.class)
                ;
        for (User user:userList) {
            System.out.println(user + "\n");
        }
    }
    @Test(enabled = false)
    public void getUsers2(){
        ExtractableResponse<Response> extractableResponse=
        given()
                .when()
                .get("https://gorest.co.in/public-api/users")
                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data",not(empty()))
                .extract();

        List<User> userList = extractableResponse.jsonPath().getList("data",User.class);
        int code = extractableResponse.jsonPath().getInt("code");
        int total = extractableResponse.jsonPath().getInt("meta.pagination.total");
        System.out.println(total);
        System.out.println(code);
        for (User user:userList) {
            System.out.println(user);
        }
    }

    @Test(priority = 1)
    public void CreateUser(){
 id=
        given()
                .header("Authorization","Bearer 1c4a732f4fefeb5b4b335ed36eea45888797ff613b7d116e3daa39afbf20b98f")
                .contentType(ContentType.JSON)
                .body("{\"name\":\"Ömer ÖZ\", \"email\":\""+getRandomEmail() +"\", \"gender\":\"Male\", \"status\":\"Active\"}")
                .post("https://gorest.co.in/public-api/users")
                .then()
                .statusCode(200)
                .body("code",equalTo(201))
 .extract().jsonPath().getInt("data.id");
    }
    private String getRandomEmail(){
        return RandomStringUtils.randomAlphabetic(8)+"@gmail.com";
    }

    @Test(priority = 2)
    public void getId(){

        given()
                .pathParam("id",id)
                .when()
                .get("https://gorest.co.in/public-api/users/{id}")
                .then()
                .statusCode(200)
                .body("data.id",equalTo(id));
        System.out.println(id);
    }

    @Test(priority = 3)
    public void update(){
        String updateText ="Update My Name";
        given()
                .header("Authorization","Bearer 1c4a732f4fefeb5b4b335ed36eea45888797ff613b7d116e3daa39afbf20b98f")
                .contentType(ContentType.JSON)
                .body("{\"name\":\"Update My Name\"}")
                .pathParam("id",id)
                .when()
                .put("https://gorest.co.in/public-api/users/{id}")
                .then()
                .statusCode(200)
                .body("data.id",equalTo(id))
                .body("data.name",equalTo(updateText))
                ;
    }

    @Test(priority = 4)
    public void delete(){

        given()
                .header("Authorization","Bearer 1c4a732f4fefeb5b4b335ed36eea45888797ff613b7d116e3daa39afbf20b98f")
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .when()
                .delete("https://gorest.co.in/public-api/users/{id}")
                .then()
                .statusCode(200)
                .body("code",equalTo(204))
                ;

    }
}
