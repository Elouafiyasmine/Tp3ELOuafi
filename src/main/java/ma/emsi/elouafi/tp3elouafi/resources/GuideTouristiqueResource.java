package ma.emsi.elouafi.tp3elouafi.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.emsi.elouafi.tp3elouafi.llm.LlmClient;
import ma.emsi.elouafi.tp3elouafi.llm.ReponseGuide;

@Path("/guide")
@Produces(MediaType.APPLICATION_JSON)
public class GuideTouristiqueResource {

    private static final LlmClient LLM = new LlmClient();

    // /api/guide/lieu/paris  (par défaut: 2 endroits)
    @GET
    @Path("lieu/{ville_ou_pays}")
    public Response getLieuDefaut(
            @PathParam("ville_ou_pays") String lieu,
            @QueryParam("nb") @DefaultValue("2") int nb
    ) {
        ReponseGuide rep = LLM.demander(lieu, nb);
        return Response.ok(rep).build();
    }

    // Variante: /api/guide/lieu/paris/4  -> 4 endroits
    @GET
    @Path("lieu/{ville_ou_pays}/{nb}")
    public Response getLieuNbChemin(
            @PathParam("ville_ou_pays") String lieu,
            @PathParam("nb") int nb
    ) {
        ReponseGuide rep = LLM.demander(lieu, nb);
        return Response.ok(rep).build();
    }
}
