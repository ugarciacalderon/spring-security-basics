package com.ugarciac.config;

import com.ugarciac.model.Authorities;
import com.ugarciac.model.Users;
import com.ugarciac.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsernamePwdAuthtenticationProvider implements AuthenticationProvider {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        List<Users> users = usersRepository.findByUsername(username);
        if (!users.isEmpty()) {
            if (passwordEncoder.matches(pwd, users.get(0).getPassword())) {
                    return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(users.get(0).getAuthorities()));
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        }else {
            throw new BadCredentialsException("No user registered with this details!");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<Authorities> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authorities authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getRoleName()));
        }
        return grantedAuthorities;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
