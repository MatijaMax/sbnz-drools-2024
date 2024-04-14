package sbnz.dto;

import sbnz.domain.ArrangementGrade;

public class ArrangementGradeResponseDTO {
    private Integer id;
    private Integer userId;
    private Integer arrangementId;
    private Integer grade;

    public ArrangementGradeResponseDTO() {
    }

    public ArrangementGradeResponseDTO(Integer id, Integer userId, Integer arrangementId, Integer grade) {
        this.id = id;
        this.userId = userId;
        this.arrangementId = arrangementId;
        this.grade = grade;
    }

    public ArrangementGradeResponseDTO(ArrangementGrade arrangementGrade) {
        this.id = arrangementGrade.getId();
        this.arrangementId = arrangementGrade.getArrangement().getId();
        this.grade = arrangementGrade.getGrade();
        this.userId = arrangementGrade.getUser().getId();
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
}
