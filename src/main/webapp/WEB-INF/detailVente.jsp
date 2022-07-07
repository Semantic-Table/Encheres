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
    <h2>Detail Vente</h2>
<main>
    <div>
        <div><img src="" alt=""></div>
        <div><p>${articleVendus.nom_article}</p>
            <p>Description: ${articleVendus.description}</p>
            <p>${categories.libelle}</p>
            <p>${articleVendus.prix_vente}</p>
            <p>${articleVendus.prix_initial}</p>
            <p>${articleVendus.date_fin_encheres}</p>
            <p>${retraits.rue}</p>
            <p>${retraits.code_postal} ${retraits.ville}</p>
            <p>${utilisateurs.pseudo}</p>
            <form action="Encherir"><input type="range" min="${articleVendus.prix_vente}" max="${utilisateursC.credit}"><button type="submit"></button></form></div>
    </div>
</main>
</body>
</html>
