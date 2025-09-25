package com.edforce.minibankapplication.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edforce.minibankapplication.dto.AuthRequest;
import com.edforce.minibankapplication.dto.AuthResponse;
import com.edforce.minibankapplication.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
    	//add proper authentication logic here. this is dummy data
        if ("admin".equals(req.getUsername()) && "password".equals(req.getPassword())) {
            String accessToken = jwtUtil.generateAccessToken(req.getUsername());
            String refreshToken = jwtUtil.generateRefreshToken(req.getUsername());
            return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
        }
        return ResponseEntity.status(401).build();
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody AuthResponse req) {
        String refreshToken = req.getRefreshToken();
        if (jwtUtil.validateToken(refreshToken) && jwtUtil.isRefreshToken(refreshToken)) {
            String username = jwtUtil.extractUsername(refreshToken);
            String newAccessToken = jwtUtil.generateAccessToken(username);
            String newRefreshToken = jwtUtil.generateRefreshToken(username);
            return ResponseEntity.ok(new AuthResponse(newAccessToken, newRefreshToken));
        }
        return ResponseEntity.status(401).build();
    }
}
