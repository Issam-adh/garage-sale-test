package com.example.garageSales.Model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CarTest {

    @Test
    public void testCreateCar() {
        Car car = new Car("Toyota", "Camry", new Date(), 20000f, "image.jpg", "50000", Car.Fuel.HYBRID, Car.Transmission.AUTOMATIC);

        assertEquals("Toyota", car.getMake());
        assertEquals("Camry", car.getModel());
        assertEquals(20000f, car.getPrice(), 0.01); // delta is used for floating-point comparisons
        assertEquals("image.jpg", car.getPicture());
        assertEquals("50000", car.getMileage());
        assertEquals(Car.Fuel.HYBRID, car.getFuel());
        assertEquals(Car.Transmission.AUTOMATIC, car.getTransmission());
    }

    @Test
    public void testCarId() {
        Car car = new Car();
        car.setId(1L);
        assertEquals(1L, car.getId());
    }

    @Test
    public void testGetAndSetMake() {
        Car car = new Car();
        car.setMake("Honda");
        assertEquals("Honda", car.getMake());
    }

    @Test
    public void testGetAndSetModel() {
        Car car = new Car();
        car.setModel("Accord");
        assertEquals("Accord", car.getModel());
    }

    @Test
    public void testGetAndSetDate() {
        Date date = new Date();
        Car car = new Car();
        car.setDate(date);
        assertEquals(date, car.getDate());
    }

    @Test
    public void testGetAndSetPrice() {
        Car car = new Car();
        car.setPrice(25000f);
        assertEquals(25000f, car.getPrice(), 0.01);
    }

    @Test
    public void testGetAndSetPicture() {
        Car car = new Car();
        car.setPicture("newImage.jpg");
        assertEquals("newImage.jpg", car.getPicture());
    }

    @Test
    public void testGetAndSetMileage() {
        Car car = new Car();
        car.setMileage("75000");
        assertEquals("75000", car.getMileage());
    }

    @Test
    public void testGetAndSetFuel() {
        Car car = new Car();
        car.setFuel(Car.Fuel.DIESEL);
        assertEquals(Car.Fuel.DIESEL, car.getFuel());
    }

    @Test
    public void testGetAndSetTransmission() {
        Car car = new Car();
        car.setTransmission(Car.Transmission.MANUAL);
        assertEquals(Car.Transmission.MANUAL, car.getTransmission());
    }

    }
