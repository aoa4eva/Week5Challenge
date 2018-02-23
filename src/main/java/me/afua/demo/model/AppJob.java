package me.afua.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AppJob {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String jobTitle;

    private String jobDescription;

    @ManyToOne
    private Organization jobOrg;

    @ManyToMany
    private Set<Skill> jobSkills;

    public AppJob() {
        this.jobSkills = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Organization getJobOrg() {
        return jobOrg;
    }

    public void setJobOrg(Organization jobOrg) {
        this.jobOrg = jobOrg;
    }

    public Set<Skill> getJobSkills() {
        return jobSkills;
    }

    public void setJobSkills(Set<Skill> jobSkills) {
        this.jobSkills = jobSkills;
    }

    public void addSkilltoJob(Skill aSkill)
    {
        this.jobSkills.add(aSkill);
    }




}
