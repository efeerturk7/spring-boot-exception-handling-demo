package com.example.exception_handling_bootcamp.service;
import com.example.exception_handling_bootcamp.dto.DtoBrandIU;
import com.example.exception_handling_bootcamp.dto.DtoCar;
import com.example.exception_handling_bootcamp.dto.DtoCarIU;
import com.example.exception_handling_bootcamp.entity.Brand;
import com.example.exception_handling_bootcamp.entity.Car;
import com.example.exception_handling_bootcamp.exception.BrandNotFoundException;
import com.example.exception_handling_bootcamp.exception.CarNotFoundException;
import com.example.exception_handling_bootcamp.repository.IBrandRepository;
import com.example.exception_handling_bootcamp.repository.ICarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private ICarRepository carRepository;
    @Autowired
    private IBrandRepository brandRepository;

    public DtoCar getCarById(Long id) {
        if (carRepository.existsById(id)) {
            DtoCar dtoCar = new DtoCar();
            Optional<Car> optional = carRepository.findById(id);
            Car dbCar = optional.get();
            Brand brand = dbCar.getBrand();
            DtoBrandIU dtoBrandIU = new DtoBrandIU();
            BeanUtils.copyProperties(brand, dtoBrandIU);
            BeanUtils.copyProperties(dbCar, dtoCar);
            dtoCar.setBrand(dtoBrandIU);
            return dtoCar;
        } else {
            throw new CarNotFoundException("Not Found car in the database");
        }
    }

    public DtoCar addCar(DtoCarIU newCar, Long id) {
        if (brandRepository.existsById(id)) {
            Car car = new Car();
            Optional<Brand> optional = brandRepository.findById(id);
            Brand brand = optional.get();
            BeanUtils.copyProperties(newCar, car);
            car.setBrand(brand);
            Car savedCar = carRepository.save(car);
            brand.getCarList().add(savedCar);
            DtoCar dtoCar = new DtoCar();
            DtoBrandIU dtoBrandIU = new DtoBrandIU();
            BeanUtils.copyProperties(savedCar, dtoCar);
            BeanUtils.copyProperties(brand, dtoBrandIU);
            dtoCar.setBrand(dtoBrandIU);
            return dtoCar;
        } else {
            throw new BrandNotFoundException("Not brand found in the database ");
        }
    }
}
