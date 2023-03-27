package controllers;

import config.Conf;
import helper.ApplicationHelper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.config.HeaderConfig.headerConfig;

public class BaseController<T> {
    private String authValue = null;

    protected RequestSpecification baseClient(final String targetPath, final String baseUri) {
        return baseClientSelect(targetPath, baseUri);
    }

    protected RequestSpecification baseClientSelect(final String targetPath, final String baseUri) {
        var baseService = given()
                .config(RestAssuredConfig.config()
                        .headerConfig(headerConfig().overwriteHeadersWithName("Authorization", "Content-Type")))
                .log().all()
                .baseUri(baseUri)
                .basePath(targetPath)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .filters(new AllureRestAssured());

        if (authValue != null) {
            baseService.header("Authorization", authValue);
        }

        return baseService;
    }

    public T withToken() {
        authValue = getToken();
        return (T) this;
    }

    public synchronized String getToken() {
        if (authValue == null) {
            authValue = ApplicationHelper.getCurrentToken();
        }
        return authValue;
    }
}
