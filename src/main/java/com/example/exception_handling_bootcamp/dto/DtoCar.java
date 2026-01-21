package com.example.exception_handling_bootcamp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DtoCar {
    private Long id;
    private String model;
    private Double price;
    private DtoBrandIU brand;
}
