package me.afua.demo.service;

import me.afua.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ResumeService {
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

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    JobRepository jobRepository;


    public AppUser findUser(String username){
        return userRepository.findAppUserByUsername(username);
    }

    public boolean hasRoles(AppUser user, ArrayList<AppRole> theRole)
    {
        /**
         * Returns true if the user has any of the roles in the given list.
         * This is also used to search for individual values.
         * See hasRole below.*/
        return userRepository.findAppUserByRolesIn(theRole)==null;
    }

    public boolean hasRole(AppUser user, String theRole)
    {
        /**
         * Searches for individual values by passing a single value list
         * */
        AppRole toFind = roleRepository.findAppRoleByRoleName(theRole);
        List searchFor = new ArrayList<>();
        searchFor.add(toFind);
        return  userRepository.findAppUserByRolesIn(searchFor)!=null;
    }

    /** Shortcut methdos for determining roles */
    public boolean isARecruiter(AppUser user)
    {
        return hasRole(user,"RECRUITER");
    }

    public boolean isAnEmployer(AppUser user)
    {
        return hasRole(user,"RECRUITER");
    }

    public boolean isAnApplicant(AppUser user)
    {
        return hasRole(user,"APPLICANT");
    }
}
