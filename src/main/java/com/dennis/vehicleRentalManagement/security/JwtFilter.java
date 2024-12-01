package com.dennis.vehicleRentalManagement.security;

import com.dennis.vehicleRentalManagement.service.JwtService;
import com.dennis.vehicleRentalManagement.service.MyUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    ApplicationContext context;
    private JwtService jwtService;


    @Autowired
    public JwtFilter(ApplicationContext context) {
        this.context = context;
    }

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String userEmail = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
            userEmail = jwtService.extractUsername(jwtToken);

        }
if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

    UserDetails userDetails = context.getBean(MyUserDetails.class).loadUserByUsername(userEmail);
if(jwtService.validateToken(jwtToken,userDetails)){
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);

}
}
filterChain.doFilter(request, response);
    }
}
