package com.jabulani.ligiopen.model.match.entity.events.dto.postMatchDto;

import com.jabulani.ligiopen.model.aws.dto.FileDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.eventsDto.*;
import com.jabulani.ligiopen.model.match.entity.events.dto.matchLocationDto.MatchLocationDto;
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
    private List<MatchCommentaryDto> minuteByMinuteCommentary;
}
