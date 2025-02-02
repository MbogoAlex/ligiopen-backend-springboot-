package com.jabulani.ligiopen.model.match.entity.events.dto;

import com.jabulani.ligiopen.model.match.entity.events.dto.eventsDto.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostMatchAnalysisDto {
    private Integer id;
    private Integer marchFixtureId;
    private Integer homeClubScore;
    private Integer awayClubScore;
    private Integer manOfTheMatchId;
    private List<CornerKickEventDto> cornerEvents;
    private List<FoulEventDto> foulEvents;
    private List<FreeKickEventDto> freeKickEvents;
    private List<FullTimeEventDto> fullTimeEvents;
    private List<GoalEventDto> goalEvents;
    private List<GoalKickEventDto> goalKickEvents;
    private List<InjuryEventDto> injuryEvents;
    private List<KickOffEventDto> kickOffEvents;
    private List<OffsideEventDto> offsideEvents;
    private List<PenaltyEventDto> penaltyEvents;
    private List<SubstitutionEventDto> substitutionEvents;
    private List<ThrowInEventDto> throwInEvents;
}
