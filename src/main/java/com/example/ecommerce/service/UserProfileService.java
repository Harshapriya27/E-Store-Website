package com.example.ecommerce.service;

import com.example.ecommerce.model.UserProfile;
import com.example.ecommerce.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    private UserProfile savedProfile; // This holds the saved data temporarily

    public Optional<UserProfile> findByEmail(String email) {
        return userProfileRepository.findByEmail(email);
    }

    public UserProfile save(UserProfile profile) {
        return userProfileRepository.save(profile);
    }

//    public void save(UserProfile profile) {
//        this.savedProfile = profile;
//    }

    public UserProfile getProfile() {
        return savedProfile;
    }

}
