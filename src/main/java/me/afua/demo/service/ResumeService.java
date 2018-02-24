package me.afua.demo.service;

import me.afua.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private AppUser currentUser;


    public AppUser findUser(String username){
        return userRepository.findAppUserByUsername(username);
    }

    public boolean hasRoles(AppUser user, ArrayList<AppRole> theRole)
    {
        /**
         * Returns true if the user has any of the roles in the given list.
         * This is also used to search for individual values.
         * See hasRole below.*/
        return userRepository.findAppUserByRolesInAndUsername(theRole,user.getUsername())!=null;
    }

    public boolean hasRole(AppUser user, String theRole)
    {
        /**
         * Searches for individual values by passing a single value list
         * */
        AppRole toFind = roleRepository.findAppRoleByRoleName(theRole);
       ArrayList <AppRole> searchFor = new ArrayList<>();
        searchFor.add(toFind);
        return  hasRoles(user,searchFor);
    }

    /** Shortcut methdos for determining roles */
    public boolean isARecruiter(AppUser user)
    {
        return hasRole(user,"RECRUITER");
    }

    public boolean isAnEmployer(AppUser user)
    {
        return hasRole(user,"EMPLOYER");
    }

    public boolean isAnApplicant(AppUser user)
    {
        return hasRole(user,"APPLICANT");
    }

    /*Use current user's authentication to a user object */
    public AppUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Authentication auth) {

        this.currentUser = userRepository.findAppUserByUsername(auth.getName());
    }

    public ArrayList <String> getSkillNames(Set<Skill> theseSkills)
    {
        ArrayList <String> theseSkillNames = new ArrayList<>();
        for(Skill eachSkill:theseSkills)
        {
            theseSkillNames.add(eachSkill.getSkillName());
        }
        System.out.println("Selected kills:"+theseSkillNames.toString());
        return theseSkillNames;

    }
}
