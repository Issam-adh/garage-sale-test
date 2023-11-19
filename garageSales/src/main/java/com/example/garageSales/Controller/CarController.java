package com.example.garageSales.Controller;


import com.example.garageSales.Model.Car;
import com.example.garageSales.Model.Response;
import com.example.garageSales.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/car")
@CrossOrigin("*")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    /**
     * Create Cars that registered after 2015
     * @param car car
     * @return car
     */
    @PostMapping
    public ResponseEntity<?> createCar(@RequestBody Car car) {
        Date registrationDate = car.getDate();

        if (registrationDate == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Car registration date must be provided");
        }

        int registrationYear = registrationDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().getYear();

        if (registrationYear <= 2015) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Car registration year must be after 2015");
        }

        Car savedCar = carRepository.save(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
    }

    /**
     * Get Cars By Fuel And MaxPrice
     * @param fuel fuel
     * @param maxPrice float
     * @return car
     */
    @GetMapping("/carsByFuelAndMaxPrice/{fuel}/{maxPrice}")
    public ResponseEntity<List<Car>> getCarsByFuelAndMaxPrice(
            @PathVariable("fuel") Car.Fuel fuel,
            @PathVariable("maxPrice") Float maxPrice) {

        List<Car> cars = carRepository.findByFuelAndPriceLessThanEqual(fuel, maxPrice);

        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cars);
    }

    /**
     * Find All Car By Makes
     * @return car
     */
    @GetMapping("/makes")
    public Response<Set<String>> findAllCarMakes() {
        try {
            List<Car> allCars = carRepository.findAll();

            Set<String> carMakes = allCars.stream()
                    .map(Car::getMake)
                    .collect(Collectors.toSet());

            return new Response<>("200", "Get all Car Makes", carMakes);
        } catch (Exception e) {
            return new Response<>("406", e.getMessage(), null);
        }
    }

    /**
     * Update car picture
     * @param carId Long
     * @param requestBody Map<String, String>
     * @return
     */
    @PutMapping("/updatePicture/{carId}")
    public ResponseEntity<?> updateCarPicture(
            @PathVariable Long carId,
            @RequestBody Map<String, String> requestBody) {

        try {
            if (!carRepository.existsById(carId)) {
                return ResponseEntity.notFound().build();
            }

            Car car = carRepository.findById(carId).orElse(null);

            if (car != null && requestBody.containsKey("newPicture")) {
                car.setPicture(requestBody.get("newPicture"));
                carRepository.save(car);
                return ResponseEntity.ok("Car picture updated successfully");
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid request body. Missing 'newPicture' property.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating car picture: " + e.getMessage());
        }
    }


}
