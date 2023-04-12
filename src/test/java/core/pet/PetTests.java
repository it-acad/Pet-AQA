package core.pet;

import common.BaseTest;
import controllers.pet.PetController;

import entities.dto.pet.PetDTO;
import helper.data_generators.PetTestDataGenerator;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[Pet tests]")
public class PetTests extends BaseTest {

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
    @DisplayName("Verify create multiple Pet")
    public void verifyAddMultiplePets() {
        for (int i = 0; i < 10; i++) {
            PetDTO pet = PetTestDataGenerator.generatePet();
            new PetController()
                    .addPet(pet)
                    .expectedCode(HttpStatus.SC_OK)
                    .expectedEqualsPet(pet);
        }
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
                .expectedEqualsPet(newPet)
                .bindAs(PetDTO.class);
        assertThat(editedPet.getName()).isEqualTo(newPet.getName());

        new PetController()
                .getPet(editedPet.getId())
                .expectedCode(HttpStatus.SC_OK)
                .expectedEqualsPet(newPet);
    }
}
