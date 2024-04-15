package sbnz.dto;

public class TripReservationCreateDTO {
    private Integer tripId;
    private Integer numberOfGuests;

    public TripReservationCreateDTO() {
    }

    public TripReservationCreateDTO(Integer tripId, Integer numberOfGuests) {
        this.tripId = tripId;
        this.numberOfGuests = numberOfGuests;
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
