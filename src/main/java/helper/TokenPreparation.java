package helper;

import config.AmadeusToken;
import config.Conf;
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

        return new AmadeusToken(result.getString("access_token"),
                result.getLong("expires_in"), result.prettyPrint());
    }
}
