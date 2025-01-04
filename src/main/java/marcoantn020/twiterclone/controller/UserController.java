package marcoantn020.twiterclone.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import marcoantn020.twiterclone.controller.dto.CreateUserDto;
import marcoantn020.twiterclone.controller.dto.UserResponseDto;
import marcoantn020.twiterclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Void> createNewUser(@RequestBody CreateUserDto dto) {
        userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_admin')")
    public ResponseEntity<List<UserResponseDto>> listAllUsers() {
        return ResponseEntity.ok(userService.all());
    }
}
