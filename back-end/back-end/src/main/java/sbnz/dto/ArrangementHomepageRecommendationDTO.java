package sbnz.dto;

import sbnz.domain.Arrangement;

import java.util.Set;

public class ArrangementHomepageRecommendationDTO {
    ArrangementDTO arrangement;
    Set<String> tags;

    Integer filterGrade;

    public ArrangementHomepageRecommendationDTO() {}

    public ArrangementHomepageRecommendationDTO(Arrangement arrangement, Set<String> tags) {
        this.arrangement = new ArrangementDTO(arrangement);
        this.tags = tags;
        this.filterGrade = 0;
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

    public void setFilterGrade(Integer filterGrade) {
        this.filterGrade = filterGrade;
    }
    public void countGrade(){
        this.filterGrade= this.tags.size();
        if(this.tags.contains("new")){
            this.filterGrade = this.filterGrade - 1;
        }
    }

}
