import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class GoRestTest {

    @Test
    public void goRest_1(){

        String format ="json";
        int page =  7;
        given()
                .param("_format",format)
                .param("page",page)
                .when()
                .get("https://gorest.co.in/public-api/users")
                .then()
                .log().body()
                .body("meta.pagination.page",equalTo(page))
                ;
    }

    @Test
    public void goRest_2(){

        String format ="json";
     //   int page = 7;
        for (int page= 1; page <10 ; page++) {
            given()
                    .param("_format",format)
                    .param("page",page)
                    .when()
                    .get("https://gorest.co.in/public-api/users")
                    .then()
                    .log().body()
                    .body("meta.pagination.page",equalTo(page))
            ;
        }
    }
}
