package com.jabulani.ligiopen.model.match.entity.events.dto.fixtureDto.mapper;

import com.jabulani.ligiopen.model.club.dto.mapper.ClubMapper;
import com.jabulani.ligiopen.model.match.entity.MatchFixture;
import com.jabulani.ligiopen.model.match.entity.events.dto.fixtureDto.MatchFixtureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchFixtureDtoMapper {
    private final ClubMapper clubMapper;
    @Autowired
    public MatchFixtureDtoMapper(ClubMapper clubMapper) {
        this.clubMapper = clubMapper;
    }
    public MatchFixtureDto matchFixtureDto(MatchFixture matchFixture) {
        return MatchFixtureDto.builder()
                .matchFixtureId(matchFixture.getId())
                .matchLocationId(matchFixture.getMatchLocation().getId())
                .postMatchAnalysisId(matchFixture.getPostMatchAnalysis().getId())
                .homeClub(clubMapper.clubDetailsDto(matchFixture.getHomeClub()))
                .awayClub(clubMapper.clubDetailsDto(matchFixture.getAwayClub()))
                .createdAt(matchFixture.getCreatedAt())
                .matchDateTime(matchFixture.getMatchDateTime())
                .matchStatus(matchFixture.getStatus())
                .cancellationReason(matchFixture.getCancellationReason())
                .build();
    }
}
