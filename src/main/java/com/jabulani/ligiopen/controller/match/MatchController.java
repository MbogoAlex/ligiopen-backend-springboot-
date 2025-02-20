package com.jabulani.ligiopen.controller.match;

import com.jabulani.ligiopen.config.response.Response;
import com.jabulani.ligiopen.model.match.entity.events.dto.fixtureDto.MatchFixtureCreationDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.fixtureDto.MatchFixtureDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.fixtureDto.MatchFixtureUpdateDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.matchLocationDto.MatchLocationCreationDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.matchLocationDto.MatchLocationDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.matchLocationDto.MatchLocationUpdateDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.postMatchDto.MatchCommentaryCreationDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.postMatchDto.MatchCommentaryDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.postMatchDto.MatchCommentaryUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MatchController {
    ResponseEntity<Response> createMatchLocation(MatchLocationCreationDto matchLocationCreationDto);
    ResponseEntity<Response> updateMatchLocation(MatchLocationUpdateDto matchLocationUpdateDto);

    ResponseEntity<Response> uploadMatchLocationFiles(Integer locationId, MultipartFile[] files) throws IOException;
    ResponseEntity<Response> removeMatchLocationFile(Integer locationId, Integer fileId);
    ResponseEntity<Response> getMatchLocationById(Integer locationId);
    ResponseEntity<Response> getAllMatchLocations();
    ResponseEntity<Response> createMatchFixture(MatchFixtureCreationDto matchFixtureCreationDto);
    ResponseEntity<Response> updateMatchFixture(MatchFixtureUpdateDto matchFixtureUpdateDto);

    ResponseEntity<Response> removeMatchFixtureFile(Integer fixtureId, Integer fileId);

    ResponseEntity<Response> getMatchFixtureById(Integer id);
    ResponseEntity<Response> getAllMatchFixtures(String status);
    ResponseEntity<Response> createMatchCommentary(MatchCommentaryCreationDto matchCommentaryCreationDto);
    ResponseEntity<Response> updateMatchCommentary(MatchCommentaryUpdateDto matchCommentaryUpdateDto);
    ResponseEntity<Response> uploadEventFiles(Integer commentaryId, MultipartFile[] files) throws IOException;

    ResponseEntity<Response> getMatchCommentary(Integer commentaryId);
    ResponseEntity<Response> getAllMatchCommentaries();
    ResponseEntity<Response> getPostMatchDetails(Integer postMatchAnalysisId);
}
