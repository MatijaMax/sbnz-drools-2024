package sbnz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.dto.BuyRequestDTO;
import sbnz.service.BuyRequestService;

@RestController
@RequestMapping("/api/buy-requests")
public class BuyRequestController {

    @Autowired
    private BuyRequestService buyRequestService;

    @GetMapping("/{id}")
    public ResponseEntity<BuyRequestDTO> getBuyRequest(@PathVariable Long id) {
        BuyRequestDTO buyRequestDTO = new BuyRequestDTO(buyRequestService.getBuyRequestById(id));
        if (buyRequestDTO != null) {
            return ResponseEntity.ok(buyRequestDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<BuyRequestDTO> createBuyRequest(@RequestBody BuyRequestDTO buyRequestDTO) {
        BuyRequestDTO createdBuyRequest = buyRequestService.createBuyRequest(buyRequestDTO);
        return ResponseEntity.ok(createdBuyRequest);
    }
}
