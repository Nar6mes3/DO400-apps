package com.redhat.shopping.integration.blackbox;

import com.redhat.shopping.cart.AddToCartCommand;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class ShoppingCartTest {

    private int randomQuantity() {
        return (new Random()).nextInt(10) + 1;
    }

    private void addProductToTheCartWithIdAndRandomQuantity(int productId) {
        AddToCartCommand productToAdd = new AddToCartCommand(
                productId,
                this.randomQuantity()
        );

        given()
                .contentType("application/json")
                .body(productToAdd)
                .put("/cart");
    }

    @BeforeEach
    public void clearCart() {
        delete("/cart");
    }

    @Test
    public void removingNonExistingProductInCatalogReturns400() {
        given()
                .pathParam("id", 9999)
        .when()
                .delete("/cart/products/{id}")
        .then()
                .statusCode(400);
    }

    @Test
    public void removingNonAddedProductToTheCartReturns404() {
        given()
                .pathParam("id", 1)
        .when()
                .delete("/cart/products/{id}")
        .then()
                .statusCode(404);
    }

    @Test
    public void removingTheOnlyProductInCartReturns204() {
        this.addProductToTheCartWithIdAndRandomQuantity(1);

        given()
                .pathParam("id", 1)
        .when()
                .delete("/cart/products/{id}")
        .then()
                .statusCode(204);
    }

    /*Add a test to cover the process of removing a product from the cart when the product is already in the cart and is not the only one stored. The specification for the test is:

    Given an existing product and a cart with other products in it.

    When the product is removed from the cart.

    Then the status code of the response is 200.*/

    @Test
    public void whatever(){
        this.addProductToTheCartWithIdAndRandomQuantity(1);
        this.addProductToTheCartWithIdAndRandomQuantity(2);
        given()
                .pathParam("id", 2)
        .when()
                .delete("/cart/products/{id}")
        .then()
                .statusCode(200);
    }
}
