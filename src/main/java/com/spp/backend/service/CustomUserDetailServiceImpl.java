package com.spp.backend.service;

// Create this interface and implement


import com.spp.backend.entity.User;
import com.spp.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

// implements CustomUserDetailsService
@Service
public class CustomUserDetailServiceImpl implements CustomUserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails mapDataForRoles(String email) {
        try {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new AuthorizationServiceException("User Not Found"));
            return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return null; // you can later set the role and permission details for user.
                }

                @Override
                public String getPassword() {
                    return user.getPassword();
                }

                @Override
                public String getUsername() {
                    return user.getEmail();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return user.isAccountNonExpired();
                }

                @Override
                public boolean isAccountNonLocked() {
                    return user.isAccountNonLocked();
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return user.isCredentialsNonExpired();
                }

                @Override
                public boolean isEnabled() {
                    return user.isEnabled();
                }
            };
        } catch (Exception ex) {
            throw new AuthorizationServiceException(ex.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws AuthorizationServiceException {
        return mapDataForRoles(username);
    }
}
