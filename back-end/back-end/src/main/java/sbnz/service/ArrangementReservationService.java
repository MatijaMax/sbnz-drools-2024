package sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.domain.ArrangementReservation;
import sbnz.domain.TripReservation;
import sbnz.dto.ArrangementReservationDTO;
import sbnz.dto.TripReservationDTO;
import sbnz.repository.ArrangementReservationRepository;
import sbnz.repository.TripReservationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArrangementReservationService {

    @Autowired
    private ArrangementReservationRepository arrangementReservationRepository;

    @Autowired
    private TripReservationRepository tripReservationRepository;

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
        // Mapping other fields if needed
        return dto;
    }

    private ArrangementReservation toEntity(ArrangementReservationDTO dto) {
        ArrangementReservation arrangementReservation = new ArrangementReservation();
        arrangementReservation.setId(dto.getId());
        arrangementReservation.setNumberOfPeople(dto.getNumberOfPeople());
        // Mapping other fields if needed
        return arrangementReservation;
    }

    private TripReservationDTO toTripDTO(TripReservation tripReservation) {
        TripReservationDTO dto = new TripReservationDTO();
        dto.setId(tripReservation.getId());
        dto.setNumberOfGuests(tripReservation.getNumberOfGuests());
        // Map other fields
        return dto;
    }

    private TripReservation toEntity(TripReservationDTO dto) {
        TripReservation tripReservation = new TripReservation();
        tripReservation.setId(dto.getId());
        tripReservation.setNumberOfGuests(dto.getNumberOfGuests());
        // Map other fields
        return tripReservation;
    }
}
