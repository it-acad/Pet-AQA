package entities.dto.pet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PhotoUrls {
    URLS1(1, "str1"),
    URLS2(2, "str2");

    private final int id;
    private final String name;
}