package com.jabulani.ligiopen.dao.club;

import com.jabulani.ligiopen.model.club.entity.Club;

import java.util.List;

public interface ClubDao {
    Club addClub(Club club);
    Club updateClub(Club club);
    Club getClubById(Integer id);
    List<Club> getClubs();
}
