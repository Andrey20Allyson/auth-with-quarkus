package www.learning.dtos;

import java.time.Instant;
import java.util.List;

import www.learning.models.UserModel;

public record UserResponseDTO(
    String username,
    String email,
    List<String> roles,
    Long createdAt
) {
    public static UserResponseDTO from(UserModel model) {
        return new UserResponseDTO(
            model.getUsername(),
            model.getEmail(),
            model.getRoles(),
            Instant.from(model.getCreatedAt()).toEpochMilli()
        );
    } 
}
