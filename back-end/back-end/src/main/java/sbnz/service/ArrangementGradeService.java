package sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.domain.Arrangement;
import sbnz.domain.ArrangementGrade;
import sbnz.domain.User;
import sbnz.dto.ArrangementGradeCreateDTO;
import sbnz.repository.ArrangementGradeRepository;
import sbnz.repository.ArrangementRepository;
import sbnz.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ArrangementGradeService {

    @Autowired
    private ArrangementGradeRepository arrangementGradeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArrangementRepository arrangementRepository;

    public ArrangementGrade findOne(Integer id) {
        return arrangementGradeRepository.findById(id).orElse(null);
    }

    public List<ArrangementGrade> findAll() {
        return arrangementGradeRepository.findAll();
    }

    public ArrangementGrade save(ArrangementGradeCreateDTO arrangementGradeCreateDTO) {
        Optional<User> optionalUser = userRepository.findById(arrangementGradeCreateDTO.getUserId());
        Optional<Arrangement> optionalArrangement = arrangementRepository.findById(arrangementGradeCreateDTO.getArrangementId());

        if (optionalUser.isPresent() && optionalArrangement.isPresent()) {
            User user = optionalUser.get();
            Arrangement arrangement = optionalArrangement.get();

            ArrangementGrade arrangementGrade = new ArrangementGrade();
            arrangementGrade.setUser(user);
            arrangementGrade.setArrangement(arrangement);
            arrangementGrade.setGrade(arrangementGradeCreateDTO.getGrade());
            return arrangementGradeRepository.save(arrangementGrade);
        } else {
            return null;
        }
    }
}
