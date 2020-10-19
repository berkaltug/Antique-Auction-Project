package com.scopic.antiqueauction.security;

import com.scopic.antiqueauction.domain.entity.User;
import com.scopic.antiqueauction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        boolean isEnabled = true;
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

         return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                isEnabled, //is enabled field
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(user));
    }


    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] userRoles = user.getRoles()
                .stream()
                .map((role) -> role.getName())
                .toArray(String[]::new);
        return AuthorityUtils.createAuthorityList(userRoles);
    }
}
