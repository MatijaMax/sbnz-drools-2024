package sbnz.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "arrangement_reservation", schema = "isa")
public class ArrangementReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrangement_id", nullable = false)
    private Arrangement arrangement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "number_of_people", nullable = false)
    private Integer numberOfPeople;

    @OneToMany(mappedBy = "arrangementReservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripReservation> tripReservations;

    public ArrangementReservation() {
        super();
    }

    public ArrangementReservation(Arrangement arrangement, User user, Integer numberOfPeople) {
        this.arrangement = arrangement;
        this.user = user;
        this.numberOfPeople = numberOfPeople;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Arrangement getArrangement() {
        return arrangement;
    }

    public void setArrangement(Arrangement arrangement) {
        this.arrangement = arrangement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public List<TripReservation> getTripReservations() {
        return tripReservations;
    }

    public void setTripReservations(List<TripReservation> tripReservations) {
        this.tripReservations = tripReservations;
    }
}
