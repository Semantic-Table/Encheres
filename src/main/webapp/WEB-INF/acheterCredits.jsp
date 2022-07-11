<%--
  Created by IntelliJ IDEA.
  User: stagiaire
  Date: 11/07/2022
  Time: 08:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div class="box cardbox">

        <div class="card">
            <form action="AcheterCredits" method="post">
                <div class="connexionBox">
                    <label for="formule1">100 point pour 10 euros</label>
                    <input type="hidden" name="credit" id="formule1" value="100">
                    <input type="submit" value="Valider" class="click">
                </div>
            </form>
        </div>

        <div class="card">
            <form action="AcheterCredits" method="post">
                <div class="connexionBox">
                    <label for="formule2">300 point pour 30 euros</label>
                    <input type="hidden" name="credit" id="formule2" value="300">
                    <input type="submit" value="Valider" class="click">
                </div>
            </form>
        </div>

        <div  class="card">
            <form action="AcheterCredits" method="post">
                <div class="connexionBox">
                    <label for="formule3">500 points pour 50 euros</label>
                    <input type="hidden" name="credit" id="formule3" value="500">
                    <input type="submit" value="Valider" class="click">
                </div>
            </form>
        </div>
    </div>
</main>


</body>
</html>
