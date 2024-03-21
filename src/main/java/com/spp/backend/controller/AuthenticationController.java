package com.spp.backend.controller;

import com.spp.backend.dto.AuthenticationRequest;
import com.spp.backend.dto.RegisterRequest;
import com.spp.backend.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reddit/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserServiceImpl userService;
    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request
    ) {
        return userService.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(userService.authenticate(request));
    }
}

