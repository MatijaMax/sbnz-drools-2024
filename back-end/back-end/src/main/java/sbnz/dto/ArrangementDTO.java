package sbnz.dto;

import sbnz.domain.Arrangement;
import sbnz.domain.Student;

public class ArrangementDTO {
    private Integer id;
    private String name;

    private Integer price;

    public ArrangementDTO() {

    }

    public ArrangementDTO(Arrangement arrangement) {
        this(arrangement.getId(), arrangement.getName(), arrangement.getPrice());
    }

    public ArrangementDTO(Integer id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ArrangementDTO(String name, Integer price) {
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


}
