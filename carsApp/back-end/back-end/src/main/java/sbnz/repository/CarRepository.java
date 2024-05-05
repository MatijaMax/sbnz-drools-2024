package sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.domain.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
