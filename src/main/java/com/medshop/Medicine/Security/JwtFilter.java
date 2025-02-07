package com.medshop.Medicine.Security;

import com.medshop.Medicine.Models.RefreshToken;
import com.medshop.Medicine.Service.RefreshTokenService;
import com.medshop.Medicine.Service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    UserDetailsService user_details_service;
    @Autowired
    RefreshTokenService refresh_token_service;
    @Autowired
    TokenService token_service;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header=request.getHeader("Authorization");
        System.out.println("Header = "+header);
        if(header==null || !header.startsWith("Bearer")) {
            System.out.println("header is null or header does not start with bearer");
            filterChain.doFilter(request, response);
            return;
        }        String access_token=header.substring(7);
        String username=token_service.extractUsername(access_token);
        UserDetails user_details=user_details_service.loadUserByUsername(username);
        if(user_details==null) {
            System.out.println("user details found null");
            filterChain.doFilter(request, response);
            return;
        }        RefreshToken refresh_token_obj= refresh_token_service.getRefreshToken(username);
        //String refresh_token=refresh_token_obj.getRefresh_token();
        if(refresh_token_obj==null) {
            System.out.println("refresh token found null");
            filterChain.doFilter(request,response);
            return;
        }
        String refresh_token=refresh_token_obj.getRefresh_token();
        System.out.println("Refresh token "+refresh_token);
        if(token_service.isValidToken(access_token) && token_service.isValidToken(refresh_token)) {
            System.out.println("Setting security context");
            setSecurityContext(access_token, request);
        }
            else if(!token_service.isValidToken(access_token) && token_service.isValidToken(refresh_token)) {
            System.out.println("setting security context");
            List<String> roles=token_service.extractRoles(refresh_token);
            access_token=token_service.getAccessToken(username, roles);
            refresh_token=token_service.getRefreshToken(username, roles);
            refresh_token_obj.setRefresh_token(refresh_token);
            refresh_token_service.saveRefreshToken(refresh_token_obj);
            setSecurityContext(access_token,request);
        }
        else {
            filterChain.doFilter(request, response);
            return;
        }        filterChain.doFilter(request,response);
    }

    private List<SimpleGrantedAuthority> getAuthorities(List<String> roles) {
        List<SimpleGrantedAuthority> auth=new ArrayList<>();
        for(String role : roles)
            auth.add(new SimpleGrantedAuthority(role));
        return auth;
    }

    public void setSecurityContext(String access_token, HttpServletRequest request) {
        System.out.println("ENTERING SECURITY CONTEXT");
        List<SimpleGrantedAuthority> authorities=getAuthorities(token_service.extractRoles(access_token));
        UserDetails user_details=user_details_service.loadUserByUsername(token_service.extractUsername(access_token));
        UsernamePasswordAuthenticationToken auth_token=new UsernamePasswordAuthenticationToken(user_details,null,authorities);
        auth_token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth_token);
        System.out.println(SecurityContextHolder.getContext().getAuthentication()==null);
    }
}
