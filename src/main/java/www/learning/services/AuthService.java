package www.learning.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.UnauthorizedException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import www.learning.dtos.SignInDTO;
import www.learning.dtos.SignUpDTO;
import www.learning.dtos.UserResponseDTO;
import www.learning.exceptions.UsernameConflictException;
import www.learning.models.UserModel;
import www.learning.repositories.UserRepository;

@Transactional
@ApplicationScoped
public class AuthService {
    private final String SIGN_IN_ERROR_MESSEGE = "Incorrect user or password!";

    @Inject
    JwtService jwtService;

    @Inject
    UserRepository repository;

    public String signIn(SignInDTO data) {
        Optional<UserModel> optionalUser = this.repository.findByUsername(data.getUsername());

        if (optionalUser.isEmpty()) {
            throw new UnauthorizedException(this.SIGN_IN_ERROR_MESSEGE);
        }

        UserModel user = optionalUser.get();
        boolean matches = BcryptUtil.matches(data.getPassword(), user.getPasswordHash());

        if (!matches) {
            throw new UnauthorizedException(this.SIGN_IN_ERROR_MESSEGE);
        }

        return this.jwtService.tokenFrom(user);
    }

    public String signUp(SignUpDTO data) {
        if (this.repository.isUsernameInUse(data.getUsername())) {
            throw new UsernameConflictException(data.getUsername());
        }

        UserModel user = new UserModel();

        user.setUsername(data.getUsername());
        user.setEmail(data.getEmail());
        user.setRoles(Arrays.asList("user"));
        user.setCreatedAt(LocalDateTime.now());

        String hashedPassword = BcryptUtil.bcryptHash(data.getPassword());

        user.setPasswordHash(hashedPassword);

        this.repository.persist(user);

        return this.jwtService.tokenFrom(user);
    }

    public List<UserResponseDTO> getUsers() {
        return this.repository
                .streamAll()
                .map(model -> UserResponseDTO.from(model))
                .toList();
    }
}
