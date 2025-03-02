package com.jabulani.ligiopen.model.club.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddClubDto {
    private String name;
    private String clubAbbreviation;
    private String description;
    private LocalDate startedOn;
    private String country;
    private String county;
    private String town;
}
