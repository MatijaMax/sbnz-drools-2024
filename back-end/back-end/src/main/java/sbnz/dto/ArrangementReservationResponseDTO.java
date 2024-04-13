package sbnz.dto;

import sbnz.domain.ArrangementReservation;
import sbnz.domain.TripReservation;

import java.util.ArrayList;
import java.util.List;

public class ArrangementReservationResponseDTO {private Integer id;
    private Integer arrangementId;
    private Integer userId;
    private Integer numberOfPeople;
    private List<TripReservationResponseDTO> tripReservations;

    public ArrangementReservationResponseDTO() {
    }

    public ArrangementReservationResponseDTO(Integer id, Integer arrangementId, Integer userId, Integer numberOfPeople, List<TripReservationResponseDTO> tripReservations) {
        this.id = id;
        this.arrangementId = arrangementId;
        this.userId = userId;
        this.numberOfPeople = numberOfPeople;
        this.tripReservations = tripReservations;
    }

    public ArrangementReservationResponseDTO(ArrangementReservation arrangementReservation){
        this.id = arrangementReservation.getId();
        this.arrangementId = arrangementReservation.getArrangement().getId();
        this.userId = arrangementReservation.getUser().getId();
        this.numberOfPeople = arrangementReservation.getNumberOfPeople();
        this.tripReservations = new ArrayList<>();
        for(TripReservation t: arrangementReservation.getTripReservations()){
            tripReservations.add(new TripReservationResponseDTO(t));
        }
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

    public List<TripReservationResponseDTO> getTripReservations() {
        return tripReservations;
    }

    public void setTripReservations(List<TripReservationResponseDTO> tripReservations) {
        this.tripReservations = tripReservations;
    }
}
