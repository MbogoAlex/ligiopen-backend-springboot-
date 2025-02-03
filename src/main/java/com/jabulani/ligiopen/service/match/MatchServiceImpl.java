package com.jabulani.ligiopen.service.match;

import com.jabulani.ligiopen.dao.club.ClubDao;
import com.jabulani.ligiopen.dao.match.MatchDao;
import com.jabulani.ligiopen.model.match.entity.events.dto.fixtureDto.MatchFixtureCreationDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.fixtureDto.MatchFixtureDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.fixtureDto.MatchFixtureUpdateDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.fixtureDto.mapper.MatchFixtureDtoMapper;
import com.jabulani.ligiopen.model.match.entity.events.dto.matchLocationDto.MatchLocationDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.matchLocationDto.MatchLocationUpdateDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.matchLocationDto.mapper.MatchLocationDtoMapper;
import com.jabulani.ligiopen.model.match.entity.events.dto.postMatchDto.MatchCommentaryCreationDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.postMatchDto.MatchCommentaryDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.postMatchDto.MatchCommentaryUpdateDto;
import com.jabulani.ligiopen.service.aws.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService{
    private final AwsService awsService;
    private final MatchDao matchDao;
    private final ClubDao clubDao;

    private final MatchLocationDtoMapper matchLocationDtoMapper;
    private final MatchFixtureDtoMapper matchFixtureDtoMapper;
    @Autowired
    public MatchServiceImpl(
            AwsService awsService,
            MatchDao matchDao,
            ClubDao clubDao,
            MatchLocationDtoMapper matchLocationDtoMapper,
            MatchFixtureDtoMapper matchFixtureDtoMapper
    ) {
        this.awsService = awsService;
        this.matchDao = matchDao;
        this.clubDao = clubDao;
        this.matchLocationDtoMapper = matchLocationDtoMapper;
        this.matchFixtureDtoMapper = matchFixtureDtoMapper;
    }
    @Transactional
    @Override
    public MatchLocationDto createMatchLocation(MatchFixtureCreationDto matchFixtureCreationDto) {
        return null;
    }

    @Override
    public MatchLocationDto updateMatchLocation(MatchLocationUpdateDto matchLocationUpdateDto) {
        return null;
    }

    @Override
    public MatchLocationDto uploadMatchLocationFiles(Integer locationId, MultipartFile[] files) {
        return null;
    }

    @Override
    public MatchLocationDto removeMatchLocationFile(Integer locationId, Integer fileId) {
        return null;
    }

    @Override
    public MatchLocationDto getMatchLocationById(Integer locationId) {
        return null;
    }

    @Override
    public List<MatchLocationDto> getAllMatchLocations() {
        return null;
    }

    @Override
    public MatchFixtureDto createMatchFixture(MatchFixtureCreationDto matchFixtureCreationDto) {
        return null;
    }

    @Override
    public MatchFixtureDto updateMatchFixture(MatchFixtureUpdateDto matchFixtureUpdateDto) {
        return null;
    }

    @Override
    public MatchFixtureDto uploadMatchFixtureFiles(Integer fixtureId, MultipartFile[] files) {
        return null;
    }

    @Override
    public MatchFixtureDto removeMatchFixtureFile(Integer fixtureId, Integer fileId) {
        return null;
    }

    @Override
    public MatchFixtureDto getMatchFixtureById(Integer id) {
        return null;
    }

    @Override
    public List<MatchFixtureDto> getAllMatchFixtures() {
        return null;
    }

    @Override
    public MatchCommentaryDto createMatchCommentary(MatchCommentaryCreationDto matchCommentaryCreationDto) {
        return null;
    }

    @Override
    public MatchCommentaryDto updateMatchCommentary(MatchCommentaryUpdateDto matchCommentaryUpdateDto) {
        return null;
    }

    @Override
    public MatchCommentaryDto uploadEventFiles(Integer commentaryId, MultipartFile[] files) {
        return null;
    }

    @Override
    public MatchCommentaryDto getMatchCommentary(Integer commentaryId) {
        return null;
    }

    @Override
    public List<MatchCommentaryDto> getAllMatchCommentaries() {
        return null;
    }
}
