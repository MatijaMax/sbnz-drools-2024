package sbnz.dto;

import sbnz.domain.Arrangement;

import java.util.Set;

public class ArrangementHomepageRecommendationDTO {
    ArrangementDTO arrangement;
    Set<String> tags;

    public ArrangementHomepageRecommendationDTO() {}

    public ArrangementHomepageRecommendationDTO(Arrangement arrangement, Set<String> tags) {
        this.arrangement = new ArrangementDTO(arrangement);
        this.tags = tags;
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
                '}';
    }
}
