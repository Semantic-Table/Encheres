package fr.afpa.encheres.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;


@Data @AllArgsConstructor
public class ArticlesVendus {
    private int no_article;
    private String nom_article;
    private String description;
    private Date date_debut_encheres;

    private Time heure_debut_encheres;
    private Date date_fin_encheres;
    private Time heure_fin_encheres;
    private int prix_initial;
    private int prix_vente;
    private int no_utilisateur;
    private int no_categorie;
    private String image;

    public ArticlesVendus(String nom_article, String description, Date date_debut_encheres, Time heure_debut_encheres, Date date_fin_encheres, Time heure_fin_encheres, int prix_initial, int prix_vente, int no_utilisateur, int no_categorie, String image) {
        this.nom_article = nom_article;
        this.description = description;
        this.date_debut_encheres = date_debut_encheres;
        this.heure_debut_encheres = heure_debut_encheres;
        this.date_fin_encheres = date_fin_encheres;
        this.heure_fin_encheres = heure_fin_encheres;
        this.prix_initial = prix_initial;
        this.prix_vente = prix_vente;
        this.no_utilisateur = no_utilisateur;
        this.no_categorie = no_categorie;
        this.image = image;
    }
}
