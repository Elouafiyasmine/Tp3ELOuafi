package ma.emsi.elouafi.tp3elouafi.llm;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;

public class LlmClient {
    private final GuideTouristique guideTouristique;

    public LlmClient() {
        String key = System.getenv("GEMINI_KEY");
        if (key == null || key.isEmpty()) {
            System.err.println("ERREUR: La clé GEMINI n'est pas définie!");
            throw new RuntimeException("La clé API GEMINI n'est pas configurée");
        }

        ChatModel chatModel = GoogleAiGeminiChatModel.builder()
                .apiKey(key)
                .modelName("gemini-2.5-flash")
                .temperature(0.2)
                .build();

        this.guideTouristique = AiServices.builder(GuideTouristique.class)
                .chatModel(chatModel)
                .build();
    }

    /** Construit un prompt strict qui impose EXACTEMENT nb endroits. */
    public ReponseGuide demander(String lieu, int nb) {
        String prompt = """
                Donne EXACTEMENT %d endroits à visiter pour : %s
                - ville_ou_pays doit être "%s"
                - "endroits_a_visiter" doit contenir EXACTEMENT %d éléments (pas plus, pas moins)
                - "prix_moyen_repas" doit être un prix moyen local suivi de la devise (ex: "20 EUR", "70 MAD")
                Rappels : Réponds UNIQUEMENT avec le JSON strict demandé (aucun texte autour).
                """.formatted(nb, lieu, lieu, nb);

        return guideTouristique.chat(prompt);
    }
}
