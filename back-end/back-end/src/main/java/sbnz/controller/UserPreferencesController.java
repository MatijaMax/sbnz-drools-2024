package sbnz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.domain.User;
import sbnz.domain.UserPreferences;
import sbnz.dto.UserPreferencesDTO;
import sbnz.service.UserPreferencesService;
import sbnz.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/user/preferences")
public class UserPreferencesController {

    @Autowired
    private UserPreferencesService userPreferencesService;
    @Autowired
    private UserService userService;

    @Autowired
    public UserPreferencesController(UserPreferencesService userPreferencesService) {
        this.userPreferencesService = userPreferencesService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<UserPreferencesDTO> createUserPreferences(@PathVariable Integer userId, @RequestBody UserPreferencesDTO userPreferencesDTO) {
        UserPreferences userPreferences = mapDtoToEntity(userId, userPreferencesDTO);
        userPreferences = userPreferencesService.createUserPreferences(userPreferences);
        UserPreferencesDTO createdDto = mapEntityToDto(userPreferences);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserPreferencesDTO> getUserPreferencesByUserId(@PathVariable Integer userId) {
        List<UserPreferences> userPreferencesList = userPreferencesService.getUserPreferencesByUserId(userId);
        if (userPreferencesList.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if no user preferences found
        }
        UserPreferencesDTO userPreferencesDTO = mapEntityToDto(userPreferencesList.get(0));
        return ResponseEntity.ok().body(userPreferencesDTO);
    }

    // Helper method to map DTO to entity
    private UserPreferences mapDtoToEntity(Integer userId, UserPreferencesDTO userPreferencesDTO) {
        User user = userService.findById(userId);
        if(user == null) return null;
        UserPreferences userPreferences = new UserPreferences();
        userPreferences.setUser(user);
        userPreferences.setDestinations(String.join("|", userPreferencesDTO.getDestinations()));
        userPreferences.setTrips(String.join("|", userPreferencesDTO.getTrips()));
        return userPreferences;
    }

    // Helper method to map entity to DTO
    private UserPreferencesDTO mapEntityToDto(UserPreferences userPreferences) {
        UserPreferencesDTO userPreferencesDTO = new UserPreferencesDTO();
        userPreferencesDTO.setId(userPreferences.getId());
        userPreferencesDTO.setUserId(userPreferences.getUser().getId());
        userPreferencesDTO.setDestinations(List.of(userPreferences.getDestinations().split("\\|")));
        userPreferencesDTO.setTrips(List.of(userPreferences.getTrips().split("\\|")));
        return userPreferencesDTO;
    }
}
