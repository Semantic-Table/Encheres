package fr.afpa.encheres.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Retraits {
    private int no_article;
    private String rue;
    private String code_postal;
    private String ville;
}
