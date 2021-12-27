package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;

public class BraveNewCoinAPISteps {

  private static RequestSpecification request;
  private Response response;
  private ValidatableResponse json;

  @Given("^I have a valid API key for the (.+) URI$")
  public void iSetTheRequestParams(String URI) {
    request = given()
        .header("X-RapidAPI-Key", "5fae973345msh786ac23f78ec4e5p128d2cjsn8cee02ca3f85")
        .header("X-RapidAPI-Host", "bravenewcoin.p.rapidapi.com")
        .contentType(ContentType.JSON)
        .baseUri(URI)
        .log()
        .all();
  }

  @When("^I send a POST request with a valid (.+) payload to the /oauth/token endpoint$")
  public void sendPOSTRequest(String endpoint, String payload) {
    File requestBody = new File("src/test/resources/Payloads/" + payload + ".json");
    response = request
        .when()
        .body(requestBody)
        .post(endpoint)
        .prettyPeek();
  }

  @Then("^I can validate I receive a valid token in the response$")
  public void validateTheToken() {

  }
}
