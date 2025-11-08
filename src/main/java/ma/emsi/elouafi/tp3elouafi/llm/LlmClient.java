package ma.emsi.elouafi.tp3elouafi.llm;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;

public class LlmClient {
    private final GuideTouristique guideTouristique;

    public LlmClient() {
        String key = System.getenv("GEMINI_KEY"); // Assure-toi que cette variable d'env existe côté Payara
        if (key == null || key.isEmpty()) {
            System.err.println("ERREUR: La clé GEMINI n'est pas définie!");
            throw new RuntimeException("La clé API GEMINI n'est pas configurée (GEMINI_KEY)");
        }

        ChatModel chatModel = GoogleAiGeminiChatModel.builder()
                .apiKey(key)
                .modelName("gemini-2.5-flash") // Si ta région/compte ne supporte pas 2.5, mets "gemini-1.5-flash"
                .temperature(0.2)
                .build();

        this.guideTouristique = AiServices.builder(GuideTouristique.class)
                .chatModel(chatModel)
                .build();
    }

    /** nbEndroits = nombre exact demandé (2 par défaut) */
    public ReponseGuide demander(String lieu, int nbEndroits) {
        String prompt = """
                Lieu: %s
                Exige exactement %d endroits dans "endroits_a_visiter".
                Réponds UNIQUEMENT avec le JSON demandé (aucun texte autour).
                """.formatted(lieu, nbEndroits <= 0 ? 2 : nbEndroits);
        return guideTouristique.chat(prompt);
    }
}
