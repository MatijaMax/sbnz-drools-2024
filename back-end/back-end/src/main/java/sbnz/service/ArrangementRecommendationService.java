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


public class ArrangementRecommendationService {
    List<ArrangementHomepageRecommendationDTO> arrangements;
    List<Arrangement> realArrangements;

    public ArrangementRecommendationService(List<Arrangement> realArrangements) {
        arrangements = new ArrayList<>();
        this.realArrangements = realArrangements;
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

    private Arrangement findArrangementById( int id) {
        for (Arrangement arrangement : realArrangements) {
            if (arrangement.getId() == id) {
                return arrangement;
            }
        }
        return null; // Return null if no arrangement with the specified ID is found
    }

    public void countPopularArrangement(ArrangementHomepageRecommendationDTO arrangement, UserPreferences preference) {
        String location = arrangement.getArrangementDTO().getLocation();
        ArrayList<String> listOne = new ArrayList<String>();
        ArrayList<String> listTwo = new ArrayList<String>();
        Arrangement karr = findArrangementById(arrangement.getArrangementDTO().getId());
        if(karr != null){
            for(Trip trip: karr.getTrips()){
                listOne.add(trip.getType().toString());
            }
        }

        listTwo.addAll( List.of(preference.getTrips().split("\\|")) );
        int counter = 0;
        int blocker = countMatchingStrings(listOne, listTwo);

        if (blocker<2){
            counter = 0;
        }
        else{
            for (Arrangement arr: realArrangements) {


                if(arr.getLocation().equals(location)){
                    counter += arr.getArrangementGrades().size();
                }

            }
        }


        System.out.println("Popular COUNTER "+location+" "+ counter);
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
