package sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sbnz.domain.ArrangementReservation;

import java.util.List;

@Repository
public interface ArrangementReservationRepository extends JpaRepository<ArrangementReservation, Integer> {
    List<ArrangementReservation> findByUserId(Integer userId);
}
