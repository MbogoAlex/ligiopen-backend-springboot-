package com.jabulani.ligiopen.service.club;

import com.jabulani.ligiopen.dao.club.ClubDao;
import com.jabulani.ligiopen.dao.club.PlayerDao;
import com.jabulani.ligiopen.model.aws.File;
import com.jabulani.ligiopen.model.club.dto.*;
import com.jabulani.ligiopen.model.club.dto.mapper.ClubMapper;
import com.jabulani.ligiopen.model.club.dto.mapper.PlayerMapper;
import com.jabulani.ligiopen.model.club.entity.Club;
import com.jabulani.ligiopen.model.club.entity.Player;
import com.jabulani.ligiopen.model.club.entity.PlayerClub;
import com.jabulani.ligiopen.service.aws.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService{

    private final String BUCKET_NAME = "ligiopen";
    private final ClubDao clubDao;
    private final PlayerDao playerDao;
    private final AwsService awsService;
    private final ClubMapper clubMapper;
    private final PlayerMapper playerMapper;
    @Autowired
    public ClubServiceImpl(
            ClubDao clubDao,
            PlayerDao playerDao,
            AwsService awsService,
            ClubMapper clubMapper,
            PlayerMapper playerMapper
    ) {
        this.clubDao = clubDao;
        this.playerDao = playerDao;
        this.awsService = awsService;
        this.clubMapper = clubMapper;
        this.playerMapper = playerMapper;
    }
    @Transactional
    @Override
    public ClubDetailsDto addClub(AddClubDto addClubDto, MultipartFile logo) throws IOException {
        String fileName = null;

        if(logo != null) {
            fileName = awsService.uploadFile(BUCKET_NAME, logo);
        }


        Club club = Club.builder()
                .name(addClubDto.getName())
                .description(addClubDto.getDescription())
                .country(addClubDto.getCountry())
                .county(addClubDto.getCounty())
                .town(addClubDto.getTown())
                .startedOn(addClubDto.getStartedOn())
                .createdAt(LocalDateTime.now())
                .archived(false)
                .playerClubs(new ArrayList<>())
                .files(new ArrayList<>())
                .clubMainPhoto(null)
                .build();

        File file = File.builder()
                .name(fileName)
                .clubAsLogo(club)
                .build();

        club.setClubLogo(file);

        return clubMapper.clubDetailsDto(clubDao.addClub(club));
    }
    @Transactional
    @Override
    public ClubDetailsDto updateClubDetails(UpdateClubDto updateClubDto) {
        Club club = clubDao.getClubById(updateClubDto.getClubId());

        if(!Objects.equals(club.getName(), updateClubDto.getName())) {
            club.setName(updateClubDto.getName());
        }

        if(!Objects.equals(club.getDescription(), updateClubDto.getDescription())) {
            club.setDescription(updateClubDto.getDescription());
        }

        if(!Objects.equals(club.getCountry(), updateClubDto.getCountry())) {
            club.setCountry(updateClubDto.getCountry());
        }

        if(!Objects.equals(club.getCounty(), updateClubDto.getCounty())) {
            club.setCounty(updateClubDto.getCounty());
        }

        if(!Objects.equals(club.getTown(), updateClubDto.getTown())) {
            club.setTown(updateClubDto.getTown());
        }

        return clubMapper.clubDetailsDto(clubDao.updateClub(club));
    }
    @Transactional
    @Override
    public ClubDetailsDto updateClubLogo(Integer clubId, MultipartFile logo) throws IOException {
        Club club = clubDao.getClubById(clubId);

        File clubLogo = club.getClubLogo();

        if(logo != null) {

            if(club.getClubLogo() != null) {
                awsService.deleteFile(BUCKET_NAME, clubLogo.getName());
            }

            clubLogo.setName(awsService.uploadFile(BUCKET_NAME, logo));
        }

        return clubMapper.clubDetailsDto(clubDao.updateClub(club));
    }
    @Transactional
    @Override
    public ClubDetailsDto setClubMainPhoto(Integer clubId, MultipartFile photo) throws IOException {

        String fileName = null;

        if(photo != null) {
            fileName = awsService.uploadFile(BUCKET_NAME, photo);
        }


        Club club = clubDao.getClubById(clubId);

        if(club.getClubMainPhoto() == null) {
            File file = File.builder()
                    .name(fileName)
                    .clubAsMainPhoto(club)
                    .build();

            club.setClubMainPhoto(file);
        } else {

            File file = club.getClubMainPhoto();

            awsService.deleteFile(BUCKET_NAME, file.getName());

            file.setName(fileName);
        }


        return clubMapper.clubDetailsDto(clubDao.updateClub(club));
    }

    @Transactional
    @Override
    public ClubDetailsDto uploadClubFiles(Integer clubId, MultipartFile[] files) throws IOException {
        Club club = clubDao.getClubById(clubId);

        for (MultipartFile file : files) {
            File clubFile = File.builder()
                    .club(club)
                    .name(awsService.uploadFile(BUCKET_NAME, file))
                    .build();
            club.getFiles().add(clubFile);
        }

        return clubMapper.clubDetailsDto(clubDao.updateClub(club));
    }

    @Override
    public ClubDetailsDto getClubById(Integer id) {
        return clubMapper.clubDetailsDto(clubDao.getClubById(id));
    }

    @Override
    public List<ClubDetailsDto> getClubs() {
        return clubDao.getClubs().stream().map(clubMapper::clubDetailsDto).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public PlayerDto addPlayer(AddPlayerDto addPlayerDto, MultipartFile mainPhoto) throws IOException {

        String mainPic = null;

        if(mainPhoto != null) {
            mainPic = awsService.uploadFile(BUCKET_NAME, mainPhoto);
        }

        File file = File.builder()
                .name(mainPic)
                .build();

        Player player = Player.builder()
                .username(addPlayerDto.getUsername())
                .mainPhoto(file)
                .number(addPlayerDto.getNumber())
                .playerPosition(addPlayerDto.getPlayerPosition())
                .age(addPlayerDto.getAge())
                .height(addPlayerDto.getHeight())
                .weight(addPlayerDto.getWeight())
                .country(addPlayerDto.getCountry())
                .county(addPlayerDto.getCounty())
                .town(addPlayerDto.getTown())
                .playerClubs(new ArrayList<>())
                .files(new ArrayList<>())
                .build();

        file.setPlayer(player);
        player.getFiles().add(file);


        return playerMapper.playerDto(playerDao.addPlayer(player));
    }
    @Transactional
    @Override
    public PlayerDto signPlayer(SignPlayerDto signPlayerDto) {
        Player player = playerDao.getPlayerById(signPlayerDto.getPlayerId());

        Club newClub = clubDao.getClubById(signPlayerDto.getNewClubId());

        PlayerClub oldPlayerClub = player.getPlayerClubs().stream()
                .filter(playerClub1 -> playerClub1.getPlayer().getId().equals(player.getId()) && !playerClub1.getArchived())
                .findFirst()
                .orElse(null);

        if(oldPlayerClub != null) {
            oldPlayerClub.setArchived(true);
            oldPlayerClub.setArchivedAt(LocalDateTime.now());
        }

        PlayerClub playerClub = PlayerClub.builder()
                .player(player)
                .club(newClub)
                .createdAt(LocalDateTime.now())
                .archived(false)
                .build();

        newClub.getPlayerClubs().add(playerClub);

        clubDao.updateClub(newClub);

        player.getPlayerClubs().add(playerClub);


        return playerMapper.playerDto(playerDao.updatePlayer(player));
    }
    @Transactional
    @Override
    public PlayerDto removePlayerFromTeam(UnsignPlayerDto unsignPlayerDto) {
        Player player = playerDao.getPlayerById(unsignPlayerDto.getPlayerId());
        Club club = clubDao.getClubById(unsignPlayerDto.getClubId());

        if (club != null && player != null) {
            PlayerClub playerClub = player.getPlayerClubs().stream()
                    .filter(pc -> pc.getPlayer().getId().equals(player.getId()) && !pc.getArchived())
                    .findFirst()
                    .orElse(null);

            if (playerClub != null) {
                playerClub.setArchived(true);
                playerClub.setArchivedAt(LocalDateTime.now());
            } else {
                throw new IllegalStateException("Player is not currently active in the specified club.");
            }

        } else {
            throw new IllegalArgumentException("Player or Club not found.");
        }


        return playerMapper.playerDto(playerDao.updatePlayer(player));
    }
    @Transactional
    @Override
    public PlayerDto updatePlayerDetails(UpdatePlayerDto updatePlayerDto) {
        Player player = playerDao.getPlayerById(updatePlayerDto.getPlayerId());

        if(!Objects.equals(player.getUsername(), updatePlayerDto.getUsername())) {
            player.setUsername(updatePlayerDto.getUsername());
        }

        if(!Objects.equals(player.getHeight(), updatePlayerDto.getHeight())) {
            player.setHeight(updatePlayerDto.getHeight());
        }

        if(!Objects.equals(player.getWeight(), updatePlayerDto.getWeight())) {
            player.setWeight(updatePlayerDto.getWeight());
        }

        if(!Objects.equals(player.getNumber(), updatePlayerDto.getNumber())) {
            player.setNumber(updatePlayerDto.getNumber());
        }

        if(player.getPlayerPosition() != updatePlayerDto.getPlayerPosition()) {
            player.setPlayerPosition(updatePlayerDto.getPlayerPosition());
        }

        if(!Objects.equals(player.getCountry(), updatePlayerDto.getCountry())) {
            player.setCountry(updatePlayerDto.getCountry());
        }

        if(!Objects.equals(player.getCounty(), updatePlayerDto.getCounty())) {
            player.setCounty(updatePlayerDto.getCounty());
        }

        if(!Objects.equals(player.getTown(), updatePlayerDto.getTown())) {
            player.setTown(updatePlayerDto.getTown());
        }

        return playerMapper.playerDto(playerDao.updatePlayer(player));
    }
    @Transactional
    @Override
    public PlayerDto updatePlayerMainPhoto(Integer playerId, MultipartFile mainPhoto) throws IOException {
        Player player = playerDao.getPlayerById(playerId);
        File mainPic = player.getMainPhoto();

        if(mainPhoto != null) {
            mainPic.setName(awsService.uploadFile(BUCKET_NAME, mainPhoto));
        }

        return playerMapper.playerDto(playerDao.updatePlayer(player));
    }
    @Transactional
    @Override
    public PlayerDto uploadPlayerFiles(Integer playerId, MultipartFile[] files) throws IOException {
        Player player = playerDao.getPlayerById(playerId);

        for (MultipartFile file : files) {
            File clubFile = File.builder()
                    .player(player)
                    .name(awsService.uploadFile(BUCKET_NAME, file))
                    .build();
            player.getFiles().add(clubFile);
        }

        return playerMapper.playerDto(playerDao.updatePlayer(player));
    }

    @Override
    public List<PlayerDto> getPlayers() {
        return playerDao.getPlayers().stream().map(playerMapper::playerDto).collect(Collectors.toList());
    }
}
