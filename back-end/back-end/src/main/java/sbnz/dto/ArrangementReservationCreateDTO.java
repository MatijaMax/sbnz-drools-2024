package sbnz.dto;

import java.util.List;

public class ArrangementReservationCreateDTO {
    private Integer arrangementId;
    private Integer userId;
    private Integer numberOfPeople;
    private List<TripReservationCreateDTO> tripReservations;

    public ArrangementReservationCreateDTO() {
    }

    public ArrangementReservationCreateDTO(Integer arrangementId, Integer userId, Integer numberOfPeople, List<TripReservationCreateDTO> tripReservations) {
        this.arrangementId = arrangementId;
        this.userId = userId;
        this.numberOfPeople = numberOfPeople;
        this.tripReservations = tripReservations;
    }

    public Integer getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(Integer arrangementId) {
        this.arrangementId = arrangementId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public List<TripReservationCreateDTO> getTripReservations() {
        return tripReservations;
    }

    public void setTripReservations(List<TripReservationCreateDTO> tripReservations) {
        this.tripReservations = tripReservations;
    }
}
