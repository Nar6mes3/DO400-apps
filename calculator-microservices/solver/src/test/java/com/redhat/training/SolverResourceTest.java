package com.redhat.training;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(SolverResource.class)
public class SolverResourceTest {

    @Test
    public void simpleSumTest() {
        given()
                .pathParam("lhs", "2")
                .pathParam("rhs", "3")
        .when()
                .get("/{lhs}+{rhs}")
        .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(is("5.0"));
    }

    @Test
    public void simpleMultiplicationTest() {
        given()
                .pathParam("lhs", "2")
                .pathParam("rhs", "3")
        .when()
                .get("/{lhs}*{rhs}")
        .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(is("6.0"));
    }

    @Test
    public void invalidValuesTest() {
        given()
                .pathParam("lhs", "a")
                .pathParam("rhs", "3")
        .when()
                .get("/{lhs}*{rhs}")
        .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode());
    }
}
