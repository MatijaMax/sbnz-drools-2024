package sbnz.dto;

public class TripReservationDTO {
    private Integer id;
    private Integer arrangementReservationId;
    private Integer tripId;
    private Integer numberOfGuests;

    public TripReservationDTO() {
    }

    public TripReservationDTO(Integer id, Integer arrangementReservationId, Integer tripId, Integer numberOfGuests) {
        this.id = id;
        this.arrangementReservationId = arrangementReservationId;
        this.tripId = tripId;
        this.numberOfGuests = numberOfGuests;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArrangementReservationId() {
        return arrangementReservationId;
    }

    public void setArrangementReservationId(Integer arrangementReservationId) {
        this.arrangementReservationId = arrangementReservationId;
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
