package com.shop.pharmacy.service;


import com.shop.pharmacy.form.UserRegistrationForm;
import com.shop.pharmacy.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationForm userRegistrationForm);
}
