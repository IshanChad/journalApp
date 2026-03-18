package net.engineeringdigest.journalApp;

import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


//for big applications we might want to test only certain sections of code skipping part like finding username where we
//could have a mocking (fake) id just for testing in order to save computations and time

/*
* Mock isn't concerned about application context so it doesn't create any instance
* so with @Autowired we use @MockBean that creates a context
*
* but if @SpringBootTest not used at this time then due to absence of application context we have to use @InjectMocks(it itself initializes it though) instead of @Autowired and @Mock instead of @Mockbean
* But @BeforeEach function also to be used to initialize all mocks before bringing them into use
*
* With @Autowired we make component/bean that's why Mockbean used so that they can be injected in it
* */

//but if we have to mock one thing and other not then simply use spring application context

@ActiveProfiles("dev")
public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){
        //here we are mocking it that whenever such request made, just return a fake one
        net.engineeringdigest.journalApp.entity.User mockUser = new net.engineeringdigest.journalApp.entity.User();
        mockUser.setUserName("Ram");
        mockUser.setPassword("Ram");
        mockUser.setRoles(new ArrayList<>());
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(mockUser);
        UserDetails user = userDetailsService.loadUserByUsername("Ram");
        Assertions.assertNotNull(user);
    }
}
