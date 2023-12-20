package www.learning.dtos;

import java.time.LocalDate;
import java.util.List;

import www.learning.models.UserModel;

public record UserResponseDTO(
    String username,
    String email,
    List<String> roles,
    LocalDate createdAt
) {
    public static UserResponseDTO from(UserModel model) {
        return new UserResponseDTO(
            model.getUsername(),
            model.getEmail(),
            model.getRoles(),
            model.getCreatedAt()
        );
    } 
}
