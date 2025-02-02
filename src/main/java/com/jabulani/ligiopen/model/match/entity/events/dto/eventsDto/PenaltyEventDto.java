package com.jabulani.ligiopen.model.match.entity.events.dto.eventsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
//@Builder
public class PenaltyEventDto extends MatchEventDto {
    private Boolean isScored;
}
