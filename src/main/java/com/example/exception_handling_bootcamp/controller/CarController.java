package com.example.exception_handling_bootcamp.controller;

import com.example.exception_handling_bootcamp.dto.DtoCar;
import com.example.exception_handling_bootcamp.dto.DtoCarIU;
import com.example.exception_handling_bootcamp.entity.RootEntity;
import com.example.exception_handling_bootcamp.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exception_bc/car")
public class CarController {
    @Autowired
    private CarService carService;
    @GetMapping("/find/{id}")
    public RootEntity<DtoCar> getByCarId(@PathVariable(name = "id") Long id){
        return RootEntity.ok(carService.getByCarId(id));
    }
    @PostMapping("/add/{id}")
    public RootEntity<DtoCar> addCar(@Valid @RequestBody DtoCarIU newCar, @PathVariable(name = "id") Long id){
        return RootEntity.ok(carService.addCar(newCar,id));
    }
}
