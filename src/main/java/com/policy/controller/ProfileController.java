package com.policy.controller;

import com.policy.bean.AddProfileRequest;
import com.policy.error.DuplicateResultException;
import com.policy.error.NotFoundException;
import com.policy.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/profiles")

public class ProfileController {

    public final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
    public static String CREATED_BY_QUARTZ = "Transfer";

    @PostMapping("/add")
    public ResponseEntity<?> addProfile(@Valid @RequestBody AddProfileRequest addProfileRequest) {
        try {
            profileService.addProfile(addProfileRequest, CREATED_BY_QUARTZ);
            return ResponseEntity.ok().body("Profile added successfully");
        } catch (NotFoundException | DuplicateResultException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody AddProfileRequest addProfileRequest) {
        try {
            profileService.updateProfile(addProfileRequest, CREATED_BY_QUARTZ);
            return ResponseEntity.ok().body("Profile updated successfully");
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
        }
    }


}
