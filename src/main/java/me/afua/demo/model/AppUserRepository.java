package me.afua.demo.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findAppUserByUsername(String username);
    AppUser findAppUserByRolesInAndUsername(List<AppRole> roles,String username);
}
