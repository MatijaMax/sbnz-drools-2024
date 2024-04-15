package sbnz.controller;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.domain.ArrangementReservation;
import sbnz.dto.ArrangementReservationCreateDTO;
import sbnz.dto.ArrangementReservationResponseDTO;
import sbnz.dto.TripReservationCreateDTO;
import sbnz.service.ArrangementReservationService;
import sbnz.service.TripReservationService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/arrangementReservations")
public class ArrangementReservationController {

    @Autowired
    private ArrangementReservationService arrangementReservationService;

    @Autowired
    private TripReservationService tripReservationService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<ArrangementReservationResponseDTO>> getAll() {
        List<ArrangementReservationResponseDTO> arrangementReservations = arrangementReservationService.getAll();
        return new ResponseEntity<>(arrangementReservations, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ArrangementReservationResponseDTO> findById(@PathVariable Integer id) {
        ArrangementReservationResponseDTO arrangementReservation = arrangementReservationService.findById(id);
        if (arrangementReservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(arrangementReservation, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ArrangementReservationResponseDTO> create(@RequestBody ArrangementReservationCreateDTO arrangementReservationCreateDTO) {
        ArrangementReservationResponseDTO createdArrangementReservation = arrangementReservationService.create(arrangementReservationCreateDTO);
        if (createdArrangementReservation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdArrangementReservation, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public void calculatePrice(@PathVariable Integer id) {
        ArrangementReservation reservation= arrangementReservationService.findOne(id);

        if (reservation == null) {
            System.out.println("Reservation not found");
            return;
        }

        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        KieSession kSession = kieContainer.newKieSession("ourRules");

        kSession.insert(reservation);

        kSession.getAgenda().getAgendaGroup("arrangement discount").setFocus();
        int fired = kSession.fireAllRules();
        System.out.println("Number of rules fired: " + fired);
        //System.out.println("Price: " + reservation.getTotalPrice());
        System.out.println("Reservation: " + reservation);

        kSession.getAgenda().getAgendaGroup("trip discount").setFocus();
        fired = kSession.fireAllRules();
        System.out.println("Number of rules fired: " + fired);
        //System.out.println("Price: " + reservation.getTotalPrice());
        System.out.println("Reservation: " + reservation);

        kSession.getAgenda().getAgendaGroup("final  discount").setFocus();
        fired = kSession.fireAllRules();
        System.out.println("Number of rules fired: " + fired);
        //System.out.println("Price: " + reservation.getTotalPrice());
        System.out.println("Reservation: " + reservation);

        arrangementReservationService.save(reservation);
    }


}
