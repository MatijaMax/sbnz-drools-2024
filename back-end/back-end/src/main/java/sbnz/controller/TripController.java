package sbnz.controller;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sbnz.domain.Student;
import sbnz.domain.Trip;
import sbnz.dto.StudentDTO;
import sbnz.dto.TripCreateDTO;
import sbnz.dto.TripDTO;
import sbnz.model.Customer;
import sbnz.model.Item;
import sbnz.model.Order;
import sbnz.model.OrderLine;
import sbnz.service.ArrangementService;
import sbnz.service.TripService;
import sbnz.util.DebugAgendaEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/trips")
public class TripController {
    @Autowired
    private TripService tripService;
    @Autowired
    private ArrangementService arrangementService;
    @GetMapping
    public ResponseEntity<List<TripDTO>> getTrips() {
        List<Trip> trips = tripService.findAll();
        List<TripDTO> tripDTOs = new ArrayList<>();
        for (Trip e : trips) {
            tripDTOs.add(new TripDTO(e));
        }
        return new ResponseEntity<>(tripDTOs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<TripDTO> save(@RequestBody TripCreateDTO eDTO) {

        Trip e = new Trip();
        e.setDescription(eDTO.getDescription());
        e.setName(eDTO.getName());
        e.setPrice(eDTO.getPrice());
        e.setType(eDTO.getType());

        e.setArrangement(arrangementService.getById(eDTO.getArrangementId()));

        e = tripService.save(e);
        return new ResponseEntity<>(new TripDTO(e), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TripDTO> getStudent(@PathVariable Integer id) {

        Trip trip= tripService.findOne(id);

        // student must exist
        if (trip == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        KieSession kSession = kieContainer.newKieSession("ksession-rules");


        kSession.insert(trip);
        int fired = kSession.fireAllRules();
        System.out.println(fired);

        System.out.println("Price: " + trip.getPrice());

        trip = tripService.save(trip);
        return new ResponseEntity<>(new TripDTO(), HttpStatus.OK);
    }
}
