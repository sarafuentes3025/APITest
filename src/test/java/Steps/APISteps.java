package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class APISteps {

  private static RequestSpecification request;
  private Response response;
  private ValidatableResponse json;

  @Given("^I send a GET request to the (.+) URI$")
  public void sendGETRequest(String URI) {
    request = given()
        .baseUri(URI)
        .contentType(ContentType.JSON);
  }

  @Then("^I get a (\\d+) status code$")
  public void expectedStatusCode(int expectedStatusCode) {
    response = request
        .when()
        .get("/users/sarafuentes3025/repos");

    json = response.then().statusCode(expectedStatusCode);
  }

  @Then("^I validate there are (\\d+) items on the (.+) endpoint$")
  public void validateSize(int expectedSize, String endpoint) {
    response = request
        .when()
        .get(endpoint);

    List<String> jsonResponse = response.jsonPath().getList("$");
    int currentSize = jsonResponse.size();

    assertEquals(expectedSize, currentSize);
  }

  @Then("^I validate there is a value: (.+) for the response at (.+) endpoint$")
  public void validateValue(String expectedValue, String endpoint) {
    response = request
        .when()
        .get(endpoint);

    List<String> jsonResponse = response.jsonPath().getList("username");
    assertTrue(expectedValue + " value was not found.", jsonResponse.contains(expectedValue));
  }
}
