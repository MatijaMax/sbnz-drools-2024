package sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.domain.Car;
import sbnz.domain.Student;
import sbnz.repository.CarRepository;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

}
