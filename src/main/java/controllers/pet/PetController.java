package controllers.pet;

import config.Conf;
import controllers.BaseController;

import entities.dto.pet.PetDTO;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import verifycators.PetVerification;

public class PetController extends BaseController<PetController> {
    private final String basePath = Conf.core().getPetUrl();

    @Step("Get Pet by id: {id}")
    public PetVerification getPet(Long id) {
        Response response = baseClient(basePath)
                .get("/{id}", id);
        return new PetVerification(response);
    }

    @Step("Add new Pet {policy.name}")
    public PetVerification addPet(PetDTO pet) {
        Response response = baseClient(basePath)
                .body(pet)
                .post();
        return new PetVerification(response);
    }

    @Step("Update existing Pet Status")
    public PetVerification updatePet(PetDTO body) {
        Response response = baseClient(basePath)
                .body(body)
                .put();
        return new PetVerification(response);
    }
}
