package com.jabulani.ligiopen.model.match.entity.events.dto.postMatchDto;

import com.jabulani.ligiopen.model.match.MatchEventType;
import com.jabulani.ligiopen.model.match.entity.events.dto.eventsDto.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchCommentaryDto {
    private Integer matchCommentaryId;
    private Integer postMatchAnalysisId;
    private List<String> files;
    private String minute;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean archived;
    private LocalDateTime archivedAt;
    private MatchEventType matchEventType;
    private CornerKickEventDto cornerEvents;
    private FoulEventDto foulEvents;
    private FreeKickEventDto freeKickEvents;
    private FullTimeEventDto fullTimeEvents;
    private GoalEventDto goalEvents;
    private GoalKickEventDto goalKickEvents;
    private InjuryEventDto injuryEvents;
    private KickOffEventDto kickOffEvents;
    private OffsideEventDto offsideEvents;
    private List<PenaltyEventDto> penaltyEvents;
    private List<SubstitutionEventDto> substitutionEvents;
    private List<ThrowInEventDto> throwInEvents;
}
