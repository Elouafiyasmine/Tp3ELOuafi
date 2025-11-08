package ma.emsi.elouafi.tp3elouafi;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello-world")
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Path("personnes/{nom}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response helloPersonne(@PathParam("nom") String nom) {
        return Response.ok("hello," + nom).build();
    }
}
