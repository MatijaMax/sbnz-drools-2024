package sbnz.dto;

import sbnz.domain.ArrangementGrade;

public class ArrangementGradeDTO {
    private Integer id;
    private Integer userId;
    private Integer arrangementId;
    private Integer grade;

    public ArrangementGradeDTO() {
    }

    public ArrangementGradeDTO(Integer id, Integer userId, Integer arrangementId, Integer grade) {
        this.id = id;
        this.userId = userId;
        this.arrangementId = arrangementId;
        this.grade = grade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(Integer arrangementId) {
        this.arrangementId = arrangementId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }


    // Static method to create DTO from entity
    public static ArrangementGradeDTO fromEntity(ArrangementGrade arrangementGrade) {
        return new ArrangementGradeDTO(
                arrangementGrade.getId(),
                arrangementGrade.getUser().getId(),
                arrangementGrade.getArrangement().getId(),
                arrangementGrade.getGrade()
        );
    }
}
