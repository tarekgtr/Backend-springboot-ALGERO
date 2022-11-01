package com.booking.algero.auth;

import com.booking.algero.appUser.AppUser;
import com.booking.algero.jwt.JwtRequest;
import com.booking.algero.jwt.JwtResponse;
import com.booking.algero.appUser.AppUserService;
import com.booking.algero.jwt.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AuthController {

    private final AppUserService appUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @GetMapping
    public String Home(){
        return "Hello wellcom home";
    }

    @PostMapping("/register")
    public String register(@RequestBody AppUser appUser){
        return appUserService.signUpUser(appUser);
    }

    @PostMapping("/auth")
    public Map<String, Object> logIn(@RequestBody JwtRequest jwtRequest) {
        Map<String, Object> response;
        try {
            response = new HashMap<>();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    jwtRequest.getEmail(),
                    jwtRequest.getPassword()
            ));
            System.out.println("USER AUTHENTICATED ------------------->");
        } catch (BadCredentialsException e) {
            throw new IllegalStateException("Invalid email, password", e);
        }

        final UserDetails userDetails = appUserService.loadUserByUsername(jwtRequest.getEmail());



        final String token = jwtUtil.generateToken(userDetails);
        response.put("jwtToken", token);
        response.put("user", userDetails);
        return response;
    }


}
