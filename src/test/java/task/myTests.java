package task;

import org.testng.annotations.Test;
import pojo.ToDo;

import static io.restassured.RestAssured.*;

public class myTests {

    @Test
    public void task1(){

        ToDo toDo=
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos/1")
                .then()
           //    .log().body()
              .extract().as(ToDo.class)
              ;

    //  System.out.println(toDo);
        System.out.println(toDo.getTitle());
        System.out.println(toDo.getId());
       System.out.println(toDo.isCompleted());
    }
    @Test
    public void task2Array(){

        ToDo toDo []=
                given()
                        .when()
                        .get("https://jsonplaceholder.typicode.com/todos")
                        .then()
                        //       .log().body()
                        .extract().as(ToDo [].class)
                ;

        for (int i = 0; i <toDo.length ; i++) {
            System.out.println(toDo[i]);

       //   System.out.println(toDo[i].getId() + "\n");
          //  System.out.println(toDo[i].getTitle() + "\n");
        }

    }
}
