package sbnz.domain;

import javax.persistence.*;

public class Trip {
    private Integer id;
    private String name;
    private String description;
    private Integer price;
    private TRIPTYPE type;
    public enum TRIPTYPE {HISTORY, ADVENTURE, SOCIAL, EXTREME};


    private Arrangement arrangement;

    public Trip() {
        // TODO Auto-generated constructor stub
        super();
    }

    public Trip(Integer id, String name, String description, Integer price, TRIPTYPE type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public Trip(Integer id, String name, String description, Integer price, TRIPTYPE type, Arrangement arrangement) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.arrangement = arrangement;
    }

    public Trip(String name, String description, Integer price, TRIPTYPE type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
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

    public TRIPTYPE getType() {
        return type;
    }

    public void setType(TRIPTYPE type) {
        this.type = type;
    }

    public Arrangement getArrangement() {
        return arrangement;
    }

    public void setArrangement(Arrangement arrangement) {
        this.arrangement = arrangement;
    }

    public void changePrice(){
        this.price=price-100;
    }
}
