package ru.itis.model;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    private Long id;

    private String type;

}
