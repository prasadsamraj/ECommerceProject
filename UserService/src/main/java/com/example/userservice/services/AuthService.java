package com.example.userservice.services;

import com.example.userservice.dtos.GenericUserDto;
import com.example.userservice.exceptions.InvalidUserCredentialsException;
import com.example.userservice.exceptions.UserAlreadyExistsException;
import com.example.userservice.models.Session;
import com.example.userservice.models.SessionStatus;
import com.example.userservice.models.User;
import com.example.userservice.repositories.SessionRepository;
import com.example.userservice.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.util.*;


@Service
public class AuthService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private SecretKey secretKey;
    private SessionRepository sessionRepository;

    @Autowired
    public AuthService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.secretKey = Jwts.SIG.HS256.key().build();
        this.sessionRepository = sessionRepository;
    }
    public GenericUserDto signUp(String email, String password) throws UserAlreadyExistsException {
        if(userRepository.findByEmail(email).isPresent()) throw new UserAlreadyExistsException();
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
        return GenericUserDto.from(user);
    }

    public ResponseEntity<GenericUserDto> login(String email, String password) throws InvalidUserCredentialsException {
        User user = userRepository.findByEmail(email).orElseThrow(InvalidUserCredentialsException::new);
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())) throw new InvalidUserCredentialsException();
        Map<String, Object> jwtData = new HashMap<>();
        jwtData.put("email", email);
        jwtData.put("createdAt", new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        jwtData.put("expireAt", calendar.getTime());
        String token = Jwts.builder().claims(jwtData).signWith(secretKey).compact();
        Session session = new Session();
        session.setToken(token);
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setUser(user);
        session.setExpireAt(calendar.getTime());
        sessionRepository.save(session);
        MultiValueMap<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token:"+token);
        return new ResponseEntity<>(GenericUserDto.from(user), headers, HttpStatus.OK);
    }

    public SessionStatus validate(String userId, String token) {
        Optional<Session> optionalSession = sessionRepository.findByTokenAndUser_Uuid(token, UUID.fromString(userId));
        if(optionalSession.isEmpty()) return SessionStatus.ENDED;
        Session session = optionalSession.get();
        if(session.getSessionStatus().equals(SessionStatus.ENDED) || new Date().compareTo(session.getExpireAt())>0){
            return SessionStatus.ENDED;
        }
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
        } catch (JwtException | IllegalArgumentException e) {
            return SessionStatus.ENDED;
        }
        return SessionStatus.ACTIVE;
    }

    public void logout(String userId, String token) {
        Optional<Session> optionalSession = sessionRepository.findByTokenAndUser_Uuid(token, UUID.fromString(userId));
        if(optionalSession.isEmpty()) return;
        Session session = optionalSession.get();
        session.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(session);
    }
}
