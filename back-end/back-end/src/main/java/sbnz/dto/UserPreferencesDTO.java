package sbnz.dto;

import java.util.List;

public class UserPreferencesDTO {
    private Integer id;
    private Integer userId;
    private List<String> destinations;
    private List<String> trips;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<String> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<String> destinations) {
        this.destinations = destinations;
    }

    public List<String> getTrips() {
        return trips;
    }

    public void setTrips(List<String> trips) {
        this.trips = trips;
    }
}
