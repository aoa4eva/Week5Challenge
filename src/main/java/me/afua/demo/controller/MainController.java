package me.afua.demo.controller;

import me.afua.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    AppRoleRepository roleRepository;

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    EducationRepository educationRepository;

    @RequestMapping("/")
    public @ResponseBody String showIndex()
    {
        return "Default home page <a href='/login'>Log in</a>";
    }

    @RequestMapping("/loggedin")
    public @ResponseBody String loggedIn(Authentication authentication)
    {
       return authentication.getName()+" Authorities: "+authentication.getAuthorities().toString()+"<a href='/roles'>Roles</a>";
    }

    @RequestMapping("/roles")
    public @ResponseBody String showRoles()
    {

        return "Roles available:"+roleRepository.findAll().toString();
    }

    @GetMapping("/register")
    public String registerUser(Model model)
    {
        model.addAttribute("newuser",new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@Valid @ModelAttribute("newuser") AppUser user, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "register";
        }
        user.addRole(roleRepository.findAppRoleByRoleName("APPLICANT"));
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String showProfile(Model model, Authentication authentication)
    {
        model.addAttribute("currentuser",userRepository.findAppUserByUsername(authentication.getName()));
        return "profile";
    }

    @PostMapping("/profile")
    public String saveProfile(@Valid @ModelAttribute("currentuser") AppUser user, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "profile";
        }
        userRepository.save(user);
        return "redirect:/";
    }

    //Replicate the profile code for Skill
    @GetMapping("/skill")
    public String addSkill(Model model)
    {
        model.addAttribute("aSkill",new Skill());
        return "addskill";
    }

    @PostMapping("/skill")
    public String saveSkill(@Valid @ModelAttribute("aSkill") Skill skill, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "addskill";
        }
        skillRepository.save(skill);
        return "redirect:/";
    }

    @GetMapping("/listskills")
    public @ResponseBody String listSkills()
    {
        return skillRepository.findAll().toString();
    }

    //Replicate the profile code for Skill
    @GetMapping("/education")
    public String addEducation(Model model)
    {
        model.addAttribute("educationItem",new Education());
        return "addeducation";
    }

    //Reproduce the Skill code for education
    @PostMapping("/education")
    public String saveEducation(@Valid @ModelAttribute("aSkill") Education education, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "addeducation";
        }
        educationRepository.save(education);
        return "redirect:/";
    }

    @GetMapping("/listeducation")
    public @ResponseBody String listEducation()
    {
        return educationRepository.findAll().toString();
    }
}
