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

    public double findAverageForArrangement(int idArr) {
        List<ArrangementGrade> grades = arrangementGradeRepository.findAll();
        if(grades == null || grades.isEmpty()){
            return 0;
        }
        int sum = 0;
        int count = 0;
        for(ArrangementGrade g : grades){
            if(g.getArrangement().getId() == idArr){
                sum += g.getGrade();
                count++;
            }
        }
        return (double)sum/count;
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

    public boolean isUserNew(Integer id) {
        List<ArrangementGrade> grades = findAll();
        int gradeCounter = 0;
        for (ArrangementGrade grade : grades) {
            if (grade.getUser().getId().equals(id)){
                gradeCounter++;
            }
        }

        return (gradeCounter < 3);
    }

    public boolean noGrade(Integer id) {
        List<ArrangementGrade> grades = findAll();
        boolean noGrade = true;
        for (ArrangementGrade grade : grades) {
            if (grade.getArrangement().getId().equals(id)){
                noGrade = false;
            }
        }
        return noGrade;
    }

}
