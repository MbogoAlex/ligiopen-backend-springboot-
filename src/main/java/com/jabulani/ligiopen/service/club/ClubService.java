package com.jabulani.ligiopen.service.club;

import com.jabulani.ligiopen.model.club.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ClubService {
    ClubDetailsDto addClub(AddClubDto addClubDto, MultipartFile logo) throws IOException;
    ClubDetailsDto updateClubDetails(UpdateClubDto updateClubDto);

    ClubDetailsDto updateClubLogo(Integer clubId, MultipartFile logo) throws IOException;

    ClubDetailsDto setClubMainPhoto(Integer clubId, MultipartFile photo) throws IOException;

    ClubDetailsDto uploadClubFiles(Integer clubId, MultipartFile[] files) throws IOException;
    ClubDetailsDto getClubById(Integer id);
    List<ClubDetailsDto> getClubs();

    PlayerDto addPlayer(AddPlayerDto addPlayerDto, MultipartFile mainPhoto) throws IOException;

    PlayerDto getPlayerById(Integer playerId);

    PlayerDto signPlayer(SignPlayerDto signPlayerDto);

    PlayerDto removePlayerFromTeam(UnsignPlayerDto unsignPlayerDto);

    PlayerDto updatePlayerDetails(UpdatePlayerDto updatePlayerDto);
    PlayerDto updatePlayerMainPhoto(Integer playerId, MultipartFile mainPhoto) throws IOException;
    PlayerDto uploadPlayerFiles(Integer playerId, MultipartFile[] files) throws IOException;
    List<PlayerDto> getPlayers();
}
