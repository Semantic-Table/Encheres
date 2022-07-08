<%--
  Created by IntelliJ IDEA.
  User: stagiaire
  Date: 08/07/2022
  Time: 08:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Modif encheres</title>
</head>
<body>
<div>
    <div><img src="" alt=""></div>
    <div>
        <form action="ModifEnchere" method="get">
            <label for="nom_article">Article:</label><input type="text" id="nom_article" name="nom_article" value="${articlesVendus.nom_article}">
            <label for="description">Description:</label><input type="text" id="description" name="description" value="${articlesVendus.description}">
            <label for="no_categorie">Catégorie</label><select name="no_categorie" id="no_categorie"  value="${articlesVendus.no_categorie}">
            <option value="1">informatique</option>
            <option value="2">ameublement</option>
            <option value="3">vetement</option>
            <option value="4">sport&loisirs</option>
        </select>
            <label for="image">Photo de l'article</label><input type="file" id="image" name="image">
            <label for="prix_initial">Mise a prix:</label><input type="text" id="prix_initial" name="prix_initial" value="${articlesVendus.prix_vente}">
            <label for="date_debut_enchere">Début de l'enchère</label><input type="date" id="date_debut_enchere" name="date_debut_enchere" value="${articlesVendus.date_debut_encheres}">
            <label for="date_fin_enchere">Fin de l'enchère</label><input type="date" id="date_fin_enchere" name="date_fin_enchere" value="${articlesVendus.date_fin_encheres}">
            <fieldset>
                <label for="rue">Rue:</label><input type="text" id="rue" name="rue" value="${retraits.rue}">
                <label for="code_postal">Code postal:</label><input type="text" id="code_postal" name="code_postal" value="${retraits.code_postal}">
                <label for="ville">Ville:</label><input type="text" id="ville" name="ville" value="${retraits.ville}">
            </fieldset>
            <input type="hidden" name="no_article" value="${articlesVendus.no_article}">
            <input type="hidden" name="no_encheres" value="${encheres.no_encheres}">

            <button type="submit">Enregistrer</button>
            <button type="reset">Annuler</button>
        </form>

    </div>

</div>
</body>
</html>
