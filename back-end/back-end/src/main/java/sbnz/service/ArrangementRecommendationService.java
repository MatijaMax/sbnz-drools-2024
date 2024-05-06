package sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.domain.Arrangement;
import sbnz.domain.Trip;
import sbnz.domain.UserPreferences;
import sbnz.dto.ArrangementHomepageRecommendationDTO;
import sbnz.repository.ArrangementGradeRepository;
import sbnz.repository.ArrangementRepository;
import sbnz.repository.UserPreferencesRepository;

import java.sql.Array;
import java.util.*;

@Service
public class ArrangementRecommendationService {
    List<ArrangementHomepageRecommendationDTO> arrangements;

    @Autowired
    private ArrangementRepository arrangementRepository;

    @Autowired
    private UserPreferencesRepository userPreferencesRepository;

    public ArrangementRecommendationService() {
        arrangements = new ArrayList<>();
    }

    public List<ArrangementHomepageRecommendationDTO> getArrangements() {
        return arrangements;
    }

    public List<ArrangementHomepageRecommendationDTO> getArrangementsByGrade() {
        return arrangements;
    }

    public void addArrangement(Arrangement arrangement, String tag) {
        ArrangementHomepageRecommendationDTO recommendationDTO = arrangements.stream()
                                                                    .filter(recomendation -> recomendation.getArrangementDTO().getId() == arrangement.getId())
                                                                    .findFirst().orElse(null);
        if (recommendationDTO == null){
            recommendationDTO = new ArrangementHomepageRecommendationDTO(arrangement, new HashSet<String>());
            recommendationDTO.addTag(tag);
            arrangements.add(recommendationDTO);
            return;
        }
        recommendationDTO.addTag(tag);
    }

    public void removeArrangement(ArrangementHomepageRecommendationDTO arrangement) {
        arrangements.remove(arrangement);

    }

    public int countMatchingStrings(List<String> list1, List<String> list2) {
        return (int) list1.stream()
                .filter(list2::contains)
                .count();
    }

    public void countPopularArrangement(ArrangementHomepageRecommendationDTO arrangement, UserPreferences preference) {
        String location = arrangement.getArrangementDTO().getLocation();
        System.out.println("BILO STA POPULr");
        ArrayList<String> listOne = new ArrayList<String>();
        ArrayList<String> listTwo ;
        for(Trip trip: arrangementRepository.findById(arrangement.getArrangementDTO().getId()).get().getTrips()){
            listOne.add(trip.getType().toString());
        }
        listTwo = (ArrayList<String>) List.of(preference.getTrips().split("\\|"));
        int counter = 0;
        int blocker = countMatchingStrings(listOne, listTwo);
        for (Arrangement arr: arrangementRepository.findAll()) {


            if(arr.getLocation().equals(location)){
                counter += arr.getArrangementGrades().size();
            }
            if (blocker<2){
                counter = 0;
            }
        }
        System.out.println("BILO STA POPULr COUNTER "+location+" "+ counter);
        arrangement.setPopularGrade(counter);
    }

    public int getSize(){
        return arrangements.size();
    }

    public int getLimiterGrade(){
        Collections.sort(arrangements, Comparator.comparingInt(ArrangementHomepageRecommendationDTO::getPopularGrade).reversed());
        if (arrangements.size() >= 5) {
            int fifthHighest = arrangements.get(4).getPopularGrade();
            return fifthHighest;
        } else {
            return -1;
        }
    }

    public double getLowestAverageGrade(){
        Collections.sort(arrangements, Comparator.comparingInt(ArrangementHomepageRecommendationDTO::getPopularGrade));
        if (arrangements.isEmpty()) {
            return -1;
        } else {
            return arrangements.get(0).getAverageGrade();
        }
    }

    public int getSizeByGrade(int grade){
        return (int) arrangements.stream()
                .filter(element -> element.getFilterGrade() == grade)
                .count();
    }

    public void countGrades() {
        for (ArrangementHomepageRecommendationDTO arr:arrangements) {
            arr.countGrade();
        }
    }

    public void removeRandomArrangement() {
        arrangements.remove(0);
    }
}
