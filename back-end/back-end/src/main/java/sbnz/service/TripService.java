package sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.domain.Trip;
import sbnz.repository.TripRepository;

import java.util.List;

@Service
public class TripService {
    @Autowired
    TripRepository tripRepository;

    public List<Trip> findAll(){
        return tripRepository.findAll();
    }

    public void remove(Integer id) {
        tripRepository.deleteById(id);
    }

    public Trip save(Trip e) {
        return tripRepository.save(e);
    }

    public Trip getById(int id) {
        return tripRepository.getReferenceById(id);
    }

}
