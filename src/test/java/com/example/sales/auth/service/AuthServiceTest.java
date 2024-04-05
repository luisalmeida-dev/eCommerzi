package com.example.sales.auth.service;

import com.example.sales.Enum.RolesEnum;
import com.example.sales.Enum.UserStatusEnum;
import com.example.sales.dto.request.LoginRequestDTO;
import com.example.sales.mapper.UserMapper;
import com.example.sales.model.UserEntity;
import com.example.sales.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class AuthServiceTest {
    @InjectMocks
    AuthService authServiceMock;

    @Mock
    UserRepository userRepositoryMock;

    @Mock
    TokenService tokenServiceMock;

    @Mock
    UserMapper userMapperMock;

    @Mock
    AuthenticationManager authenticationManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    private UserEntity createUserEntity() {
        UserEntity entity = new UserEntity();
        entity.setActivationCode(123);
        entity.setPassword("pass123");
        entity.setUserStatus(UserStatusEnum.ACTIVE);
        entity.setEmail("email");
        entity.setLogin("login");
        entity.setName("name");
        entity.setPhone("6199999999");
        entity.setRole(RolesEnum.USER);
        entity.setRegistrationDate(LocalDateTime.now());

        return entity;
    }

    @Test
    void loadUserByUsernameTest() {
        UserEntity user = createUserEntity();
        UserDetails userDetailsMock = mock(UserDetails.class);

        when(userRepositoryMock.findByLogin(user.getLogin())).thenReturn(user);
        when(userMapperMock.userEntityToDetails(user)).thenReturn(userDetailsMock);

        UserDetails userDetails = authServiceMock.loadUserByUsername(user.getLogin());

        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(userDetailsMock, userDetails);
        verify(userRepositoryMock, times(1)).findByLogin(());
        verify(userMapperMock, times(1)).userEntityToDetails(any(UserEntity.class));
    }

    @Test
    void login() {
        LoginRequestDTO loginRequestMock = mock(LoginRequestDTO.class);
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestMock.getLogin(), loginRequestMock.getPassword());


    }
}
