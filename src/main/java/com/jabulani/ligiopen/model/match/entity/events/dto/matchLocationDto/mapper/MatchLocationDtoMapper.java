package com.jabulani.ligiopen.model.match.entity.events.dto.matchLocationDto.mapper;

import com.jabulani.ligiopen.model.match.entity.MatchLocation;
import com.jabulani.ligiopen.model.match.entity.events.dto.fixtureDto.mapper.MatchFixtureDtoMapper;
import com.jabulani.ligiopen.model.match.entity.events.dto.matchLocationDto.MatchLocationDto;
import com.jabulani.ligiopen.service.aws.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MatchLocationDtoMapper {
    private final String BUCKET_NAME = "ligiopen";

    private final AwsService awsService;

    private final MatchFixtureDtoMapper matchFixtureDtoMapper;
    @Autowired
    public MatchLocationDtoMapper(
            AwsService awsService,
            MatchFixtureDtoMapper matchFixtureDtoMapper
    ) {
        this.awsService = awsService;
        this.matchFixtureDtoMapper = matchFixtureDtoMapper;
    }

    public MatchLocationDto matchLocationDto(MatchLocation matchLocation) {
        List<String> files = new ArrayList<>();
        if(!matchLocation.getLocationPhotos().isEmpty()) {
            files.addAll(matchLocation.getLocationPhotos().stream().map(file -> awsService.getFileUrl(BUCKET_NAME, file.getName())).toList());
        }
        return MatchLocationDto.builder()
                .locationId(matchLocation.getId())
                .venueName(matchLocation.getVenueName())
                .country(matchLocation.getCountry())
                .county(matchLocation.getCounty())
                .town(matchLocation.getTown())
                .photos(files)
                .matchFixtures(matchLocation.getMatchFixtures().stream().map(matchFixtureDtoMapper::matchFixtureDto).collect(Collectors.toList()))
                .build();
    }
}
