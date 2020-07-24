package com.xebia.treewalaproject.controller;

import com.xebia.treewalaproject.entity.User;
import com.xebia.treewalaproject.entity.UserResponse;
import com.xebia.treewalaproject.model.Password;
import com.xebia.treewalaproject.model.Username;
import com.xebia.treewalaproject.services.GetZohoData;
import com.xebia.treewalaproject.services.impl.EmailService;
import com.xebia.treewalaproject.services.impl.MyUserDetailsService;
import com.xebia.treewalaproject.services.impl.TempUsername;
import com.xebia.treewalaproject.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*")
public class SecurityController {

    @Autowired
    private TempUsername tempUsername;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private GetZohoData getZohoData;

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping({"/user"})
    public String admin(){
        return "user";
    }

    @PostMapping("/username")
    public ResponseEntity<UserResponse> verifyUsername(@RequestBody Username username) throws Exception{
        //System.out.println("CHECK");
        UserResponse userResponse=new UserResponse();
        try{
            getZohoData.loadNyUsername(username.getUsername());
            if(userDetailsService.isUserPresent(username.getUsername()).isPresent()){

            }else{
                User user = new User();
                user.setActive(true);
                user.setPassword("password");
                user.setRoles("ROLE_USER");
                user.setUsername(username.getUsername());
                userDetailsService.save(user);
            }
            //userDetailsService.loadUserByUsername(username.getUsername());
            emailService.sendEmail(username.getUsername());
            UserDetails userDetails = userDetailsService.loadUserByUsername(username.getUsername());
            tempUsername.setUsername(username.getUsername());
            userResponse.setMessage("user verified and mail send");
            userResponse.setStatus(HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(userResponse);
        }
        catch(NoSuchElementException e){
            userResponse.setMessage("invalid user");
            userResponse.setStatus(HttpStatus.FORBIDDEN);
//            throw new Exception("Incorrect username",e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(userResponse);
        }
    }

    @PostMapping("/password")
    public ResponseEntity<UserResponse> verifyPassword(@RequestBody Password password) throws Exception{
        UserResponse userResponse = new UserResponse();
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            tempUsername.getUsername(),password.getPassword()));
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(tempUsername.getUsername());

            final String jwt = jwtTokenUtil.generateToken(userDetails);
            userResponse.setStatus(HttpStatus.OK);
            userResponse.setMessage("Otp verified");
            String jwtList = jwt;
            userResponse.setPayload(jwtList);
            return ResponseEntity.status(HttpStatus.OK).body(userResponse);
        }catch (BadCredentialsException e){
            userResponse.setStatus(HttpStatus.BAD_REQUEST);
            userResponse.setMessage("Invalid OTP");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
        }
    }
}
