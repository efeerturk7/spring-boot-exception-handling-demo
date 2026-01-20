package com.example.exception_handling_bootcamp.controller;

import com.example.exception_handling_bootcamp.dto.DtoBrand;
import com.example.exception_handling_bootcamp.dto.DtoBrandIU;
import com.example.exception_handling_bootcamp.entity.RootEntity;
import com.example.exception_handling_bootcamp.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exception_bc/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @PostMapping("/add")
    public DtoBrand addBrand(@Valid @RequestBody DtoBrandIU newBrand){
        return brandService.addBrand(newBrand);
    }
    @GetMapping("/find/{id}")
    public RootEntity<DtoBrand> findBrandById(@PathVariable(name = "id") Long id){
        return RootEntity.ok(brandService.findBrandById(id));
    }
}
