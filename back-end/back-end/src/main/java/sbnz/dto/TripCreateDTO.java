package sbnz.dto;

import sbnz.domain.Arrangement;

import javax.persistence.*;

public class TripCreateDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer price;
    private Integer arrangementId;

    public TripCreateDTO(Integer id, String name, String description, Integer price, Integer arrangementId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.arrangementId = arrangementId;
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

    public Integer getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(Integer arrangementId) {
        this.arrangementId = arrangementId;
    }
}
