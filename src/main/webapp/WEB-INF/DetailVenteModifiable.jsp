<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Encheres.org</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/breakpoints.css"></head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
<main>
    <h1>Modifier une vente</h1>
    <div class="box detailbox">
        <div class="boxdetailimg"><img src="${articlesVendus.image}" alt="" class="detailimg"></div>
        <div>

            <form action="ModifEnchere" method="post" enctype="multipart/form-data">
                <div class="connexionBox">
                    <label for="nom_article">Article:</label><input type="text" id="nom_article" name="nom_article"
                                                                    value="${articlesVendus.nom_article}" required>
                </div>
                <div class="connexionBox">
                    <label for="description">Description:</label><input type="text" id="description" name="description"
                                                                        value="${articlesVendus.description}" required>
                </div>
                <div class="connexionBox">
                    <label for="no_categorie">Catégorie</label><select name="no_categorie" id="no_categorie"
                                                                       value="${articlesVendus.no_categorie}" required>
                    <c:forEach items="${categorieses}" var="categorie">
                        <option value="${categorie.no_categorie}">${categorie.libelle}</option>
                    </c:forEach>
                </select>
                </div>
                <div class="connexionBox">
                    <label for="image">Photo de l'article:</label><input type="file" id="image" name="image" required>
                </div>
                <div class="connexionBox">
                    <label for="prix_initial">Mise a prix:</label><input type="text" id="prix_initial"
                                                                         name="prix_initial"
                                                                         value="${articlesVendus.prix_vente}" required>
                </div>
                <div class="connexionBox">
                    <label for="date_debut_encheres">Début de l'enchère</label><input type="date" id="date_debut_encheres"
                                                                                     name="date_debut_encheres"
                                                                                     value="${articlesVendus.date_debut_encheres}" required> à <input type="time" id="heure_debut_encheres"
                                                                                                                                             name="heure_debut_encheres"
                                                                                                                                             value="${articlesVendus.heure_debut_encheres}" required>
                </div>

                <div class="connexionBox">
                    <label for="date_fin_encheres">Fin de l'enchère</label><input type="date" id="date_fin_encheres"
                                                                                 name="date_fin_encheres"
                                                                                 value="${articlesVendus.date_fin_encheres}" required> à <input type="time" id="heure_fin_encheres"
                                                                                                                                       name="heure_fin_encheres"
                                                                                                                                       value="${articlesVendus.heure_fin_encheres}" required>

                </div>
                <div class="connexionBox">
                    <label for="rue">Rue:</label><input type="text" id="rue" name="rue" value="${retraits.rue}" required>
                </div>
                <div class="connexionBox">
                    <label for="code_postal">Code postal:</label><input type="text" id="code_postal" name="code_postal"
                                                                        value="${retraits.code_postal}" required>
                </div>
                <div class="connexionBox">
                    <label for="ville">Ville:</label><input type="text" id="ville" name="ville"
                                                            value="${retraits.ville}" required>
                </div>
                <div class="connexionBox">
                    <input type="hidden" name="no_article" value="${articlesVendus.no_article}">
                    <input type="hidden" name="no_encheres" value="${encheres.no_encheres}">
                </div>
                <div class="connexionBox">
                    <button type="submit" class="click">Enregistrer</button>
                </div>

            </form>
            <div class="connexionBox">
                <form action="AnnulerVente" method="post">
                    <input type="hidden" name="no_article" value="${articlesVendus.no_article}">
                    <button type="submit" class="click">Annuler la vente</button>
                </form>
            </div>

        </div>

    </div>
</main>

</body>
</html>
