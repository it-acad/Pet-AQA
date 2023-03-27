package controllers.amadeus;

import config.Conf;
import controllers.BaseController;
import entities.dto.pet.PetDTO;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import verifycators.AmadeusVerification;
import verifycators.PetVerification;

public class AmadeusController extends BaseController<AmadeusController> {
    private final String baseUri = Conf.core().getAmadeusUrl();
    private final String basePath = Conf.core().getShoppingPath();

    @Step("Get flight by original: {original} and maxPrice: {maxPrice}")
    public AmadeusVerification getFlightDestination(String original, long maxPrice) {
        Response response = baseClient(basePath, baseUri)
                .queryParam("origin", original)
                .queryParam("maxPrice", maxPrice)
                .get("/flight-destinations");
        return new AmadeusVerification(response);
    }
}
