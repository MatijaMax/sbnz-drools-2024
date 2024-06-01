package sbnz.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="TRANSACTION")
public class Transaction {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buy_request_id", nullable = false)
    BuyRequest buyRequest;

    @Column(name="datePayed")
    LocalDate datePayed;

    @Column(name="amount")
    Double amount;
}
