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
        <h1>Liste des enchères</h1>

        <form action="" method="post">
            <div>
                <label>Filtre:</label>
                <input type="text" id="filtre" name="filtre">
            </div>
            <div>
                <label>Catégorie :</label>
                <select name="categories" id="categories">
                    <option value="1">Toutes</option>
                    <option value="2">Informatique</option>
                    <option value="3">Ameublement</option>
                    <option value="4">Vêtement</option>
                    <option value="5">Sport & Loisirs</option>
                </select>
            </div>
            <div>
                <input type="submit" value="Rechercher">
            </div>
        </form>

        <c:forEach items="${articlesVenduses}" var="articlesVendus">
            <div>
                <div>
                    <img src="" alt="">
                </div>
                <div>
                    <p>${articlesVendus.nom_article}</p>
                    <p></p>
                    <p></p>
                    <c:forEach items="utilisateurses" var="utilisateurs">

                    </c:forEach>
                </div>

            </div>

        </c:forEach>

    </body>
</html>