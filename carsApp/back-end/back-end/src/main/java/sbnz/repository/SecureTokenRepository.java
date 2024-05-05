package sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.domain.SecureToken;

public interface SecureTokenRepository extends JpaRepository<SecureToken, Long > {

    SecureToken findByToken(final String token);
    Long removeByToken(String token);
}
