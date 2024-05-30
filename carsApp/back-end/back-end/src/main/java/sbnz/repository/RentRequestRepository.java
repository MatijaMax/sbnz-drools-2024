package sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.domain.RentRequest;

public interface RentRequestRepository extends JpaRepository<RentRequest, Integer> {
}
