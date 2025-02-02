package com.jabulani.ligiopen.model.match.entity.events.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class SubstitutionEventDto extends MatchEventDto {
    private MatchEventDto matchEventDto;
    private Integer subbedOutPlayerId;
}
