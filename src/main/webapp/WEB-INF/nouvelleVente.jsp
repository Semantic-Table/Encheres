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
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Encheres.org</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/breakpoints.css"></head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
<main>
    <h1>Vendre un article</h1>
    <div class="box">
        <form action="NouvelleVente" method="post" class="box" enctype="multipart/form-data">
            <div class="connexionBox">
                <label for="nom_article">Article:</label><input type="text" id="nom_article" name="nom_article" required>
            </div>
            <div class="connexionBox">
                <label for="description">Description:</label><input type="text" id="description" name="description" required>
            </div>
            <div class="connexionBox">
                <label for="no_categorie">Catégorie</label><select name="no_categorie" id="no_categorie" required>
                <c:forEach items="${categorieses}" var="categorie">
                    <option value="${categorie.no_categorie}">${categorie.libelle}</option>
                </c:forEach>
            </select>
            </div>
            <div class="connexionBox">
                <label for="image">Photo de l'article :</label><input type="file" id="image" accept="image/*" name="image" required>
            </div>
            <div class="connexionBox">
                <label for="prix_initial">Mise a prix :</label><input type="number" id="prix_initial" name="prix_initial">
            </div>
            <div class="connexionBox">
                <label for="date_debut_encheres">Début de l'enchère :</label><input type="date" id="date_debut_encheres" min="${now}"
                                                                                 name="date_debut_encheres" required>
            </div>
            <div class="connexionBox">
                <label for="heure_debut_encheres">Début de l'enchère :</label><input type="time" id="heure_debut_encheres"
                                                                                 name="heure_debut_encheres" required>
            </div>
            <div class="connexionBox">
                <label for="date_fin_encheres">Fin de l'enchère :</label><input type="date" id="date_fin_encheres" min="${nowend}"
                                                                             name="date_fin_encheres" required>
            </div>
            <div class="connexionBox">
                <label for="heure_fin_encheres">Fin de l'enchère :</label><input type="time" id="heure_fin_encheres"
                                                                                   name="heure_fin_encheres" required>
            </div>
            <div class="connexionBox">
                <label for="rue">Rue :</label><input type="text" id="rue" value="${utilisateursC.rue}" name="rue" required>
            </div>
            <div class="connexionBox">
                <label for="code_postal">Code postal :</label><input type="number" id="code_postal" value="${utilisateursC.code_postal}" name="code_postal" required>
            </div>
            <div class="connexionBox">
                <label for="ville">Ville :</label><input type="text" id="ville" value="${utilisateursC.ville}" name="ville" required>
            </div>
            <button type="submit" class="click">Enregistrer</button>
            <button type="reset" class="click">Annuler</button>
        </form>
    </div>
</main>

</body>
</html>
