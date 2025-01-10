package com.jabulani.ligiopen.model.club.dto.mapper;

import com.jabulani.ligiopen.model.aws.File;
import com.jabulani.ligiopen.model.club.entity.Player;
import com.jabulani.ligiopen.model.club.dto.PlayerDto;
import com.jabulani.ligiopen.service.aws.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class PlayerMapper {

    private final String BUCKET_NAME = "ligiopen";

    private final AwsService awsService;
    @Autowired
    public PlayerMapper(AwsService awsService) {
        this.awsService = awsService;
    }
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
                .mainPhoto(awsService.getFileUrl(BUCKET_NAME, player.getMainPhoto().getName()))
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
                .files(player.getFiles().stream().map(file -> awsService.getFileUrl(BUCKET_NAME, file.getName())).collect(Collectors.toList()))
                .build();
    }
}
