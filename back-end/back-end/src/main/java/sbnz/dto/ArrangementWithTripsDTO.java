package sbnz.dto;

import sbnz.domain.Arrangement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrangementWithTripsDTO {
    private Integer id;
    private String name;
    private Integer price;
    private String location;
    private Double averageGrade;
    private Set<TripDTO> trips;

    public ArrangementWithTripsDTO() {

    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public ArrangementWithTripsDTO(Arrangement arrangement) {
        this(arrangement.getId(), arrangement.getName(), arrangement.getPrice(),arrangement.getLocation());
    }

    public ArrangementWithTripsDTO(Integer id, String name, Integer price,String location) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.location = location;
        this.trips = new HashSet<>();
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
