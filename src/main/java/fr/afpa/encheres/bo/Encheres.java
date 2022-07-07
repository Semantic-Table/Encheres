package fr.afpa.encheres.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.sql.Date;

@Data
@AllArgsConstructor
public class Encheres {
    private int no_encheres;
    private int no_utilisateur;
    private int no_article;
    private Date date_enchere;
    private Time time_enchere;
    private int montant_enchere;

    public Encheres(int no_utilisateur, int no_article, Date date_enchere, Time time_enchere, int montant_enchere) {
        this.no_utilisateur = no_utilisateur;
        this.no_article = no_article;
        this.date_enchere = date_enchere;
        this.time_enchere = time_enchere;
        this.montant_enchere = montant_enchere;
    }
}
