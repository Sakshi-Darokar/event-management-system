package com.ems.backend.service;

import com.ems.backend.dto.UpdateUserRequest;
import com.ems.backend.dto.UserProfileResponse;
import com.ems.backend.entity.Event;

import java.util.List;

public interface UserService {
    UserProfileResponse getProfileByEmail(String email);
    UserProfileResponse updateProfileByEmail(String email, UpdateUserRequest request);
    List<Event> getMyEventsByEmail(String email);
}
