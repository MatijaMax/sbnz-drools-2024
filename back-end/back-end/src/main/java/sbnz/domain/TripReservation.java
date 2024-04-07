package sbnz.domain;

import javax.persistence.*;

@Entity
@Table(name = "trip_reservation", schema = "isa")
public class TripReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrangement_reservation_id", nullable = false)
    private ArrangementReservation arrangementReservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @Column(name = "number_of_guests", nullable = false)
    private Integer numberOfGuests;

    public TripReservation() {
        super();
    }

    public TripReservation(ArrangementReservation arrangementReservation, Trip trip, Integer numberOfGuests) {
        this.arrangementReservation = arrangementReservation;
        this.trip = trip;
        this.numberOfGuests = numberOfGuests;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrangementReservation getArrangementReservation() {
        return arrangementReservation;
    }

    public void setArrangementReservation(ArrangementReservation arrangementReservation) {
        this.arrangementReservation = arrangementReservation;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
}
