package com.footman.footballmanager.services.impl;

import com.footman.footballmanager.dto.DtoMapper;
import com.footman.footballmanager.dto.TeamDTO;
import com.footman.footballmanager.exception.InsufficientBalanceException;
import com.footman.footballmanager.exception.InvalidPlayerStateException;
import com.footman.footballmanager.exception.InvalidRequestException;
import com.footman.footballmanager.exception.ResourceNotFoundException;
import com.footman.footballmanager.models.Player;
import com.footman.footballmanager.repos.PlayerRepo;
import com.footman.footballmanager.models.Team;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.footman.footballmanager.repos.TeamRepo;
import com.footman.footballmanager.services.TeamService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    private static final Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);
    private final TeamRepo teamRepo;
    private final PlayerRepo playerRepo;
    private final DtoMapper dtoMapper;

    public TeamServiceImpl(TeamRepo teamRepo, PlayerRepo playerRepo, DtoMapper dtoMapper) {
        this.teamRepo = teamRepo;
        this.playerRepo = playerRepo;
        this.dtoMapper = dtoMapper;
    }

    @Override
    @Transactional
    public TeamDTO createTeam(TeamDTO teamDTO) {
        if (teamRepo.findByTeamName(teamDTO.getTeamName()).isPresent()) {
            throw new InvalidRequestException("Team with name " + teamDTO.getTeamName() + " already exists");
        }
        Team team = dtoMapper.fromTeamDTO(teamDTO);
        Team savedTeam = teamRepo.save(team);
        return dtoMapper.toTeamDTO(savedTeam);
    }

    @Override
    public TeamDTO findById(long id) {
        Team team = teamRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", id));
        return dtoMapper.toTeamDTO(team);
    }

    @Override
    @Transactional
    public TeamDTO updateTeam(long teamId, TeamDTO teamDTO) {
        if (teamId <= 0) {
            throw new InvalidRequestException("Team ID must be positive");
        }
        Team existingTeam = teamRepo.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", teamId));
        if (!existingTeam.getTeamName().equals(teamDTO.getTeamName()) &&
                teamRepo.findByTeamName(teamDTO.getTeamName()).isPresent()) {
            throw new InvalidRequestException("Team with name " + teamDTO.getTeamName() + " already exists");
        }
        dtoMapper.updateTeamFromDTO(teamDTO, existingTeam);
        Team savedTeam = teamRepo.save(existingTeam);
        return dtoMapper.toTeamDTO(savedTeam);
    }

    @Override
    @Transactional
    public void deleteTeam(long teamId) {
        if (!teamRepo.existsById(teamId)) {
            throw new ResourceNotFoundException("Team", "id", teamId);
        }
        teamRepo.deleteById(teamId);
    }

    @Override
    public List<TeamDTO> getAllTeams() {
        return teamRepo.findAll().stream()
                .map(dtoMapper::toTeamDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Team> findByTeamName(String teamName) {
        return teamRepo.findByTeamName(teamName);
    }

    @Override
    @Transactional
    public TeamDTO transferPlayer(long playerId, long toTeamId) {
        logger.info("Starting transfer of player {} to team {}", playerId, toTeamId);
        Player player = playerRepo.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player", "id", playerId));
        Team toTeam = teamRepo.findById(toTeamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", toTeamId));
        Team fromTeam = player.getTeam();
        if (fromTeam == null) {
            throw new InvalidPlayerStateException("Player with id " + playerId + " is not in any team");
        }
        if (fromTeam.getTeamId() == toTeamId) {
            throw new InvalidPlayerStateException("Player is already in the target team");
        }
        long ageInYears = ChronoUnit.YEARS.between(player.getBirthDate(), LocalDate.now());
        if (ageInYears <= 0) {
            throw new InvalidPlayerStateException("Player age must be greater than 0");
        }
        long experienceInMonths = ChronoUnit.MONTHS.between(player.getCareerStart(), LocalDate.now());
        if (experienceInMonths < 0) {
            throw new InvalidPlayerStateException("Career start date cannot be in the future");
        }
        BigDecimal transferCost = BigDecimal.valueOf(experienceInMonths * 100000.0 / ageInYears);
        BigDecimal commission = transferCost
                .multiply(toTeam.getCommission()
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP));
        BigDecimal totalCost = transferCost.add(commission);
        if (toTeam.getBalance().compareTo(totalCost) < 0) {
            throw new InsufficientBalanceException("Insufficient balance in team " + toTeam.getTeamName() + " for transfer");
        }
        toTeam.setBalance(toTeam.getBalance().subtract(totalCost));
        fromTeam.setBalance(fromTeam.getBalance().add(totalCost));
        fromTeam.removePlayer(player);
        toTeam.addPlayer(player);
        teamRepo.save(fromTeam);
        teamRepo.save(toTeam);
        logger.info("Transfer completed: player {} to team {}, cost: {}, commission: {}",
                playerId, toTeamId, transferCost, commission);
        return dtoMapper.toTeamDTO(toTeam);
    }
}