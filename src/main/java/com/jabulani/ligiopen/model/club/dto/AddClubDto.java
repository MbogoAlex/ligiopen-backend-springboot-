package com.jabulani.ligiopen.model.club.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddClubDto {
    private String name;
    private String description;
    private String country;
    private String county;
    private String town;
}
