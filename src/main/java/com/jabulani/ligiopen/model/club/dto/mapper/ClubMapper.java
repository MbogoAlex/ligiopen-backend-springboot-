package com.jabulani.ligiopen.model.club.dto.mapper;

import com.jabulani.ligiopen.model.aws.dto.FileDto;
import com.jabulani.ligiopen.model.aws.dto.mapper.FileMapper;
import com.jabulani.ligiopen.model.club.dto.ClubDetailsDto;
import com.jabulani.ligiopen.model.club.dto.PlayerClubDto;
import com.jabulani.ligiopen.model.club.dto.PlayerDto;
import com.jabulani.ligiopen.model.club.entity.Club;
import com.jabulani.ligiopen.model.club.entity.PlayerClub;
import com.jabulani.ligiopen.service.aws.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClubMapper {
    private final String BUCKET_NAME = "ligiopen";
    private final PlayerMapper playerMapper;
    private final FileMapper fileMapper;
    private final AwsService awsService;
    @Autowired
    public ClubMapper(
            PlayerMapper playerMapper,
            AwsService awsService,
            FileMapper fileMapper
    ) {
        this.playerMapper = playerMapper;
        this.awsService = awsService;
        this.fileMapper = fileMapper;
    }

    public ClubDetailsDto clubDetailsDto(Club club) {

        FileDto clubMainPhoto = null;

        FileDto clubLogo = null;

        if(club.getClubMainPhoto() != null) {
            clubMainPhoto = fileMapper.fileDto(club.getClubMainPhoto());
        }

        if(club.getClubLogo() != null) {
            clubLogo = fileMapper.fileDto(club.getClubLogo());
        }

        List<PlayerDto> players = new ArrayList<>();

        List<FileDto> files = new ArrayList<>();

        if(club.getPlayerClubs() != null) {
            players = club.getPlayerClubs().stream()
                    .filter(playerClub -> !playerClub.getArchived())
                    .map(playerClub -> playerMapper.playerDto(playerClub.getPlayer()))
                    .collect(Collectors.toList());
        }

        if(!club.getFiles().isEmpty()) {
            files.addAll(club.getFiles().stream().map(fileMapper::fileDto).toList());
        }

        return ClubDetailsDto.builder()
                .clubId(club.getId())
                .clubLogo(clubLogo)
                .clubMainPhoto(clubMainPhoto)
                .name(club.getName())
                .clubAbbreviation(club.getClubAbbreviation())
                .description(club.getDescription())
                .startedOn(club.getStartedOn())
                .country(club.getCountry())
                .county(club.getCounty())
                .town(club.getTown())
                .createdAt(club.getCreatedAt())
                .archived(club.getArchived())
                .archivedAt(club.getArchivedAt())
                .players(players)
                .files(files)
                .build();
    }

    public PlayerClubDto playerClubDto(PlayerClub playerClub) {
        return PlayerClubDto.builder()
                .id(playerClub.getId())
                .playerId(playerClub.getPlayer().getId())
                .clubId(playerClub.getClub().getId())
                .createdAt(playerClub.getCreatedAt())
                .archived(playerClub.getArchived())
                .archivedAt(playerClub.getArchivedAt())
                .build();
    }
}
