package helper.clean_up;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CleanUpExtension implements AfterAllCallback {

    private final Set<Long> idsToDelete;

    public CleanUpExtension() {
        this.idsToDelete = TestDataRemoveCollector.getIds();
    }

    @Override
    public void afterAll(ExtensionContext context) {
        for (Long id : idsToDelete) {
            Allure.step("Delete pet with ID: " + id + " and verify status code is 200", () -> {
                int statusCode = given()
                        .log().all()
                        .baseUri("https://petstore3.swagger.io")
                        .basePath("/api/v3/")
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .delete("/pet/{id}", id)
                        .then()
                        .extract()
                        .statusCode();

                assertEquals(200, statusCode, "Status code is not 200 but {statusCode}");
            });
        }
    }
}
