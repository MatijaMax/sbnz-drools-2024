package sbnz.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="BUYREQUEST")
public class BuyRequest {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column(name="userAge",  nullable = false)
    Integer userAge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    Car car;

    //Broj rata kredita
    @Column(name="numberOfCreditPayments", nullable = false)
    Integer numberOfCreditPayments;

    public enum USEREMPLOYMENTTYPE { UNEMPLOYED, EMPLOYED_UNSPECIFIC_TIME, EMPLOYED_SPECIFIC_TIME }
    @Column(name="useremploymenttype", nullable = false)
    USEREMPLOYMENTTYPE useremploymenttype;

    //Ako je zaposlen, datum početka važenja ugovora o radu
    @Column(name="employmentStart")
    LocalDate employmentStart;

    //Ako je zaposlen na određeno vreme, datum isteka ugovora o radu
    @Column(name="employmentEnd")
    LocalDate employmentEnd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Integer getNumberOfCreditPayments() {
        return numberOfCreditPayments;
    }

    public void setNumberOfCreditPayments(Integer numberOfCreditPayments) {
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
}
