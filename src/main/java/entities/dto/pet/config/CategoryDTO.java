package entities.dto.pet.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {
    private Long id;
    private String name;
}