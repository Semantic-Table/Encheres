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
}
