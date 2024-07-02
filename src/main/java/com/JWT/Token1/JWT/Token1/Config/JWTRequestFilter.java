package com.JWT.Token1.JWT.Token1.Config;


import com.JWT.Token1.JWT.Token1.Service.JWTService;
import com.JWT.Token1.JWT.Token1.entity.User;
import com.JWT.Token1.JWT.Token1.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {
private JWTService jwtService ;
private UserRepository userRepository ;

    public JWTRequestFilter(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        System.out.println(tokenHeader);
        if (tokenHeader!=null && tokenHeader.startsWith("Bearer ")){
        String token = tokenHeader.substring(8, tokenHeader.length() - 1);
        String username = jwtService.getUsername(token);

            Optional<User> byUsername = userRepository.findByUsername(username);
            User user = byUsername.get();
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken
                            (user,null, Collections.singleton(new SimpleGrantedAuthority(
                                    user.getRole())
                            ));
            authenticationToken.setDetails(new WebAuthenticationDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
        filterChain.doFilter(request,response);
    }
}
