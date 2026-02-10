package com.ems.backend.controller;

import com.ems.backend.dto.UpdateUserRequest;
import com.ems.backend.dto.UserProfileResponse;
import com.ems.backend.entity.Event;
import com.ems.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // TEMP: email query se "current user" simulate
    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> me(@RequestParam String email) {
        return ResponseEntity.ok(userService.getProfileByEmail(email));
    }

    @PutMapping("/update")
    public ResponseEntity<UserProfileResponse> update(
            @RequestParam String email,
            @RequestBody UpdateUserRequest request
    ) {
        return ResponseEntity.ok(userService.updateProfileByEmail(email, request));
    }

    @GetMapping("/my-events")
    public ResponseEntity<List<Event>> myEvents(@RequestParam String email) {
        return ResponseEntity.ok(userService.getMyEventsByEmail(email));
    }
}
