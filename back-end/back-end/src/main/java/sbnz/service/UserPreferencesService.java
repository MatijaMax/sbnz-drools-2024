package sbnz.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.domain.UserPreferences;
import sbnz.repository.UserPreferencesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserPreferencesService {

    private final UserPreferencesRepository userPreferencesRepository;

    @Autowired
    public UserPreferencesService(UserPreferencesRepository userPreferencesRepository) {
        this.userPreferencesRepository = userPreferencesRepository;
    }

    // Create operation
    public UserPreferences createUserPreferences(UserPreferences userPreferences) {
        return userPreferencesRepository.save(userPreferences);
    }

    // Read operation - Get all user preferences
    public List<UserPreferences> getAllUserPreferences() {
        return userPreferencesRepository.findAll();
    }

    // Read operation - Get user preferences by ID
    public UserPreferences getUserPreferencesById(Integer id) {
        return userPreferencesRepository.findById(id).orElse(null);
    }

    // Update operation
    public UserPreferences updateUserPreferences(Integer id, UserPreferences newUserPreferences) {
        Optional<UserPreferences> existingUserPreferencesOptional = userPreferencesRepository.findById(id);
        if (existingUserPreferencesOptional.isPresent()) {
            UserPreferences existingUserPreferences = existingUserPreferencesOptional.get();
            // Update the existing user preferences with the new values
            existingUserPreferences.setDestinations(newUserPreferences.getDestinations());
            existingUserPreferences.setTrips(newUserPreferences.getTrips());
            // Save the updated user preferences
            return userPreferencesRepository.save(existingUserPreferences);
        } else {
            // User preferences with the given ID not found
            return null;
        }
    }

    // Delete operation
    public void deleteUserPreferences(Integer id) {
        userPreferencesRepository.deleteById(id);
    }

    public List<UserPreferences> getUserPreferencesByUserId(Integer userId) {
        return userPreferencesRepository.findByUserId(userId);
    }

    public UserPreferences getUserPreferencesByUserIdSafe(Integer userId) {
        var list = userPreferencesRepository.findByUserId(userId);
        if (list.isEmpty()) return null;

        return list.get(0);
    }
}
