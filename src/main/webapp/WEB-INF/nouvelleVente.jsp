<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: stagiaire
  Date: 07/07/2022
  Time: 09:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Nouvelle Vente</title>
</head>
<body>

<form action="NouvelleVente">
    <label for="nom_article">Article:</label><input type="text" id="nom_article" name="nom_article">
    <label for="description">Description:</label><input type="text" id="description" name="description">
    <label for="no_categorie">Catégorie</label><select name="no_categorie" id="no_categorie">
        <option value="1">informatique</option>
        <option value="2">ameublement</option>
        <option value="3">vetement</option>
        <option value="4">sport&loisirs</option>
    </select>
    <label for="image">Photo de l'article</label><input type="file" id="image" name="image">
    <label for="prix_initial">Mise a prix:</label><input type="text" id="prix_initial" name="prix_initial">
    <label for="date_debut_enchere">Début de l'enchère</label><input type="text" id="date_debut_enchere" name="date_debut_enchere">
    <label for="date_fin_enchere">Fin de l'enchère</label><input type="text" id="date_fin_enchere" name="date_fin_enchere">
    <fieldset>
        <label for="rue">Rue:</label><input type="text" id="rue" name="rue">
        <label for="code_postal">Code postal:</label><input type="text" id="code_postal" name="code_postal">
        <label for="ville">Ville:</label><input type="text" id="ville" name="ville">
    </fieldset>
    <button type="submit">Enregistrer</button>
    <button type="reset">Annuler</button>
</form>
</body>
</html>
