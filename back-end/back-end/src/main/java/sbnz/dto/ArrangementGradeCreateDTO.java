package sbnz.dto;

import sbnz.domain.ArrangementGrade;

public class ArrangementGradeCreateDTO {
    private Integer userId;
    private Integer arrangementId;
    private Integer grade;

    public ArrangementGradeCreateDTO() {
    }

    public ArrangementGradeCreateDTO(Integer userId, Integer arrangementId, Integer grade) {
        this.userId = userId;
        this.arrangementId = arrangementId;
        this.grade = grade;
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
}
