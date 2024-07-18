package com.pavan.foodie.onlineordering.springsecurity.configuration;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**public class JwtTokenValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Add your JWT validation logic here
    	String jwt = request.getHeader(JwtConstant.JWT_HEADER);
    	if(jwt!= null) {
    		jwt = jwt.substring(7);
    		try {
    			SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
    			Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
    			String email=String.valueOf(claims.get("email"));

    			String authorities=String.valueOf((claims.get("authorities")));

//    			ROLE_CUSTOMER, ROLE_AMDIN

    			List<GrantedAuthority> auth= AuthorityUtils.commaSeparatedStringToAuthorityList (authorities);

    			Authentication authentication=new UsernamePasswordAuthenticationToken (email, null,auth);

    			SecurityContextHolder.getContext().setAuthentication(authentication);
    		}catch(Exception e) {
    			throw new BadCredentialsException("Invalid Token....");
    		}
    	}
        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}**/


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class JwtTokenValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Extract JWT from the request header
        String jwt = request.getHeader(JwtConstant.JWT_HEADER);
        
        if (jwt != null && jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7); // Remove the "Bearer " prefix
            try {
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
                Claims claims = Jwts.parser()
                                    .setSigningKey(key)
                                    .build()
                                    .parseClaimsJws(jwt)
                                    .getBody();

                String email = String.valueOf(claims.get("email"));
                String authorities = String.valueOf(claims.get("authorities"));

                // Convert the comma-separated string to a list of GrantedAuthority
                List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                // Create an Authentication object
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auth);

                // Set the authentication in the SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                throw new BadCredentialsException("Invalid Token", e);
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}

