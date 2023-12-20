package www.learning.repositories;

import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import www.learning.models.UserModel;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserModel> {
    public boolean isUsernameInUse(String username) {
        return this.findByUsername(username).isPresent();
    }

    public Optional<UserModel> findByUsername(String username) {
        return Optional.ofNullable(this.find("username", username).firstResult());
    }
}
