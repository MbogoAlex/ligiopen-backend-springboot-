package com.jabulani.ligiopen.controller.match;

import com.jabulani.ligiopen.config.response.BuildResponse;
import com.jabulani.ligiopen.config.response.Response;
import com.jabulani.ligiopen.model.match.entity.events.dto.fixtureDto.MatchFixtureCreationDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.fixtureDto.MatchFixtureUpdateDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.matchLocationDto.MatchLocationCreationDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.matchLocationDto.MatchLocationUpdateDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.postMatchDto.MatchCommentaryCreationDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.postMatchDto.MatchCommentaryUpdateDto;
import com.jabulani.ligiopen.service.match.MatchService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/")
public class MatchControllerImpl implements MatchController{
    private final BuildResponse buildResponse;
    private final EntityManager entityManager;
    private final MatchService matchService;
    @Autowired
    public MatchControllerImpl(
            BuildResponse buildResponse,
            EntityManager entityManager,
            MatchService matchService
    ) {
        this.buildResponse = buildResponse;
        this.entityManager = entityManager;
        this.matchService = matchService;
    }
    @PostMapping("match-location")
    @Override
    public ResponseEntity<Response> createMatchLocation(
            @RequestBody MatchLocationCreationDto matchLocationCreationDto
    ) {
        System.out.println("RECEIVED-REQUEST"+matchLocationCreationDto);
        return buildResponse.createResponse("match", matchService.createMatchLocation(matchLocationCreationDto), "Match location created", HttpStatus.CREATED);
    }
    @PutMapping("match-location")
    @Override
    public ResponseEntity<Response> updateMatchLocation(
            @RequestBody MatchLocationUpdateDto matchLocationUpdateDto
    ) {
        return buildResponse.createResponse("match", matchService.updateMatchLocation(matchLocationUpdateDto), "Match location updated", HttpStatus.OK);
    }
    @PutMapping("match-location/files/{locationId}")
    @Override
    public ResponseEntity<Response> uploadMatchLocationFiles(
            @PathVariable(name = "locationId") Integer locationId,
            @RequestPart("file") MultipartFile[] files
    ) throws IOException {
        if(files.length < 1) {
            return buildResponse.createResponse(null, null, "No files uploaded", HttpStatus.BAD_REQUEST);
        }
        return buildResponse.createResponse("match", matchService.uploadMatchLocationFiles(locationId, files), "Match location files uploaded", HttpStatus.OK);
    }
    @PutMapping("match-location/file-removal/{locationId}/{fileId}")
    @Override
    public ResponseEntity<Response> removeMatchLocationFile(
            @PathVariable(name = "locationId") Integer locationId,
            @PathVariable(name = "fileId") Integer fileId
    ) {
        return buildResponse.createResponse("match", matchService.removeMatchLocationFile(locationId, fileId), "Match location file removed", HttpStatus.OK);
    }
    @GetMapping("match-location/{locationId}")
    @Override
    public ResponseEntity<Response> getMatchLocationById(@PathVariable("locationId") Integer locationId) {
        return buildResponse.createResponse("match", matchService.getMatchLocationById(locationId), "Match location fetched", HttpStatus.OK);
    }
    @GetMapping("match-location/all")
    @Override
    public ResponseEntity<Response> getAllMatchLocations() {
        return buildResponse.createResponse("match", matchService.getAllMatchLocations(), "Match locations fetched", HttpStatus.OK);
    }
    @PostMapping("match-fixture")
    @Override
    public ResponseEntity<Response> createMatchFixture(
            @RequestBody MatchFixtureCreationDto matchFixtureCreationDto
    ) {
        return buildResponse.createResponse("match", matchService.createMatchFixture(matchFixtureCreationDto), "Match fixture created", HttpStatus.CREATED);
    }
    @PutMapping("match-fixture")
    @Override
    public ResponseEntity<Response> updateMatchFixture(
            @RequestBody MatchFixtureUpdateDto matchFixtureUpdateDto
    ) {
        return buildResponse.createResponse("match", matchService.updateMatchFixture(matchFixtureUpdateDto), "Match fixture updated", HttpStatus.OK);
    }
    @PutMapping("match-fixture/file-remove/{fixtureId}/{fileId}")
    @Override
    public ResponseEntity<Response> removeMatchFixtureFile(
            @PathVariable("fixtureId") Integer fixtureId,
            @PathVariable("fileId") Integer fileId
    ) {
        return buildResponse.createResponse("match", matchService.removeMatchFixtureFile(fixtureId, fileId), "Match fixture file removed", HttpStatus.OK);
    }
    @GetMapping("match-fixture/{id}")
    @Override
    public ResponseEntity<Response> getMatchFixtureById(
            @PathVariable("id") Integer id
    ) {
        return buildResponse.createResponse("match", matchService.getMatchFixtureById(id), "Match fixture fetched", HttpStatus.OK);
    }
    @GetMapping("match-fixture/all")
    @Override
    public ResponseEntity<Response> getAllMatchFixtures(
            @RequestParam(name = "status", required = false) String status
    ) {
        return buildResponse.createResponse("match", matchService.getAllMatchFixtures(status), "Match fixtures fetched", HttpStatus.OK);
    }
    @PostMapping("match-commentary")
    @Override
    public ResponseEntity<Response> createMatchCommentary(
            @RequestBody MatchCommentaryCreationDto matchCommentaryCreationDto
    ) {
        return buildResponse.createResponse("match", matchService.createMatchCommentary(matchCommentaryCreationDto), "Match commentary created", HttpStatus.CREATED);
    }
    @PutMapping("match-commentary")
    @Override
    public ResponseEntity<Response> updateMatchCommentary(
            @RequestBody MatchCommentaryUpdateDto matchCommentaryUpdateDto
    ) {
        return buildResponse.createResponse("match", matchService.updateMatchCommentary(matchCommentaryUpdateDto), "Match commentary updated", HttpStatus.OK);
    }
    @PutMapping("match-event/files/{commentaryId}")
    @Override
    public ResponseEntity<Response> uploadEventFiles(
            @PathVariable("commentaryId") Integer commentaryId,
            @RequestPart("file") MultipartFile[] files
    ) throws IOException {
        if(files.length < 1) {
            return buildResponse.createResponse(null, null, "No files uploaded", HttpStatus.BAD_REQUEST);
        }
        return buildResponse.createResponse("match", matchService.uploadEventFiles(commentaryId, files), "Match event files uploaded", HttpStatus.OK);
    }
    @GetMapping("match-commentary/{commentaryId}")
    @Override
    public ResponseEntity<Response> getMatchCommentary(@PathVariable("commentaryId") Integer commentaryId) {
        return buildResponse.createResponse("match", matchService.getMatchCommentary(commentaryId), "Match commentary fetched", HttpStatus.OK);
    }
    @GetMapping("match-commentary/all")
    @Override
    public ResponseEntity<Response> getAllMatchCommentaries() {
        return buildResponse.createResponse("match", matchService.getAllMatchCommentaries(), "Match commentaries fetched", HttpStatus.OK);
    }
    @GetMapping("post-match-details/{postMatchAnalysisId}")
    @Override
    public ResponseEntity<Response> getPostMatchDetails(@PathVariable("postMatchAnalysisId") Integer postMatchAnalysisId) {
        return buildResponse.createResponse("match", matchService.getPostMatchDetails(postMatchAnalysisId), "Post match details fetched", HttpStatus.OK);
    }
}
