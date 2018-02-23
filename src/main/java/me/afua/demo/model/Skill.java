package me.afua.demo.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String skillName;

    @NotEmpty
    private String proficiency;

    @ManyToMany(mappedBy="mySkills")
    private List<AppUser> people;

    @ManyToMany(mappedBy="jobSkills")
    private List<AppJob> skillsforJob;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getProficiency() {
        return proficiency;
    }

    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
    }

    public List<AppUser> getPeople() {
        return people;
    }

    public void setPeople(List<AppUser> people) {
        this.people = people;
    }

    public List<AppJob> getSkillsforJob() {
        return skillsforJob;
    }

    public void setSkillsforJob(List<AppJob> skillsforJob) {
        this.skillsforJob = skillsforJob;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skillName='" + skillName + '\'' +
                ", proficiency='" + proficiency + '\'' +
                '}';
    }


}
