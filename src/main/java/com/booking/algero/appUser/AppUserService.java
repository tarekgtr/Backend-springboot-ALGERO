package com.booking.algero.appUser;

import com.booking.algero.appUser.AppUser;
import com.booking.algero.appUser.AppUserRole;
import com.booking.algero.appUser.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Getter @Setter
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;




    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User not found with this email %s", email))
        );
    }

    public String signUpUser(AppUser appUser){
        boolean isExist = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if (isExist) throw new IllegalStateException("User already exist");

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUser.setAppUserRole(AppUserRole.ADMIN);

        appUserRepository.save(appUser);

        return  "registration succesful";
    }



}
