package sbnz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.dto.ArrangementReservationDTO;
import sbnz.dto.TripReservationDTO;
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
    public ResponseEntity<List<ArrangementReservationDTO>> getAll() {
        List<ArrangementReservationDTO> arrangementReservations = arrangementReservationService.getAll();
        return new ResponseEntity<>(arrangementReservations, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ArrangementReservationDTO> findById(@PathVariable Integer id) {
        ArrangementReservationDTO arrangementReservation = arrangementReservationService.findById(id);
        if (arrangementReservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(arrangementReservation, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ArrangementReservationDTO> create(@RequestBody ArrangementReservationDTO arrangementReservationDTO) {
        List<TripReservationDTO> tripReservationsDTO = arrangementReservationDTO.getTripReservations();
        ArrangementReservationDTO createdArrangementReservation = arrangementReservationService.create(arrangementReservationDTO);
        if (createdArrangementReservation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
/*        // TODO: Creating trip reservations
        for (TripReservationDTO tripReservationDTO : tripReservationsDTO) {
            tripReservationDTO.setArrangementReservationId(createdArrangementReservation.getId());
            tripReservationService.save(tripReservationDTO);
        }*/
        return new ResponseEntity<>(createdArrangementReservation, HttpStatus.CREATED);
    }
}
