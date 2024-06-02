package sbnz.controller;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sbnz.domain.BuyRequest;
import sbnz.domain.User;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sbnz.dto.BuyRequestDTO;
import sbnz.service.BuyRequestService;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5000", allowedHeaders = "*")
@RestController
@RequestMapping("/api/buy-requests")
public class BuyRequestController {

    @Autowired
    private BuyRequestService buyRequestService;

    @GetMapping("/{id}")
    public ResponseEntity<BuyRequestDTO> getBuyRequest(@PathVariable Integer id) {
        BuyRequest buyRequest = buyRequestService.getBuyRequestById(id);
        if (buyRequest != null) {
            return ResponseEntity.ok(new BuyRequestDTO(buyRequest));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<BuyRequestDTO> createBuyRequest(@RequestBody BuyRequestDTO buyRequestDTO) {
        BuyRequestDTO createdBuyRequest = buyRequestService.createBuyRequest(buyRequestDTO);
        return ResponseEntity.ok(createdBuyRequest);
    }

    @PostMapping("/pay/{id}")
    public ResponseEntity<String> payOffBuyRequest(@PathVariable Integer id, @RequestParam Long amount) {
        try {
            buyRequestService.payOffBuyRequest(id, amount);
            return ResponseEntity.ok("Payment successful");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Payment failed: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BuyRequestDTO>> getBuyRequestsByUser(@PathVariable Integer userId) {
        List<BuyRequestDTO> buyRequests = buyRequestService.getBuyRequestsByUser(userId);
        return ResponseEntity.ok(buyRequests);
    }

    @GetMapping(value = "rule")
    public ResponseEntity<List<BuyRequest>> test() {
        List res = new ArrayList();

        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession ksession = kc.newKieSession("buyRules");

        User temp = new User();
        temp.setId(10);

        BuyRequest request1 = new BuyRequest();
        request1.setUser(temp);
        request1.setId(1);
        request1.setLeftToPay(200L);
        request1.setNumberOfCreditPayments(1);
        request1.setUserAge(50);
        request1.setDateUntilToPay(LocalDate.now().plusDays(1));
        ksession.insert(request1);


        BuyRequest request2 = new BuyRequest();
        request2.setUser(temp);
        request2.setId(1);
        request2.setLeftToPay(200L);
        request2.setNumberOfCreditPayments(1);
        request2.setUserAge(50);
        request2.setDateUntilToPay(LocalDate.now().plusDays(1));
        ksession.insert(request2);


        BuyRequest request3 = new BuyRequest();
        request3.setUser(temp);
        request3.setId(1);
        request3.setLeftToPay(200L);
        request3.setNumberOfCreditPayments(1);
        request3.setUserAge(50);
        request3.setDateUntilToPay(LocalDate.now().plusDays(1));
        ksession.insert(request3);


        BuyRequest request4 = new BuyRequest();
        request4.setUser(temp);
        request4.setId(1);
        request4.setLeftToPay(0L);
        request4.setNumberOfCreditPayments(1);
        request4.setUserAge(50);
        request4.setDateUntilToPay(LocalDate.now().plusDays(1));
        ksession.insert(request4);


        ksession.setGlobal("user", temp);

        ksession.fireAllRules();

        QueryResults results = ksession.getQueryResults("notPayedCredits", (Object) temp);
        System.out.println(results.size());
        for (QueryResultsRow result : results) {
            System.out.println(result);
            System.out.println(result.toString());
            System.out.println(result.get("$request"));
        }

        results = ksession.getQueryResults("notMoreThan3ActiveCredits", (Object) temp);
        System.out.println(results.size());
        for (QueryResultsRow result : results) {
            System.out.println(result);
            System.out.println(result.toString());
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
