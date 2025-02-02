package com.jabulani.ligiopen.model.match.entity.events.dto.eventsDto;

import com.jabulani.ligiopen.model.match.MatchEventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
//@Builder
public class MatchEventDto {
    private String title;
    private String summary;
    private String minute;
    private MatchEventType matchEventType;
    private Integer mainPlayerId;
    private List<String> files;
}
