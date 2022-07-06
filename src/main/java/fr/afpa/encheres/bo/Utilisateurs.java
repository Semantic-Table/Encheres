package fr.afpa.encheres.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Utilisateurs {
    private int no_utilisateur;
    private String pseudo;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String rue;
    private String code_postal;
    private String ville;
    private String mot_de_passe;
    private int credit;
    private boolean administrateur;
}
