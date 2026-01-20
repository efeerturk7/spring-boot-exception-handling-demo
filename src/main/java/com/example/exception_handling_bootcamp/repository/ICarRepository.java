package com.example.exception_handling_bootcamp.repository;

import com.example.exception_handling_bootcamp.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarRepository extends JpaRepository<Car,Long> {
}
