package sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sbnz.domain.ArrangementGrade;

@Repository
public interface ArrangementGradeRepository extends JpaRepository<ArrangementGrade, Integer> {
    // You can add custom query methods here if needed
}
