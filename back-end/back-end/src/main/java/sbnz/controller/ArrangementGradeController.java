package sbnz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.domain.ArrangementGrade;
import sbnz.dto.ArrangementGradeDTO;
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
    public ResponseEntity<List<ArrangementGradeDTO>> getAll() {
        List<ArrangementGrade> arrangementGrades = arrangementGradeService.findAll();
        List<ArrangementGradeDTO> arrangementGradeDTOs = arrangementGrades.stream()
                .map(ArrangementGradeDTO::fromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(arrangementGradeDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ArrangementGradeDTO> getById(@PathVariable Integer id) {
        ArrangementGrade arrangementGrade = arrangementGradeService.findOne(id);
        if (arrangementGrade == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ArrangementGradeDTO.fromEntity(arrangementGrade), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ArrangementGradeDTO> save(@RequestBody ArrangementGradeDTO arrangementGradeDTO) {
        ArrangementGrade arrangementGrade = arrangementGradeService.save(arrangementGradeDTO);
        return new ResponseEntity<>(ArrangementGradeDTO.fromEntity(arrangementGrade), HttpStatus.CREATED);
    }
}
