package practice_Day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class diffWaysToCreatePostRequestBody {

	/*
	 * Different ways to create Post Request Body 
	 * 1) POST request body using HashMap
	 * 2) POST request body creation using Org.json 
	 * 3) POST request body creation using POJO class
	 * 4) POST request using external JSON file data
	 */
	

	// 1) POST request body using HashMap

	//@Test(priority=1)
	void testPOSTusingHashMap() 
	{
	  HashMap data = new HashMap();
			  
      data.put("name", "Ryan");
	  data.put("mobile", "9473943471");
      data.put("location", "France");
      
      String courseArr[] = {"C","C++"};
      
      data.put("courses", courseArr);
      
      given()
         .contentType("application/json")
         .body(data)
      
      .when()
         .post("http://localhost:3000/students")
       
      .then()
         .statusCode(201)
         .body("name", equalTo("Ryan"))
         .body("mobile", equalTo("9473943471"))
         .body("location", equalTo("France"))
         .body("courses[0]", equalTo("C"))
         .body("courses[1]", equalTo("C++"))
         .header("Content-Type", "application/json")
         .log().all();  
		
	}
	
	// 2) POST request body creation using Org.json

		//@Test(priority=1)
		void testPOSTusingJsonLibrary() 
		{
		 JSONObject data = new JSONObject();
				  
	      data.put("name", "Tyler");
		  data.put("mobile", "9473653471");
	      data.put("location", "Italy");
	      
	      String courseArr[] = {"Java","Python"};
	      
	      data.put("courses", courseArr);
	      
	      given()
	         .contentType("application/json")
	         .body(data.toString())
	      
	      .when()
	         .post("http://localhost:3000/students")
	       
	      .then()
	         .statusCode(201)
	         .body("name", equalTo("Tyler"))
	         .body("mobile", equalTo("9473653471"))
	         .body("location", equalTo("Italy"))
	         .body("courses[0]", equalTo("Java"))
	         .body("courses[1]", equalTo("Python"))
	         .header("Content-Type", "application/json")
	         .log().all();  
			
		}
		
		// 3) POST request body creation using POJO Class

	
		        //@Test(priority=1)
				void testPOSTusingPOJO() 
				{
			
				 POJO_PostRequest data = new POJO_PostRequest();
						  
			      data.setName("Billy");
				  data.setMobile("9483653471");
			      data.setLocation("Greece");
			      
			      String courseArr[] = {"Java","Python"};
			      
			      data.setCourses(courseArr);
			      
			      given()
			         .contentType("application/json")
			         .body(data)
			      
			      .when()
			         .post("http://localhost:3000/students")
			       
			      .then()
			         .statusCode(201)
			         .body("name", equalTo("Billy"))
			         .body("mobile", equalTo("9483653471"))
			         .body("location", equalTo("Greece"))
			         .body("courses[0]", equalTo("Java"))
			         .body("courses[1]", equalTo("Python"))
			         .header("Content-Type", "application/json")
			         .log().all();  
					
				}
			
		
		// 4) POST request body creation using external JSON File

		
			        //@Test(priority=1)
					void testPOSTusingExternalJSONFile() throws FileNotFoundException 
					{
				
					File f = new File(".\\body.json");
					
					FileReader fr = new FileReader(f);
					
					JSONTokener jt = new JSONTokener(fr);
					
					JSONObject data = new JSONObject(jt);
				      
				      given()
				         .contentType("application/json")
				         .body(data.toString())
				      
				      .when()
				         .post("http://localhost:3000/students")
				       
				      .then()
				         .statusCode(201)
				         .body("name", equalTo("Jason"))
				         .body("mobile", equalTo("8876543210"))
				         .body("location", equalTo("Delhi"))
				         .body("courses[0]", equalTo("Java"))
				         .body("courses[1]", equalTo("Javascript"))
				         .header("Content-Type", "application/json")
				         .log().all();  
						
					}
					
    // Delete Post Request 					
	
	@Test(priority=2)
	void testDelete() 
	{
		given()
		
		.when()
		    .delete("http://localhost:3000/students/939b")
		
		.then()
		    .statusCode(200);
	}

}
