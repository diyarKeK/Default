package org.diyar.diyar.rest_controllers;

import org.diyar.diyar.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class VerifyApiController {

    @GetMapping("/verify")
    public ResponseEntity<?> verify(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(new HashMap<String, Object>() {{
            put("email", user.getEmail());
            put("name", user.getName());
            put("roles", user.getAuthorities());
        }});
    }
}
