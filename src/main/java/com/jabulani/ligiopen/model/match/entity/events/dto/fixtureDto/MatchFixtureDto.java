package com.jabulani.ligiopen.model.match.entity.events.dto.fixtureDto;

import com.jabulani.ligiopen.model.club.dto.ClubDetailsDto;
import com.jabulani.ligiopen.model.match.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchFixtureDto {
    private Integer matchFixtureId;
    private Integer matchLocationId;
    private String matchLocation;
    private Integer postMatchAnalysisId;
    private ClubDetailsDto homeClub;
    private ClubDetailsDto awayClub;
    private Integer homeClubScore;
    private Integer awayClubScore;
    private LocalDateTime createdAt;
    private LocalDateTime matchDateTime;
    private MatchStatus matchStatus;
    private String cancellationReason;
}
