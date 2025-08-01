package com.footman.footballmanager.services.impl;

import com.footman.footballmanager.dto.DtoMapper;
import com.footman.footballmanager.dto.PlayerDTO;
import com.footman.footballmanager.exception.InvalidRequestException;
import com.footman.footballmanager.exception.ResourceNotFoundException;
import com.footman.footballmanager.models.Player;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.footman.footballmanager.repos.PlayerRepo;
import com.footman.footballmanager.services.PlayerService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepo playerRepo;
    private final DtoMapper dtoMapper;

    public PlayerServiceImpl(PlayerRepo playerRepo, DtoMapper dtoMapper) {
        this.playerRepo = playerRepo;
        this.dtoMapper = dtoMapper;
    }

    @Override
    @Transactional
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        Player player = dtoMapper.fromPlayerDTO(playerDTO);
        Player savedPlayer = playerRepo.save(player);
        return dtoMapper.toPlayerDTO(savedPlayer);
    }

    @Override
    public PlayerDTO readById(long playerId) {
        Player player = playerRepo.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player", "id", playerId));
        return dtoMapper.toPlayerDTO(player);
    }

    @Override
    @Transactional
    public PlayerDTO updatePlayer(long playerId, PlayerDTO playerDTO) {

        if (playerId <= 0) {
            throw new InvalidRequestException("Player ID must be positive");
        }

        Player existingPlayer = playerRepo.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player", "id", playerId));
        dtoMapper.updatePlayerFromDTO(playerDTO, existingPlayer);
        Player savedPlayer = playerRepo.save(existingPlayer);
        return dtoMapper.toPlayerDTO(savedPlayer);
    }

    @Override
    @Transactional
    public void deleteById(long playerId) {

        if (!playerRepo.existsById(playerId)) {
            throw new ResourceNotFoundException("Player", "id", playerId);
        }
        playerRepo.deleteById(playerId);
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return playerRepo.findAll().stream()
                .map(dtoMapper::toPlayerDTO)
                .collect(Collectors.toList());
    }
}
