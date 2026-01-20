package com.example.exception_handling_bootcamp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCarIU {
    @Size(min = 2,message = "The model name must be at least 2 letters long.")
    private String model;
    @Min(value = 1,message = "The price can be at least 1.")
    private Double price;
}
