package ma.emsi.elouafi.tp3elouafi.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.emsi.elouafi.tp3elouafi.llm.LlmClient;
import ma.emsi.elouafi.tp3elouafi.llm.ReponseGuide;

@Path("/guide")
@Produces(MediaType.APPLICATION_JSON)
public class GuideTouristiqueResource {

    // Réutiliser un seul client (évite de reconstruire le modèle à chaque requête)
    private static final LlmClient LLM = new LlmClient();

    /** Variante Query Param : /api/guide/lieu/Paris?nb=4  (nb par défaut = 2) */
    @GET
    @Path("/lieu/{ville_ou_pays}")
    public Response getLieuAvecQuery(
            @PathParam("ville_ou_pays") String lieu,
            @QueryParam("nb") @DefaultValue("2") int nb
    ) {
        if (nb < 1) nb = 1;
        ReponseGuide out = LLM.demander(lieu, nb);
        return Response.ok(out).build();
    }

    /** Variante Path Param : /api/guide/lieu/Paris/4 */
    @GET
    @Path("/lieu/{ville_ou_pays}/{nb}")
    public Response getLieuAvecChemin(
            @PathParam("ville_ou_pays") String lieu,
            @PathParam("nb") int nb
    ) {
        if (nb < 1) nb = 1;
        ReponseGuide out = LLM.demander(lieu, nb);
        return Response.ok(out).build();
    }
}
