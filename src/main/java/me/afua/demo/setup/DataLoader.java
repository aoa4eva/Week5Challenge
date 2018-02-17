package me.afua.demo.setup;

import me.afua.demo.model.AppRole;
import me.afua.demo.model.AppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//Remove the annotation after you have run this method once in a database that persists to storage.
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    AppRoleRepository roleRepo;

    @Override
    public void run(String... strings) throws Exception {

        //Add all data that should be in the database at the beginning of the application
        AppRole role = new AppRole();
        role.setRoleName("EMPLOYER");
        roleRepo.save(role);

        role = new AppRole();
        role.setRoleName("APPLICANT");
        roleRepo.save(role);

        role = new AppRole();
        role.setRoleName("ADMIN");
        roleRepo.save(role);

        //Add test data for users


    }
}
