package sbnz.dto;

import java.util.List;

public class ArrangementReservationDTO {
    private Integer id;
    private Integer arrangementId;
    private Integer userId;
    private Integer numberOfPeople;
    private List<TripReservationDTO> tripReservations;

    public ArrangementReservationDTO() {
    }

    public ArrangementReservationDTO(Integer id, Integer arrangementId, Integer userId, Integer numberOfPeople, List<TripReservationDTO> tripReservations) {
        this.id = id;
        this.arrangementId = arrangementId;
        this.userId = userId;
        this.numberOfPeople = numberOfPeople;
        this.tripReservations = tripReservations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<TripReservationDTO> getTripReservations() {
        return tripReservations;
    }

    public void setTripReservations(List<TripReservationDTO> tripReservations) {
        this.tripReservations = tripReservations;
    }
}
