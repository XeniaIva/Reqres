import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresTests {

    @Test
    void registerSuccessfulTest() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "pistol");

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody.toString())
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void registerUnsuccessfulTest() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "sydney@fife");

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody.toString())
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void createTest() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "leader");

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody.toString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void updatePutTest() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "zion resident");

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody.toString())
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }

    @Test
    void updatePatchTest() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "zion resident");

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody.toString())
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }
}

