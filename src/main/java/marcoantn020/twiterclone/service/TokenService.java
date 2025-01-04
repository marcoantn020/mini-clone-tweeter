package marcoantn020.twiterclone.service;

import marcoantn020.twiterclone.controller.dto.LoginRequestDto;
import marcoantn020.twiterclone.controller.dto.ResponseTokenDto;
import marcoantn020.twiterclone.entity.Role;
import marcoantn020.twiterclone.entity.User;
import marcoantn020.twiterclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class TokenService {

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseTokenDto generateToken(LoginRequestDto dto) {
        var user = getUser(dto.username());
        passwordIsCorrect(dto.password(), user);

        var now = Instant.now();
        var expiredIn = 30000L;
        var scopes = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("CloneTweet")
                .subject(user.getUserId().toString())
                .expiresAt(now.plusSeconds(expiredIn))
                .issuedAt(now)
                .claim("scope",scopes)
                .build();

        var jwtToken = jwtEncoder
                .encode(JwtEncoderParameters.from(claims))
                .getTokenValue();

        return new ResponseTokenDto(jwtToken,expiredIn);
    }

    private User getUser(String username) {
        var user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new BadCredentialsException("Usuário e/ou senha inválido");
        }
        return user.get();
    }

    private void passwordIsCorrect(String password, User user) {
        if (!user.passwordIsValid(password,bCryptPasswordEncoder)) {
            throw new BadCredentialsException("Usuário e/ou senha inválido");
        }
    }
}
