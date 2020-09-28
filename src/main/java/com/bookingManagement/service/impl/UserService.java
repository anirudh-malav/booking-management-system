package com.bookingManagement.service.impl;

import com.bookingManagement.common.exceptions.AuthenticationFailed;
import com.bookingManagement.model.UserDetails;
import com.bookingManagement.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public UserDetails validateUser(String email, String password) {
        UserDetails userDetails = userDetailsRepository.validateUser(email, password);
        if (Objects.isNull(userDetails)) {
            throw new AuthenticationFailed("User Authentication Fail | UserEmail : " + email);
        }
        return userDetails;
    }

}
