package sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.domain.BuyRequest;

import java.util.List;

public interface BuyRequestRepository extends JpaRepository<BuyRequest, Integer> {
    List<BuyRequest> findByUserId(Integer userId);
}
