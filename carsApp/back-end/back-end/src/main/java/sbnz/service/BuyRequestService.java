package sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.domain.Car;
import sbnz.domain.Transaction;
import sbnz.dto.BuyRequestDTO;
import sbnz.domain.BuyRequest;
import sbnz.repository.BuyRequestRepository;
import sbnz.repository.CarRepository;
import sbnz.repository.UserRepository;
import sbnz.repository.TransactionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuyRequestService {

    @Autowired
    private BuyRequestRepository buyRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private TransactionRepository transactionRepository;
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

    public void payOffBuyRequest(Integer id, Long amount) {
        BuyRequest buyRequest = buyRequestRepository.findById(id).orElseThrow(() -> new RuntimeException("BuyRequest not found"));
        long value = buyRequest.getLeftToPay() - amount;
        if(value < 0 ) {
            throw new RuntimeException("Cannot pay more than owed");
        }
        Transaction transaction = new Transaction(buyRequest, LocalDate.now(), amount);
        transactionRepository.save(transaction);
        buyRequest.setLeftToPay(value);
        buyRequestRepository.save(buyRequest);

    }

    public List<BuyRequestDTO> getBuyRequestsByUser(Integer userId) {
        List<BuyRequest> buyRequests = buyRequestRepository.findByUserId(userId);
        return buyRequests.stream().map(BuyRequestDTO::new).collect(Collectors.toList());
    }

    public BuyRequest toEntity(BuyRequestDTO dto, UserRepository userRepository, CarRepository carRepository) {
        BuyRequest buyRequest = new BuyRequest();
        Car car = carRepository.findById(dto.getCarId()).orElse(null);
        buyRequest.setUser(userRepository.findById(dto.getUserId()).orElse(null));
        buyRequest.setUserAge(dto.getUserAge());
        buyRequest.setCar(car);
        buyRequest.setNumberOfCreditPayments(dto.getNumberOfCreditPayments());
        buyRequest.setUseremploymenttype(BuyRequest.USEREMPLOYMENTTYPE.valueOf(dto.getUseremploymenttype()));
        buyRequest.setEmploymentStart(dto.getEmploymentStart());
        buyRequest.setEmploymentEnd(dto.getEmploymentEnd());
        buyRequest.setLeftToPay((long)Math.ceil(car.getPrice()));
        buyRequest.setDateUntilToPay(LocalDate.now().plusYears(1));
        return buyRequest;
    }
}
