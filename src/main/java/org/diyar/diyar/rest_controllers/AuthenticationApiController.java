package org.diyar.diyar.rest_controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.diyar.diyar.entities.AuthenticationResponse;
import org.diyar.diyar.entities.User;
import org.diyar.diyar.services.AuthenticationService;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationApiController {

    private final AuthenticationService authService;

    public AuthenticationApiController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User request, HttpServletResponse response) {
        AuthenticationResponse auth = authService.register(request);

        ResponseCookie cookie = ResponseCookie.from("jwt", auth.getToken())
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(24 * 60 * 60)
                .sameSite("Strict")
                .build();

        response.setHeader("Set-Cookie", cookie.toString());

        return ResponseEntity.ok("Registration successful");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User request, HttpServletResponse response) {
        AuthenticationResponse auth = authService.authenticate(request);

        ResponseCookie cookie = ResponseCookie.from("jwt", auth.getToken())
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(24 * 60 * 60)
                .sameSite("Strict")
                .build();

        response.setHeader("Set-Cookie", cookie.toString());

        return ResponseEntity.ok("Login successful");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("jwt", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();

        response.setHeader("Set-Cookie", cookie.toString());

        return ResponseEntity.ok("Logged out");
    }
}
