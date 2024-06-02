package sbnz.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
