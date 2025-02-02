package com.jabulani.ligiopen.model.match.entity.events.dto;

import com.jabulani.ligiopen.model.match.entity.events.dto.eventsDto.MatchEventDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchCommentaryCreationDto {
    private Integer postMatchAnalysisId;
    private String minute;
    private MatchEventDto matchEventDto;

}
