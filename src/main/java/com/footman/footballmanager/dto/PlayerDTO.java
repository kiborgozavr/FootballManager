package com.footman.footballmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PlayerDTO {

    private long playerId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    @PastOrPresent
    private LocalDate birthDate;

    @NotNull
    @PastOrPresent
    private LocalDate careerStart;

    @NotNull
    @PastOrPresent
    private LocalDate joiningTeamDate;

    @NotBlank
    private String teamName;
}
