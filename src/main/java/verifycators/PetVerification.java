package verifycators;

import entities.dto.policy.PetDTO;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

public class PetVerification extends Verification<PetVerification> {

    public PetVerification(Response response) {
        super(response);
    }

    @Step("Expected response value equals to Policy Plan: '{expected.name}'")
    public PetVerification expectedEqualsPet(PetDTO expected) {
        var actual = response.getBody().as(PetDTO.class);
        Assertions.assertThat(actual).usingRecursiveComparison().ignoringFields(
                "id").isEqualTo(expected);
        return this;
    }
}
