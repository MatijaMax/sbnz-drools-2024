package sbnz.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sbnz.domain.UserPreferences;

import java.util.List;

@Repository
public interface UserPreferencesRepository extends JpaRepository<UserPreferences, Integer> {
    List<UserPreferences> findByUserId(Integer userId);
}