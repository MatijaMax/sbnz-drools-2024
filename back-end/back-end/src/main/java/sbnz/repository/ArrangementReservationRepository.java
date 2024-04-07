package sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sbnz.domain.ArrangementReservation;

@Repository
public interface ArrangementReservationRepository extends JpaRepository<ArrangementReservation, Integer> {
}
