package ftn.isa.controller;

import java.util.*;

import ftn.isa.domain.*;
import ftn.isa.dto.StudentDTO;
import ftn.isa.service.StudentService;
import ftn.isa.util.DebugAgendaEventListener;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;


@RestController
@RequestMapping(value = "api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {

        List<Student> students = studentService.findAll();

        // convert students to DTOs
        List<StudentDTO> studentsDTO = new ArrayList<>();
        for (Student s : students) {
            studentsDTO.add(new StudentDTO(s));
        }

        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }

    // GET /api/students?page=0&size=5&sort=firstName,DESC
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getStudentsPage(Pageable page) {

        // page object holds data about pagination and sorting
        // the object is created based on the url parameters "page", "size" and "sort"
        Page<Student> students = studentService.findAll(page);

        // convert students to DTOs
        List<StudentDTO> studentsDTO = new ArrayList<>();
        for (Student s : students) {
            studentsDTO.add(new StudentDTO(s));
        }

        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Integer id) {

//        Student student = studentService.findOne(id);
//
//        // studen must exist
//        if (student == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        KieSession kSession = kieContainer.newKieSession("ksession-rules");

        kSession.addEventListener(new DebugAgendaEventListener());

        kSession.setGlobal("comboCount", 2);

        Customer c1 = new Customer();
        c1.setAge(21);
        c1.setCategory(Customer.Category.NA);
        c1.setCustomerId((long) 21);
        c1.setName("Pera");
        c1.setEmail("pera@pera.pera");

        Order o = new Order();
        o.setCustomer(c1);
        o.setDate(new Date());
        List<OrderLine> l1 = new LinkedList<OrderLine>();

        OrderLine ol1 = new OrderLine();
        ol1.setItem(new Item((long)1,"i1", 123.0, 123.0));
        OrderLine ol2 = new OrderLine();
        ol2.setItem(new Item((long)2,"i2", 223.0, 223.0));
        OrderLine ol3 = new OrderLine();
        ol3.setItem(new Item((long)3,"i3", 293.0, 213.0));
        OrderLine ol4 = new OrderLine();
        ol4.setItem(new Item((long)4,"i4", 993.0, 1213.0));
        OrderLine ol5 = new OrderLine();
        ol5.setItem(new Item((long)5,"i5", 793.0, 1013.0));

        l1.add(ol1);
        l1.add(ol2);
        l1.add(ol3);
        l1.add(ol4);
        l1.add(ol5);

        o.setOrderLines(l1);

        System.out.println("Discount: " + o.getDiscount());

        kSession.insert(c1);
        kSession.insert(o);

        int fired = kSession.fireAllRules();
        System.out.println(fired);

        System.out.println("Discount: " + o.getDiscount());

        return new ResponseEntity<>(new StudentDTO(new Student()), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO) {

        Student student = new Student();
        student.setIndex(studentDTO.getIndex());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());

        student = studentService.save(student);
        return new ResponseEntity<>(new StudentDTO(student), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) {

        // a student must exist
        Student student = studentService.findOne(studentDTO.getId());

        if (student == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        student.setIndex(studentDTO.getIndex());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());

        student = studentService.save(student);
        return new ResponseEntity<>(new StudentDTO(student), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {

        Student student = studentService.findOne(id);

        if (student != null) {
            studentService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/findIndex")
    public ResponseEntity<StudentDTO> getStudentByIndex(@RequestParam String index) {

        Student student = studentService.findByIndex(index);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new StudentDTO(student), HttpStatus.OK);
    }

    @GetMapping(value = "/findLastName")
    public ResponseEntity<List<StudentDTO>> getStudentsByLastName(@RequestParam String lastName) {

        List<Student> students = studentService.findByLastName(lastName);

        // convert students to DTOs
        List<StudentDTO> studentsDTO = new ArrayList<>();
        for (Student s : students) {
            studentsDTO.add(new StudentDTO(s));
        }
        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/prezime")
    public ResponseEntity<List<StudentDTO>> pronadjiStudentePoPrezimenu(@RequestParam String lastName) {

        List<Student> students = studentService.pronadjiPoPrezimenu(lastName);

        // convert students to DTOs
        List<StudentDTO> studentsDTO = new ArrayList<>();
        for (Student s : students) {
            studentsDTO.add(new StudentDTO(s));
        }
        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/findFirstLast")
    public ResponseEntity<List<StudentDTO>> getStudentsByFirstNameAndLastName(@RequestParam String firstName,
                                                                              @RequestParam String lastName) {

        List<Student> students = studentService.findByFirstNameAndLastName(firstName, lastName);

        // convert students to DTOs
        List<StudentDTO> studentsDTO = new ArrayList<>();
        for (Student s : students) {
            studentsDTO.add(new StudentDTO(s));
        }
        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }

}
