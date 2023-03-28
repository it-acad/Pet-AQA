package helper.clean_up;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Set;

import static io.restassured.RestAssured.given;

public class CleanUpExtension implements AfterAllCallback {

    private final Set<Long> idsToDelete;

    public CleanUpExtension() {
        this.idsToDelete = TestDataRemoveCollector.getIds();
    }

    @Override
    public void afterAll(ExtensionContext context) {
        for (Long id : idsToDelete) {
            given()
                    .log().all()
                    .baseUri("https://petstore3.swagger.io")
                    .basePath("/api/v3/")
                    .accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
                    .delete("/pet/{id}", id)
                    .then()
                    .statusCode(200);
        }
    }
}
