package gr.hua.dit.ds.ds_lab_2024.controllers;

import gr.hua.dit.ds.ds_lab_2024.entities.Profile;
import gr.hua.dit.ds.ds_lab_2024.entities.Role;
import gr.hua.dit.ds.ds_lab_2024.entities.User;
import gr.hua.dit.ds.ds_lab_2024.entities.enums.Status;
import gr.hua.dit.ds.ds_lab_2024.repositories.RoleRepository;
import gr.hua.dit.ds.ds_lab_2024.service.ProfileService;
import gr.hua.dit.ds.ds_lab_2024.service.RoleService;
import gr.hua.dit.ds.ds_lab_2024.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {

    private final ProfileController profileController;
    private final ProfileService profileService;
    private UserService userService;

    private RoleRepository roleRepository;
    private RoleService roleService;

    public UserController(UserService userService, RoleRepository roleRepository, ProfileController profileController, ProfileService profileService, RoleService roleService) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.profileController = profileController;
        this.profileService = profileService;
        this.roleService = roleService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "auth/register";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, Model model){
        System.out.println("Roles: "+user.getRoles());
        Profile profile = new Profile();

        profile.setUser(user);
        user.setProfile(profile);
        Long id = userService.saveUser(user);
        String message = "User '"+id+"' saved successfully !";
        model.addAttribute("msg", message);
        return "index";
    }

    @GetMapping("/users")
    public String showUsers(Model model){
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", roleRepository.findAll());
        return "auth/users";
    }

    @GetMapping("/user/{user_id}")
    public String showUser(@PathVariable Long user_id, Model model){
        model.addAttribute("user", userService.getUser(user_id));
        return "auth/user";
    }



    @GetMapping("/user/role/delete/{user_id}/{role_id}")
    public String deleteRolefromUser(@PathVariable Long user_id, @PathVariable Integer role_id, Model model){
        User user = (User) userService.getUser(user_id);
        Role role = roleRepository.findById(role_id).get();
        user.getRoles().remove(role);
        System.out.println("Roles: "+user.getRoles());
        userService.updateUser(user);
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", roleRepository.findAll());
        return "auth/users";

    }

    @GetMapping("/user/role/add/{user_id}/{role_id}")
    public String addRoletoUser(@PathVariable Long user_id, @PathVariable Integer role_id, Model model){
        User user = (User) userService.getUser(user_id);
        Role role = roleRepository.findById(role_id).get();
        user.getRoles().add(role);
        System.out.println("Roles: "+user.getRoles());
        userService.updateUser(user);
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", roleRepository.findAll());
        return "auth/users";

    }

    @GetMapping("/tenants")
    public String showPendingTenants(Model model){
        model.addAttribute("tenants",userService.getPendingUsers());
        return "auth/tenants";
    }

    @GetMapping("/tenants/tenant/{id}")
    public String examineTenant(@PathVariable Long id, Model model){
       User user = (User) userService.getUser(id);
       Profile profile = user.getProfile();
       model.addAttribute("profile", profile);
        return "auth/tenant";
    }


    @GetMapping("/tenant/accept/{id}")
    public String acceptTenant(@PathVariable Long id, Model model){
        Profile profile =profileService.getProfile(id);
        User user = profile.getUser();
        user.setStatus(Status.ACCEPTED);


        Optional<Role> role = roleService.getRole("ROLE_TENANT");
        user.getRoles().add(role.get());
        userService.updateUser(user);

        model.addAttribute("tenants",userService.getPendingUsers());
        return"auth/tenants";
    }

    @GetMapping("/tenant/decline/{id}")
    public String declineTenant(@PathVariable Long id, Model model){
        Profile profile =profileService.getProfile(id);
        User user = profile.getUser();
        user.setStatus(Status.DECLINED);
        userService.updateUser(user);

        model.addAttribute("tenants",userService.getPendingUsers());
        return"auth/tenants";
    }




    //TODO FILTER BY AREA AND TYPE

    

}