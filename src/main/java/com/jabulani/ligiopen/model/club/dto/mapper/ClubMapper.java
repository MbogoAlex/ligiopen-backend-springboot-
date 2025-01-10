package com.jabulani.ligiopen.model.club.dto.mapper;

import com.jabulani.ligiopen.model.aws.File;
import com.jabulani.ligiopen.model.club.Club;
import com.jabulani.ligiopen.model.club.dto.ClubDetailsDto;
import com.jabulani.ligiopen.model.club.dto.PlayerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClubMapper {
    private final PlayerMapper playerMapper;
    @Autowired
    public ClubMapper(PlayerMapper playerMapper) {
        this.playerMapper = playerMapper;
    }

    public ClubDetailsDto clubDetailsDto(Club club) {

        List<PlayerDto> players = new ArrayList<>();

        if(club.getPlayerClubs() != null) {
            players = club.getPlayerClubs().stream()
                    .filter(playerClub -> !playerClub.getArchived())
                    .map(playerClub -> playerMapper.playerDto(playerClub.getPlayer()))
                    .collect(Collectors.toList());
        }

        return ClubDetailsDto.builder()
                .clubId(club.getId())
                .clubLogo(club.getClubLogo().getName())
                .name(club.getName())
                .description(club.getDescription())
                .startedOn(club.getStartedOn())
                .country(club.getCountry())
                .county(club.getCounty())
                .town(club.getTown())
                .createdAt(club.getCreatedAt())
                .archived(club.getArchived())
                .archivedAt(club.getArchivedAt())
                .players(players)
                .files(club.getFiles().stream().map(File::getName).collect(Collectors.toList()))
                .build();
    }
}
