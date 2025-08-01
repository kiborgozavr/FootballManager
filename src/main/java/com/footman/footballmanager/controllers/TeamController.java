package com.footman.footballmanager.controllers;

import com.footman.footballmanager.dto.TeamDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.footman.footballmanager.services.TeamService;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;

    }

    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(@Valid @RequestBody TeamDTO teamDTO) {
        TeamDTO savedTeamDTO = teamService.createTeam(teamDTO);
        return new ResponseEntity<>(savedTeamDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamDTO> getTeam(@PathVariable long teamId) {
        TeamDTO teamDTO = teamService.findById(teamId);
        return ResponseEntity.ok(teamDTO);
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<TeamDTO> updateTeam(@PathVariable long teamId, @Valid @RequestBody TeamDTO teamDTO) {
                TeamDTO updatedTeamDTO = teamService.updateTeam(teamId, teamDTO);
        return ResponseEntity.ok(updatedTeamDTO);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable long teamId) {
        teamService.deleteTeam(teamId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @PostMapping("/{teamId}/players/{playerId}")
    public ResponseEntity<TeamDTO> transferPlayer(@PathVariable long teamId, @PathVariable long playerId) {
        TeamDTO updatedTeamDTO = teamService.transferPlayer(playerId, teamId);
        return ResponseEntity.ok(updatedTeamDTO);
    }
}
