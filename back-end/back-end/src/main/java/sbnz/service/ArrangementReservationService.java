package sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.domain.Arrangement;
import sbnz.domain.ArrangementReservation;
import sbnz.domain.TripReservation;
import sbnz.domain.User;
import sbnz.dto.ArrangementReservationDTO;
import sbnz.dto.TripReservationDTO;
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
    public ArrangementReservationDTO findById(Integer id) {
        ArrangementReservation arrangementReservation = arrangementReservationRepository.findById(id).orElse(null);
        return arrangementReservation != null ? toDTO(arrangementReservation) : null;
    }

    public List<ArrangementReservationDTO> getAll() {
        List<ArrangementReservation> arrangementReservations = arrangementReservationRepository.findAll();
        return arrangementReservations.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ArrangementReservationDTO create(ArrangementReservationDTO arrangementReservationDTO) {
        ArrangementReservation arrangementReservation = toEntity(arrangementReservationDTO);
        arrangementReservation = arrangementReservationRepository.save(arrangementReservation);
        if (arrangementReservation.getId() != null) {
            // Creating trip reservations
            for (TripReservationDTO tripReservationDTO : arrangementReservationDTO.getTripReservations()) {
                TripReservation tripReservation = toEntity(tripReservationDTO);
                tripReservation.setArrangementReservation(arrangementReservation);
                tripReservationRepository.save(tripReservation);
            }
            return toDTO(arrangementReservation);
        }
        return null;
    }

    private ArrangementReservationDTO toDTO(ArrangementReservation arrangementReservation) {
        ArrangementReservationDTO dto = new ArrangementReservationDTO();
        dto.setId(arrangementReservation.getId());
        dto.setNumberOfPeople(arrangementReservation.getNumberOfPeople());
        // Fetching trip reservations for this arrangement reservation
        List<TripReservation> tripReservations = tripReservationRepository.findByArrangementReservation(arrangementReservation);
        List<TripReservationDTO> tripReservationDTOs = tripReservations.stream()
                .map(this::toTripDTO)
                .collect(Collectors.toList());
        dto.setTripReservations(tripReservationDTOs);
        dto.setUserId(arrangementReservation.getUser().getId());
        dto.setArrangementId(arrangementReservation.getArrangement().getId());
        return dto;
    }

    private ArrangementReservation toEntity(ArrangementReservationDTO dto) {
        ArrangementReservation arrangementReservation = new ArrangementReservation();
        arrangementReservation.setNumberOfPeople(dto.getNumberOfPeople());

        // Fetching arrangement from repository
        Arrangement arrangement = arrangementRepository.findById(dto.getArrangementId()).orElse(null);
        if (arrangement == null) {
            // Handle if arrangement is not found
        }
        arrangementReservation.setArrangement(arrangement);

        // Fetching user from repository
        User user = userRepository.findById(dto.getUserId()).orElse(null);
        if (user == null) {
            // Handle if user is not found
        }
        arrangementReservation.setUser(user);

        // Fetching trip reservations from repository
       List<TripReservation> tripReservations = dto.getTripReservations().stream()
                .map(tripReservationDTO -> {
                    TripReservation tripReservation = new TripReservation();
                    tripReservation.setNumberOfGuests(tripReservationDTO.getNumberOfGuests());
                    // Map other fields if needed
                    return tripReservation;
                })
                .collect(Collectors.toList());
        arrangementReservation.setTripReservations(tripReservations);

        return arrangementReservation;
    }


    private TripReservationDTO toTripDTO(TripReservation tripReservation) {
        TripReservationDTO dto = new TripReservationDTO();
        dto.setNumberOfGuests(tripReservation.getNumberOfGuests());
        // Map other fields
        return dto;
    }

    private TripReservation toEntity(TripReservationDTO dto) {
        TripReservation tripReservation = new TripReservation();
        tripReservation.setNumberOfGuests(dto.getNumberOfGuests());
        tripReservation.setTrip(tripRepository.getById(dto.getId()));

        return tripReservation;
    }
}
