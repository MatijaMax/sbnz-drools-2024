package sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sbnz.domain.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer> {
}
