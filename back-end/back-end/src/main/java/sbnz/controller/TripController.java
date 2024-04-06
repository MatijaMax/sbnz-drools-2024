package sbnz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.domain.Trip;
import sbnz.dto.TripCreateDTO;
import sbnz.dto.TripDTO;
import sbnz.service.ArrangementService;
import sbnz.service.TripService;

import java.util.ArrayList;
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

    @PostMapping(consumes = "application/json")
    public ResponseEntity<TripDTO> saveStudent(@RequestBody TripCreateDTO eDTO) {

        Trip e = new Trip();
        e.setDescription(eDTO.getDescription());
        e.setName(eDTO.getName());
        e.setPrice(eDTO.getPrice());

        e.setArrangement(arrangementService.getById(eDTO.getArrangementId()));

        e = tripService.save(e);
        return new ResponseEntity<>(new TripDTO(e), HttpStatus.CREATED);
    }
}
