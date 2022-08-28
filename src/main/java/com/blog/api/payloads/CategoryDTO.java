package com.blog.api.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {
    private Integer categoryId;

    @NotBlank
    @Size(min = 4, message = "Minimum size of characters should be 4")
    private String categoryTitle;

    @NotBlank
    @Size(min = 10,message = "Minimum size of characters should be 10")
    private String categoryDescription;
}

