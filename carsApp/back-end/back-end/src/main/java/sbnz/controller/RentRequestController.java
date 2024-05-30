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


    @PostMapping(consumes = "application/json")
    public ResponseEntity<RentRentingDto> save(@RequestBody RentRentingDto r) {

        //trenutno ovako inace ono ispod
        RentRequest rq = new RentRequest();
        if(true){
            rq.setId(r.getId());
            rq.setScheduleDT(LocalDateTime.now());
            rq.setCancelDT(LocalDateTime.now());
            rq.setBeginDT(LocalDateTime.now());
            rq.setReturnDT(LocalDateTime.now());
            rq.setUser(uService.findOne(r.getUserId()));
            rq.setCar(cService.findOne(r.getCarId()));
            rq.setCancelReason(r.getCancelReason());
            rq.setReturnState(r.getReturnState());
            rq.setCanceled(r.getCanceled());
            rq.setLate(r.getLate());
        }else {
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
        }

        rq = rService.save(rq);
        return new ResponseEntity<>(new RentRentingDto(rq), HttpStatus.CREATED);
    }
}
