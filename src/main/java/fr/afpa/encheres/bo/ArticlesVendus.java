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
}
