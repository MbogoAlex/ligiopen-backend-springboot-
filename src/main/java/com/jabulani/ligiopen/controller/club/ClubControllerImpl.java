package com.jabulani.ligiopen.controller.club;

import com.jabulani.ligiopen.config.response.BuildResponse;
import com.jabulani.ligiopen.config.response.Response;
import com.jabulani.ligiopen.model.club.dto.*;
import com.jabulani.ligiopen.service.club.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/")
public class ClubControllerImpl implements ClubController{
    private final ClubService clubService;
    private final BuildResponse buildResponse;
    @Autowired
    public ClubControllerImpl(
            ClubService clubService,
            BuildResponse buildResponse
    ) {
        this.clubService = clubService;
        this.buildResponse = buildResponse;
    }
    @PostMapping("club")
    @Override
    public ResponseEntity<Response> addClub(
            @RequestPart AddClubDto addClubDto,
            @RequestPart("file") MultipartFile logo
    ) throws IOException {
        return buildResponse.createResponse("club", clubService.addClub(addClubDto, logo), "Club added", HttpStatus.CREATED);
    }
    @PutMapping("club/details")
    @Override
    public ResponseEntity<Response> updateClubDetails(
            @RequestBody UpdateClubDto updateClubDto
    ) {
        return buildResponse.createResponse("club", clubService.updateClubDetails(updateClubDto), "CLub updated", HttpStatus.OK);
    }
    @PutMapping("club/logo/{clubId}")
    @Override
    public ResponseEntity<Response> updateClubLogo(
            @PathVariable("clubId") Integer clubId,
            @RequestPart("file") MultipartFile logo
    ) throws IOException {
        return buildResponse.createResponse("club", clubService.updateClubLogo(clubId, logo), "Club logo updated", HttpStatus.OK);
    }
    @PutMapping("club/file-upload/{clubId}")
    @Override
    public ResponseEntity<Response> uploadClubFiles(
            @PathVariable("clubId") Integer clubId,
            @RequestPart("file") MultipartFile[] files
    ) throws IOException {
        return buildResponse.createResponse("club", clubService.uploadClubFiles(clubId, files), "Club files uploaded", HttpStatus.OK);
    }
    @GetMapping("club/{id}")
    @Override
    public ResponseEntity<Response> getClubById(
            @PathVariable("id") Integer id
    ) {
        return buildResponse.createResponse("club", clubService.getClubById(id), "Club fetched", HttpStatus.OK);
    }
    @GetMapping("club")
    @Override
    public ResponseEntity<Response> getClubs() {
        return buildResponse.createResponse("club", clubService.getClubs(), "Clubs fetched", HttpStatus.OK);
    }
    @PostMapping("player")
    @Override
    public ResponseEntity<Response> addPlayer(
            @RequestPart AddPlayerDto addPlayerDto,
            @RequestPart("file") MultipartFile mainPhoto
    ) throws IOException {
        return buildResponse.createResponse("player", clubService.addPlayer(addPlayerDto, mainPhoto), "Player added", HttpStatus.OK);
    }
    @PutMapping("player/sign")
    @Override
    public ResponseEntity<Response> signPlayer(
            @RequestBody SignPlayerDto signPlayerDto
    ) {
        return buildResponse.createResponse("player", clubService.signPlayer(signPlayerDto), "Player signed", HttpStatus.OK);
    }
    @PutMapping("player/remove-from-club")
    @Override
    public ResponseEntity<Response> removePlayerFromTeam(
            @RequestBody UnsignPlayerDto unsignPlayerDto
    ) {
        return buildResponse.createResponse("player", clubService.removePlayerFromTeam(unsignPlayerDto), "Player removed from team", HttpStatus.OK);
    }
    @PutMapping("player")
    @Override
    public ResponseEntity<Response> updatePlayerDetails(
            @RequestBody UpdatePlayerDto updatePlayerDto
    ) {
        return buildResponse.createResponse("player", clubService.updatePlayerDetails(updatePlayerDto), "Player details updated", HttpStatus.OK);
    }
    @PutMapping("player/main-photo/{playerId}")
    @Override
    public ResponseEntity<Response> updatePlayerMainPhoto(
            @PathVariable("playerId") Integer playerId,
            @RequestPart("file") MultipartFile mainPhoto
    ) throws IOException {
        return buildResponse.createResponse("player", clubService.updatePlayerMainPhoto(playerId, mainPhoto), "Player photo updated", HttpStatus.OK);
    }
    @PutMapping("player/file-upload/{playerId}")
    @Override
    public ResponseEntity<Response> uploadPlayerFiles(
            @PathVariable("playerId") Integer playerId,
            @RequestPart("file") MultipartFile[] files
    ) throws IOException {
        return buildResponse.createResponse("player", clubService.uploadPlayerFiles(playerId, files), "Player files uploaded", HttpStatus.OK);
    }
    @GetMapping("player")
    @Override
    public ResponseEntity<Response> getPlayers() {
        return buildResponse.createResponse("player", clubService.getPlayers(), "Players fetched", HttpStatus.OK);
    }
}
