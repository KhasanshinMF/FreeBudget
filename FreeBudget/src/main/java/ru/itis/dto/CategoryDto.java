package ru.itis.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private long id;

    private long userId;

    private String name;

}
