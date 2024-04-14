package sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sbnz.domain.ArrangementReservation;
import sbnz.domain.TripReservation;

import java.util.List;

@Repository
public interface TripReservationRepository extends JpaRepository<TripReservation, Integer> {
    List<TripReservation> findByArrangementReservation(ArrangementReservation arrangementReservation);
}
