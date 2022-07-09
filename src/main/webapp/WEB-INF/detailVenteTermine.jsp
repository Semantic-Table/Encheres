<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>

<main>
    <h1>Detail Vente</h1>
    <div class="box detailbox">
        <div class="boxdetailimg"><img src="${articlesVendus.image}" alt="" class="detailimg"></div>
        <div><p class="bigger">${articlesVendus.nom_article}</p>
            <p>Description: ${articlesVendus.description}</p>
            <p>Catégorie:${categories.libelle}</p>
            <p>Meilleur offre:${articlesVendus.prix_vente}</p>
            <p>Mise a prix: ${articlesVendus.prix_initial}</p>
            <p>Fin de l'enchere: ${articlesVendus.date_fin_encheres}</p>
            <p>Retraits: ${retraits.rue}</p>
            <p>${retraits.code_postal} ${retraits.ville}</p>
            <p>Vendeur: ${utilisateurs.pseudo}</p>
            <p>Remporté par: ${utilisateursGagnant.pseudo}</p></div>
    </div>
</main>
</body>
</html>
