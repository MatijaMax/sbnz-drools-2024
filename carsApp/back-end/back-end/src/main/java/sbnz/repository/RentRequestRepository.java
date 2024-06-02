package sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.domain.RentRequest;
import sbnz.domain.Student;

import java.util.List;

public interface RentRequestRepository extends JpaRepository<RentRequest, Integer> {
    public List<RentRequest> findAllByUserId(Integer id);
}
