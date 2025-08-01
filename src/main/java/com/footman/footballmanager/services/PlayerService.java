package com.footman.footballmanager.services;

import com.footman.footballmanager.dto.PlayerDTO;
import java.util.List;

public interface PlayerService {

    PlayerDTO createPlayer(PlayerDTO playerDTO);

    PlayerDTO readById(long id);

    PlayerDTO updatePlayer(long playerId, PlayerDTO playerDTO);

    void deleteById(long playerId);

    List<PlayerDTO> getAllPlayers();
}
