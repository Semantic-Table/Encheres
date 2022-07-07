<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Accueil</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
<jsp:include page="menu.jsp"></jsp:include>
<h1>Liste des enchères</h1>
<div>
    <form action="" method="post">
        <div>
            <label>Filtre:</label>
            <input type="text" id="nom_article" name="nom_article">
        </div>
        <div>
            <label>Catégorie :</label>
            <select name="no_categorie" id="no_categorie">
                <option value="1">Informatique</option>
                <option value="2">Ameublement</option>
                <option value="3">Vêtement</option>
                <option value="4">Sport & Loisirs</option>
            </select>
            <input type="submit" value="Rechercher">
        </div>
    </form>
    <c:if test="${utilisateursCno_utilisateurs != null}">
        <a href="FiltreGagne">enchereWin</a>
    </c:if>
    <c:if test="${utilisateursCno_utilisateurs != null}">
        <a href="FiltreParticipation">enchereParticipe</a>
    </c:if>
    <c:if test="${utilisateursCno_utilisateurs != null}">
        <a href="FiltreProprietaire">enchereProprietaire</a>
    </c:if>
    <a href="FiltreEnCours">enCours</a>
</div>


<main>
    <div class="display">
        <%-- Affichage des Encheres en cours "articlesVenduses" et "utilisateurses"--%>
        <c:forEach items="${articlesVenduses}" var="articlesVendus">
            <div>
                <div>
                    <img src="img/champi.png" alt="photo" id="photoProfil">
                </div>
                <div>
                    <p id="soulignage"><a
                            href="AfficherArticle?no_article=${articlesVendus.no_article}">${articlesVendus.nom_article}</a>
                    </p>
                    <p>Prix de vente: ${articlesVendus.prix_vente} €</p>
                    <p>Fin de l'enchère: ${articlesVendus.date_fin_encheres}</p>
                    <c:forEach items="${utilisateurses}" var="utilisateurs">
                        <c:if test="${utilisateurs.no_utilisateur == articlesVendus.no_utilisateur}">
                            <p>Vendeur : <a
                                    href="AfficherProfil?no_utilisateur=${utilisateurs.no_utilisateur}"> ${utilisateurs.pseudo}</a>
                            </p>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
    </div>
</main>
</body>
</html>