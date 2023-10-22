package com.example.sales.auth.service;

import com.example.sales.Enum.UserStatusEnum;
import com.example.sales.dto.request.EmailRequestDTO;
import com.example.sales.dto.request.LoginRequestDTO;
import com.example.sales.dto.request.UserRequestDTO;
import com.example.sales.dto.response.LoginResponseDTO;
import com.example.sales.mapper.UserMapper;
import com.example.sales.model.UserEntity;
import com.example.sales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findByLogin(username);
        if (entity != null) {
            return userMapper.userEntityToDetails(entity);
        } else throw new UsernameNotFoundException("User Not Found!");
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(tokenService.generateToken((UserEntity) auth.getPrincipal()));
        return response;
    }

    public void register(UserRequestDTO userRequest) throws Exception {
        if (userRepository.findByLogin(userRequest.getLogin()) != null) {
            throw new Exception("User Already Registered!");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(userRequest.getPassword());
        LocalDateTime dateTime = LocalDateTime.now();
        UserEntity user = userMapper.toUserEntity(userRequest);
        user.setRegistrationDate(dateTime);
        user.setPassword(encryptedPassword);
        user.setUserStatus(UserStatusEnum.INACTIVE);
        userRepository.save(user);
    }

    public long generateActivationCode(EmailRequestDTO emailRequest) throws AccountNotFoundException {
        UserEntity user = userRepository.findByEmail(emailRequest.getEmail());
        if (user != null) {
            long seed = System.currentTimeMillis();
            Random rng = new Random(seed);
            long code = (rng.nextLong() % 900000L) + 100000L;
            user.setActivationCode(code);
            userRepository.save(user);
            return code;
        } else {
            throw new AccountNotFoundException("The user was not found");
        }
    }

    public void validateCode(EmailRequestDTO emailRequest, Long code) throws Exception {
        UserEntity user = userRepository.findByEmail(emailRequest.getEmail());
        if (user != null && (user.getActivationCode().equals(code))) {
            user.setUserStatus(UserStatusEnum.ACTIVE);
            user.setActivationCode(null);
            userRepository.save(user);
        } else {
            throw new Exception("This code isn't valid");
        }
    }
}
