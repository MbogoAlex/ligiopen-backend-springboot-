package com.jabulani.ligiopen.service.match;

import com.jabulani.ligiopen.model.match.entity.events.dto.postMatchDto.MatchCommentaryCreationDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.fixtureDto.MatchFixtureCreationDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.fixtureDto.MatchFixtureDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.fixtureDto.MatchFixtureUpdateDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.matchLocationDto.MatchLocationDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.matchLocationDto.MatchLocationUpdateDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.postMatchDto.MatchCommentaryDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.postMatchDto.MatchCommentaryUpdateDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MatchService {
    MatchLocationDto createMatchLocation(MatchFixtureCreationDto matchFixtureCreationDto);
    MatchLocationDto updateMatchLocation(MatchLocationUpdateDto matchLocationUpdateDto);

    MatchLocationDto uploadMatchLocationFiles(Integer locationId, MultipartFile[] files);
    MatchLocationDto removeMatchLocationFile(Integer locationId, Integer fileId);
    MatchLocationDto getMatchLocationById(Integer locationId);
    List<MatchLocationDto> getAllMatchLocations();
    MatchFixtureDto createMatchFixture(MatchFixtureCreationDto matchFixtureCreationDto);
    MatchFixtureDto updateMatchFixture(MatchFixtureUpdateDto matchFixtureUpdateDto);

    MatchFixtureDto uploadMatchFixtureFiles(Integer fixtureId, MultipartFile[] files);
    MatchFixtureDto removeMatchFixtureFile(Integer fixtureId, Integer fileId);

    MatchFixtureDto getMatchFixtureById(Integer id);
    List<MatchFixtureDto> getAllMatchFixtures();
    MatchCommentaryDto createMatchCommentary(MatchCommentaryCreationDto matchCommentaryCreationDto);
    MatchCommentaryDto updateMatchCommentary(MatchCommentaryUpdateDto matchCommentaryUpdateDto);
    MatchCommentaryDto uploadEventFiles(Integer commentaryId, MultipartFile[] files);

    MatchCommentaryDto getMatchCommentary(Integer commentaryId);
    List<MatchCommentaryDto> getAllMatchCommentaries();
}
