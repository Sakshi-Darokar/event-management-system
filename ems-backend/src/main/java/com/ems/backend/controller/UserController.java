package com.ems.backend.controller;

import com.ems.backend.dto.UpdateUserRequest;
import com.ems.backend.dto.UserProfileResponse;
import com.ems.backend.entity.Event;
import com.ems.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> me(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(userService.getProfileByEmail(email));
    }


    @PutMapping("/update")
    public ResponseEntity<UserProfileResponse> update(
            Authentication authentication,
            @RequestBody UpdateUserRequest request
    ) {
        String email = authentication.getName();
        return ResponseEntity.ok(userService.updateProfileByEmail(email, request));
    }


    @GetMapping("/my-events")
    public ResponseEntity<List<Event>> myEvents(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(userService.getMyEventsByEmail(email));
    }
}
