package sbnz.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="arrangement_grades", schema = "isa")
public class ArrangementGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrangement_id", nullable = false)
    private Arrangement arrangement;

    @Column(name = "grade", nullable = false)
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
