package me.afua.demo.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String image;

    @NotEmpty
    @Column(unique = true)
    private String username;

    @NotEmpty
    private String password;

    private String firstName;

    private String lastName;

    private String resumeSummary;

    @Email
    private String email;

    @CreationTimestamp
    Timestamp createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    //This needs to be instantiated in the construtor so you can use it to add and remove individual roles
    private Set<AppRole> roles;

    @ManyToMany
    private Set<Education> myEdus;

    @ManyToMany
    private Set <Experience> myExperience;

    @ManyToMany
    private Set <Skill> mySkills;

    public AppUser() {
        this.roles = new HashSet<>();
        this.myEdus = new HashSet<>();
        this.myExperience = new HashSet<>();
        this.mySkills = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Set<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppRole> roles) {
        this.roles = roles;
    }

    public void addRole(AppRole role)
    {
        this.roles.add(role);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getResumeSummary() {
        return resumeSummary;
    }

    public void setResumeSummary(String resumeSummary) {
        this.resumeSummary = resumeSummary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addEducation(Education edu)
    {
        this.myEdus.add(edu);
    }

    public void addSkill(Skill aSkill)
    {
        this.mySkills.add(aSkill);
    }

    public void addExperience(Experience exp)
    {
        this.myExperience.add(exp);
    }

    public Set<Education> getMyEdus() {
        return myEdus;
    }

    public void setMyEdus(Set<Education> myEdus) {
        this.myEdus = myEdus;
    }

    public Set<Experience> getMyExperience() {
        return myExperience;
    }

    public void setMyExperience(Set<Experience> myExperience) {
        this.myExperience = myExperience;
    }

    public Set<Skill> getMySkills() {
        return mySkills;
    }

    public void setMySkills(Set<Skill> mySkills) {
        this.mySkills = mySkills;
    }
}