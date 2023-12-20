package www.learning.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class UsernameConflictException extends WebApplicationException {
    public UsernameConflictException(String username) {
        super("Username \"%s\" is already in use!".formatted(username), Response.Status.CONFLICT);
    }
}
