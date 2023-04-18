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
    private final String basePath = Conf.core().getPetPath();
    private final String baseUrl = Conf.core().getPetUrl();

    @Step("Get Pet by id: {id}")
    public PetVerification getPet(Long id) {
        Response response = baseClient(baseUrl, basePath)
                .get("/{id}", id);
        return new PetVerification(response);
    }

    @Step("Add new Pet {PetDTO.name}")
    public PetVerification addPet(PetDTO pet) {
        Response response = baseClient(baseUrl, basePath)
                .body(pet)
                .post();
        if (response.getStatusCode() == HttpStatus.SC_OK) {
            TestDataRemoveCollector
                    .addId(response.jsonPath().getLong("id"));
        }
        return new PetVerification(response);
    }

    @Step("Update existing Pet")
    public PetVerification updatePet(PetDTO body) {
        Response response = baseClient(baseUrl, basePath)
                .body(body)
                .put();
        return new PetVerification(response);
    }
}
