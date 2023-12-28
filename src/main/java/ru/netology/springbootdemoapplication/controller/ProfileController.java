package ru.netology.springbootdemoapplication.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.springbootdemoapplication.profile.SystemProfile;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/")
public class ProfileController {
    private final SystemProfile profile;

    public ProfileController(SystemProfile profile) {
        this.profile = profile;
    }

    @GetMapping("profile")
    public String getProfile() {
        return profile.getProfile();
    }

    @GetMapping("hello")
    public String getHello() {
        return "Hello";
    }

    @GetMapping("security")
    @Secured("ROLE_READ")
    public String getSecurity() {
        return "security";
    }

    @GetMapping("/roles")
    @RolesAllowed("ROLE_WRITE")
    public String getRoles() {
        return "roles";
    }


    @GetMapping("/preauthorize")
    @PreAuthorize("hasRole('ROLE_WRITE')or hasRole('ROLE_DELETE')")
    public String getPreAuthorize() {
        return "preauthorize";
    }

    @GetMapping("/username")
    @PreAuthorize("#username == authentication.principal.username")
    public String getUsername(@RequestParam String username) {
        return username + " welcome";
    }
}

// http://localhost:8081/username?username=Alex