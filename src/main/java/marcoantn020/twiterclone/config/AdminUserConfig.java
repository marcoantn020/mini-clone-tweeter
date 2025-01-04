package marcoantn020.twiterclone.config;

import marcoantn020.twiterclone.entity.Role;
import marcoantn020.twiterclone.entity.User;
import marcoantn020.twiterclone.repository.RoleRepository;
import marcoantn020.twiterclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());
        var userAdmin = userRepository.findByUsername("admin");
        userAdmin.ifPresentOrElse(
                admin -> {
                    System.out.println("Admin já existe");
                },
                () -> {
                    var user = new User(
                            "admin",
                            bCryptPasswordEncoder.encode("123"),
                            Set.of(roleAdmin)
                    );
                    userRepository.save(user);
                }
        );



    }
}
