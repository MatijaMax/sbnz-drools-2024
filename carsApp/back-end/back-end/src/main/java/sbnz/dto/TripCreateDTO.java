package sbnz.dto;

import sbnz.domain.Arrangement;
import sbnz.domain.Trip;

import javax.persistence.*;

public class TripCreateDTO {
    private String name;
    private String description;
    private Integer price;
    private Integer arrangementId;
    private Trip.TRIPTYPE type;

    public TripCreateDTO(String name, String description, Integer price, Integer arrangementId, Trip.TRIPTYPE type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.arrangementId = arrangementId;
        this.type = type;
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

    public Integer getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(Integer arrangementId) {
        this.arrangementId = arrangementId;
    }

    public Trip.TRIPTYPE getType() {
        return type;
    }

    public void setType(Trip.TRIPTYPE type) {
        this.type = type;
    }
}
