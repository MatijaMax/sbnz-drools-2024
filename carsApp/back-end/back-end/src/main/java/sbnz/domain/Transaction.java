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
    Long amount;

    public Transaction() { }

    public Transaction(BuyRequest buyRequest, LocalDate datePayed, Long amount) {
        this.buyRequest = buyRequest;
        this.datePayed = datePayed;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BuyRequest getBuyRequest() {
        return buyRequest;
    }

    public void setBuyRequest(BuyRequest buyRequest) {
        this.buyRequest = buyRequest;
    }

    public LocalDate getDatePayed() {
        return datePayed;
    }

    public void setDatePayed(LocalDate datePayed) {
        this.datePayed = datePayed;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
