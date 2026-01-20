package com.example.exception_handling_bootcamp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoBrandIU {
    @NotNull(message = "name cannot be empty")
    private String name;
}
