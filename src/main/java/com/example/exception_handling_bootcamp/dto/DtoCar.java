package com.example.exception_handling_bootcamp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCar {
    private String model;
    private Double price;
    private DtoBrandIU brand;
}
