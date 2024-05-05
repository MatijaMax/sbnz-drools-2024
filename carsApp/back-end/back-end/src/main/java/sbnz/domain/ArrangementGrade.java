package sbnz.domain;

import javax.persistence.*;
import java.util.Objects;

public class ArrangementGrade {
    private Integer id;

    private User user;

    private Arrangement arrangement;

    private Integer grade;

    public ArrangementGrade() {
        super();
    }

    public ArrangementGrade(User user, Arrangement arrangement, Integer grade) {
        this.user = user;
        this.arrangement = arrangement;
        this.grade = grade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Arrangement getArrangement() {
        return arrangement;
    }

    public void setArrangement(Arrangement arrangement) {
        this.arrangement = arrangement;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrangementGrade that = (ArrangementGrade) o;
        return Objects.equals(user, that.user) && Objects.equals(arrangement, that.arrangement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, arrangement);
    }

}
