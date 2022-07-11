<%--
  Created by IntelliJ IDEA.
  User: stagiaire
  Date: 07/07/2022
  Time: 09:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Encheres.org</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/breakpoints.css">
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
<main>
    <h1>Mon profil</h1>
    <div class="box">
        <form action="Modifier" method="post" class="box">
            <div class="connexionBox">


                <label>Pseudo:</label>
                <input type="text" name="pseudo" value="${utilisateurs.pseudo}">
            </div>
            <div class="connexionBox">
                <label>Prénom:</label>
                <input type="text" name="prenom" value="${utilisateurs.prenom}">
            </div>
            <div class="connexionBox">
                <label>Téléphone:</label>
                <input type="tel" name="telephone" value="${utilisateurs.telephone}">
            </div>
            <div class="connexionBox">
                <label>Code Postal:</label>
                <input type="number" name="code_postal" value="${utilisateurs.code_postal}">
            </div>
            <div class="connexionBox">
                <label>Nouveau mot de passe:</label>
                <input type="password" name="mot_de_passe" value="${utilisateurs.mot_de_passe}">
            </div>
            <div class="connexionBox">
                <label>Nom:</label>
                <input type="text" name="nom" value="${utilisateurs.nom}">
            </div>
            <div class="connexionBox">
                <label>Email:</label>
                <input type="email" name="email" value="${utilisateurs.email}">
            </div>
            <div class="connexionBox">
                <label>Rue:</label>
                <input type="text" name="rue" value="${utilisateurs.rue}">
            </div>
            <div class="connexionBox">
                <label>Ville:</label>
                <input type="text" name="ville" value="${utilisateurs.ville}">
            </div>
            <input type="submit" value="Enregistrer" class="click">

        </form>

        <a href="Supprimer" class="click">Supprimer mon compte</a>
        <a href="AjoutPoint">+100 000</a>
        <p>${utilisateurs.credit}</p>
        <a href="AcheterCredits">Acheter des crédits</a>
    </div>
    <c:if test="${utilisateurs.administrateur}">
        <div>
            <form action="GestionCategorie" method="post">
                Catégorie à ajouter : <input type="text" name="libelle">
                <input type="submit" value="Ajouter">
            </form>
            <div>Supprimer/modifier une categorie :</div>
            <c:forEach items="${categorieses}" var="categories">
                <form action="ModifierCategorie" method="post">
                    <input type="text" name="libelle" value="${categories.libelle}">
                    <input type="hidden" name="no_categorie" value="${categories.no_categorie}">
                    <input type="submit" value="Modifier">
                </form>
                <div><a href="GestionCategorie?no_categorie=${categories.no_categorie}">supprimer</a></div>
            </c:forEach>
        </div>
    </c:if>
</main>

</body>
</html>
