package sbnz.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="BUYREQUEST")
public class BuyRequest {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
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
}
