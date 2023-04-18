package controllers;

import helper.ApplicationHelper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.config.HeaderConfig.headerConfig;

public class BaseController<T> {
    private String authValue = null;

    protected RequestSpecification baseClient(final String baseUrl, final String targetPath) {
        return baseClientSelect(baseUrl, targetPath);
    }

    protected RequestSpecification baseClientSelect(final String baseUrl, final String targetPath) {
        var baseService = given()
                .config(RestAssuredConfig.config()
                        .headerConfig(headerConfig().overwriteHeadersWithName("Authorization", "Content-Type")))
                .baseUri(baseUrl)
                .basePath(targetPath)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .filters(new AllureRestAssured())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());

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
