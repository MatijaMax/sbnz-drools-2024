package sbnz.dto;

import sbnz.domain.Arrangement;

public class ArrangementDTO {
    private Integer id;
    private String name;
    private Integer price;
    private String location;

    public ArrangementDTO() {

    }

    public ArrangementDTO(Arrangement arrangement) {
        this(arrangement.getId(), arrangement.getName(), arrangement.getPrice(),arrangement.getLocation());
    }

    public ArrangementDTO(Integer id, String name, Integer price,String location) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
