<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: stagiaire
  Date: 07/07/2022
  Time: 08:37
  To change this template use File | Settings | File Templates.
--%>
<header>
    <div class="parentMenu">
        <a href="Accueil" class="lienMenu">Logo</a>
        <div class="menu">


            <c:if test="${utilisateursCno_utilisateurs == null}">


                <a href="Connexion" class="lienMenu">
                    <div class="liendivMenu">
                        Se connecter-S'inscrire
                    </div>
                </a>


            </c:if>
            <c:if test="${utilisateursCno_utilisateurs != null}">

                <a href="Accueil" class="lienMenu">
                    <div class="liendivMenu">Enchères</div>
                </a>
                <a href="NouvelleVente" class="lienMenu">
                    <div class="liendivMenu">Vendre un article</div>
                </a>
                <a href="Profil" class="lienMenu">
                    <div class="liendivMenu">Mon profil</div>
                </a>
                <a href="Deconnexion" class="lienMenu">
                    <div class="liendivMenu">Déconnexion</div>
                </a>

            </c:if>
        </div>

    </div>
    <div>
        <a href="#" class="lienMenu">
            <img src="img/twitter-brands.svg"
                 width="40"
                 height="40"
                 class="svg"
                 type="image/svg+xml">
        </a>
        <a href="#" class="lienMenu">
            <img src="img/facebook-brands.svg"
                 width="40"
                 height="40"
                 class="svg"
                 type="image/svg+xml">
        </a>
        <a href="#" class="lienMenu">
            <img src="img/instagram-brands.svg"
                 width="40"
                 height="40"
                 class="svg"
                 type="image/svg+xml"/>
        </a>
    </div>
</header>

