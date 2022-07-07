<%--
  Created by IntelliJ IDEA.
  User: stagiaire
  Date: 07/07/2022
  Time: 09:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inscription</title>
</head>
<body>
  <form action="Inscription" method="get">
    <label>Pseudo:</label>
    <input type="text" name="pseudo">

    <label>Prénom:</label>
    <input type="text" name="prenom" >

    <label>Téléphone:</label>
    <input type="tel" name="telephone" >

    <label>Code Postal:</label>
    <input type="number" name="code_postal" >

    <label>Mot de passe:</label>
    <input type="password" name="mot_de_passe" >

    <label>Nom:</label>
    <input type="text" name="nom" >

    <label>Email:</label>
    <input type="email" name="email" >

    <label>Rue:</label>
    <input type="text" name="rue" >

    <label>Ville:</label>
    <input type="text" name="ville">

    <label>Confirmation:</label>
    <input type="password" name="mot_de_passe">

    <input type="submit" value="Créer">
    <input type="submit" value="Annuler">
  </form>
</body>
</html>
