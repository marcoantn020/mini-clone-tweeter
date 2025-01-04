package marcoantn020.twiterclone.controller.dto;

import marcoantn020.twiterclone.entity.User;

import java.util.List;

public record UserResponseDto(String username, List<RoleDto> roles) {
    public static UserResponseDto toUserResponseDto(User user) {
        var roles = user.getRoles()
                .stream()
                .map(role -> new RoleDto(role.getName()))
                .toList();

        return new UserResponseDto(
                user.getUsername(),
                roles
        );
    }
}
