package sbnz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sbnz.domain.Car;
import sbnz.dto.CarDto;
import sbnz.service.CarService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5000", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/cars")
public class CarController {
    @Autowired
    private CarService cService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<CarDto>> getAll() {

        List<Car> cars = cService.findAll();

        // convert students to DTOs
        List<CarDto> carDTOs = new ArrayList<>();
        for (Car c : cars) {
            carDTOs.add(new CarDto(c));
        }

        return new ResponseEntity<>(carDTOs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<CarDto> save(@RequestBody CarDto dto) {

        Car car = new Car();
        car.setBrand(dto.getBrand());
        car.setPrice(dto.getPrice());
        car.setType(dto.getType());
        car.setModel(dto.getModel());
        car.setEngineType(dto.getEngineType());

        car = cService.save(car);
        return new ResponseEntity<>(new CarDto(car), HttpStatus.CREATED);
    }
}
