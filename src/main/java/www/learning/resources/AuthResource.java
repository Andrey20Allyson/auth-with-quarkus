package www.learning.resources;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import www.learning.dtos.SignInDTO;
import www.learning.dtos.SignUpDTO;
import www.learning.dtos.UserResponseDTO;
import www.learning.services.AuthService;

@Path("/auth")
public class AuthResource {
    @Inject
    AuthService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sign-in")
    public Response signIn(@Valid SignInDTO body) {
        String jwtToken = this.service.signIn(body);
        
        return Response
        .ok(jwtToken)
        .build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sign-up")
    public Response signUp(@Valid SignUpDTO body) {
        String jwtToken = this.service.signUp(body);
        
        return Response
            .status(Response.Status.CREATED)
            .entity(jwtToken)
            .build();
            
    }

    @GET
    @RolesAllowed("user")
    @Path("/users")
    public Response getUsers() {
        List<UserResponseDTO> users = this.service.getUsers();

        return Response
            .ok(users)
            .build();
    }
}
