package com.example.exception_handling_bootcamp.service;

import com.example.exception_handling_bootcamp.dto.DtoBrand;
import com.example.exception_handling_bootcamp.dto.DtoBrandIU;
import com.example.exception_handling_bootcamp.dto.DtoCar;
import com.example.exception_handling_bootcamp.entity.Brand;
import com.example.exception_handling_bootcamp.entity.Car;
import com.example.exception_handling_bootcamp.exception.BrandNotFoundException;
import com.example.exception_handling_bootcamp.repository.IBrandRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    @Autowired
    private IBrandRepository brandRepository;
    public DtoBrand addBrand(DtoBrandIU newBrand){
        if (newBrand.getName()!=null||!newBrand.getName().isEmpty()){
            Brand brand=new Brand();
            DtoBrand dtoBrand = new DtoBrand();
            BeanUtils.copyProperties(newBrand,brand);
            Brand savedBrand=brandRepository.save(brand);
            BeanUtils.copyProperties(savedBrand,dtoBrand);
            return dtoBrand;
        }else {
            throw new RuntimeException("Not Valid name");
        }
    }
    public DtoBrand findBrandById(Long id){
        if (brandRepository.existsById(id)){
            Optional<Brand>optional=brandRepository.findById(id);
            Brand brand=optional.get();
            List<Car>carList=brand.getCarList();
            List<DtoCar>dtoCarList=new ArrayList<>();
            DtoBrand dtoBrand=new DtoBrand();
            DtoBrandIU dtoBrandIU=new DtoBrandIU();
            BeanUtils.copyProperties(brand,dtoBrand);
            BeanUtils.copyProperties(dtoBrand,dtoBrandIU);
            for (Car car:carList){
                DtoCar dtoCar=new DtoCar();
                BeanUtils.copyProperties(car,dtoCar);
                dtoCar.setBrand(dtoBrandIU);
                dtoCarList.add(dtoCar);
            }
            dtoBrand.setDtoCarList(dtoCarList);
            return dtoBrand;


        }else {
            throw new BrandNotFoundException("Not Founden Brand");
        }
    }
}
