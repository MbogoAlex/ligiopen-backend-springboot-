package com.jabulani.ligiopen.model.match.entity.events.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class FoulEventDto extends MatchEventDto {
    private MatchEventDto matchEventDto;
    private Integer fouledPlayerId;
    private Boolean isYellowCard = false;
    private Boolean isRedCard = false;
}
