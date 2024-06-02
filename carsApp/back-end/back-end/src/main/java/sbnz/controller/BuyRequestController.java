package sbnz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.domain.BuyRequest;
import sbnz.dto.BuyRequestDTO;
import sbnz.service.BuyRequestService;

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
}
