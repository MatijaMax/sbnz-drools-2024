package sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.domain.TripReservation;
import sbnz.dto.TripReservationDTO;
import sbnz.repository.TripReservationRepository;

import java.util.List;

@Service
public class TripReservationService {

    @Autowired
    private TripReservationRepository tripReservationRepository;

    public TripReservation findById(Integer id) {
        return tripReservationRepository.findById(id).orElse(null);
    }

    public List<TripReservation> getAll() {
        return tripReservationRepository.findAll();
    }

    public TripReservation save(TripReservation tripReservation) {
        return tripReservationRepository.save(tripReservation);
    }

    public TripReservation save(TripReservationDTO tripReservationDto) {
        //TODO
        return null;
    }

    public void delete(Integer id) {
        tripReservationRepository.deleteById(id);
    }
}
