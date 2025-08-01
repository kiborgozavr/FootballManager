package com.footman.footballmanager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private long playerId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    @PastOrPresent
    private LocalDate birthDate;

    /**Date of the beginning of the player's sports career */
    @NotNull
    @PastOrPresent
    private LocalDate careerStart;

    /**Date when the player was accepted into the team*/
    @NotNull
    @PastOrPresent
    private LocalDate joiningTeamDate;

    /** Team in which the player currently plays*/
    @NotNull
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
}
