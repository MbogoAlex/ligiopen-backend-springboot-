package com.jabulani.ligiopen.model.club.dto;

import com.jabulani.ligiopen.model.user.dto.PlayerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClubDetailsDto {
    private Integer clubId;
    private String clubLogo;
    private String name;
    private String description;
    private String country;
    private String county;
    private String town;
    private LocalDate startedOn;
    private LocalDateTime createdAt;
    private Boolean archived;
    private LocalDateTime archivedAt;
    private List<PlayerDto> players;
    private List<String> files;
}
