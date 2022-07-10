<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: marvi
  Date: 07/07/2022
  Time: 19:37
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
    <link rel="stylesheet" href="css/breakpoints.css"></head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
<main>
    <h1>Profil de ${utilisateurs.pseudo}</h1>
    <div class="box">
        <p>
            Pseudo:
            ${utilisateurs.pseudo}
        </p>
        <p>
            Prénom:
            ${utilisateurs.prenom}
        </p>
        <p>
            Téléphone:
            ${utilisateurs.telephone}
        </p>
        <p>
            Email:
            ${utilisateurs.email}
        </p>
        <p>
            Rue:
            ${utilisateurs.rue}
        </p>
        <p>
            Code Postal:
            ${utilisateurs.code_postal}
        </p>
        <p>
            Ville:
            ${utilisateurs.ville}
        </p>
    </div>
    <c:if test="${utilisateursC.administrateur}">
        <div class="box">
            <h3>tableau administrateur</h3>
            <form action="AdminSuppr" method="post"><input type="hidden" name="no_utilisateur" value="${utilisateurs.no_utilisateur}"> <input type="submit" value="supprimer le compte"></form>
            <form action="AdminDesac" method="post"><input type="hidden" name="no_utilisateur" value="${utilisateurs.no_utilisateur}"> <input type="submit" value="desactiver le compte"></form>
            ${utilisateurs.actif}
        </div>
    </c:if>

</main>

</body>
</html>
