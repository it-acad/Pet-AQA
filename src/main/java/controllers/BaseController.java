package controllers;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.config.HeaderConfig.headerConfig;

public class BaseController<T> {
    private String authValue = null;

    protected RequestSpecification baseClient(final String targetPath) {
        return baseClientSelect(targetPath);
    }

    protected RequestSpecification baseClientSelect(final String targetPath) {
        var baseService = given()
                .config(RestAssuredConfig.config()
                        .headerConfig(headerConfig().overwriteHeadersWithName("Authorization", "Content-Type")))
                .baseUri("https://petstore3.swagger.io")
                .basePath(targetPath)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .filters(new AllureRestAssured());

        if (authValue != null) {
            baseService.header("Authorization", authValue);
        }

        return baseService;
    }
}
