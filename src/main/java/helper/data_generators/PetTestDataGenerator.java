package helper.data_generators;

import com.github.javafaker.Faker;
import entities.dto.pet.*;
import entities.dto.pet.config.CategoryDTO;
import entities.dto.pet.enums.PhotoUrls;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class PetTestDataGenerator {

    public PetDTO generatePet() {

        return PetDTO.builder()
                .name("doggie")
                .status("available")
                .category(new CategoryDTO(2L, "string"))
                .photoUrls(List.of(PhotoUrls.URLS1))
                .tags(List.of(new TagsDTO(2L, "string")))
                .build();
    }
}
