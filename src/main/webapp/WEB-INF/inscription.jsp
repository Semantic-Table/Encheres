<%--
  Created by IntelliJ IDEA.
  User: stagiaire
  Date: 07/07/2022
  Time: 09:15
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
    <link rel="stylesheet" href="css/breakpoints.css"></head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
<main>
    <h1>Inscription</h1>
    <div class="box">
        <form action="Inscription" method="post">
            <div class="inscriptionform">


            <div class="inscriptionbox">
                <div>
                    <div>Pseudo:</div>
                    <input type="text" name="pseudo" required>
                </div>
                <div>
                    <div>Prénom:</div>
                    <input type="text" name="prenom" required>
                </div>
                <div>
                    <div>Ville:</div>
                    <input type="text" name="ville" required>
                </div>
            </div>
            <div class="inscriptionbox">
                <div>
                    <div>Mot de passe:</div>
                    <input type="password" name="mot_de_passe" required>
                </div>
                <div>
                    <div>Nom:</div>
                    <input type="text" name="nom" required>
                </div>
                <div>
                    <div>Code Postal:</div>
                    <input type="number" name="code_postal" required>
                </div>

            </div>
            <div class="inscriptionbox">
                <div>
                    <div>Email:</div>
                    <input type="email" name="email" required>
                </div>
                <div>
                    <div>Téléphone:</div>
                    <input type="tel" name="telephone" required>
                </div>
                <div>
                    <div>Rue:</div>
                    <input type="text" name="rue" required>
                </div>
            </div>
            </div>
            <div class="connexionBox">
                <input type="submit" value="Créer" class="click">
                <input type="reset" value="Annuler" class="click">
            </div>
        </form>
    </div>
</main>

</body>
</html>
