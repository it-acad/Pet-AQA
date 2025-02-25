package helper;

import config.AmadeusToken;
import config.Conf;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class TokenPreparation {

    public AmadeusToken signIn() {
        var result = given()
                .baseUri(Conf.core().getAmadeusUrl())
                .contentType(ContentType.URLENC)
                .formParam("client_id", Conf.core().getClientId())
                .formParam("client_secret", Conf.core().getSecretKey())
                .formParam("grant_type", "client_credentials")
                .post("/v1/security/oauth2/token").jsonPath();

        String accessToken = result.getString("access_token");
        long expiresIn = result.getLong("expires_in");

        Allure.step("Generated amadeus token: " + accessToken);

        return new AmadeusToken(accessToken, expiresIn, result.prettyPrint());
    }
}
