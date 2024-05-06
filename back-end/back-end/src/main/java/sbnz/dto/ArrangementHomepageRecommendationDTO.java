package sbnz.dto;

import sbnz.domain.Arrangement;

import java.util.Set;

public class ArrangementHomepageRecommendationDTO {
    ArrangementDTO arrangement;
    Set<String> tags;

    Integer filterGrade;

    Double averageGrade;

    Integer popularGrade;

    public ArrangementHomepageRecommendationDTO() {}

    public ArrangementHomepageRecommendationDTO(Arrangement arrangement, Set<String> tags) {
        this.arrangement = new ArrangementDTO(arrangement);
        this.tags = tags;
        this.filterGrade = 0;
        this.popularGrade = 0;
        this.averageGrade = arrangement.calculateAverage();
    }

    public ArrangementDTO getArrangementDTO() {
        return arrangement;
    }

    public void setArrangementDTO(ArrangementDTO arrangement) {
        this.arrangement = arrangement;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return "ArrangementHomepageRecommendationDTO{" +
                "arrangement=" + arrangement +
                ", tags=" + tags +
                ", filterGrade=" + filterGrade +
                '}';
    }

    public Integer getFilterGrade() {
        return filterGrade;
    }

    public Integer getPopularGrade() { return popularGrade; }

    public void setFilterGrade(Integer filterGrade) {
        this.filterGrade = filterGrade;
    }
    public void countGrade(){
        this.filterGrade= this.tags.size();
        if(this.tags.contains("new")){
            this.filterGrade = this.filterGrade - 1;
        }
    }

    public void setPopularGrade(Integer popularGrade) {
        this.popularGrade = popularGrade;
    }

    public ArrangementDTO getArrangement() {
        return arrangement;
    }

    public void setArrangement(ArrangementDTO arrangement) {
        this.arrangement = arrangement;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }
}
