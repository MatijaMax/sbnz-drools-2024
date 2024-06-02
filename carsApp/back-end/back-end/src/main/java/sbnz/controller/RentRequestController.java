package sbnz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.domain.RentRequest;
import sbnz.dto.RentRentingDto;
import sbnz.service.CarService;
import sbnz.service.RentRequestService;
import sbnz.service.UserService;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5000", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/rents")
public class RentRequestController {
    @Autowired
    private RentRequestService rService;
    @Autowired
    private UserService uService;
    @Autowired
    private CarService cService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<RentRentingDto>> getAll() {

        List<RentRequest> cars = rService.findAll();

        // convert students to DTOs
        List<RentRentingDto> RentRentingDtos = new ArrayList<>();
        for (RentRequest c : cars) {
            RentRentingDtos.add(new RentRentingDto(c));
        }

        return new ResponseEntity<>(RentRentingDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/all/{id}")
    public ResponseEntity<List<RentRentingDto>> getAllById(@PathVariable Integer id) {

        List<RentRequest> cars = rService.findAllByUser(id);

        // convert students to DTOs
        List<RentRentingDto> RentRentingDtos = new ArrayList<>();
        for (RentRequest c : cars) {
            RentRentingDtos.add(new RentRentingDto(c));
        }

        return new ResponseEntity<>(RentRentingDtos, HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<RentRentingDto> save(@RequestBody RentRentingDto r) {

        RentRequest rq = new RentRequest();

        rq.setId(r.getId());
        rq.setScheduleDT(LocalDateTime.now());
        rq.setCancelDT(null);
        rq.setBeginDT(r.getBeginDT());
        rq.setReturnDT(r.getReturnDT());
        rq.setUser(uService.findOne(r.getUserId()));
        rq.setCar(cService.findOne(r.getCarId()));
        rq.setCancelReason(r.getCancelReason());
        rq.setReturnState(r.getReturnState());
        rq.setCanceled(r.getCanceled());
        rq.setLate(r.getLate());

        rq = rService.save(rq);
        return new ResponseEntity<>(new RentRentingDto(rq), HttpStatus.CREATED);
    }

    @PostMapping(value = "/cancel", consumes = "application/json")
    public ResponseEntity<RentRentingDto> cancel(@RequestBody RentRentingDto r) {

        RentRequest rq = new RentRequest();

        rq.setId(r.getId());
        rq.setScheduleDT(r.getScheduleDT());
        rq.setCancelDT(LocalDateTime.now());
        rq.setBeginDT(r.getBeginDT());
        rq.setReturnDT(r.getReturnDT());
        rq.setUser(uService.findOne(r.getUserId()));
        rq.setCar(cService.findOne(r.getCarId()));
        rq.setCancelReason(r.getCancelReason());
        rq.setReturnState(r.getReturnState());
        rq.setCanceled(true);
        rq.setLate(r.getLate());

        rq = rService.save(rq);
        return new ResponseEntity<>(new RentRentingDto(rq), HttpStatus.CREATED);
    }

    @PostMapping(value = "/returnRenting", consumes = "application/json")
    public ResponseEntity<RentRentingDto> returnRenting(@RequestBody RentRentingDto r) {

        RentRequest rq = new RentRequest();

        rq.setId(r.getId());
        rq.setScheduleDT(r.getScheduleDT());
        rq.setCancelDT(r.getCancelDT());
        rq.setBeginDT(r.getBeginDT());
        rq.setReturnDT(r.getReturnDT());
        rq.setUser(uService.findOne(r.getUserId()));
        rq.setCar(cService.findOne(r.getCarId()));
        rq.setCancelReason(r.getCancelReason());
        rq.setReturnState(r.getReturnState());
        rq.setCanceled(r.getCanceled());
        rq.setLate(r.getLate());

        rq = rService.save(rq);
        return new ResponseEntity<>(new RentRentingDto(rq), HttpStatus.CREATED);
    }
}
