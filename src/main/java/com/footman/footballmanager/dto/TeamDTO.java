package com.footman.footballmanager.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class TeamDTO {

    private long teamId;

    @NotBlank
    private String teamName;

    @NotNull
    @DecimalMin(value = "0.0", message = "Commission cannot be less than 0")
    @DecimalMax(value = "10.0", message = "Commission cannot be more than 10")
    private BigDecimal commission;

    @NotNull
    @PositiveOrZero
    private BigDecimal balance;

    @NotNull
    private List<PlayerDTO> players = new ArrayList<>();
}