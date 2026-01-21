package com.example.exception_handling_bootcamp;

import com.example.exception_handling_bootcamp.dto.DtoBrand;
import com.example.exception_handling_bootcamp.dto.DtoBrandIU;
import com.example.exception_handling_bootcamp.dto.DtoCar;
import com.example.exception_handling_bootcamp.dto.DtoCarIU;
import com.example.exception_handling_bootcamp.service.BrandService;
import com.example.exception_handling_bootcamp.service.CarService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {ExceptionHandlingBootcampApplication.class})
@Transactional
public class ExceptionHandlingBootcampApplicationTests {
	@Autowired
	private CarService carService;
	@Autowired
	private BrandService brandService;
	private DtoCarIU dtoCarIU=new DtoCarIU();
	private DtoBrandIU dtoBrandIU=new DtoBrandIU();
	@Test
	public void testGetByCarId(){
		dtoCarIU.setModel("A6");
		dtoCarIU.setPrice(668693.5);
		DtoCar dtoCar=carService.addCar(dtoCarIU,1L);
		assertNotNull(dtoCar);
		assertEquals("A6",dtoCar.getModel());
	}
	@Test
	public void testfindBrandById(){
		dtoBrandIU.setName("BMW");
		DtoBrand dtoBrand=brandService.findBrandById(2L);
		assertNotNull(dtoBrand);
		assertEquals("BMW",dtoBrandIU.getName());
	}
	@Test
	public void testFindBrandById_ShouldReturnCarsInside() {
		DtoBrand result = brandService.findBrandById(1L);
		List<DtoCar> cars = result.getDtoCarList();
		assertFalse(cars.isEmpty());
		assertNotNull(cars.get(0).getId());
		assertEquals("M5CS", cars.get(0).getModel());
	}


}
