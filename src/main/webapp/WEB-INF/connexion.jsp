<%--
  Created by IntelliJ IDEA.
  User: stagiaire
  Date: 07/07/2022
  Time: 09:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Connexion</title>
</head>
<body>
    <form method="get" action="Connexion">
        <label for="pseudo">Identifiant: </label>
        <input type="text" name="pseudo" id="pseudo">
        <label for="mot_de_passe">Mot de passe: </label>
        <input type="password" name="mot_de_passe" id="mot_de_passe">

        <input type="submit" value="Connexion">
        <input type="checkbox" name="" id="">
        <a href="#">Mot de passe oublié</a>
    </form>

    <form>
        <input type="submit" value="Créer un compte">
    </form>
</body>
</html>
