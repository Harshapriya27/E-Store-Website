//package com.example.ecommerce.controller;
//
//import com.example.ecommerce.model.UserProfile;
//import com.example.ecommerce.service.UserProfileService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.*;
//import java.util.UUID;
//@Controller
//@RequestMapping("/user")
//public class ProfileController {
//
//    @Autowired
//    private UserProfileService userProfileService;
//
//    @GetMapping("/profile")
//    public String showProfile(Model model) {
//        model.addAttribute("profile", new UserProfile());
//        return "User/profile"; // Case-sensitive path
//    }
//
//    @PostMapping("/profile")
//    public String saveProfile(@ModelAttribute("profile") UserProfile profile,
//                              @RequestParam("imageFile") MultipartFile imageFile) {
//        // Save logic (can skip here if already done)
//        return "redirect:/user/profile"; // ✅ This is correct
//    }
//}

package com.example.ecommerce.controller;

import com.example.ecommerce.model.UserProfile;
import com.example.ecommerce.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class ProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/profile")
    public String showProfile(Model model) {
        UserProfile profile = userProfileService.getProfile();
        model.addAttribute("profile", profile != null ? profile : new UserProfile());
        return "User/profile";
    }

    @PostMapping("/profile")
    public String saveProfile(@RequestParam("fullName") String fullName,
                              @RequestParam("email") String email,
                              @RequestParam("phone") String phone,
                              @RequestParam("address") String address,
                              @RequestParam("city") String city,
                              @RequestParam("state") String state,
                              @RequestParam("pincode") String pincode,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              Model model) {

        UserProfile profile = new UserProfile();
        profile.setFullName(fullName);
        profile.setEmail(email);
        profile.setPhone(phone);
        profile.setAddress(address);
        profile.setCity(city);
        profile.setState(state);
        profile.setPincode(pincode);

        if (!imageFile.isEmpty()) {
            try {
                String uploadDir = "uploads/profiles/";
                String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
                Path uploadPath = Paths.get("src/main/resources/static/" + uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Files.copy(imageFile.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                profile.setProfileImage("/" + uploadDir + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        userProfileService.save(profile);
        model.addAttribute("profile", profile);
        model.addAttribute("success", "Profile saved successfully!");
        return "User/profile-saved";
    }
}
