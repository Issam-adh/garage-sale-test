package com.example.garageSales.Repository;

import com.example.garageSales.Model.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void testFindByFuelAndPriceLessThanEqual() {

        Car hybridCar1 = new Car("Toyota", "Prius", null, 25000f, "image1.jpg", "30000",
                Car.Fuel.HYBRID, Car.Transmission.AUTOMATIC);
        Car hybridCar2 = new Car("Honda", "Civic", null, 20000f, "image2.jpg", "40000",
                Car.Fuel.HYBRID, Car.Transmission.AUTOMATIC);

        carRepository.save(hybridCar1);
        carRepository.save(hybridCar2);

        List<Car> foundCars = carRepository.findByFuelAndPriceLessThanEqual(Car.Fuel.HYBRID, 25000);

        assertEquals(2, foundCars.size());
    }
}

