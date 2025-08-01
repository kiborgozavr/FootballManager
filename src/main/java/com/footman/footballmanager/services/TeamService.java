package com.footman.footballmanager.services;

import com.footman.footballmanager.dto.TeamDTO;
import com.footman.footballmanager.models.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {

    TeamDTO createTeam(TeamDTO teamDTO);

    TeamDTO findById(long id);

    TeamDTO updateTeam(long teamId, TeamDTO teamDTO);

    void deleteTeam(long teamId);

    List<TeamDTO> getAllTeams();

    Optional<Team> findByTeamName(String teamName);

    TeamDTO transferPlayer(long playerId, long toTeamId);
}
