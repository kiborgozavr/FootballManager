package com.footman.footballmanager.repos;

import com.footman.footballmanager.models.Team;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepo extends JpaRepository<Team, Long> {

    @EntityGraph(attributePaths = {"players"})
    Optional<Team> findById(Long id);

    Optional<Team> findByTeamName(String teamName);
}