package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user!=null) {
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0])) //[0] added so that if list size above 1 so resize it
                    .build();

            return userDetails;
        }
        throw new UsernameNotFoundException("User not found with username: "+username);
    }
}

/**In Spring Security, 

UserDetailsServiceMpl.java
 acts as a bridge between your database and Spring Security's authentication process.

Whenever a user tries to log in, Spring Security needs to verify if the user exists and if their password is correct. However, Spring Security doesn't know anything about your MongoDB database or your 

User
 entity. It only understands its own 

UserDetails
 object.

Here is exactly what this class does:

Implements 

UserDetailsService
: By implementing this core Spring Security interface, you are telling Spring: "Hey, use this class to find users."
Loads the User from Database: Inside the 

loadUserByUsername
 method, it uses the 

UserRepository
 (which we talked about earlier) to fetch the user from MongoDB using their username.
Converts to Spring's Format:
If the user is found, it takes your application's 

User
 object (with your custom username, password, and roles).
It builds and returns a Spring Security 

UserDetails
 object using those details.
It also safely converts your list of roles into an array format that Spring expects: .roles(user.getRoles().toArray(new String[0])).
Handles Missing Users: If the user isn't found in the database, it throws a UsernameNotFoundException, which Spring Security will catch and use to deny access (e.g., returning a 401 Unauthorized error). */
