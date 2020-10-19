package com.scopic.antiqueauction.service.implementation;

import com.scopic.antiqueauction.domain.entity.Role;
import com.scopic.antiqueauction.domain.entity.User;
import com.scopic.antiqueauction.repository.RoleRepository;
import com.scopic.antiqueauction.repository.UserRepository;
import com.scopic.antiqueauction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(User user) {
        User existingUser=this.findByEmail(user.getEmail());
        User existingUsername=this.findByUsername(user.getUsername());
        if(existingUser!=null){
            throw new RuntimeException("This email is already exist");
        }else if(existingUsername!=null){
            throw new RuntimeException ("This username is already exist");
        }else if (!user.getPassword().contains("$2a$10$")) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        user.setRoles(new ArrayList<Role>(Arrays.asList(roleRepository.getOne(2)))); // direk user rolunde ekliyor
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findLoggedInUser() {
        return userRepository.findByUsername(findLoggedInUsername());
    }

    private String findLoggedInUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails ud=(UserDetails)principal;
        return ud.getUsername();
    }

}
