package com.data.toan.dto.reponse.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder

public class CategoryResponseDTO {
    private long id;
    private String name;
    private String type;
}
