<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
<jsp:include page="WEB-INF/menu.jsp"></jsp:include>
<main>
    <h1>Liste des enchères</h1>
    <div class="box boxFiltre">
        <form action="Accueil" method="post">
            <div class="divFiltre">
                <label>Filtre:</label>
                <input type="text" id="nom_article" name="nom_article">
            </div>
            <div class="divFiltre">
                <label>Catégorie :</label>
                <select name="no_categorie" id="no_categorie">
                    <option value="1">Informatique</option>
                    <option value="2">Ameublement</option>
                    <option value="3">Vêtement</option>
                    <option value="4">Sport & Loisirs</option>
                </select>
                <input type="submit" value="Rechercher" class="click">
            </div>
        </form>
        <div class="divFiltre">


        <c:if test="${utilisateursCno_utilisateurs != null}">
            <a href="FiltreGagne" class="lienFiltre">enchereWin</a>
        </c:if>
        <c:if test="${utilisateursCno_utilisateurs != null}">
            <a href="FiltreParticipation" class="lienFiltre">enchereParticipe</a>
        </c:if>
        <c:if test="${utilisateursCno_utilisateurs != null}">
            <a href="FiltreProprietaire" class="lienFiltre">enchereProprietaire</a>
        </c:if>
        <a href="FiltreEnCours" class="lienFiltre">enCours</a>
        </div>
    </div>


    <div  class="box cardbox">
        <%-- Affichage des Encheres en cours "articlesVenduses" et "utilisateurses"--%>
        <c:forEach items="${articlesVenduses}" var="articlesVendus">
            <div class="card">
                <div class="cardimg">
                    <a
                       href="AfficherArticle?no_article=${articlesVendus.no_article}"><img src="${articlesVendus.image}" alt="photo" id="photoProfil"></a>
                </div>
                <div class="cardtxt">
                    <div><a class="profilLink bigger"
                            href="AfficherArticle?no_article=${articlesVendus.no_article}">${articlesVendus.nom_article}</a>
                    </div>
                    <div>Prix de vente: ${articlesVendus.prix_vente}p</div>
                    <div>Fin de l'enchère: ${articlesVendus.date_fin_encheres}</div>
                    <c:forEach items="${utilisateurses}" var="utilisateurs">
                        <c:if test="${utilisateurs.no_utilisateur == articlesVendus.no_utilisateur}">
                            <div>Vendeur : <a class="profilLink"
                                    href="AfficherProfil?no_utilisateur=${utilisateurs.no_utilisateur}"> ${utilisateurs.pseudo}</a>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
    </div>

</main>

</body>
</html>