package helper.clean_up;

import config.Conf;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
@DisplayName("Clean up test data")
public class CleanUpExtension implements AfterEachCallback {

    private final Set<Long> idsToDelete;

    public CleanUpExtension() {
        idsToDelete = TestDataRemoveCollector.getIds();
    }

    @Override
    public void afterEach(ExtensionContext context) {

        for (Long id : idsToDelete) {
            deletePetById(id);
        }
    }

    @Step("Delete pet by id: {id}")
    private static void deletePetById(Long id) {
        int statusCode = given()
                .log().all()
                .baseUri(Conf.core().getPetUrl())
                .basePath(Conf.core().getPetPath())
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .delete("/{id}", id)
                .then()
                .extract()
                .statusCode();

        assertEquals(200, statusCode, "Status code of clean up" + statusCode);
    }

}
