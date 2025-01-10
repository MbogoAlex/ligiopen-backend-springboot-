package com.jabulani.ligiopen.controller.club;

import com.jabulani.ligiopen.config.response.Response;
import com.jabulani.ligiopen.model.club.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ClubController {
    ResponseEntity<Response> addClub(AddClubDto addClubDto, MultipartFile logo) throws IOException;
    ResponseEntity<Response> updateClubDetails(UpdateClubDto updateClubDto);

    ResponseEntity<Response> updateClubLogo(Integer clubId, MultipartFile logo) throws IOException;

    ResponseEntity<Response> uploadClubFiles(Integer clubId, MultipartFile[] files) throws IOException;
    ResponseEntity<Response> getClubById(Integer id);
    ResponseEntity<Response> getClubs();

    ResponseEntity<Response> addPlayer(AddPlayerDto addPlayerDto, MultipartFile mainPhoto) throws IOException;

    ResponseEntity<Response> signPlayer(SignPlayerDto signPlayerDto);

    ResponseEntity<Response> removePlayerFromTeam(UnsignPlayerDto unsignPlayerDto);

    ResponseEntity<Response> updatePlayerDetails(UpdatePlayerDto updatePlayerDto);
    ResponseEntity<Response> updatePlayerMainPhoto(Integer playerId, MultipartFile mainPhoto) throws IOException;
    ResponseEntity<Response> uploadPlayerFiles(Integer playerId, MultipartFile[] files) throws IOException;
    ResponseEntity<Response> getPlayers();
}
