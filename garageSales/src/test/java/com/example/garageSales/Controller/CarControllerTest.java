package com.example.garageSales.Controller;

import com.example.garageSales.Repository.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.garageSales.Model.Car;

import org.springframework.beans.factory.annotation.Autowired;


@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarRepository carRepository;

    @InjectMocks
    private CarController carController;

    @Test
    public void testCreateCar() throws Exception {
        Car car = new Car("Toyota", "Camry", new Date(), 20000f, "image.jpg", "50000", Car.Fuel.HYBRID, Car.Transmission.AUTOMATIC);

        when(carRepository.save(any(Car.class))).thenReturn(car);

        mockMvc.perform(post("/api/v1/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(car)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.make").value("Toyota"))
                .andExpect(jsonPath("$.model").value("Camry"))
                .andExpect(jsonPath("$.price").value(20000.0));
    }

    @Test
    public void testGetCarsByFuelAndMaxPrice() throws Exception {
        Car car = new Car("Toyota", "Camry", new Date(), 20000f, "image.jpg", "50000", Car.Fuel.HYBRID, Car.Transmission.AUTOMATIC);

        when(carRepository.findByFuelAndPriceLessThanEqual(any(Car.Fuel.class), any(Float.class)))
                .thenReturn(Collections.singletonList(car));

        mockMvc.perform(get("/api/v1/car/carsByFuelAndMaxPrice/HYBRID/25000")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].make").value("Toyota"))
                .andExpect(jsonPath("$[0].model").value("Camry"))
                .andExpect(jsonPath("$[0].price").value(20000.0));
    }

    @Test
    public void testFindAllCarMakes() throws Exception {
        Car car1 = new Car("Toyota", "Camry", new Date(), 20000f, "image.jpg", "50000", Car.Fuel.HYBRID, Car.Transmission.AUTOMATIC);
        Car car2 = new Car("Honda", "Civic", new Date(), 18000f, "image.jpg", "45000", Car.Fuel.ELECTRIC, Car.Transmission.MANUAL);

        when(carRepository.findAll()).thenReturn(Arrays.asList(car1, car2));

        mockMvc.perform(get("/api/v1/car/makes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0]").value("Toyota"))
                .andExpect(jsonPath("$.data[1]").value("Honda"));
    }

    @Test
    public void testUpdateCarPicture() throws Exception {
        Long carId = 1L;
        Car car = new Car("Toyota", "Camry", new Date(), 20000f, "image.jpg", "50000", Car.Fuel.HYBRID, Car.Transmission.AUTOMATIC);

        when(carRepository.existsById(carId)).thenReturn(true);
        when(carRepository.findById(carId)).thenReturn(java.util.Optional.of(car));
        when(carRepository.save(any(Car.class))).thenReturn(car);

        mockMvc.perform(put("/api/v1/car/updatePicture/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"newPicture\":\"newImage.jpg\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Car picture updated successfully"));
    }

}
