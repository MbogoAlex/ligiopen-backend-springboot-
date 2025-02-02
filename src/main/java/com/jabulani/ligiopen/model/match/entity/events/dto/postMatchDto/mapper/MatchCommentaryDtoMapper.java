package com.jabulani.ligiopen.model.match.entity.events.dto.postMatchDto.mapper;

import com.jabulani.ligiopen.model.match.entity.MatchCommentary;
import com.jabulani.ligiopen.model.match.entity.events.*;
import com.jabulani.ligiopen.model.match.entity.events.dto.eventsDto.*;
import com.jabulani.ligiopen.model.match.entity.events.dto.eventsDto.mapper.MatchEventsDtoMapper;
import com.jabulani.ligiopen.model.match.entity.events.dto.postMatchDto.MatchCommentaryDto;
import com.jabulani.ligiopen.service.aws.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MatchCommentaryDtoMapper {
    private final String BUCKET_NAME = "ligiopen";
    private final MatchEventsDtoMapper matchEventsDtoMapper;
    private final AwsService awsService;
    @Autowired
    public MatchCommentaryDtoMapper(
            MatchEventsDtoMapper matchEventsDtoMapper,
            AwsService awsService
    ) {
        this.matchEventsDtoMapper = matchEventsDtoMapper;
        this.awsService = awsService;
    }
    public MatchCommentaryDto matchCommentaryDto(MatchCommentary matchCommentary) {
        MatchEventDto matchEventDto;
        List<String> files = new ArrayList<>();

        if (matchCommentary.getFiles() != null && !matchCommentary.getFiles().isEmpty()) {
            files.addAll(matchCommentary.getFiles().stream()
                    .map(file -> awsService.getFileUrl(BUCKET_NAME, file.getName()))
                    .toList());
        }

        matchEventDto = switch (matchCommentary.getMatchEvent().getMatchEventType()) {
            case GOAL -> matchEventsDtoMapper.goalEventDto((GoalEvent) matchCommentary.getMatchEvent());
            case OWN_GOAL -> matchEventsDtoMapper.ownGoalEventDto((OwnGoalEvent) matchCommentary.getMatchEvent());
            case SUBSTITUTION -> matchEventsDtoMapper.substitutionEventDto((SubstitutionEvent) matchCommentary.getMatchEvent());
            case FOUL -> matchEventsDtoMapper.foulEventDto((FoulEvent) matchCommentary.getMatchEvent());
            case CORNER_KICK -> matchEventsDtoMapper.cornerKickEventDto((CornerKickEvent) matchCommentary.getMatchEvent());
            case FREE_KICK -> matchEventsDtoMapper.freeKickEventDto((FreeKickEvent) matchCommentary.getMatchEvent());
            case PENALTY -> matchEventsDtoMapper.penaltyEventDto((PenaltyEvent) matchCommentary.getMatchEvent());
            case INJURY -> matchEventsDtoMapper.injuryEventDto((InjuryEvent) matchCommentary.getMatchEvent());
            case THROW_IN -> matchEventsDtoMapper.throwInEventDto((ThrowInEvent) matchCommentary.getMatchEvent());
            case KICK_OFF -> matchEventsDtoMapper.kickOffEventDto((KickOffEvent) matchCommentary.getMatchEvent());
            case FULL_TIME -> matchEventsDtoMapper.fullTimeEventDto((FullTimeEvent) matchCommentary.getMatchEvent());
            case OFFSIDE -> matchEventsDtoMapper.offsideEventDto((OffsideEvent) matchCommentary.getMatchEvent());
            default -> null;
        };

        return MatchCommentaryDto.builder()
                .matchCommentaryId(matchCommentary.getId())
                .postMatchAnalysisId(matchCommentary.getPostMatchAnalysis() != null ? matchCommentary.getPostMatchAnalysis().getId() : null)
                .files(files)
                .minute(matchCommentary.getMinute())
                .createdAt(matchCommentary.getCreatedAt())
                .updatedAt(matchCommentary.getUpdatedAt())
                .archived(matchCommentary.getArchived())
                .archivedAt(matchCommentary.getArchivedAt())
                .matchEventType(matchCommentary.getMatchEvent().getMatchEventType())
                .cornerEvents(matchEventDto instanceof CornerKickEventDto ? (CornerKickEventDto) matchEventDto : null)
                .foulEvents(matchEventDto instanceof FoulEventDto ? (FoulEventDto) matchEventDto : null)
                .freeKickEvents(matchEventDto instanceof FreeKickEventDto ? (FreeKickEventDto) matchEventDto : null)
                .fullTimeEvents(matchEventDto instanceof FullTimeEventDto ? (FullTimeEventDto) matchEventDto : null)
                .goalEvents(matchEventDto instanceof GoalEventDto ? (GoalEventDto) matchEventDto : null)
                .goalKickEvents(matchEventDto instanceof GoalKickEventDto ? (GoalKickEventDto) matchEventDto : null)
                .injuryEvents(matchEventDto instanceof InjuryEventDto ? (InjuryEventDto) matchEventDto : null)
                .kickOffEvents(matchEventDto instanceof KickOffEventDto ? (KickOffEventDto) matchEventDto : null)
                .offsideEvents(matchEventDto instanceof OffsideEventDto ? (OffsideEventDto) matchEventDto : null)
                .penaltyEvents(matchEventDto instanceof PenaltyEventDto ? List.of((PenaltyEventDto) matchEventDto) : null)
                .substitutionEvents(matchEventDto instanceof SubstitutionEventDto ? List.of((SubstitutionEventDto) matchEventDto) : null)
                .throwInEvents(matchEventDto instanceof ThrowInEventDto ? List.of((ThrowInEventDto) matchEventDto) : null)
                .build();
    }

}
