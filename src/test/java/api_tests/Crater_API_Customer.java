package api_tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Crater_API_Customer {
    String baseUrl = "http://crater.primetech-apps.com/";
    String token;
    Response response;

    @Test
    public void craterAppLogin() {
        String endPoint = "api/v1/auth/login";//path
        String userEmail = "entityadmin@primetechschool.com";
        String userPassword = "primetech@school";

        Map<String, String> requestHeaders = new HashMap<>();//For Map also, we need to use the  entire wording, and capitalize the First Letter.
        requestHeaders.put("Content-Type", "application/json");
        requestHeaders.put("Accept", "application/json");
        requestHeaders.put("company", "1");

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", userEmail);
        requestBody.put("password", userPassword);
        requestBody.put("device_name", "mobile_app");

        response = RestAssured.given()
                .headers(requestHeaders)
                .body(requestBody)
                .when()
                .post(baseUrl + endPoint);
        response.prettyPrint();
        response.then().statusCode(200);
        token = response.path("token");
        System.out.println("Token is : " + token);
    }
    @Test(dependsOnMethods = "craterAppLogin")
    public void create_a_customer(){
        Faker faker = new Faker();//class

        String endPoint = "api/v1/customers";//Given
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Content-Type", "application/json");
        requestHeaders.put("Accept", "application/json");
        requestHeaders.put("Authorization", "Bearer " +token);
        requestHeaders.put("company", "1");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("email", email);
        requestBody.put("enable_portal", true);
        requestBody.put("billing", new ArrayList<>()); // Empty array
        requestBody.put("shipping", new ArrayList<>()); // Empty array

        response = RestAssured.given()
                .headers(requestHeaders)
                .body(requestBody)
                .when()
                .post(baseUrl+endPoint);

        response.then().statusCode(200);
        response.prettyPrint();
    }




}