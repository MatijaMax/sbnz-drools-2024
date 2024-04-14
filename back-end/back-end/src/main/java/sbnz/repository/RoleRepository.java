package sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.domain.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);
}
