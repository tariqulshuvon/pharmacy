package com.shop.pharmacy.service;



import com.shop.pharmacy.form.UserRegistrationForm;
import com.shop.pharmacy.model.Role;
import com.shop.pharmacy.model.User;
import com.shop.pharmacy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;




    @Override
    public User save(UserRegistrationForm userRegistrationForm) {

        User user = new User(userRegistrationForm.getId(),
                userRegistrationForm.getName(),
                userRegistrationForm.getEmail(),
                passwordEncoder.encode(userRegistrationForm.getPassword()),
                Arrays.asList(new Role("ROLE_USER")));

        return userRepository.save(user);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid Username or Password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {

        return roles.stream().map(role->
                new SimpleGrantedAuthority(role
                        .getName()))
                        .collect(Collectors.toList());

    }

}
