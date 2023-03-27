package entities.dto.pet;

import com.fasterxml.jackson.annotation.JsonInclude;
import entities.dto.pet.config.CategoryDTO;
import entities.dto.pet.enums.PhotoUrls;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PetDTO {
    private Long id;
    private String name;
    private CategoryDTO category;
    private List<PhotoUrls> photoUrls;

    private List<TagsDTO> tags;
    private String status;

}