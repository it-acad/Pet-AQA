package core.pet.pet;

import controllers.pet.PetController;
import core.pet.BasePetTests;

import entities.dto.policy.PetDTO;
import helper.data_generators.PetTestDataGenerator;
import io.qameta.allure.TmsLink;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@TmsLink("EL-835")
@DisplayName("[Pet]")
public class PetTests extends BasePetTests {

    @Test
    @DisplayName("Verify create new Pet")
    public void verifyAddPet() {
        PetDTO pet = PetTestDataGenerator.generatePet();

        new PetController()
                .addPet(pet)
                .expectedCode(HttpStatus.SC_OK)
                .expectedEqualsPet(pet);
    }

    @Test
    @DisplayName("Verify get new Pet")
    public void verifyGetPet() {
        PetDTO pet = PetTestDataGenerator.generatePet();

        var result = new PetController()
                .addPet(pet)
                .expectedCode(HttpStatus.SC_OK)
                .expectedEqualsPet(pet)
                .bindAs(PetDTO.class);

        new PetController()
                .getPet(result.getId())
                .expectedCode(HttpStatus.SC_OK)
                .expectedEqualsPet(pet);
    }

    @Test
    @DisplayName("Verify update Pet")
    public void verifyUpdatePet() {
        PetDTO pet = PetTestDataGenerator.generatePet();

        var newPet = new PetController()
                .addPet(pet)
                .expectedCode(HttpStatus.SC_OK)
                .expectedEqualsPet(pet)
                .bindAs(PetDTO.class);

        newPet.setName("bla");

        var editedPet = new PetController()
                .updatePet(newPet)
                .expectedCode(HttpStatus.SC_OK)
                .expectedEqualsPet(pet)
                .bindAs(PetDTO.class);
        assertThat(editedPet.getName()).isEqualTo(newPet.getName());

        new PetController()
                .getPet(editedPet.getId())
                .expectedCode(HttpStatus.SC_OK)
                .expectedEqualsPet(pet);
    }
}
