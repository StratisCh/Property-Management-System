package gr.hua.dit.ds.ds_lab_2024.controllers;

import gr.hua.dit.ds.ds_lab_2024.entities.Profile;
import gr.hua.dit.ds.ds_lab_2024.entities.Role;
import gr.hua.dit.ds.ds_lab_2024.entities.User;
import gr.hua.dit.ds.ds_lab_2024.entities.enums.Status;
import gr.hua.dit.ds.ds_lab_2024.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("profile")
public class ProfileController {

    private final UserService userService;
    private ProfileService profileService;
    private ListingService listingService;
    private OfferService offerService;
    private RoleService roleService;

    public ProfileController(ProfileService profileService, ListingService listingService, OfferService offerService, UserService userService, RoleService roleService) {
        this.profileService = profileService;
        this.listingService = listingService;
        this.offerService = offerService;
        this.userService = userService;
        this.roleService = roleService;
    }

    public Profile getLoggedInUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        Profile profile = profileService.getProfileByUserId(user.getUserId());
        return profile;
    }


    @Secured("ROLE_USER")
    @GetMapping("")
    public String showProfile(Model model) {
        Profile profile = getLoggedInUserProfile();

         if (profile.getFirstName() == null) {

             model.addAttribute("roles", roleService.getAllRolesAsSet());
             model.addAttribute("profile",profile);
             return "profile/new";
         }
        model.addAttribute("profile", profile);
        return "profile/profile";
    }

    @Secured("ROLE_USER")
    @GetMapping("/new")
    public String updateProfile(Model model) {

        Profile profile = getLoggedInUserProfile();

        model.addAttribute("roles", roleService.getAllRolesAsSet());
        model.addAttribute("profile", profile);
        return "profile/new";
    }



    @Secured("ROLE_USER")
    @PostMapping("/new")
    public String saveProfile(@ModelAttribute("profile") Profile profile,@ModelAttribute Role role, Model model) {

        Profile existingProfile = getLoggedInUserProfile();

        User user = existingProfile.getUser();


        Set<Role> roles = user.getRoles();
        Iterator<Role> iterator = roles.iterator();

        while (iterator.hasNext()) {
            Role r = iterator.next();

            if (role.getName().equals("ROLE_OWNER") && r.getName().equals("ROLE_TENANT")) {
                iterator.remove();
            }

            if (role.getName().equals("ROLE_TENANT") && r.getName().equals("ROLE_OWNER")) {
                iterator.remove();
            }
        }


        if (role.getName().equals("ROLE_TENANT")) {
            user.setStatus(Status.PENDING);
        }

        if (role.getName().equals("ROLE_OWNER")) {
            user.setStatus(Status.ACCEPTED);
            user.getRoles().add(role);
        }

        userService.updateUser(user);

        existingProfile.setFirstName(profile.getFirstName());
        existingProfile.setLastName(profile.getLastName());
        existingProfile.setDescription(profile.getDescription());

        profileService.saveProfile(existingProfile);

        //model.addAttribute("profile", existingProfile);
        //return "profile/profile";
        return "auth/login";
    }


    @Secured("ROLE_USER")
    @GetMapping("/offers")
    public String showOffers(Model model) {
        Profile profile = getLoggedInUserProfile();
        model.addAttribute("offers",offerService.getProfileOffers(profile));
        return "offer/offers";
    }

}
