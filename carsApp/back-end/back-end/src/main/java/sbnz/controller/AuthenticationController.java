package sbnz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import sbnz.domain.User;
import sbnz.dto.JwtAuthenticationRequest;
import sbnz.dto.UserTokenState;
import sbnz.service.RoleService;
import sbnz.service.SecureTokenService;
import sbnz.service.UserService;
import sbnz.util.TokenUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:5000", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/auth")
public class AuthenticationController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SecureTokenService tokenService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private TokenUtils tokenUtils;

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {
        // Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
        // AuthenticationException
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(), authenticationRequest.getPassword()));

        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
        // kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername(), user.getRoles().get(0).getName(), user.getId());
        int expiresIn = tokenUtils.getExpiredIn();

        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }
}
