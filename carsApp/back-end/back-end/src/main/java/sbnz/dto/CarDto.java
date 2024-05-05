package sbnz.dto;

import sbnz.domain.Car;

import javax.persistence.Column;

public class CarDto {
    private Integer id;

    private String brand;

    private String model;

    private Double price;

    private String type;

    private String engineType;

    public CarDto(Integer id, String brand, String model, Double price, String type, String engineType) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.type = type;
        this.engineType = engineType;
    }

    public CarDto(Car c) {
        this.id = c.getId();
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.price = c.getPrice();
        this.type = c.getType();
        this.engineType = c.getEngineType();
    }

    public Integer getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getEngineType() {
        return engineType;
    }
}
