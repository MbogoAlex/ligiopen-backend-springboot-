package com.jabulani.ligiopen.model.club.dto;

import com.jabulani.ligiopen.model.club.PlayerPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerDto {
    private Integer playerId;
    private String mainPhoto;
    private String username;
    private Integer number;
    private PlayerPosition playerPosition;
    private Integer age;
    private Double height;
    private Double weight;
    private String country;
    private String county;
    private String town;
    private Integer clubId;
    private List<String> files;
}
