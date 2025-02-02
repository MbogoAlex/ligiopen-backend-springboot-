package com.jabulani.ligiopen.model.match.entity.events.dto.eventsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class GoalEventDto extends MatchEventDto {
    private Integer assistingPlayerId;
}
