package ma.emsi.elouafi.tp3elouafi.llm;

import java.util.List;

// POJO que LangChain4j va remplir à partir du JSON renvoyé par le modèle
public class ReponseGuide {
    public String ville_ou_pays;
    public List<String> endroits_a_visiter;
    public String prix_moyen_repas;

    // (facultatif) getters/setters si tu préfères
}
