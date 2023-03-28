package controllers.pet;

import config.Conf;
import controllers.BaseController;

import entities.dto.pet.PetDTO;
import helper.clean_up.TestDataRemoveCollector;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import verifycators.PetVerification;

public class PetController extends BaseController<PetController> {
    private final String basePath = Conf.core().getPetUrl();
    private final String baseUri = Conf.core().getPetPath();

    @Step("Get Pet by id: {id}")
    public PetVerification getPet(Long id) {
        Response response = baseClient(basePath, baseUri)
                .get("/{id}", id);
        return new PetVerification(response);
    }

    @Step("Add new Pet {policy.name}")
    public PetVerification addPet(PetDTO pet) {
        Response response = baseClient(basePath, baseUri)
                .body(pet)
                .post();
        if (response.getStatusCode() == HttpStatus.SC_OK) {
            TestDataRemoveCollector
                    .addId(response.jsonPath().getLong("id"));
        }
        return new PetVerification(response);
    }

    @Step("Update existing Pet Status")
    public PetVerification updatePet(PetDTO body) {
        Response response = baseClient(basePath, baseUri)
                .body(body)
                .put();
        return new PetVerification(response);
    }
}
