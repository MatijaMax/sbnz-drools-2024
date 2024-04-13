package sbnz.dto;

import sbnz.domain.TripReservation;

public class TripReservationResponseDTO {
    private Integer id;
    private Integer tripId;
    private Integer numberOfGuests;

    public TripReservationResponseDTO() {
    }

    public TripReservationResponseDTO(Integer id, Integer tripId, Integer numberOfGuests) {
        this.id = id;
        this.tripId = tripId;
        this.numberOfGuests = numberOfGuests;
    }

    public TripReservationResponseDTO(TripReservation tripReservation){
        this.id = tripReservation.getId();
        this.tripId = tripReservation.getTrip().getId();
        this.numberOfGuests = tripReservation.getNumberOfGuests();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
}
