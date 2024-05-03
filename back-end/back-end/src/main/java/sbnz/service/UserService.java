package sbnz.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import sbnz.domain.User;
import sbnz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User findById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }
}
