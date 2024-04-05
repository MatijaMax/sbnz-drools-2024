package sbnz.dto;

import sbnz.domain.Arrangement;
import sbnz.domain.Student;

public class ArrangementDTO {
    private Integer id;
    private String name;

    public ArrangementDTO() {

    }

    public ArrangementDTO(Arrangement arrangement) {
        this(arrangement.getId(), arrangement.getName());
    }

    public ArrangementDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
