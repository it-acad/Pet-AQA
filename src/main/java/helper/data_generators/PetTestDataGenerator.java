package helper.data_generators;

import com.github.javafaker.Faker;
import entities.dto.policy.*;
import entities.dto.policy.config.CategoryDTO;
import entities.dto.policy.enums.PhotoUrls;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class PetTestDataGenerator {

    public PetDTO generatePet() {

        return PetDTO.builder()
                .id(new Faker().number().randomNumber())
                .name("doggie")
                .status("available")
                .category(new CategoryDTO(2L, "string"))
                .photoUrls(List.of(PhotoUrls.URLS1))
                .tags(List.of(new TagsDTO(2L, "string")))
                .build();
    }
}
