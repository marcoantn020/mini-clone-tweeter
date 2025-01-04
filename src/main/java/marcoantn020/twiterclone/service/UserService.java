package marcoantn020.twiterclone.service;

import marcoantn020.twiterclone.controller.dto.CreateUserDto;
import marcoantn020.twiterclone.controller.dto.UserResponseDto;
import marcoantn020.twiterclone.entity.Role;
import marcoantn020.twiterclone.entity.User;
import marcoantn020.twiterclone.repository.RoleRepository;
import marcoantn020.twiterclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void create(CreateUserDto dto) {
        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());
        var userDB = userRepository.findByUsername(dto.username());

        if (userDB.isPresent()) {
            throw new RuntimeException("Usuário já existe");
        }

        var user = new User(
                dto.username(),
                passwordEncoder.encode(dto.password()),
                Set.of(basicRole)
        );

        userRepository.save(user);
    }

    public List<UserResponseDto> all() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::toUserResponseDto)
                .toList();

    }
}
