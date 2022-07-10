
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
    <link rel="stylesheet" href="css/breakpoints.css"></head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
<main>
    <h1>Réinitialisation du mot de passe</h1>
    <div class="box">
        <form method="post" action="Reinitialisation">
            <div class="connexionBox">
                <label for="pseudo">Identifiant: </label>
                <input type="text" name="pseudo" id="pseudo" value="${pseudo}">
            </div>
            <div class="connexionBox">
                <label for="mot_de_passe">Nouveau mot de passe :</label>
                <input type="password" name="mot_de_passe" id="mot_de_passe">
            </div>
            <div class="connexionBox">
                <input type="submit" value="Changer le mot de passe" class="click">
            </div>
        </form>
    </div>
</main>

</body>
</html>
