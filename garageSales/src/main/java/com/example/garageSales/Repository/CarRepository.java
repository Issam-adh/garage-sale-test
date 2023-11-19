package com.example.garageSales.Repository;

import com.example.garageSales.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByFuelAndPriceLessThanEqual(Car.Fuel fuel, float maxPrice);
}
