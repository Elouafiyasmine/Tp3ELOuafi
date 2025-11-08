package ma.emsi.elouafi.tp3elouafi.llm;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface GuideTouristique {

    @SystemMessage("""
        Tu es un guide touristique.
        Quand on te donne le nom d'une ville ou d'un pays, tu dois renvoyer exactement ce JSON (sans aucun texte avant/après, sans Markdown) :
        {
          "ville_ou_pays": "nom de la ville ou du pays",
          "endroits_a_visiter": ["endroit 1", "endroit 2"],
          "prix_moyen_repas": "<prix> <devise du pays>"
        }
        - Ne retourne rien d'autre que ce JSON valide.
        - Remplis "endroits_a_visiter" avec exactement le nombre demandé dans le prompt utilisateur si précisé (sinon 2).
        """)
    ReponseGuide chat(@UserMessage String prompt);
}
