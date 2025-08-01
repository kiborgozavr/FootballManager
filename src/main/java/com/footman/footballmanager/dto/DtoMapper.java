package com.footman.footballmanager.dto;

import com.footman.footballmanager.exception.ResourceNotFoundException;
import com.footman.footballmanager.models.Player;
import com.footman.footballmanager.models.Team;
import com.footman.footballmanager.repos.TeamRepo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class DtoMapper {
    private final TeamRepo teamRepo;

    public DtoMapper(TeamRepo teamRepo) {
        this.teamRepo = teamRepo;
    }

    public TeamDTO toTeamDTO(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeamId(team.getTeamId());
        teamDTO.setTeamName(team.getTeamName());
        teamDTO.setCommission(team.getCommission());
        teamDTO.setBalance(team.getBalance());
        teamDTO.setPlayers(team.getPlayers().stream()
                .map(this::toPlayerDTO)
                .collect(Collectors.toCollection(ArrayList::new)));
        return teamDTO;
    }

    public PlayerDTO toPlayerDTO(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerId(player.getPlayerId());
        playerDTO.setFirstName(player.getFirstName());
        playerDTO.setLastName(player.getLastName());
        playerDTO.setBirthDate(player.getBirthDate());
        playerDTO.setCareerStart(player.getCareerStart());
        playerDTO.setJoiningTeamDate(player.getJoiningTeamDate());
        playerDTO.setTeamName(player.getTeam() != null ? player.getTeam().getTeamName() : null);
        return playerDTO;
    }

    public Player fromPlayerDTO(PlayerDTO dto) {
        Player player = new Player();
        mapPlayerFields(dto, player);
        return player;
    }

    public void updatePlayerFromDTO(PlayerDTO dto, Player player) {
        mapPlayerFields(dto, player);
    }

    private void mapPlayerFields(PlayerDTO dto, Player player) {
        player.setFirstName(dto.getFirstName());
        player.setLastName(dto.getLastName());
        player.setBirthDate(dto.getBirthDate());
        player.setCareerStart(dto.getCareerStart());
        player.setJoiningTeamDate(dto.getJoiningTeamDate());
        Team team = teamRepo.findByTeamName(dto.getTeamName())
                .orElseThrow(() -> new ResourceNotFoundException("Team", "name", dto.getTeamName()));
        player.setTeam(team);
    }

    public Team fromTeamDTO(TeamDTO dto) {
        Team team = new Team();
        mapTeamFields(dto, team);
        return team;
    }

    public void updateTeamFromDTO(TeamDTO dto, Team team) {
        mapTeamFields(dto, team);
    }

    private void mapTeamFields(TeamDTO dto, Team team) {
        team.setTeamName(dto.getTeamName());
        team.setCommission(dto.getCommission());
        team.setBalance(dto.getBalance());
    }
}