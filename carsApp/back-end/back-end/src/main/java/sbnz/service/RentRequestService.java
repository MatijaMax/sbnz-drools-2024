package sbnz.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.domain.Car;
import sbnz.domain.RentRequest;
import sbnz.repository.RentRequestRepository;

import java.util.List;

@Service
public class RentRequestService {
    @Autowired
    private RentRequestRepository rentRequestRepository;

    public List<RentRequest> findAll() {
        return rentRequestRepository.findAll();
    }

    public List<RentRequest> findAllByUser(Integer id) {
        return rentRequestRepository.findAllByUserId(id);
    }

    public RentRequest save(RentRequest r) {
        return rentRequestRepository.save(r);
    }
}
