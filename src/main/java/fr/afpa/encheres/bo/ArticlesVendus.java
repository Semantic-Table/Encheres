package fr.afpa.encheres.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;


@Data @AllArgsConstructor
public class ArticlesVendus {
    private int no_article;
    private String nom_article;
    private String description;
    private Date date_debut_encheres;
    private Date date_fin_encheres;
    private int prix_initial;
    private int prix_vente;
    private int no_utilisateur;
    private int no_categorie;

    public ArticlesVendus(String nom_article, String description, Date date_debut_encheres, Date date_fin_encheres, int prix_initial, int prix_vente, int no_utilisateur, int no_categorie) {
        this.nom_article = nom_article;
        this.description = description;
        this.date_debut_encheres = date_debut_encheres;
        this.date_fin_encheres = date_fin_encheres;
        this.prix_initial = prix_initial;
        this.prix_vente = prix_vente;
        this.no_utilisateur = no_utilisateur;
        this.no_categorie = no_categorie;
    }
}
