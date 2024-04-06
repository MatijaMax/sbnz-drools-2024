package sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sbnz.domain.Arrangement;
import sbnz.domain.Student;
import sbnz.domain.Trip;
import sbnz.repository.ArrangementRepository;
import sbnz.repository.StudentRepository;

import java.util.List;

@Service
public class ArrangementService {
    @Autowired
    private ArrangementRepository arrangementRepository;

    public Arrangement findOne(Integer id) {
        return arrangementRepository.findById(id).orElseGet(null);
    }

    public List<Arrangement> findAll() {
        return arrangementRepository.findAll();
    }

    public Arrangement getById(int id) {
        return arrangementRepository.getReferenceById(id);
    }

    public Arrangement findOneWithTrips(Integer id) {
        return arrangementRepository.findOneWithTrips(id);
    }

    public List<Arrangement> findAllWithTrips() {
        return arrangementRepository.findAllWithTrips();
    }

    public Arrangement save(Arrangement arrangement) {
        return arrangementRepository.save(arrangement);
    }
}
