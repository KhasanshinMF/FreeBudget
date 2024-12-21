package ru.itis.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private int status;

    private String statusDesc;

    private CategoryDto categoryDto;

}
