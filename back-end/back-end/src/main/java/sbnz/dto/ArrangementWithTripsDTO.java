package sbnz.dto;

import sbnz.domain.Arrangement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrangementWithTripsDTO {
    private Integer id;
    private String name;
    private Integer price;
    private Set<TripDTO> trips;

    public ArrangementWithTripsDTO() {

    }

    public ArrangementWithTripsDTO(Arrangement arrangement) {
        this(arrangement.getId(), arrangement.getName(), arrangement.getPrice());
    }

    public ArrangementWithTripsDTO(Integer id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.trips = new HashSet<>();
    }

    public ArrangementWithTripsDTO(Integer id, String name, Integer price, Set<TripDTO> tripDTOS) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.trips = tripDTOS;
    }

    public ArrangementWithTripsDTO(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public Integer getPrice() {
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<TripDTO> getTrips() {
        return trips;
    }

    public void setTrips(Set<TripDTO> trips) {
        this.trips = trips;
    }
}
