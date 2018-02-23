package me.afua.demo.model;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.HashSet;

public interface SkillRepository extends CrudRepository<Skill,Long> {
    Iterable <Skill> findAllBySkillNameContainingIgnoreCase(String searchstring);
}
