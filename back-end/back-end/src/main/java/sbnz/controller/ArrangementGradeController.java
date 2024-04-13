package sbnz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sbnz.domain.ArrangementGrade;
import sbnz.dto.ArrangementGradeCreateDTO;
import sbnz.dto.ArrangementGradeResponseDTO;
import sbnz.service.ArrangementGradeService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/arrangementGrades")
public class ArrangementGradeController {

    @Autowired
    private ArrangementGradeService arrangementGradeService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<ArrangementGradeResponseDTO>> getAll() {
        List<ArrangementGrade> arrangementGrades = arrangementGradeService.findAll();
        List<ArrangementGradeResponseDTO> arrangementGradeDTOs = arrangementGrades.stream()
                .map(ArrangementGradeResponseDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(arrangementGradeDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ArrangementGradeResponseDTO> getById(@PathVariable Integer id) {
        ArrangementGrade arrangementGrade = arrangementGradeService.findOne(id);
        if (arrangementGrade == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ArrangementGradeResponseDTO(arrangementGrade), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Regular')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ArrangementGradeResponseDTO> save(@RequestBody ArrangementGradeCreateDTO arrangementGradeCreateDTO) {
        ArrangementGrade arrangementGrade = arrangementGradeService.save(arrangementGradeCreateDTO);
        if(arrangementGrade != null){
            return new ResponseEntity<>(new ArrangementGradeResponseDTO(arrangementGrade), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
