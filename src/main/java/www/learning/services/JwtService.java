package www.learning.services;

import java.util.HashSet;
import java.util.Set;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;
import www.learning.models.UserModel;

@Singleton
public class JwtService {
    public String tokenFrom(UserModel user) {
        Set<String> roleSet = new HashSet<>(user.getRoles());

        return Jwt
            .issuer("auth-app")
            .subject("auth-app")
            .preferredUserName(user.getUsername())
            .groups(roleSet)
            .sign();
    }
}
