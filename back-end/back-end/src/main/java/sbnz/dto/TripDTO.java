package sbnz.dto;

import sbnz.domain.Trip;

public class TripDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer price;

    public TripDTO(Trip e) {
        this.id = e.getId();
        this.name = e.getName();
        this.description = e.getDescription();
        this.price = e.getPrice();
    }

    public TripDTO(Integer id, String name, String description, Integer price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
