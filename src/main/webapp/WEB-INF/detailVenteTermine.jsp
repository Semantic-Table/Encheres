<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <p>Remporté par: ${gagnant}</p></div>
    </div>
</main>
</body>
</html>
