package com.pavan.foodie.onlineordering.springsecurity.controller;

import com.pavan.foodie.onlineordering.entity.cart.Cart;
import com.pavan.foodie.onlineordering.entity.user.UserEntity;
import com.pavan.foodie.onlineordering.entity.user.UserRole;
import com.pavan.foodie.onlineordering.repository.CartRepository;
import com.pavan.foodie.onlineordering.repository.UserRepository;
import com.pavan.foodie.onlineordering.request.LoginRequest;
import com.pavan.foodie.onlineordering.response.AuthResponse;
import com.pavan.foodie.onlineordering.springsecurity.configuration.JwtProvider;
import com.pavan.foodie.onlineordering.springsecurity.service.CustomerUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody UserEntity user) {
        try {
            UserEntity isEmailExist = userRepository.findByEmail(user.getEmail());
            if (isEmailExist != null) {
                throw new Exception("Email is already used with another account");
            }

            UserEntity createdUser = new UserEntity();
            createdUser.setEmail(user.getEmail());
            createdUser.setFirstName(user.getFirstName());
            createdUser.setRoles(user.getRoles());
            createdUser.setPassword(passwordEncoder.encode(user.getPassword()));

            UserEntity savedUser = userRepository.save(createdUser);

            // Create a cart for the user
            Cart cart = new Cart();
            cart.setCustomer(savedUser);
            cartRepository.save(cart);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    user.getEmail(),
                    user.getPassword()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtProvider.generateToken(authentication);

            AuthResponse authResponse = new AuthResponse();
            authResponse.setJwt(jwt);
            authResponse.setMessage("Registration successful");
            authResponse.setRole(savedUser.getRoles());

            return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest req) {
        String username = req.getEmail();
        String password = req.getPassword();

        try {
            Authentication authentication = authenticate(username, password);

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

            String jwt = jwtProvider.generateToken(authentication);

            AuthResponse authResponse = new AuthResponse();
            authResponse.setJwt(jwt);
            authResponse.setMessage("Login success");
            authResponse.setRole(UserRole.valueOf(role));

            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username...");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password...");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
