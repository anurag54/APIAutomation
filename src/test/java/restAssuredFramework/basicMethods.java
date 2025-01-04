package restAssuredFramework;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 * given()
 * 
 *     content type, set cookies, add auth, add param, set headers info etc....
 * 
 * when()
 * 
 *  	get, post, put, delete
 * 
 * then()
 *      validate status code, extract response, extract header cookies & response body
 * 
 * 
 */


public class basicMethods {
	
	int id;
	
	@Test(priority=1)
	void getUser() {
		
		  given()
		   
		  .when()
		     .get("https://reqres.in/api/users?page=2")
		     
		 .then()
		      .statusCode(200)
		      .body("page",equalTo(2))
		      .log().all();
		
	}
	
	@Test(priority=2)
	void createUsers() {
		
		HashMap<String, String> data = new HashMap<String, String>();
		
	    data.put("name", "Anurag");
	    data.put("job","Trainer");
		
		id = given()
		 .contentType("application/json")
		 .body(data)
		
		.when()
		 .post("https://reqres.in/api/users")
		 .jsonPath().getInt("id");
		
//		.then()
//		 .statusCode(200)
//		 .log().all();
		
	}
	
	@Test(priority=3,dependsOnMethods= {"createUsers"})
	void updateUsers() {
		
		HashMap<String, String> data = new HashMap<String, String>();
		
	    data.put("name", "Ankita");
	    data.put("job","Manager");
		
		given()
		 .contentType("application/json")
		 .body(data)
		
		.when()
		 .put("https://reqres.in/api/users/"+id)
		
		.then()
		 .statusCode(200)
		 .log().all();
		
	}
	
	@Test(priority=4)
	void deleteUsers() {
		
		given()
		
		.when()
		 .delete("https://reqres.in/api/users/"+id)
		
		.then()
		 .statusCode(204)
		  .log().all();
		
	}
	 
}
