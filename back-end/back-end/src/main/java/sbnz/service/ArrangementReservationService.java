package sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.domain.*;
import sbnz.dto.ArrangementReservationCreateDTO;
import sbnz.dto.ArrangementReservationResponseDTO;
import sbnz.dto.TripReservationCreateDTO;
import sbnz.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArrangementReservationService {
    @Autowired
    private ArrangementReservationRepository arrangementReservationRepository;

    @Autowired
    private ArrangementRepository arrangementRepository;

    @Autowired
    private TripReservationRepository tripReservationRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserRepository userRepository;
    public ArrangementReservationResponseDTO findById(Integer id) {
        ArrangementReservation arrangementReservation = arrangementReservationRepository.findById(id).orElse(null);
        return arrangementReservation != null ? new ArrangementReservationResponseDTO(arrangementReservation) : null;
    }

    public List<ArrangementReservationResponseDTO> findByUserId(Integer userId) {
        List<ArrangementReservation> reservations = arrangementReservationRepository.findByUserId(userId);
        List<ArrangementReservationResponseDTO> responseDTOs = new ArrayList<>();
        for (ArrangementReservation reservation : reservations) {
            responseDTOs.add(new ArrangementReservationResponseDTO(reservation));
        }
        return responseDTOs;
    }

    public List<ArrangementReservation> getByUserId(Integer userId) {

        return arrangementReservationRepository.findByUserId(userId);
    }


    public List<ArrangementReservationResponseDTO> getAll() {
        List<ArrangementReservation> arrangementReservations = arrangementReservationRepository.findAll();
        return arrangementReservations.stream()
                .map(ArrangementReservationResponseDTO::new)
                .collect(Collectors.toList());
    }

    public ArrangementReservationResponseDTO create(ArrangementReservationCreateDTO arrangementReservationCreateDTO) {
        ArrangementReservation arrangementReservation = new ArrangementReservation();
        arrangementReservation.setNumberOfPeople(arrangementReservationCreateDTO.getNumberOfPeople());
        arrangementReservation.setDiscount(0.0);

        // Fetching arrangement from repository
        Arrangement arrangement = arrangementRepository.findById(arrangementReservationCreateDTO.getArrangementId()).orElse(null);
        if (arrangement == null) {
            return null;
        }
        arrangementReservation.setArrangement(arrangement);

        User user = userRepository.findById(arrangementReservationCreateDTO.getUserId()).orElse(null);
        if (user == null) {
            return null;
        }
        arrangementReservation.setUser(user);

        arrangementReservation.setTotalPrice((double) 0);
        arrangementReservation.setTripPrice((double) 0);
        arrangementReservation.setArrangementPrice((double) arrangementReservation.getNumberOfPeople()*arrangement.getPrice());

        arrangementReservation = arrangementReservationRepository.save(arrangementReservation);


        if (arrangementReservation.getId() != null) {
            for (TripReservationCreateDTO tripReservationCreateDTO : arrangementReservationCreateDTO.getTripReservations()) {
                TripReservation tripReservation = new TripReservation();
                tripReservation.setNumberOfGuests(tripReservationCreateDTO.getNumberOfGuests());
                Trip trip = tripRepository.getById(tripReservationCreateDTO.getTripId());
                tripReservation.setTrip(trip);
                tripReservation.setTotalPrice((double) (tripReservation.getNumberOfGuests() * trip.getPrice()));
                tripReservation.setArrangementReservation(arrangementReservation);
                tripReservation.setDiscount(0.0);
                tripReservationRepository.save(tripReservation);
            }
            arrangementReservation = arrangementReservationRepository.findById(arrangementReservation.getId()) != null? arrangementReservationRepository.findById(arrangementReservation.getId()).orElse(null) : null;
            if(arrangementReservation == null){
                return null;
            }
            return new ArrangementReservationResponseDTO(arrangementReservation);
        }
        return null;
    }

    public ArrangementReservation save(ArrangementReservation e) {
        return arrangementReservationRepository.save(e);
    }

    public ArrangementReservation getById(int id) {
        return arrangementReservationRepository.getReferenceById(id);
    }

    public ArrangementReservation findOne(Integer id) {
        return arrangementReservationRepository.findById(id).orElseGet(null);
    }

}
