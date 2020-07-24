package com.xebia.treewalaproject.services.impl;

import com.xebia.treewalaproject.entity.User;
import com.xebia.treewalaproject.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(MyUserDetails::new).get();
    }

    public void save(User user){
        userRepository.save(user);
    }

    public Optional<User> isUserPresent(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return  user;
    }
}
