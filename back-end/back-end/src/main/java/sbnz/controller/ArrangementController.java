package sbnz.controller;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sbnz.domain.*;
import sbnz.dto.*;
import sbnz.model.Customer;
import sbnz.model.Item;
import sbnz.model.Order;
import sbnz.model.OrderLine;
import sbnz.service.*;
import sbnz.util.DebugAgendaEventListener;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/arrangements")
public class ArrangementController {
    @Autowired
    private ArrangementService aService;

    @Autowired
    private ArrangementGradeService arrangementGradeService;

    @Autowired

    private UserService userService;

    @Autowired
    private UserPreferencesService userPreferencesService;
    @GetMapping(value = "/all")
    public ResponseEntity<List<ArrangementDTO>> getAll() {

        List<Arrangement> arrangements = aService.findAll();

        // convert students to DTOs
        List<ArrangementDTO> arrangementDTOs = new ArrayList<>();
        for (Arrangement a : arrangements) {
            arrangementDTOs.add(new ArrangementDTO(a));
        }

        return new ResponseEntity<>(arrangementDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ArrangementDTO> getById(@PathVariable Integer id) {

        Arrangement arrangement = aService.findOne(id);

        // student must exist
        if (arrangement == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        KieSession kSession = kieContainer.newKieSession("ksession-rules");

        kSession.addEventListener(new DebugAgendaEventListener());

        Customer c1 = new Customer();
        c1.setAge(21);
        c1.setCategory(Customer.Category.NA);
        c1.setCustomerId((long) 21);
        c1.setName("Pera");
        c1.setEmail("pera@pera.pera");

        Order o = new Order();
        o.setCustomer(c1);
        o.setDate(new Date());
        List<OrderLine> l1 = new LinkedList<OrderLine>();

        OrderLine ol1 = new OrderLine();
        ol1.setItem(new Item((long)1,"i1", 123.0, 123.0));
        OrderLine ol2 = new OrderLine();
        ol2.setItem(new Item((long)2,"i2", 223.0, 223.0));
        OrderLine ol3 = new OrderLine();
        ol3.setItem(new Item((long)3,"i3", 293.0, 213.0));
        OrderLine ol4 = new OrderLine();
        ol4.setItem(new Item((long)4,"i4", 993.0, 1213.0));
        OrderLine ol5 = new OrderLine();
        ol5.setItem(new Item((long)5,"i5", 793.0, 1013.0));
        OrderLine ol6 = new OrderLine();
        ol6.setItem(new Item((long)2,"i2", 223.0, 223.0));
        OrderLine ol7 = new OrderLine();
        ol7.setItem(new Item((long)3,"i3", 293.0, 213.0));
        OrderLine ol8 = new OrderLine();
        ol8.setItem(new Item((long)4,"i4", 993.0, 1213.0));
        OrderLine ol9 = new OrderLine();
        ol9.setItem(new Item((long)5,"i5", 793.0, 1013.0));
        OrderLine ol10 = new OrderLine();
        ol10.setItem(new Item((long)5,"i5", 793.0, 1013.0));
        OrderLine ol11 = new OrderLine();
        ol11.setItem(new Item((long)5,"i5", 793.0, 1013.0));
        OrderLine ol12 = new OrderLine();
        ol12.setItem(new Item((long)5,"i5", 793.0, 1013.0));


        l1.add(ol1);
        l1.add(ol2);
        l1.add(ol3);
        l1.add(ol4);
        l1.add(ol5);
        l1.add(ol6);
        l1.add(ol7);
        l1.add(ol8);
        l1.add(ol9);
        l1.add(ol10);
        l1.add(ol11);
        l1.add(ol12);

        o.setOrderLines(l1);

        System.out.println("Category: " + c1.getCategory());

        kSession.insert(c1);
        kSession.insert(o);

        int fired = kSession.fireAllRules();
        System.out.println(fired);

        System.out.println("Category: " + c1.getCategory());


        return new ResponseEntity<>(new ArrangementDTO(arrangement), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ArrangementDTO> save(@RequestBody ArrangementDTO arrangementDTO) {

        Arrangement arrangement = new Arrangement();
        arrangement.setName(arrangementDTO.getName());
        arrangement.setPrice(arrangementDTO.getPrice());
        arrangement.setLocation(arrangementDTO.getLocation());

        arrangement = aService.save(arrangement);
        return new ResponseEntity<>(new ArrangementDTO(arrangement), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{arrangementId}/trips")
    public ResponseEntity<List<TripDTO>> getArrangementTrips(@PathVariable Integer arrangementId) {

        Arrangement arrangement = aService.findOneWithTrips(arrangementId);
        if(arrangement == null) {
            List<TripDTO> tDTOs = new ArrayList<>();
            return new ResponseEntity<>(tDTOs, HttpStatus.OK);
        }
        Set<Trip> trips = arrangement.getTrips();
        List<TripDTO> tripDTOS = new ArrayList<>();

        for (Trip e : trips) {
            tripDTOS.add(new TripDTO(e));
        }
        return new ResponseEntity<>(tripDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{arrangementId}/withTrips")
    public ResponseEntity<ArrangementWithTripsDTO> getArrangementWithTrips(@PathVariable Integer arrangementId) {

        Arrangement arrangement = aService.findOneWithTrips(arrangementId);
        if(arrangement == null) {
            ArrangementWithTripsDTO adto = new ArrangementWithTripsDTO();
            return new ResponseEntity<>(adto, HttpStatus.OK);
        }
        Set<Trip> trips = arrangement.getTrips();
        Set<TripDTO> tripDTOS = new HashSet<>();

        for (Trip e : trips) {
            tripDTOS.add(new TripDTO(e));
        }

        ArrangementWithTripsDTO adto = new ArrangementWithTripsDTO(arrangement);
        adto.setTrips(tripDTOS);
        return new ResponseEntity<>(adto, HttpStatus.OK);
    }

    @GetMapping(value = "/allWithTrips")
    public ResponseEntity<List<ArrangementWithTripsDTO>> getAllWithTrips() {

        List<Arrangement> arrangements = aService.findAllWithTrips();
        System.out.println(arrangements);

        List<ArrangementWithTripsDTO> arrangementDTOs = new ArrayList<>();
        for (Arrangement a : arrangements) {
            Set<Trip> trips = a.getTrips();
            Set<TripDTO> tripDTOS = new HashSet<>();

            for (Trip e : trips) {
                tripDTOS.add(new TripDTO(e));
            }
            ArrangementWithTripsDTO adto = new ArrangementWithTripsDTO(a);
            adto.setTrips(tripDTOS);
            adto.setAverageGrade(arrangementGradeService.findAverageForArrangement(a.getId()));
            arrangementDTOs.add(adto);
        }

        return new ResponseEntity<>(arrangementDTOs, HttpStatus.OK);
    }

    public ResponseEntity<List<ArrangementHomepageRecommendationDTO>> getVisitorRecommendation() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        KieSession kSession = kieContainer.newKieSession("arrangementRecommendation");

        kSession.setGlobal("recommendations", new ArrangementRecommendationService());
        for (Arrangement a : aService.findAll()) {
            kSession.insert(a);
        }
        for (ArrangementGrade g : arrangementGradeService.findAll()) {
            kSession.insert(g);
        }


        kSession.getAgenda().getAgendaGroup("new arrangements").setFocus();
        int fired = kSession.fireAllRules();
        System.out.println("Number of rules fired new arrangements nonauth: " + fired);

        kSession.getAgenda().getAgendaGroup("popular arrangements").setFocus();
        fired = kSession.fireAllRules();
        System.out.println("Number of rules fired nonauth: " + fired);

        kSession.getAgenda().getAgendaGroup("average grade").setFocus();
        fired = kSession.fireAllRules();
        System.out.println("Number of rules fired nonauth: " + fired);

        kSession.getAgenda().getAgendaGroup("filter1").setFocus();
        fired = kSession.fireAllRules();
        System.out.println("Number of rules fired nonauth: " + fired);


        ArrangementRecommendationService recommendations = (ArrangementRecommendationService) kSession.getGlobal("recommendations");
        kSession.insert(recommendations);

        kSession.getAgenda().getAgendaGroup("filter2").setFocus();
        fired = kSession.fireAllRules();
        System.out.println("Number of rules fired nonauth: " + fired);

        kSession.getAgenda().getAgendaGroup("filter3").setFocus();
        fired = kSession.fireAllRules();
        System.out.println("Number of rules fired nonauth: " + fired);

        return new ResponseEntity<>(recommendations.getArrangements(), HttpStatus.OK);
    }

    @GetMapping(value = "/homepageRecommendations")
    public ResponseEntity<List<ArrangementHomepageRecommendationDTO>> getHomepageRecommendation() {

        return getVisitorRecommendation();
    }


    @GetMapping(value = "/{userId}/loggedUserRecommendations")
    public ResponseEntity<List<ArrangementHomepageRecommendationDTO>> getLoggedUserRecommendation(@PathVariable Integer userId) {
        boolean isUserNew = arrangementGradeService.isUserNew(userId);
        System.out.println("User new: " + isUserNew);
        UserPreferences preference = userPreferencesService.getUserPreferencesByUserIdSafe(userId);
        if(isUserNew){
            //PRAVILA ZA NOVOG KORISNIKA
            if(preference == null){
                System.out.println("Preferencija nula :* : " + preference);
                return getVisitorRecommendation();
            }
            KieServices ks = KieServices.Factory.get();
            KieContainer kieContainer = ks.getKieClasspathContainer();
            KieSession kSession = kieContainer.newKieSession("arrangementRecommendation");
            kSession.setGlobal("recommendationsNew", new ArrangementRecommendationService());
            kSession.setGlobal("preference", preference);

            for(Arrangement a: aService.findAll()){
                kSession.insert(a);
            }
            for(ArrangementGrade g: arrangementGradeService.findAll()){
                kSession.insert(g);
            }

            kSession.getAgenda().getAgendaGroup("graded").setFocus();
            int fired = kSession.fireAllRules();
            System.out.println("Number of rules fired gradede: " + fired);

            kSession.getAgenda().getAgendaGroup("popular").setFocus();
            fired = kSession.fireAllRules();
            System.out.println("Number of rules fired popular: " + fired);

            ArrangementRecommendationService recommendations = (ArrangementRecommendationService) kSession.getGlobal("recommendationsNew");
            kSession.insert(recommendations);

            kSession.getAgenda().getAgendaGroup("filter top destination").setFocus();
            fired = kSession.fireAllRules();
            System.out.println("Number of rules fired filter top destination: " + fired);

            kSession.getAgenda().getAgendaGroup("filter avg grade").setFocus();
            fired = kSession.fireAllRules();
            System.out.println("Number of rules fired filter avg grade: " + fired);


            return new ResponseEntity<>(recommendations.getArrangements(), HttpStatus.OK);
        }
        else{
            //PRAVILA ZA STAROG KORISNIKA
            KieServices ks = KieServices.Factory.get();
            KieContainer kieContainer = ks.getKieClasspathContainer();
            KieSession kSession = kieContainer.newKieSession("arrangementRecommendation");
            kSession.setGlobal("recommendationsOld", new ArrangementRecommendationService());
            kSession.setGlobal("preferenceOld", preference);

            for(Arrangement a: aService.findAll()){
                kSession.insert(a);
            }
            for(ArrangementGrade g: arrangementGradeService.findAll()){
                kSession.insert(g);
            }

            kSession.getAgenda().getAgendaGroup("novo").setFocus();
            int fired = kSession.fireAllRules();
            System.out.println("Number of rules fired staaari: " + fired);

            kSession.getAgenda().getAgendaGroup("pirson").setFocus();
            fired = kSession.fireAllRules();
            System.out.println("Number of rules fired staaari: " + fired);

            kSession.getAgenda().getAgendaGroup("slicnost").setFocus();
            fired = kSession.fireAllRules();
            System.out.println("Number of rules fired staaari: " + fired);

            kSession.getAgenda().getAgendaGroup("preferenca").setFocus();
            fired = kSession.fireAllRules();
            System.out.println("Number of rules fired staaari: " + fired);

            kSession.getAgenda().getAgendaGroup("filter bez bodova").setFocus();
            fired = kSession.fireAllRules();
            System.out.println("Number of rules fired staaari: " + fired);

            ArrangementRecommendationService recommendations = (ArrangementRecommendationService) kSession.getGlobal("recommendationsOld");
            kSession.insert(recommendations);

            kSession.getAgenda().getAgendaGroup("filterByGrade3New").setFocus();
            fired = kSession.fireAllRules();
            System.out.println("Number of rules fired staaari: " + fired);

            kSession.getAgenda().getAgendaGroup("filterByGrade2New").setFocus();
            fired = kSession.fireAllRules();
            System.out.println("Number of rules fired staaari: " + fired);

            kSession.getAgenda().getAgendaGroup("filterByGrade1New").setFocus();
            fired = kSession.fireAllRules();
            System.out.println("Number of rules fired staaari: " + fired);

            kSession.getAgenda().getAgendaGroup("filterByGrade1Final").setFocus();
            fired = kSession.fireAllRules();
            System.out.println("Number of rules fired staaari: " + fired);

            kSession.getAgenda().getAgendaGroup("filterByGrade2Final").setFocus();
            fired = kSession.fireAllRules();
            System.out.println("Number of rules fired staaari: " + fired);


            return new ResponseEntity<>(recommendations.getArrangements(), HttpStatus.OK);
        }
    }





}
