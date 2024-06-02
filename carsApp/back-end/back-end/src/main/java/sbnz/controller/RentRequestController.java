package sbnz.controller;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.domain.RentRequest;
import sbnz.domain.User;
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

    @GetMapping(value = "testRule1")
    public ResponseEntity<String> testRule1() {

        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession ksession = kc.newKieSession("suspicionUserRules");

        User temp = new User();
        temp.setId(10);
        temp.setFirstName("UserTest1");
        ksession.setGlobal("u", temp);

        RentRequest request1 = new RentRequest();
        request1.setScheduleDT(LocalDateTime.now());
        request1.setCancelDT(LocalDateTime.now().plusDays(1).minusHours(2));
        request1.setUser(temp);
        request1.setLate(false);
        request1.setReturnState("ok");
        ksession.insert(request1);

        RentRequest request2 = new RentRequest();
        request2.setScheduleDT(LocalDateTime.now());
        request2.setCancelDT(LocalDateTime.now().plusDays(1).minusHours(1));
        request2.setUser(temp);
        request2.setLate(false);
        request2.setReturnState("ok");
        ksession.insert(request2);

        RentRequest request3 = new RentRequest();
        request3.setScheduleDT(LocalDateTime.now());
        request3.setCancelDT(LocalDateTime.now().plusDays(1).minusHours(3));
        request3.setUser(temp);
        request3.setLate(false);
        request3.setReturnState("ok");
        ksession.insert(request3);

        RentRequest request4 = new RentRequest();
        request4.setScheduleDT(LocalDateTime.now());
        request4.setCancelDT(LocalDateTime.now().plusDays(1).minusHours(3));
        request4.setUser(temp);
        request4.setLate(false);
        request4.setReturnState("ok");
        ksession.insert(request4);

        ksession.getAgenda().getAgendaGroup("rule1").setFocus();
        ksession.fireAllRules();

        return new ResponseEntity<>("Testing rule 1", HttpStatus.OK);
    }

    @GetMapping(value = "testRule2")
    public ResponseEntity<String> testRule2() {

        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession ksession = kc.newKieSession("suspicionUserRules");

        User temp = new User();
        temp.setId(10);
        temp.setFirstName("UserTest2");
        ksession.setGlobal("u", temp);

        RentRequest request1 = new RentRequest();
        request1.setScheduleDT(LocalDateTime.now());
        request1.setCancelDT(LocalDateTime.now().plusDays(2).plusHours(1));
        request1.setBeginDT(LocalDateTime.now().plusDays(2).plusHours(2).minusMinutes(10));
        request1.setUser(temp);
        request1.setLate(false);
        request1.setReturnState("ok");
        ksession.insert(request1);

        RentRequest request2 = new RentRequest();
        request2.setScheduleDT(LocalDateTime.now());
        request2.setCancelDT(LocalDateTime.now().plusDays(2).plusHours(1));
        request2.setBeginDT(LocalDateTime.now().plusDays(2).plusHours(2).minusMinutes(15));
        request2.setUser(temp);
        request2.setLate(false);
        request2.setReturnState("ok");
        ksession.insert(request2);

        RentRequest request3 = new RentRequest();
        request3.setScheduleDT(LocalDateTime.now());
        request3.setCancelDT(LocalDateTime.now().plusDays(2).plusHours(1));
        request3.setBeginDT(LocalDateTime.now().plusDays(2).plusHours(2).minusMinutes(20));
        request3.setUser(temp);
        request3.setLate(false);
        request3.setReturnState("ok");
        ksession.insert(request3);

        RentRequest request4 = new RentRequest();
        request4.setScheduleDT(LocalDateTime.now());
        request4.setCancelDT(LocalDateTime.now().plusDays(2).plusHours(1));
        request4.setBeginDT(LocalDateTime.now().plusDays(2).plusHours(2).minusMinutes(34));
        request4.setUser(temp);
        request4.setLate(false);
        request4.setReturnState("ok");
        ksession.insert(request4);

        ksession.getAgenda().getAgendaGroup("rule2").setFocus();
        ksession.fireAllRules();

        return new ResponseEntity<>("Testing rule 2", HttpStatus.OK);
    }

    @GetMapping(value = "testRule3")
    public ResponseEntity<String> testRule3() {

        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession ksession = kc.newKieSession("suspicionUserRules");

        User temp = new User();
        temp.setId(10);
        temp.setFirstName("UserTest3");
        ksession.setGlobal("u", temp);

        RentRequest request1 = new RentRequest();
        request1.setScheduleDT(LocalDateTime.now());
        request1.setCancelDT(null);
        request1.setBeginDT(LocalDateTime.now().plusDays(2).plusHours(2).minusMinutes(10));
        request1.setUser(temp);
        request1.setLate(false);
        request1.setReturnState("dirty");
        ksession.insert(request1);

        RentRequest request2 = new RentRequest();
        request2.setScheduleDT(LocalDateTime.now());
        request2.setCancelDT(null);
        request2.setBeginDT(LocalDateTime.now().plusDays(2).plusHours(2).minusMinutes(15));
        request2.setUser(temp);
        request2.setLate(false);
        request2.setReturnState("dirty");
        ksession.insert(request2);

        RentRequest request3 = new RentRequest();
        request3.setScheduleDT(LocalDateTime.now());
        request3.setCancelDT(null);
        request3.setBeginDT(LocalDateTime.now().plusDays(2).plusHours(2).minusMinutes(20));
        request3.setUser(temp);
        request3.setLate(false);
        request3.setReturnState("dirty");
        ksession.insert(request3);

        ksession.getAgenda().getAgendaGroup("rule3").setFocus();
        ksession.fireAllRules();

        return new ResponseEntity<>("Testing rule 3", HttpStatus.OK);
    }

    @GetMapping(value = "testRule4")
    public ResponseEntity<String> testRule4() {

        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession ksession = kc.newKieSession("suspicionUserRules");

        User temp = new User();
        temp.setId(10);
        temp.setFirstName("UserTest4");
        ksession.setGlobal("u", temp);

        RentRequest request1 = new RentRequest();
        request1.setScheduleDT(LocalDateTime.now());
        request1.setCancelDT(null);
        request1.setBeginDT(LocalDateTime.now().plusDays(2).plusHours(2).minusMinutes(10));
        request1.setUser(temp);
        request1.setLate(true);
        request1.setReturnState("ok");
        ksession.insert(request1);

        RentRequest request2 = new RentRequest();
        request2.setScheduleDT(LocalDateTime.now());
        request2.setCancelDT(null);
        request2.setBeginDT(LocalDateTime.now().plusDays(2).plusHours(2).minusMinutes(15));
        request2.setUser(temp);
        request2.setLate(true);
        request2.setReturnState("ok");
        ksession.insert(request2);

        RentRequest request3 = new RentRequest();
        request3.setScheduleDT(LocalDateTime.now());
        request3.setCancelDT(null);
        request3.setBeginDT(LocalDateTime.now().plusDays(2).plusHours(2).minusMinutes(20));
        request3.setUser(temp);
        request3.setLate(true);
        request3.setReturnState("ok");
        ksession.insert(request3);

        ksession.getAgenda().getAgendaGroup("rule4").setFocus();
        ksession.fireAllRules();

        return new ResponseEntity<>("Testing rule 4", HttpStatus.OK);
    }

    @GetMapping(value = "testRule5")
    public ResponseEntity<String> testRule5() {

        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession ksession = kc.newKieSession("suspicionUserRules");

        User temp = new User();
        temp.setId(10);
        temp.setFirstName("UserTest5");
        ksession.setGlobal("u", temp);

        RentRequest request1 = new RentRequest();
        request1.setScheduleDT(LocalDateTime.now());
        request1.setCancelDT(null);
        request1.setBeginDT(LocalDateTime.now().plusDays(2).plusHours(2).minusMinutes(10));
        request1.setUser(temp);
        request1.setLate(false);
        request1.setReturnState("broken");
        ksession.insert(request1);

        ksession.getAgenda().getAgendaGroup("rule5").setFocus();
        ksession.fireAllRules();

        return new ResponseEntity<>("Testing rule 5", HttpStatus.OK);
    }
}
