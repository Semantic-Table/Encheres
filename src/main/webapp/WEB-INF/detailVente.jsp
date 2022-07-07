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
    <title>Detail Vente</title>
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
    <h2>Detail Vente</h2>
<main>
    <div>
        <div><img src="" alt=""></div>
        <div><p>${articlesVendus.nom_article}</p>
            <p>Description: ${articlesVendus.description}</p>
            <p>Catégorie:${categories.libelle}</p>
            <p>Meilleur offre:${articlesVendus.prix_vente}</p>
            <p>Mise a prix: ${articlesVendus.prix_initial}</p>
            <p>Fin de l'enchere: ${articlesVendus.date_fin_encheres}</p>
            <p>Retraits: ${retraits.rue}</p>
            <p>${retraits.code_postal} ${retraits.ville}</p>
            <p>Vendeur: ${utilisateurs.pseudo}</p>
            <form action="Encherir">Ma proposition : <input type="number" id="montant_enchere" name="montant_enchere"><input type="hidden" value="${articlesVendus.no_article}" name="no_article">
                <input type="hidden" name="no_categorie" value="${categories.no_categorie}"><button type="submit">encherir</button></form></div>
    </div>
</main>
</body>
</html>
