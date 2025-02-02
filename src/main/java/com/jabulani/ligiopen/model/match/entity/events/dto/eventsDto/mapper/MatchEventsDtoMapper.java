package com.jabulani.ligiopen.model.match.entity.events.dto.eventsDto.mapper;

import com.jabulani.ligiopen.model.match.entity.events.*;
import com.jabulani.ligiopen.model.match.entity.events.dto.eventsDto.*;
import com.jabulani.ligiopen.service.aws.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MatchEventsDtoMapper {

    private final String BUCKET_NAME = "ligiopen";

    private final AwsService awsService;
    @Autowired
    public MatchEventsDtoMapper(AwsService awsService) {
        this.awsService = awsService;
    }
    public CornerKickEventDto cornerKickEventDto(CornerKickEvent cornerKickEvent) {
        List<String> files = new ArrayList<>();
        if(!cornerKickEvent.getMatchCommentary().getFiles().isEmpty()) {
            files.addAll(cornerKickEvent.getMatchCommentary().getFiles().stream().map(file -> awsService.getFileUrl(BUCKET_NAME, file.getName())).toList());
        }

        return (CornerKickEventDto) CornerKickEventDto.builder()
                .title(cornerKickEvent.getTitle())
                .summary(cornerKickEvent.getSummary())
                .minute(cornerKickEvent.getMinute())
                .matchEventType(cornerKickEvent.getMatchEventType())
                .mainPlayerId(cornerKickEvent.getPlayer().getId())
                .files(files)
                .build();
    }

    public FoulEventDto foulEventDto(FoulEvent foulEvent) {
        List<String> files = new ArrayList<>();
        if(!foulEvent.getMatchCommentary().getFiles().isEmpty()) {
            files.addAll(foulEvent.getMatchCommentary().getFiles().stream().map(file -> awsService.getFileUrl(BUCKET_NAME, file.getName())).toList());
        }
        return FoulEventDto.builder()
                .title(foulEvent.getTitle())
                .summary(foulEvent.getSummary())
                .minute(foulEvent.getMinute())
                .matchEventType(foulEvent.getMatchEventType())
                .mainPlayerId(foulEvent.getPlayer().getId())
                .fouledPlayerId(foulEvent.getFouledPlayer().getId())
                .isYellowCard(foulEvent.getIsYellowCard())
                .isRedCard(foulEvent.getIsRedCard())
                .files(files)
                .build();
    }

    public FreeKickEventDto freeKickEventDto(FreeKickEvent freeKickEvent) {
        List<String> files = new ArrayList<>();
        if(!freeKickEvent.getMatchCommentary().getFiles().isEmpty()) {
            files.addAll(freeKickEvent.getMatchCommentary().getFiles().stream().map(file -> awsService.getFileUrl(BUCKET_NAME, file.getName())).toList());
        }
        return (FreeKickEventDto) FreeKickEventDto.builder()
                .title(freeKickEvent.getTitle())
                .summary(freeKickEvent.getSummary())
                .minute(freeKickEvent.getMinute())
                .matchEventType(freeKickEvent.getMatchEventType())
                .mainPlayerId(freeKickEvent.getPlayer().getId())
                .files(files)
                .build();
    }

    public FullTimeEventDto fullTimeEventDto(FullTimeEvent fullTimeEvent) {
        List<String> files = new ArrayList<>();
        if(!fullTimeEvent.getMatchCommentary().getFiles().isEmpty()) {
            files.addAll(fullTimeEvent.getMatchCommentary().getFiles().stream().map(file -> awsService.getFileUrl(BUCKET_NAME, file.getName())).toList());
        }
        return (FullTimeEventDto) FullTimeEventDto.builder()
                .title(fullTimeEvent.getTitle())
                .summary(fullTimeEvent.getSummary())
                .minute(fullTimeEvent.getMinute())
                .matchEventType(fullTimeEvent.getMatchEventType())
                .mainPlayerId(fullTimeEvent.getPlayer().getId())
                .files(files)
                .build();
    }

    public GoalEventDto goalEventDto(GoalEvent goalEvent) {
        List<String> files = new ArrayList<>();
        if(!goalEvent.getMatchCommentary().getFiles().isEmpty()) {
            files.addAll(goalEvent.getMatchCommentary().getFiles().stream().map(file -> awsService.getFileUrl(BUCKET_NAME, file.getName())).toList());
        }

        return (GoalEventDto) GoalEventDto.builder()
                .title(goalEvent.getTitle())
                .summary(goalEvent.getSummary())
                .minute(goalEvent.getMinute())
                .matchEventType(goalEvent.getMatchEventType())
                .mainPlayerId(goalEvent.getPlayer().getId())
                .files(files)
                .build();
    }

    public InjuryEventDto injuryEventDto(InjuryEvent injuryEvent) {
        List<String> files = new ArrayList<>();
        if(!injuryEvent.getMatchCommentary().getFiles().isEmpty()) {
            files.addAll(injuryEvent.getMatchCommentary().getFiles().stream().map(file -> awsService.getFileUrl(BUCKET_NAME, file.getName())).toList());
        }

        return (InjuryEventDto) InjuryEventDto.builder()
                .title(injuryEvent.getTitle())
                .summary(injuryEvent.getSummary())
                .minute(injuryEvent.getMinute())
                .matchEventType(injuryEvent.getMatchEventType())
                .mainPlayerId(injuryEvent.getPlayer().getId())
                .files(files)
                .build();
    }

    public KickOffEventDto kickOffEventDto(KickOffEvent kickOffEvent) {
        List<String> files = new ArrayList<>();
        if(!kickOffEvent.getMatchCommentary().getFiles().isEmpty()) {
            files.addAll(kickOffEvent.getMatchCommentary().getFiles().stream().map(file -> awsService.getFileUrl(BUCKET_NAME, file.getName())).toList());
        }

        return (KickOffEventDto) KickOffEventDto.builder()
                .title(kickOffEvent.getTitle())
                .summary(kickOffEvent.getSummary())
                .minute(kickOffEvent.getMinute())
                .matchEventType(kickOffEvent.getMatchEventType())
                .mainPlayerId(kickOffEvent.getPlayer().getId())
                .files(files)
                .build();
    }

    public OffsideEventDto offsideEventDto(OffsideEvent offsideEvent) {
        List<String> files = new ArrayList<>();
        if(!offsideEvent.getMatchCommentary().getFiles().isEmpty()) {
            files.addAll(offsideEvent.getMatchCommentary().getFiles().stream().map(file -> awsService.getFileUrl(BUCKET_NAME, file.getName())).toList());
        }

        return (OffsideEventDto) OffsideEventDto.builder()
                .title(offsideEvent.getTitle())
                .summary(offsideEvent.getSummary())
                .minute(offsideEvent.getMinute())
                .matchEventType(offsideEvent.getMatchEventType())
                .mainPlayerId(offsideEvent.getPlayer().getId())
                .files(files)
                .build();
    }

    public PenaltyEventDto penaltyEventDto(PenaltyEvent penaltyEvent) {
        List<String> files = new ArrayList<>();
        if(!penaltyEvent.getMatchCommentary().getFiles().isEmpty()) {
            files.addAll(penaltyEvent.getMatchCommentary().getFiles().stream().map(file -> awsService.getFileUrl(BUCKET_NAME, file.getName())).toList());
        }

        return (PenaltyEventDto) PenaltyEventDto.builder()
                .title(penaltyEvent.getTitle())
                .summary(penaltyEvent.getSummary())
                .minute(penaltyEvent.getMinute())
                .matchEventType(penaltyEvent.getMatchEventType())
                .mainPlayerId(penaltyEvent.getPlayer().getId())
                .isScored(penaltyEvent.getIsScored())
                .files(files)
                .build();
    }

    public SubstitutionEventDto substitutionEventDto(SubstitutionEvent substitutionEvent) {
        List<String> files = new ArrayList<>();
        if(!substitutionEvent.getMatchCommentary().getFiles().isEmpty()) {
            files.addAll(substitutionEvent.getMatchCommentary().getFiles().stream().map(file -> awsService.getFileUrl(BUCKET_NAME, file.getName())).toList());
        }

        return (SubstitutionEventDto) SubstitutionEventDto.builder()
                .title(substitutionEvent.getTitle())
                .summary(substitutionEvent.getSummary())
                .minute(substitutionEvent.getMinute())
                .matchEventType(substitutionEvent.getMatchEventType())
                .mainPlayerId(substitutionEvent.getPlayer().getId())
                .subbedOutPlayerId(substitutionEvent.getSubbedOutPlayer().getId())
                .files(files)
                .build();
    }

    public ThrowInEventDto throwInEventDto(ThrowInEvent throwInEvent) {
        List<String> files = new ArrayList<>();
        if(!throwInEvent.getMatchCommentary().getFiles().isEmpty()) {
            files.addAll(throwInEvent.getMatchCommentary().getFiles().stream().map(file -> awsService.getFileUrl(BUCKET_NAME, file.getName())).toList());
        }

        return (ThrowInEventDto) ThrowInEventDto.builder()
                .title(throwInEvent.getTitle())
                .summary(throwInEvent.getSummary())
                .minute(throwInEvent.getMinute())
                .matchEventType(throwInEvent.getMatchEventType())
                .mainPlayerId(throwInEvent.getPlayer().getId())
                .files(files)
                .build();
    }
}
