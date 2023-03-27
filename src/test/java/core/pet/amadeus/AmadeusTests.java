package core.pet.amadeus;

import common.BaseApiTests;
import controllers.pet.PetController;
import entities.dto.pet.PetDTO;
import helper.data_generators.PetTestDataGenerator;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[Base Amadeus test]")
public class AmadeusTests extends BaseApiTests {
    @Test
    @DisplayName("Verify create new Pet")
    public void verifyAddAmadeus() {
        PetDTO pet = PetTestDataGenerator.generatePet();

        new PetController()
                .withToken()
                .addPet(pet)
                .expectedCode(HttpStatus.SC_OK)
                .expectedEqualsPet(pet);
    }

}
