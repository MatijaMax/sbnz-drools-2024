package sbnz.service;

import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.springframework.security.crypto.password.PasswordEncoder;
import sbnz.domain.Student;
import sbnz.domain.User;
import sbnz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public User findOne(Integer id) {
        return userRepository.findById(id).orElseGet(null);
    }
}
