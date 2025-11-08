package ma.emsi.elouafi.tp3elouafi.llm;

import dev.langchain4j.service.SystemMessage;

/**
 * On garde une méthode qui prend un "prompt" unique.
 * Le prompt sera formaté côté LlmClient pour injecter (lieu, nb).
 * Le retour est ReponseGuide : LangChain4j essaiera de parser le JSON du modèle.
 */
public interface GuideTouristique {

    @SystemMessage("""
        Tu es un guide touristique.
        Rappels STRICTS de format :
        - Réponds UNIQUEMENT avec un JSON, sans texte avant/après, sans Markdown.
        - Le JSON DOIT avoir exactement cette structure :
          {
            "ville_ou_pays": "nom de la ville ou du pays",
            "endroits_a_visiter": ["endroit 1", "endroit 2", "..."],
            "prix_moyen_repas": "<prix> <devise du pays>"
          }
        - Pas d'autres clés. Pas de commentaires. Pas de backticks. Pas d'explications.
        """)
    ReponseGuide chat(String prompt);
}
