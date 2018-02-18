package me.afua.demo.controller;

import me.afua.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    ExperienceRepository experienceRepository;

    @RequestMapping("/")
    public String showIndex()
    {
        //return "Default home page <a href='/login'>Log in</a>";
        return "index";
    }

    @RequestMapping("/loggedin")
    public String loggedIn(Model model)
    {
        model.addAttribute("educationlist",educationRepository.findAll());
        return "loggedin";
       //return authentication.getName()+" Authorities: "+authentication.getAuthorities().toString()+"<a href='/roles'>Roles</a>";
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
    public String saveEducation(@Valid @ModelAttribute("educationItem") Education education, BindingResult result)
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

    //Reproduce the Education code for Experience

    @GetMapping("/experience")
    public String addExperience(Model model)
    {
        model.addAttribute("workexperience",new Experience());
        return "addexperience";
    }

    @PostMapping("/experience")
    public String saveExperience(@Valid @ModelAttribute("workexperience") Experience experience, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "addexperience";
        }
        experienceRepository.save(experience);
        return "redirect:/";
    }

    @GetMapping("/listexperience")
    public @ResponseBody String listExperience()
    {
        return experienceRepository.findAll().toString();
    }


    //UPDATE METHODS - ADDED LAST
    @PostMapping("/update/education")
    public String updateEducation(HttpServletRequest request, Model model)
    {
        System.out.println("Education ID:"+request.getParameter("id"));
        model.addAttribute("educationItem",educationRepository.findOne(new Long(request.getParameter("id"))));
        return "addeducation";
    }

    @PostMapping("/update/experience")
    public String updateExperience(HttpServletRequest request, Model model)
    {
        model.addAttribute("workexperience",experienceRepository.findOne(new Long(request.getParameter("id"))));
        return "addexperience";
    }

    @PostMapping("/update/skill")
    public String updateSkill(HttpServletRequest request, Model model)
    {
        model.addAttribute("aSkill",experienceRepository.findOne(new Long(request.getParameter("id"))));
        return "addskill";
    }




}
