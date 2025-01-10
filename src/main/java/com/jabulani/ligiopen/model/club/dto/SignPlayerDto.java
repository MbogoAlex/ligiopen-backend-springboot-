package com.jabulani.ligiopen.model.club.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignPlayerDto {
    private Integer playerId;
    private Integer newClubId;
}
