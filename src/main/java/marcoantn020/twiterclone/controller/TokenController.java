package marcoantn020.twiterclone.controller;

import marcoantn020.twiterclone.controller.dto.LoginRequestDto;
import marcoantn020.twiterclone.controller.dto.ResponseTokenDto;
import marcoantn020.twiterclone.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<ResponseTokenDto> login(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(tokenService.generateToken(dto));
    }
}
