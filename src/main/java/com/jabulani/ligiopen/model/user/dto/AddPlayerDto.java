package com.jabulani.ligiopen.model.user.dto;

import com.jabulani.ligiopen.model.user.PlayerPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddPlayerDto {
    private String username;
    private Integer age;
    private Double height;
    private Double weight;
    private String number;
    private PlayerPosition playerPosition;
    private String country;
    private String county;
    private String town;
}
