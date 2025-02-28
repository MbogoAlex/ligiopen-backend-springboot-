package com.jabulani.ligiopen.model.match.entity.events.dto.eventsDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class HalfTimeEventDto extends MatchEventDto{
    private Integer awayClubScore;
    private Integer homeClubScore;
}
