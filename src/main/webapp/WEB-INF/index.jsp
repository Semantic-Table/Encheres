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
    <link rel="stylesheet" href="css/breakpoints.css">

</head>

<body>
<jsp:include page="menu.jsp"></jsp:include>
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

                    <c:forEach items="${categorieses}" var="categorie">
                        <option value="${categorie.no_categorie}">${categorie.libelle}</option>
                    </c:forEach>

                </select>
                <input type="submit" value="Rechercher" id="search">
            </div>
        </form>
        <div class="divFiltre">


        <c:if test="${utilisateursCno_utilisateurs != null}">

            <a href="FiltreGagne"><div class="lienFiltre">Gagné</div></a>
        </c:if>
        <c:if test="${utilisateursCno_utilisateurs != null}">
            <a href="FiltreParticipation"><div class="lienFiltre">Je participe</div></a>
        </c:if>
        <c:if test="${utilisateursCno_utilisateurs != null}">
            <a href="FiltreProprietaire"><div class="lienFiltre">Mes enchères</div></a>
        </c:if>
        <a href="FiltreEnCours"><div class="lienFiltre">Enchères en cours</div></a>
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
    <div>

        <c:forEach var="i" begin="1" end="${nbPages}" step="1">
            <a href="Accueil?pages=${i-1}">${i}</a>
        </c:forEach>
    </div>
</main>

</body>
</html>