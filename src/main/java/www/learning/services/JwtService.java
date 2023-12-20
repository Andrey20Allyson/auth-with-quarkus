package www.learning.services;

import java.util.HashSet;
import java.util.Set;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;
import www.learning.models.UserModel;

@Singleton
public class JwtService {
    public final double TOKEN_LIFETIME_IN_MINUTES = 5.;

    public String tokenFrom(UserModel user) {
        Set<String> roleSet = new HashSet<>(user.getRoles());

        long expiresAt = System.currentTimeMillis() + (long) (this.TOKEN_LIFETIME_IN_MINUTES * 60. * 1000.);

        final String token = Jwt.issuer("auth-app")
            .subject("auth-app")
            .preferredUserName(user.getUsername())
            .groups(roleSet)
            .expiresAt(expiresAt)
            .sign();

        return token;
    }
}
