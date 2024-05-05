package sbnz.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="cars", schema = "sbnz")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "engineType", nullable = false)
    private String engineType;

    public Car(Integer id, String brand, String model, Double price, String type, String engineType) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.type = type;
        this.engineType = engineType;
    }

    public Car(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(price, car.price) && Objects.equals(type, car.type) && Objects.equals(engineType, car.engineType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, price, type, engineType);
    }
}
