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
    public String showIndex(Model model, Authentication auth)
    {
        model.addAttribute("educationlist",educationRepository.findAll());
        model.addAttribute("skilllist",skillRepository.findAll());
        model.addAttribute("experiencelist",experienceRepository.findAll());
        if(auth!=null)
        {
            System.out.println(auth.getName()+" authorities:"+auth.getAuthorities().toString());
            model.addAttribute("person",userRepository.findAppUserByUsername(auth.getName()));
        }
        else
            model.addAttribute("inMemory",true);
        return "index";
    }

    @RequestMapping("/loggedin")
    public String loggedIn(Model model)
    {
        model.addAttribute("educationlist",educationRepository.findAll());
        model.addAttribute("skilllist",skillRepository.findAll());
        model.addAttribute("experiencelist",experienceRepository.findAll());
        return "loggedin";
       //return authentication.getName()+" Authorities: "+authentication.getAuthorities().toString()+"<a href='/roles'>Roles</a>";
    }

    @RequestMapping("/showresume")
    public String showResume(Model model,Authentication auth)
    {
        model.addAttribute("educationlist",educationRepository.findAll());
        model.addAttribute("skilllist",skillRepository.findAll());
        model.addAttribute("experiencelist",experienceRepository.findAll());
        if(auth!=null)
        {
            System.out.println(auth.getName()+" authorities:"+auth.getAuthorities().toString());
            model.addAttribute("person",userRepository.findAppUserByUsername(auth.getName()));
        }
        else
            model.addAttribute("inMemory",true);
        return "showresume";
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
    public String saveUser(@Valid @ModelAttribute("newuser") AppUser user, BindingResult result,HttpServletRequest request)
    {
        if(result.hasErrors())
        {
            return "register";
        }


        if(request.getParameter("isEmployer")!=null)
            user.addRole(roleRepository.findAppRoleByRoleName("EMPLOYER"));
        else
            user.addRole(roleRepository.findAppRoleByRoleName("APPLICANT"));
        userRepository.save(user);
        return "redirect:/loggedin";
    }

    @GetMapping("/profile")
    public String showProfile(Model model, Authentication authentication) {

        AppUser getCurrentUser = userRepository.findAppUserByUsername(authentication.getName());
        if (getCurrentUser == null)
            model.addAttribute("inmemory", true);
        else
        {       model.addAttribute("inmemory", false);
                model.addAttribute("currentuser", getCurrentUser);
        }
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
        return "redirect:/loggedin";
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
        return "redirect:/loggedin";
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
        return "redirect:/loggedin";
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
        return "redirect:/loggedin";
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
        model.addAttribute("aSkill",skillRepository.findOne(new Long(request.getParameter("id"))));
        return "addskill";
    }

    @RequestMapping("/references")
    public String showReferences()
    {
        return "references";
    }





}
