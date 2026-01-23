package com.example.exception_handling_bootcamp;

import static org.mockito.Mockito.*;

import com.example.exception_handling_bootcamp.dto.DtoBrandIU;
import com.example.exception_handling_bootcamp.dto.DtoCar;
import com.example.exception_handling_bootcamp.dto.DtoCarIU;
import com.example.exception_handling_bootcamp.entity.Brand;
import com.example.exception_handling_bootcamp.entity.Car;
import com.example.exception_handling_bootcamp.exception.BrandNotFoundException;
import com.example.exception_handling_bootcamp.exception.CarNotFoundException;
import com.example.exception_handling_bootcamp.repository.IBrandRepository;
import com.example.exception_handling_bootcamp.repository.ICarRepository;
import com.example.exception_handling_bootcamp.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ServiceUnitTests {
    @Mock
    private ICarRepository carRepository;
    @Mock
    private IBrandRepository brandRepository;
    @InjectMocks
    private CarService carService;

    @Test
    public void testGetCarFindById() {
        Long carId = 1L;
        Brand mockBrand = new Brand();
        mockBrand.setName("BMW");
        mockBrand.setId(carId);

        Car mockCar = new Car();
        mockCar.setBrand(mockBrand);
        mockCar.setPrice(668693.5);
        mockCar.setModel("M5CS");

        when(carRepository.existsById(carId)).thenReturn(true);
        when(carRepository.findById(carId)).thenReturn(Optional.of(mockCar));

        DtoCar dtoCar = carService.getCarById(carId);
        assertNotNull(dtoCar);
        assertEquals("M5CS", dtoCar.getModel());
        assertEquals("BMW", dtoCar.getBrand());
    }

    @Test
    public void getCarFindById_ShouldThrowException_WhenCarNotFound() {
        Long carId = 99L;
        when(carRepository.existsById(carId)).thenReturn(false);
        assertThrows(CarNotFoundException.class, () -> {
            carService.getCarById(carId);
        });
        verify(carRepository, never()).findById(carId);

    }

    @Test
    public void testAddCar() {
        Long brandId = 1L;
        DtoCarIU dtoCarIU = new DtoCarIU();
        dtoCarIU.setModel("A5");
        dtoCarIU.setPrice(324745.6);
        Brand mockBrand = new Brand();
        mockBrand.setId(brandId);
        mockBrand.setName("AUDI");
        Car savedCar = new Car();
        savedCar.setId(10L);
        savedCar.setModel("A5");
        savedCar.setPrice(324745.6);
        savedCar.setBrand(mockBrand);

        when(brandRepository.existsById(brandId)).thenReturn(true);git push origin main

        when(brandRepository.findById(brandId)).thenReturn(Optional.of(mockBrand));

        when(carRepository.save(any(Car.class))).thenReturn(savedCar);

        DtoCar dtoCar = carService.addCar(dtoCarIU, brandId);
        assertNotNull(dtoCar);
        assertEquals(10L,dtoCar.getId());
        assertEquals("AUDI", dtoCar.getBrand().getName());
        assertEquals("A5", dtoCar.getModel());
        assertEquals(324745.6, dtoCar.getPrice());

        verify(carRepository, times(1)).save(any(Car.class));

    }
    public void testAddCar_WhenBrandDoesNotExist_ShouldThrowException(){
        Long brandId=99L;
        DtoCarIU dtoCarIU=new DtoCarIU();
        when(carRepository.existsById(brandId)).thenReturn(false);
        assertThrows(BrandNotFoundException.class, () -> {
            carService.addCar(dtoCarIU, brandId);
        });
        verify(carRepository, never()).save(any(Car.class));
    }
}
