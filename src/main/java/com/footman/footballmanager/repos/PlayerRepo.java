package com.footman.footballmanager.repos;

import com.footman.footballmanager.models.Player;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Long> {
    @EntityGraph(attributePaths = {"team"})
    Optional<Player> findById(long id);
}
