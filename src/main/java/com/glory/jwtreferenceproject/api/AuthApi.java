package com.glory.jwtreferenceproject.api;

import com.glory.jwtreferenceproject.jwt.JwtTokenUtil;
import com.glory.jwtreferenceproject.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthApi {
    private final AuthenticationManager authManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthApi(AuthenticationManager authManager, JwtTokenUtil jwtTokenUtil) {
        this.authManager = authManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try{
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(), accessToken);

            return ResponseEntity.ok(response);
        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
