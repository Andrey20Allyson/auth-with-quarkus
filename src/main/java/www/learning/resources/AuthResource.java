package www.learning.resources;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Encoding;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
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
@Tag(name = "Authentication", description = "The JWT Auth resource")
public class AuthResource {
    @Inject
    AuthService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "If username and password is correct returns the JWT token",
            content = @Content(
                mediaType = MediaType.TEXT_PLAIN,
                encoding = @Encoding(
                    contentType = "base64"
                )
            )
        ),
        @APIResponse(
            responseCode = "400",
            description = "IF request body is invalid"
        ),
        @APIResponse(
            responseCode = "401",
            description = "If username or password is incorrect."
        ),
    })
    @Operation(summary = "the sign-in end point, verify if auth data matches and returns a JWT token")
    @Path("/sign-in")
    public Response signIn(@Valid SignInDTO body) {
        String jwtToken = this.service.signIn(body);

        return Response
            .ok(jwtToken)
            .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "the sign-up end point, creates a account and returns a JWT token")
    @APIResponses({
        @APIResponse(
            responseCode = "201",
            description = "If sign-up has successful and returns a JWT token",
            content = @Content(
                mediaType = MediaType.TEXT_PLAIN
            )
        ),
        @APIResponse(
            responseCode = "409",
            description = "If username already exists"
        )
    })
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
    @Operation(summary = "Returns all users registred")
    @Path("/users")
    public Response getUsers() {
        List<UserResponseDTO> users = this.service.getUsers();

        return Response
            .ok(users)
            .build();
    }
}
