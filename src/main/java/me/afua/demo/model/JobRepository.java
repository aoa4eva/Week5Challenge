package me.afua.demo.model;

import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;

public interface JobRepository extends CrudRepository<AppJob,Long>{
    HashSet<AppJob> findAppJobsByJobSkillsIn(HashSet <Skill> mySkills);
}
