package ma.emsi.elouafi.tp3elouafi;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import jakarta.ws.rs.PathParam;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
    @GET
    @Path("personnes/{nom}")
    @Produces("text/plain")
    public String helloPersonne(@PathParam("nom") String nom) {
        return "Hello, " + nom + "!";
    }
}
