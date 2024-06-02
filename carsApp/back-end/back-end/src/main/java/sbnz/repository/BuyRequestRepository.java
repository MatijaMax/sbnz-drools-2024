package sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.domain.BuyRequest;

public interface BuyRequestRepository extends JpaRepository<BuyRequest, Integer> {
}
