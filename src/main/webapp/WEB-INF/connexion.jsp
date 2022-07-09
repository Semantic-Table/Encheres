<%--
  Created by IntelliJ IDEA.
  User: stagiaire
  Date: 07/07/2022
  Time: 09:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
<main>
    <h1>Connection</h1>
    <div class="box">


        <form method="post" action="Connexion">
            <div class="connexionBox">


                <label for="pseudo">Identifiant: </label>
                <input type="text" name="pseudo" id="pseudo" value="${pseudo}">
            </div>
            <div class="connexionBox">
                <label for="mot_de_passe">Mot de passe: </label>
                <input type="password" name="mot_de_passe" id="mot_de_passe">
            </div>
            <div class="connexionBox">
                <input type="submit" value="Connexion" class="click">


                <label for="souvenir">se souvenir de moi</label>
                <input type="checkbox" name="souvenir" id="souvenir">
            </div>
            <div>
                <a href="#">Mot de passe oublié</a>
            </div>

        </form>
        <form action="Inscription" method="get">
            <input type="submit" value="Créer un compte" class="click">
        </form>


    </div>
</main>

</body>
</html>
