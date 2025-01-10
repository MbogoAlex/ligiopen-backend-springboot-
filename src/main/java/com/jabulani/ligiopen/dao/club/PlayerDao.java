package com.jabulani.ligiopen.dao.club;

import com.jabulani.ligiopen.model.club.Player;

import java.util.List;

public interface PlayerDao {
    Player addPlayer(Player player);
    Player updatePlayer(Player player);
    Player getPlayerById(Integer id);
    List<Player> getPlayers();
}
