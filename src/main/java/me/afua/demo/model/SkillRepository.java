package me.afua.demo.model;

import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;

public interface SkillRepository extends CrudRepository<Skill,Long> {
    Iterable<Skill> findSkillBySkillNameContainingIgnoreCase(String search);
    Iterable <Skill> findAllBySkillNameContainingIgnoreCase(HashSet<String> skillnames);

}
