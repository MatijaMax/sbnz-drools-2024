package sbnz.controller;

import sbnz.domain.Role;
import sbnz.domain.Student;
import sbnz.domain.User;
import sbnz.dto.UserCreateDTO;
import sbnz.dto.UserResponseDTO;
import sbnz.service.RoleService;
import sbnz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @GetMapping(value = "/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<User> users = userService.findAll();
        List<UserResponseDTO> userDTOs = new ArrayList<UserResponseDTO>();
        for(User u : users){
            userDTOs.add(new UserResponseDTO(u));
        }
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }
    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserResponseDTO> save(@RequestBody UserCreateDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setPassword(userDTO.getPassword());
        user.setCompanyInformation(userDTO.getCompanyInformation());
        user.setProfession(userDTO.getProfession());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        List<Role> roles = roleService.findByName("ROLE_Regular");
        user.setRoles(roles);
        user = userService.save(user);
        UserResponseDTO userResponseDTO = new UserResponseDTO(user);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }
    @PostMapping(consumes = "application/json", path = "/admin")
    public ResponseEntity<UserResponseDTO> saveAdmin(@RequestBody UserCreateDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setPassword(userDTO.getPassword());
        user.setCompanyInformation(userDTO.getCompanyInformation());
        user.setProfession(userDTO.getProfession());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        List<Role> roles = roleService.findByName("ROLE_Admin");
        user.setRoles(roles);
        user = userService.save(user);
        UserResponseDTO userResponseDTO = new UserResponseDTO(user);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }
}
