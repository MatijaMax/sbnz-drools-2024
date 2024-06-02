package sbnz.domain;

import javax.persistence.*;
import java.time.LocalDate;
import org.kie.api.definition.type.Position;

@Entity
@Table(name="BUYREQUEST")
public class BuyRequest {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @Position(0)
    User user;

    @Column(name="userAge",  nullable = false)
    Long userAge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    Car car;

    //Broj rata kredita
    @Column(name="numberOfCreditPayments", nullable = false)
    Long numberOfCreditPayments;

    public enum USEREMPLOYMENTTYPE { UNEMPLOYED, EMPLOYED_UNSPECIFIC_TIME, EMPLOYED_SPECIFIC_TIME }
    @Column(name="useremploymenttype", nullable = false)
    USEREMPLOYMENTTYPE useremploymenttype;

    //Ako je zaposlen, datum početka važenja ugovora o radu
    @Column(name="employmentStart")
    LocalDate employmentStart;

    //Ako je zaposlen na određeno vreme, datum isteka ugovora o radu
    @Column(name="employmentEnd")
    LocalDate employmentEnd;

    @Column(name="leftToPay", nullable = false)
    Long leftToPay;

    @Column(name="dateUntilToPay")
    LocalDate dateUntilToPay;

    public BuyRequest() { }

    public BuyRequest(Long id, User user, Long userAge, Car car, Long numberOfCreditPayments, USEREMPLOYMENTTYPE useremploymenttype, LocalDate employmentStart, LocalDate employmentEnd, Long leftToPay, LocalDate dateUntilToPay) {
        this.id = id;
        this.user = user;
        this.userAge = userAge;
        this.car = car;
        this.numberOfCreditPayments = numberOfCreditPayments;
        this.useremploymenttype = useremploymenttype;
        this.employmentStart = employmentStart;
        this.employmentEnd = employmentEnd;
        this.leftToPay = leftToPay;
        this.dateUntilToPay = dateUntilToPay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserAge() {
        return userAge;
    }

    public void setUserAge(Long userAge) {
        this.userAge = userAge;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Long getNumberOfCreditPayments() {
        return numberOfCreditPayments;
    }

    public void setNumberOfCreditPayments(Long numberOfCreditPayments) {
        this.numberOfCreditPayments = numberOfCreditPayments;
    }

    public USEREMPLOYMENTTYPE getUseremploymenttype() {
        return useremploymenttype;
    }

    public void setUseremploymenttype(USEREMPLOYMENTTYPE useremploymenttype) {
        this.useremploymenttype = useremploymenttype;
    }

    public LocalDate getEmploymentStart() {
        return employmentStart;
    }

    public void setEmploymentStart(LocalDate employmentStart) {
        this.employmentStart = employmentStart;
    }

    public LocalDate getEmploymentEnd() {
        return employmentEnd;
    }

    public void setEmploymentEnd(LocalDate employmentEnd) {
        this.employmentEnd = employmentEnd;
    }

    public Long getLeftToPay() {
        return leftToPay;
    }

    public void setLeftToPay(Long leftToPay) {
        this.leftToPay = leftToPay;
    }

    public LocalDate getDateUntilToPay() {
        return dateUntilToPay;
    }

    public void setDateUntilToPay(LocalDate dateUntilToPay) {
        this.dateUntilToPay = dateUntilToPay;
    }

    public boolean isBuyRequestExpired() {
        return (leftToPay > 0 && LocalDate.now().isAfter(dateUntilToPay));
    }

    public int getMonthlyPay() {
        return (int)(this.leftToPay/this.numberOfCreditPayments);
    }
}
