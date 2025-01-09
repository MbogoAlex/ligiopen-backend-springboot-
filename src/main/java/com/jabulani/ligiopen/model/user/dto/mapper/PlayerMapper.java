package com.jabulani.ligiopen.model.user.dto.mapper;

import com.jabulani.ligiopen.model.aws.File;
import com.jabulani.ligiopen.model.club.PlayerClub;
import com.jabulani.ligiopen.model.user.Player;
import com.jabulani.ligiopen.model.user.dto.PlayerDto;

import java.util.stream.Collectors;

public class PlayerMapper {
    public PlayerDto playerDto(Player player) {

        Integer clubId = null;

        if(player.getPlayerClubs() != null) {
            clubId = player.getPlayerClubs().stream()
                    .filter(playerClub -> !playerClub.getArchived())
                    .map(playerClub -> playerClub.getClub().getId())
                    .findFirst()
                    .orElse(null);
        }

        return PlayerDto.builder()
                .playerId(player.getId())
                .username(player.getUsername())
                .number(player.getNumber())
                .playerPosition(player.getPlayerPosition())
                .age(player.getAge())
                .height(player.getHeight())
                .weight(player.getWeight())
                .country(player.getCountry())
                .county(player.getCounty())
                .town(player.getTown())
                .clubId(clubId)
                .files(player.getFiles().stream().map(File::getName).collect(Collectors.toList()))
                .build();
    }
}
