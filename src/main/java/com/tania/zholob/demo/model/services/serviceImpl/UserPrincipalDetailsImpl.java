package com.tania.zholob.demo.model.services.serviceImpl;

import com.tania.zholob.demo.model.UserPrincipal;
import com.tania.zholob.demo.model.entity.Users;
import com.tania.zholob.demo.model.repos.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsImpl implements UserDetailsService {
    private UserRepo userRepo;


    public UserPrincipalDetailsImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(s);
        UserPrincipal userPrincipal = new UserPrincipal(user);

        return userPrincipal;
    }
}
