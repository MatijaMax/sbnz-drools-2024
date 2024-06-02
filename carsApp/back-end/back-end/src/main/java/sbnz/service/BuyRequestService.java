package sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.dto.BuyRequestDTO;
import sbnz.domain.BuyRequest;
import sbnz.repository.BuyRequestRepository;
import sbnz.repository.CarRepository;
import sbnz.repository.UserRepository;

import java.util.Optional;

@Service
public class BuyRequestService {

    @Autowired
    private BuyRequestRepository buyRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    public BuyRequest getBuyRequestById(Integer id) {
        Optional<BuyRequest> buyRequestOptional = buyRequestRepository.findById(id);
        if (buyRequestOptional.isPresent()) {
            BuyRequest buyRequest = buyRequestOptional.get();
            return buyRequest;
        } else {
            // Handle the case where the BuyRequest is not found
            return null;
        }
    }

    public BuyRequestDTO createBuyRequest(BuyRequestDTO buyRequestDTO) {
        BuyRequest buyRequest = toEntity(buyRequestDTO, userRepository, carRepository);
        BuyRequest savedBuyRequest = buyRequestRepository.save(buyRequest);
        return new BuyRequestDTO(savedBuyRequest);
    }

    public BuyRequest toEntity(BuyRequestDTO dto, UserRepository userRepository, CarRepository carRepository) {
        BuyRequest buyRequest = new BuyRequest();
        buyRequest.setUser(userRepository.findById(dto.getUserId()).orElse(null));
        buyRequest.setUserAge(dto.getUserAge());
        buyRequest.setCar(carRepository.findById(dto.getCarId()).orElse(null));
        buyRequest.setNumberOfCreditPayments(dto.getNumberOfCreditPayments());
        buyRequest.setUseremploymenttype(BuyRequest.USEREMPLOYMENTTYPE.valueOf(dto.getUseremploymenttype()));
        buyRequest.setEmploymentStart(dto.getEmploymentStart());
        buyRequest.setEmploymentEnd(dto.getEmploymentEnd());
        buyRequest.setLeftToPay(0L);
        return buyRequest;
    }
}
