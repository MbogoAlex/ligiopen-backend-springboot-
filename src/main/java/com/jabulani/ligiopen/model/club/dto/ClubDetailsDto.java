package com.jabulani.ligiopen.model.club.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClubDetailsDto {
    private Integer clubId;
    private String name;
    private String description;
    private String country;
    private String county;
    private String town;
    private LocalDateTime createdAt;
    private Boolean archived;
    private LocalDateTime archivedAt;
    private List<String> files;
}
