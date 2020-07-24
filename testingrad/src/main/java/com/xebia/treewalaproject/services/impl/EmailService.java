package com.xebia.treewalaproject.services.impl;


import com.xebia.treewalaproject.entity.User;
import com.xebia.treewalaproject.repository.UserRepository;
import com.xebia.treewalaproject.util.EmailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class EmailService {
    @Autowired
    private UserRepository userRepository;

    public void sendEmail(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        int otp = getRandomNumberUsingNextInt();
        user.get().setPassword(String.valueOf(otp));
        userRepository.save(user.get());
        System.out.println(otp);
        EmailSend.setEmail(user.get().getUsername(),"OTP : ",otp+" is your OTP for ThanX login. ");
    }

    private int getRandomNumberUsingNextInt() {
        Random random = new Random();
        return random.nextInt(999999 - 100000) + 100000;
    }
}
