package sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.domain.*;
import sbnz.dto.ArrangementHomepageRecommendationDTO;
import sbnz.repository.ArrangementGradeRepository;
import sbnz.repository.ArrangementRepository;
import sbnz.repository.UserPreferencesRepository;

import java.sql.Array;
import java.util.*;


public class ArrangementRecommendationService {
    List<ArrangementHomepageRecommendationDTO> arrangements;
    List<Arrangement> realArrangements;
    List<ArrangementGrade> grades;
    List<ArrangementReservation> reservations;

    public ArrangementRecommendationService(List<Arrangement> realArrangements, List<ArrangementGrade> grades, List<ArrangementReservation> reservations) {
        arrangements = new ArrayList<>();
        this.realArrangements = realArrangements;
        this.grades = grades;
        this.reservations = reservations;
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

    public void checkArrPref(ArrangementHomepageRecommendationDTO arrangement) {
            if(destinationsRec().contains(arrangement.getArrangement().getLocation())){
                System.out.println("AAAAAA");
                arrangement.addTag("preferenca-arr");
            }
    }

    private Set<String> destinationsRec(){
        Set<String> uniqueDestinations = new HashSet<>();
        Set<String> resultDestinations = new HashSet<>();
        for(var res : reservations){
            if(uniqueDestinations.contains(res.getArrangement().getLocation())){
                resultDestinations.add(res.getArrangement().getLocation());
            }
            uniqueDestinations.add(res.getArrangement().getLocation());
        }
        return resultDestinations;
    }

    public void checkSimilar(ArrangementHomepageRecommendationDTO arrangement) {
        for(var arr : arrangements){
            if(arr.getTags().contains("graded_like")){
                if(areSimmilar(arr, arrangement)){
                    arrangement.addTag("slican");
                }
            }
        }
    }

    private Boolean areSimmilar(ArrangementHomepageRecommendationDTO ar1, ArrangementHomepageRecommendationDTO ar2){
        ArrayList<ArrangementGrade> grades1 = new ArrayList<>();
        ArrayList<ArrangementGrade> grades2 = new ArrayList<>();
        for(var g : grades){
            if(g.getArrangement().getId() == ar1.getArrangement().getId()){
                grades1.add(g);
            }
        }
        for(var g : grades){
            if(g.getArrangement().getId() == ar2.getArrangement().getId()){
                grades2.add(g);
            }
        }
        double counterSimilar = 0;
        double counterTotal = 0;
        for(var g1 : grades1){
            for(var g2 : grades2){
                if(g1.getUser().getId() == g2.getUser().getId()){
                    if((g1.getGrade() == g2.getGrade()) ||
                            (g1.getGrade() == g2.getGrade() + 1) ||
                            (g1.getGrade() == g2.getGrade() - 1)){
                        counterSimilar++;
                    }
                    counterTotal++;
                }
            }
        }
        if(counterTotal == 0){
            return false;
        }
        return counterSimilar/counterTotal > 0.7;
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
        var b = (int) arrangements.stream()
                .filter(element -> element.getFilterGrade() == grade)
                .count();
        System.out.println("RRRRRRRRRRRRRR"+b);
        return b;
    }

    public void countGrades() {
        for (ArrangementHomepageRecommendationDTO arr:arrangements) {
            arr.countGrade();
            System.out.println("NNNNNNNNN"+arr.getFilterGrade());
        }
    }

    public void countGradesA(ArrangementHomepageRecommendationDTO a) {
        for (ArrangementHomepageRecommendationDTO arr:arrangements) {
            if(arr.getArrangement().getId() == a.getArrangement().getId()){
                arr.countGrade();
            }
        }
    }

    public void removeRandomArrangement() {
        arrangements.remove(0);
    }
}
