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
    <link rel="stylesheet" href="css/breakpoints.css">
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>


<main>
    <h1>Detail Vente</h1>
    <div class="box detailbox">
        <div class="boxdetailimg"><img src="${articlesVendus.image}" alt="" class="detailimg"></div>
        <div class="detailTxt"><p class="bigger">${articlesVendus.nom_article}</p>
            <p>Description : ${articlesVendus.description}</p>
            <p>Catégorie : ${categories.libelle}</p>
            <p>Meilleur offre : ${articlesVendus.prix_vente}</p>
            <p>Mise a prix : ${articlesVendus.prix_initial}</p>
            <p>Fin de l'enchere : ${articlesVendus.date_fin_encheres} à ${articlesVendus.heure_fin_encheres}</p>
            <p>Retraits : ${retraits.rue}</p>
            <p>${retraits.code_postal} ${retraits.ville}</p>
            <p>Vendeur : ${utilisateurs.pseudo}</p>
            <c:if test="${articlesVendus.no_utilisateur != utilisateursCno_utilisateurs && utilisateursC.actif}">
                <form action="Encherir" method="post">
                    <label for="montant_enchere">Ma proposition :</label><input type="number" id="montant_enchere"
                                                                                 name="montant_enchere" required>
                    <input type="hidden" value="${articlesVendus.no_article}" name="no_article">
                    <input type="hidden" name="no_categorie" value="${categories.no_categorie}">
                    <button type="submit" class="click">encherir</button>
                </form>
            </c:if>
        </div>
        <c:if test="${articlesVendus.no_utilisateur == utilisateursCno_utilisateurs}">
            <div>
                <c:forEach items="${enchereses}" var="encheres">
                    <div>
                        <c:forEach items="${utilisateurses}" var="utilisateurs">
                            <c:if test="${utilisateurs.no_utilisateur == encheres.no_utilisateur}">
                                <p>${utilisateurs.pseudo}
                            </c:if>
                        </c:forEach>
                        ${encheres.montant_enchere}</p></div>
                </c:forEach>
            </div>
        </c:if>
    </div>
</main>
</body>
</html>
