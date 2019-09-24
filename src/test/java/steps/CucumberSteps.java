package steps;


import com.socialnetworking.data.Post_Data;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import java.io.IOException;

public class CucumberSteps {

    static ValidatableResponse response = null;
    static Post_Data dataItem = null;



    @Before
    public void setup() throws IOException {

    }

    @Given("^I navigate to the api URI$")
    public void i_navigate_to_the_api_URI() {
        RestAssured.baseURI = "http://dummy.restapiexample.com";
    }

    private static Post_Data postData(String name, String salary, String age){
        Post_Data data = new Post_Data();
        data.setName(name);
        data.setSalary(salary);
        data.setAge(age);
        return data;

    }

    @When("^I make a post using these data \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void i_make_a_post_using_these_data(String name, String salary, String age)throws Throwable  {
                Post_Data data = postData(name, salary, age);
        dataItem = data;
        response = SerenityRest.given()
                .contentType(ContentType.JSON)
                .log().all()  //This will print the request information
                .when()
                .body(data)
                .post("/api/v1/create")
                .then()
                .log()
                .all()
                .statusCode(200);
    }
}



