package gr.hua.dit.ds.ds_lab_2024.controllers;


import gr.hua.dit.ds.ds_lab_2024.entities.Role;
import gr.hua.dit.ds.ds_lab_2024.entities.User;
import gr.hua.dit.ds.ds_lab_2024.service.ProfileService;
import gr.hua.dit.ds.ds_lab_2024.service.RoleService;
import gr.hua.dit.ds.ds_lab_2024.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("contact")
public class ContactController {

    private UserService userService;
    private ProfileService profileService;
    private RoleService roleService;



    public ContactController(UserService userService, ProfileService profileService, RoleService roleService) {
        this.userService = userService;
        this.profileService = profileService;
        this.roleService = roleService;
    }


    @GetMapping("")
    public String showAdmins(Model model) {

        Optional<Role> admin = roleService.getRole("ROLE_ADMIN");
        List<User> admins = userService.getUsersByRole(admin.get());
        model.addAttribute("admins", admins);
        return "contact/admins";
    }

}
