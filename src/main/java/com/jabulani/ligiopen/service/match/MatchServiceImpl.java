package com.jabulani.ligiopen.service.match;

import com.jabulani.ligiopen.dao.club.ClubDao;
import com.jabulani.ligiopen.dao.club.PlayerDao;
import com.jabulani.ligiopen.dao.fileDao.FileDao;
import com.jabulani.ligiopen.dao.match.MatchDao;
import com.jabulani.ligiopen.model.aws.File;
import com.jabulani.ligiopen.model.club.entity.Club;
import com.jabulani.ligiopen.model.club.entity.Player;
import com.jabulani.ligiopen.model.match.MatchStatus;
import com.jabulani.ligiopen.model.match.entity.MatchCommentary;
import com.jabulani.ligiopen.model.match.entity.MatchFixture;
import com.jabulani.ligiopen.model.match.entity.MatchLocation;
import com.jabulani.ligiopen.model.match.entity.PostMatchAnalysis;
import com.jabulani.ligiopen.model.match.entity.events.MatchEvent;
import com.jabulani.ligiopen.model.match.entity.events.dto.fixtureDto.MatchFixtureCreationDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.fixtureDto.MatchFixtureDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.fixtureDto.MatchFixtureUpdateDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.fixtureDto.mapper.MatchFixtureDtoMapper;
import com.jabulani.ligiopen.model.match.entity.events.dto.matchLocationDto.MatchLocationCreationDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.matchLocationDto.MatchLocationDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.matchLocationDto.MatchLocationUpdateDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.matchLocationDto.mapper.MatchLocationDtoMapper;
import com.jabulani.ligiopen.model.match.entity.events.dto.postMatchDto.MatchCommentaryCreationDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.postMatchDto.MatchCommentaryDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.postMatchDto.MatchCommentaryUpdateDto;
import com.jabulani.ligiopen.model.match.entity.events.dto.postMatchDto.mapper.MatchCommentaryDtoMapper;
import com.jabulani.ligiopen.service.aws.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService{
    private final String BUCKET_NAME = "ligiopen";
    private final AwsService awsService;
    private final MatchDao matchDao;
    private final ClubDao clubDao;
    private final FileDao fileDao;
    private final PlayerDao playerDao;

    private final MatchLocationDtoMapper matchLocationDtoMapper;
    private final MatchFixtureDtoMapper matchFixtureDtoMapper;
    private final MatchCommentaryDtoMapper matchCommentaryDtoMapper;
    @Autowired
    public MatchServiceImpl(
            AwsService awsService,
            MatchDao matchDao,
            ClubDao clubDao,
            MatchLocationDtoMapper matchLocationDtoMapper,
            MatchFixtureDtoMapper matchFixtureDtoMapper,
            FileDao fileDao,
            PlayerDao playerDao,
            MatchCommentaryDtoMapper matchCommentaryDtoMapper
    ) {
        this.awsService = awsService;
        this.matchDao = matchDao;
        this.clubDao = clubDao;
        this.matchLocationDtoMapper = matchLocationDtoMapper;
        this.matchFixtureDtoMapper = matchFixtureDtoMapper;
        this.fileDao = fileDao;
        this.playerDao = playerDao;
        this.matchCommentaryDtoMapper = matchCommentaryDtoMapper;
    }
    @Transactional
    @Override
    public MatchLocationDto createMatchLocation(MatchLocationCreationDto matchLocationCreationDto) {
        MatchLocation matchLocation = MatchLocation.builder()
                .country(matchLocationCreationDto.getCountry())
                .county(matchLocationCreationDto.getCounty())
                .town(matchLocationCreationDto.getTown())
                .locationPhotos(new ArrayList<>())
                .build();

        return matchLocationDtoMapper.matchLocationDto(matchDao.createMatchLocation(matchLocation));
    }
    @Transactional
    @Override
    public MatchLocationDto updateMatchLocation(MatchLocationUpdateDto matchLocationUpdateDto) {
        MatchLocation matchLocation = matchDao.getMatchLocationById(matchLocationUpdateDto.getLocationId());
        if(!matchLocationUpdateDto.getCountry().equals(matchLocation.getCountry())) {
            matchLocation.setCountry(matchLocationUpdateDto.getCountry());
        }

        if(!matchLocationUpdateDto.getCounty().equals(matchLocation.getCounty())) {
            matchLocation.setCounty(matchLocationUpdateDto.getCounty());
        }

        if(!matchLocationUpdateDto.getTown().equals(matchLocation.getTown())) {
            matchLocation.setTown(matchLocationUpdateDto.getTown());
        }

        return matchLocationDtoMapper.matchLocationDto(matchDao.updateMatchLocation(matchLocation));
    }
    @Transactional
    @Override
    public MatchLocationDto uploadMatchLocationFiles(Integer locationId, MultipartFile[] files) throws IOException {
        MatchLocation matchLocation = matchDao.getMatchLocationById(locationId);

        for (MultipartFile file : files) {
            File locationFile = File.builder()
                    .matchLocation(matchLocation)
                    .name(awsService.uploadFile(BUCKET_NAME, file))
                    .build();

            matchLocation.getLocationPhotos().add(locationFile);
        }



        return matchLocationDtoMapper.matchLocationDto(matchDao.updateMatchLocation(matchLocation));
    }
    @Transactional
    @Override
    public MatchLocationDto removeMatchLocationFile(Integer locationId, Integer fileId) {
        MatchLocation matchLocation = matchDao.getMatchLocationById(locationId);
        File file = fileDao.getFileById(fileId);
        awsService.deleteFile(BUCKET_NAME, file.getName());

        return matchLocationDtoMapper.matchLocationDto(matchDao.updateMatchLocation(matchLocation));
    }

    @Override
    public MatchLocationDto getMatchLocationById(Integer locationId) {

        return matchLocationDtoMapper.matchLocationDto(matchDao.getMatchLocationById(locationId));
    }

    @Override
    public List<MatchLocationDto> getAllMatchLocations() {
        return matchDao.getMatchLocations().stream().map(matchLocationDtoMapper::matchLocationDto).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public MatchFixtureDto createMatchFixture(MatchFixtureCreationDto matchFixtureCreationDto) {
        MatchLocation matchLocation = matchDao.getMatchLocationById(matchFixtureCreationDto.getLocationId());
        Club homeClub = clubDao.getClubById(matchFixtureCreationDto.getHomeClub());
        Club awayClub = clubDao.getClubById(matchFixtureCreationDto.getAwayClub());

        MatchFixture matchFixture = MatchFixture.builder()
                .matchLocation(matchLocation)
                .homeClub(homeClub)
                .awayClub(awayClub)
                .createdAt(LocalDateTime.now())
                .matchDateTime(matchFixtureCreationDto.getMatchDateTime())
                .status(MatchStatus.PENDING)
                .matchStatistics(new ArrayList<>())
                .build();

        return matchFixtureDtoMapper.matchFixtureDto(matchDao.createMatchFixture(matchFixture));
    }
    @Transactional
    @Override
    public MatchFixtureDto updateMatchFixture(MatchFixtureUpdateDto matchFixtureUpdateDto) {
        MatchFixture matchFixture = matchDao.getMatchFixtureById(matchFixtureUpdateDto.getMatchFixtureId());

        if(!matchFixtureUpdateDto.getHomeClub().equals(matchFixture.getHomeClub().getId())) {
            Club homeClub = clubDao.getClubById(matchFixture.getHomeClub().getId());
            matchFixture.setHomeClub(homeClub);
        }

        if(!matchFixtureUpdateDto.getAwayClub().equals(matchFixture.getAwayClub().getId())) {
            Club awayClub = clubDao.getClubById(matchFixture.getAwayClub().getId());
            matchFixture.setHomeClub(awayClub);
        }

        if(!matchFixtureUpdateDto.getMatchDateTime().equals(matchFixture.getMatchDateTime())) {
            matchFixture.setMatchDateTime(matchFixtureUpdateDto.getMatchDateTime());
        }

        if(matchFixtureUpdateDto.getMatchStatus().equals(matchFixture.getStatus())) {
            matchFixture.setStatus(matchFixtureUpdateDto.getMatchStatus());
        }

        return matchFixtureDtoMapper.matchFixtureDto(matchDao.updateMatchFixture(matchFixture));
    }
    @Transactional
    @Override
    public MatchFixtureDto uploadMatchFixtureFiles(Integer fixtureId, MultipartFile[] files) throws IOException {
//        MatchFixture matchFixture = matchDao.getMatchFixtureById(fixtureId);
//
//        for (MultipartFile file : files) {
//            File matchFixtureFile = File.builder()
//                    .name(awsService.uploadFile(BUCKET_NAME, file))
//                    .build();
//
//            matchFixture.get
//        }

        return null;
    }

    @Override
    public MatchFixtureDto removeMatchFixtureFile(Integer fixtureId, Integer fileId) {
        return null;
    }

    @Override
    public MatchFixtureDto getMatchFixtureById(Integer id) {
        return matchFixtureDtoMapper.matchFixtureDto(matchDao.getMatchFixtureById(id));
    }

    @Override
    public List<MatchFixtureDto> getAllMatchFixtures() {
        return matchDao.getMatchFixtures().stream().map(matchFixtureDtoMapper::matchFixtureDto).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public MatchCommentaryDto createMatchCommentary(MatchCommentaryCreationDto matchCommentaryCreationDto) {

//        for (MultipartFile file : files) {
//            File locationFile = File.builder()
//                    .matchLocation(matchLocation)
//                    .name(awsService.uploadFile(BUCKET_NAME, file))
//                    .build();
//
//            matchLocation.getLocationPhotos().add(locationFile);
//        }

        PostMatchAnalysis postMatchAnalysis = matchDao.getPostMatchAnalysisById(matchCommentaryCreationDto.getPostMatchAnalysisId());
        Player player = playerDao.getPlayerById(matchCommentaryCreationDto.getMatchEvent().getMainPlayerId());

        MatchEvent matchEvent = MatchEvent.builder()
                .title(matchCommentaryCreationDto.getMatchEvent().getTitle())
                .summary(matchCommentaryCreationDto.getMatchEvent().getSummary())
                .minute(matchCommentaryCreationDto.getMinute())
                .createdAt(LocalDateTime.now())
                .player(player)
                .matchEventType(matchCommentaryCreationDto.getMatchEvent().getMatchEventType())
                .build();

        MatchCommentary matchCommentary = MatchCommentary.builder()
                .postMatchAnalysis(postMatchAnalysis)
                .createdAt(LocalDateTime.now())
                .archived(false)
                .matchEvent(matchEvent)
                .build();

        matchEvent.setMatchCommentary(matchCommentary);

        return matchCommentaryDtoMapper.matchCommentaryDto(matchDao.createMatchCommentary(matchCommentary));
    }
    @Transactional
    @Override
    public MatchCommentaryDto updateMatchCommentary(MatchCommentaryUpdateDto matchCommentaryUpdateDto) {
        MatchCommentary matchCommentary = matchDao.getMatchCommentaryById(matchCommentaryUpdateDto.getCommentaryId());
        MatchEvent matchEvent = matchCommentary.getMatchEvent();

        LocalDateTime updatedAt = LocalDateTime.now();

        if(!matchEvent.getTitle().equals(matchCommentaryUpdateDto.getMatchEvent().getTitle())) {
            matchEvent.setTitle(matchCommentaryUpdateDto.getMatchEvent().getTitle());
            matchCommentary.setUpdatedAt(updatedAt);
        }

        if(!matchEvent.getSummary().equals(matchCommentaryUpdateDto.getMatchEvent().getSummary())) {
            matchEvent.setSummary(matchCommentaryUpdateDto.getMatchEvent().getSummary());
            matchCommentary.setUpdatedAt(updatedAt);
        }

        if(!matchEvent.getMinute().equals(matchCommentaryUpdateDto.getMatchEvent().getMinute())) {
            matchEvent.setMinute(matchCommentaryUpdateDto.getMatchEvent().getMinute());
            matchCommentary.setUpdatedAt(updatedAt);
        }

        if(!matchEvent.getPlayer().getId().equals(matchCommentaryUpdateDto.getMatchEvent().getMainPlayerId())) {
            Player player = playerDao.getPlayerById(matchCommentaryUpdateDto.getMatchEvent().getMainPlayerId());
            matchEvent.setPlayer(player);
            matchCommentary.setUpdatedAt(updatedAt);
        }

        return matchCommentaryDtoMapper.matchCommentaryDto(matchDao.updateMatchCommentary(matchCommentary));
    }
    @Transactional
    @Override
    public MatchCommentaryDto uploadEventFiles(Integer commentaryId, MultipartFile[] files) throws IOException {

        MatchCommentary matchCommentary = matchDao.getMatchCommentaryById(commentaryId);

        for (MultipartFile file : files) {
            File eventFile = File.builder()
                    .name(awsService.uploadFile(BUCKET_NAME, file))
                    .build();

            matchCommentary.getFiles().add(eventFile);
        }
        return matchCommentaryDtoMapper.matchCommentaryDto(matchDao.updateMatchCommentary(matchCommentary));
    }

    @Override
    public MatchCommentaryDto getMatchCommentary(Integer commentaryId) {
        return matchCommentaryDtoMapper.matchCommentaryDto(matchDao.getMatchCommentaryById(commentaryId));
    }

    @Override
    public List<MatchCommentaryDto> getAllMatchCommentaries() {
        return matchDao.getAllMatchCommentaries().stream().map(matchCommentaryDtoMapper::matchCommentaryDto).collect(Collectors.toList());
    }
}
